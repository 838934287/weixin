package com.huawei.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.huawei.model.CustomerAdviceModel;
import com.huawei.model.EmployeeModel;
import com.huawei.model.MessageModel;
import com.huawei.model.PersonModel;
import com.huawei.model.WeixinContextModel;
import com.huawei.model.WeixinUserModel;
import com.huawei.model.WeixinUserOrderModel;
import com.huawei.service.IPersonService;
import com.huawei.service.WeixinService;
import com.huawei.util.FtpClientEntity;
import com.huawei.util.MessageHandle;
import com.huawei.util.SecurityKit;

@Controller
public class WechatController{
	
	/**
	 * 获取公众号Get方式发送或接收到的信息
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	
	@RequestMapping(value = "/wget",method = RequestMethod.GET)
	public void init(HttpServletRequest req,HttpServletResponse resp) throws IOException {
//		signature 微信加密签名
//		timestamp 时间戳
//		nonce 随机数
//		echostr 
		String signature = req.getParameter("signature");
		String timestamp = req.getParameter("timestamp");
		String nonce = req.getParameter("nonce");
		String echostr = req.getParameter("echostr");
		System.out.println("signature: "+signature);
		System.out.println("timestamp: " + timestamp);
		System.out.println("nonce: " + nonce);
		System.out.println("echostr: " + echostr);
		
		
		String[] arras = {WeixinContextModel.token,timestamp,nonce};
		Arrays.sort(arras);
		StringBuffer sb = new StringBuffer();
		for(String s:arras) {
			sb.append(s);
		}
		String sha1 = SecurityKit.sha1(sb.toString());
		System.out.println(sha1.equals(signature));
		if(sha1.equals(signature)) {
			resp.getWriter().println(echostr);
		}
		
	}
	@Resource(name = "weixinService")
	private WeixinService weixinService;
//	public WeixinService getWeixinService() {
//		return weixinService;
//	}
//	@Autowired
//	public void setWeixinService(WeixinService weixinService) {
//		this.weixinService = weixinService;
//	}

	

	/**
	 * 获取公众号POST方式发送过来或者接收到的信息
	 * 1.如果是用户关注,自动获取用户数据保存进数据库.
	 * @param req
	 * @param resp
	 * @throws IOException
	 */

	String compareKey = "";
	@RequestMapping(value = "/wget",method = RequestMethod.POST)
	public void getInfo(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		Map<String,String> map = new HashMap<String,String>();
		Map<String,String> respMap = new HashMap<String,String>();
			try {
				map = MessageHandle.reqMsg2Map(req);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("reqMsg2Map Error");
			}
			System.out.println(map);
//			检查是否是重复信息
			String tempCompareKey = map.get("FromUserName").toString()+map.get("CreateTime").toString()+map.get("MsgType").toString();			
			if(!compareKey.contains(tempCompareKey)) {
			
	//			判断如果用户发送过来是文本信息,自动回复用户,并将信息保存进数据库.
				System.out.println(map.get("MsgType"));
				if(map.get("MsgType").toString().equals("text")) {
					
		//			insert DB
					MessageModel message = (MessageModel) MessageHandle.map2Bean(map, MessageModel.class);
	//				System.out.println(message.getContent());
	//				System.out.println(message.getCreateTime());
	//				System.out.println(message.getFromUserName());
	//				System.out.println(message.getMsgId());
	//				System.out.println(message.getMsgType());
	//				System.out.println(message.getToUserName());
					
					weixinService.insertMessage(message);
					
		//			send response
					
					respMap.put("ToUserName", map.get("FromUserName"));
					respMap.put("FromUserName", map.get("ToUserName"));
					respMap.put("CreateTime", new Date().getTime()+"");
					respMap.put("MsgType", "text");
					respMap.put("Content", "你输入的内容是: " + map.get("Content"));
					String xml = MessageHandle.map2xml(respMap);
					resp.setContentType("application/xml;charset=UTF-8");
					resp.setCharacterEncoding("UTF-8");
					resp.getWriter().write(xml);
				}
	//			判断用户关注公众号,自动获取用户相关信息(后面如果访问商城时,也要获取用户授权,获取用户信息)
				else if(map.get("MsgType").equals("event") && map.get("Event").equals("subscribe")) {
					System.out.println("subscribe");
					weixinService.insertUserToDb(map.get("FromUserName").toString());
				}
	//			用户取消订阅
				else if(map.get("MsgType").equals("event") && map.get("Event").equals("unsubscribe")) {
					System.out.println("unsubscribe");
					WeixinUserModel weixinUserModel = new WeixinUserModel();
					weixinUserModel.setOpenId(map.get("FromUserName").toString());
					weixinService.deleteUserByOpenId(weixinUserModel);
					
				}
				
			}
			compareKey = map.get("FromUserName").toString()+map.get("CreateTime").toString()+map.get("MsgType").toString();
	}

	/**
	 * 获取用户评价
	 * 1.获取用户评价信息写入数据库
	 * 2.将用户评价信息发送给运维人员
	 * 3.开发界面维护运维人员
	 * @param customerAdviceModel
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/receiveAdvice", method = RequestMethod.POST)
	public String receiveAdvice(CustomerAdviceModel customerAdviceModel,ModelMap modelMap) {
		System.out.println(customerAdviceModel.toString());
		String errorContent = "";
		String employeeId = customerAdviceModel.getEmployeeId().toString();
//		检查用户是否存在
		String checkEmployee = weixinService.checkEmployee(employeeId);
		if(checkEmployee.equals("Y")) {
			//		客户意见录入数据库		
			String insertFlag = weixinService.insertCustomerAdvice(customerAdviceModel);
//		
			if(insertFlag.equals("Y")){
//		    将客户建议推送给管理人员
				String role = "M";
//				List<EmployeeModel> listEmployee=new ArrayList<EmployeeModel>();
//				listEmployee = weixinService.selectEmployeeByRole(role);
				String pushAdviceToEmployee = weixinService.pushAdviceToEmployee(customerAdviceModel,role);
				if(pushAdviceToEmployee.equals("Y")) {
					return "Success";
				}else {
					errorContent = "评价意见保存失败,请重新输入.";
					modelMap.addAttribute("content",errorContent);
					return "Error";
				}
			}else {
				errorContent = "评价意见保存失败,请重新输入.";
				modelMap.addAttribute("content",errorContent);
				return "Error";
			}
		}else {
			errorContent = "没有此师傅ID信息,请确认后重新输入师傅ID.";
			modelMap.addAttribute("content",errorContent);
			return "Error";
		}
		
	}
	
   /**
    * @author johnli
    * 获取用户评价测试
    */
	@RequestMapping(value = "/receiveAdviceNew", method = RequestMethod.POST)
	public void receiveAdviceNew(CustomerAdviceModel customerAdviceModel) {
		System.out.println(customerAdviceModel.getAdviceContent().toString());
		
	}
	
	
	
	
	/**
	 * 接收用户订单信息
	 * 1.接收用户订单信息,并将订单信息写入数据库.
	 * 2.接收到用户信息后，推送给到运维人员(要有运维人员维护名单,根据运维人员名单定向推送).
	 * 
	 * 
	 * @return
	 */
	
	@RequestMapping(value = "/receiveOrder",method = RequestMethod.POST)
	public String receiveOrder(@RequestParam(value="file") MultipartFile[] files,WeixinUserOrderModel weixinUserOrderModel,ModelMap modelMap) {
		String errorContent = "";
		String role = "T";
		
		
		
		String insertOrderFlag = weixinService.insertWeixinOrder(weixinUserOrderModel);
		if(insertOrderFlag.equals("Y")) {
			String pushOrderFlag = weixinService.pushWeixinOrderInfo(weixinUserOrderModel,role);
			if(pushOrderFlag.equals("Y")) {
				return "Success";
			}else {
				errorContent = "订单录入成功,但微信推送发给师傅失败,如果紧急请拨打400电话.";
				modelMap.addAttribute("content",errorContent);
				return "Error";
			}
		}else {
			errorContent = "订单录入失败,请重新录入.";
			modelMap.addAttribute("content",errorContent);
			return "Error";
		}
		
	}
	
	
	

	
	@RequestMapping(value = "/recvTestJsp", method = RequestMethod.POST)
	public String recvTestJsp(@RequestParam("interest")String[] interest, Model model) {
		System.out.print(interest);
		
		
		return "Success";
		
	}
	
	@RequestMapping(value = "/receiveAdvice2", method = RequestMethod.POST)
	public String receiveAdvice2(@RequestParam("employeeId") String employeeId,@RequestParam("adviceContent") String adviceContent,@RequestParam("attitudeCode") String attitudeCode) {
		System.out.println("receiveAdvice2");
		System.out.println(employeeId);
		System.out.println(adviceContent);
		System.out.println(attitudeCode);
		
		
		return "Success";
		
	}
	
	
	
	@RequestMapping("/at")
	public void testAccessToken(HttpServletResponse resp) throws IOException {
		
		List<MessageModel> list = weixinService.selectMessage();
		System.out.println("at");
		System.out.println(list.get(0).getContent()+"/"+list.get(0).getCreateTime()+"/"+list.get(0).getFromUserName()+"/"+list.get(0).getToUserName());
		System.out.println(list.get(1).getContent()+"/"+list.get(1).getCreateTime()+"/"+list.get(1).getFromUserName()+"/"+list.get(1).getToUserName());
		System.out.println(list.get(2).getContent()+"/"+list.get(2).getCreateTime()+"/"+list.get(2).getFromUserName()+"/"+list.get(2).getToUserName());
		
		
		resp.getWriter().println(WeixinContextModel.at);
	}
	
	
//	从config.properties 中获取文件
	@Value("${token}")
	private String attr1;
	@Value("${config.attr2}")
	private String attr2;
	
	@RequestMapping("/getProperties")
	public ModelMap getProperties(ModelMap modelMap) {
		System.out.println("attr1"+ attr1);
		modelMap.put("arrt1",attr1);
		return modelMap;
	}
//	从config.properties 中获取文件
	@Value("${FTP_IP}")
	private String FTP_IP;
	
	@RequestMapping("/uploadImages")
	public String saveAndShowImage(@RequestParam(value="file") MultipartFile[] files,HttpServletRequest request) throws Exception{
		
		String imeagesFilePath = "http://" + this.FTP_IP + "/images/";
		ArrayList<String> fullFilePath = new ArrayList<String>(); 
		if(files==null){
			System.out.println("------------上传文件为空-----------");
//			return null;
		}
		
		//存在ftp图片服务器的路径
		String path = "/var/ftp/images";
		String username = "ftp";
		String password = "123";
		FtpClientEntity a = new FtpClientEntity();
		FTPClient ftp = a.getConnectionFTP(FTP_IP, 21, username, password);
		
		for(MultipartFile file:files) {
			
			String filename = file.getOriginalFilename(); //获得原始的文件名
			String outputFileName = changeName(filename); //重新获取文件名
			InputStream input=file.getInputStream();
			System.out.println("------------上传文件名-----------"+outputFileName);
			fullFilePath.add(imeagesFilePath+outputFileName);
			a.uploadFile(ftp, path, outputFileName, input);
		}
        //退出ftp
        ftp.logout(); 
        a.closeFTP(ftp);
		request.setAttribute("fullFilePath", fullFilePath);
        return "ShowCustomerOrder";
	}//saveImage

	
	

    /**
     * 生成新的文件的名称
     * @param fileName
     * @return
     */
    public static String changeName(String fileName) {

            int b = fileName.lastIndexOf(".");//最后一出现小数点的位置
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            StringBuffer suffix = new StringBuffer(fileName.substring(b + 1));//后缀的名称
            fileName = uuid + "." + suffix;
        return fileName.toString();
    }	

	
	
	
}
