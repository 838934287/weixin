package com.huawei.service.impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huawei.dao.CustomerAdviceModelMapper;
import com.huawei.dao.EmployeeModelMapper;
import com.huawei.dao.HandleMessageMapper;
import com.huawei.dao.WeixinUserModelMapper;
import com.huawei.dao.WeixinUserOrderModelMapper;
import com.huawei.service.WeixinService;
import com.huawei.util.HttpClientUtil;
import com.huawei.model.*;


@Service("weixinService")
public class WeixinServiceImpl implements WeixinService {
	@Resource(name = "handleMessageMapper")
	private HandleMessageMapper handleMessageMapper;
	@Resource(name = "weixinUserModelMapper")
	private WeixinUserModelMapper  weixinUserModelMapper;
	@Resource(name = "customerAdviceModelMapper")
	private CustomerAdviceModelMapper  customerAdviceModelMapper;
	@Resource(name = "employeeModelMapper")
	private EmployeeModelMapper  employeeModelMapper;
	@Resource(name = "weixinUserOrderModelMapper")
	private WeixinUserOrderModelMapper weixinUserOrderModelMapper;
	
	private WeixinContextModel weixinContextModel;
	
	
	
	
//	public HandleMessage getHandleMessage() {
//		return handleMessage;
//	}
//
//	@Autowired
//	public void setHandleMessage(HandleMessage handleMessage) {
//		this.handleMessage = handleMessage;
//	}


	@Override
	public void insertMessage(MessageModel message) {
		// 
		handleMessageMapper.insertMessage(message);
		
	}
	
	public List<MessageModel>  selectMessage(){
		
		return handleMessageMapper.selectMessage();
	}

	/**
	 * 
	 * 检查用户是否存在
	 * @param weixinUserModel
	 * @return
	 */
	
	public String checkWeixinUserByOpenId(WeixinUserModel weixinUserModel) {
		String checkUser = "N";
		
		WeixinUserModel user = weixinUserModelMapper.selectByPrimaryKey(weixinUserModel.getOpenId().toString());
		if(user != null) {
			checkUser = "Y";
		}
		return checkUser;
	
	}
	public void updateUser(WeixinUserModel weixinUserModel) {
		
	}
	public WeixinUserModel getWeixinUser() {
		WeixinUserModel weixinUserModel = new WeixinUserModel();
		return weixinUserModel;
	}

	public void deleteUserByOpenId(WeixinUserModel weixinUserModel) {
		weixinUserModelMapper.deleteUserByOpenId(weixinUserModel.getOpenId().toString());
	}
	
	public void insertUser(WeixinUserModel weixinUserModel) {
		weixinUserModelMapper.insert(weixinUserModel);
	}
	
	@Override
	public String pushAdviceToEmployee(CustomerAdviceModel customerAdviceModel,String role) {
		// TODO Auto-generated method stub
		String pushAdviceFlag = "Y";
		String templateId = "";
//		获取用户评价后发送给管理人员名单
		List<EmployeeModel> list = employeeModelMapper.selectEmployeeByRole(role);
//		String employeeWeixinId = "otDRR5qp_11gjIIsWZ4i8YyYIIBQ";
//		String employeeId = "101001001";
//		循环发送给每一个管理者客户评价
		for(EmployeeModel e:list) {
//			String employeeId = e.getEmployeeid().toString();
			String employeeWeixinId = e.getWeixinId().toString();
			//		生成要发送的文件格式
			WeixinAdviceInformTemplate wxAdviceInformTemplate = new WeixinAdviceInformTemplate();
			wxAdviceInformTemplate.setTemplate_id("8FB0hpm9LxroEKVAyNgr2V2EOd4UIT2dl4a3esyP1bM");
			wxAdviceInformTemplate.setTopcolor("#000000");
			wxAdviceInformTemplate.setTouser(employeeWeixinId);
			wxAdviceInformTemplate.setUrl("www.baidu.com");
			Map<String,TemplateDataModel> map = new HashMap<String,TemplateDataModel>();
			TemplateDataModel templateDataModel = new TemplateDataModel();
			templateDataModel.setValue(customerAdviceModel.getEmployeeId().toString());
			templateDataModel.setColor("#000000");
			map.put("employeeId", templateDataModel);
			
			TemplateDataModel templateDataModel1 = new TemplateDataModel();
			templateDataModel1.setValue(customerAdviceModel.getAttitudeCode().toString());
			templateDataModel1.setColor("#000000");
			map.put("score", templateDataModel1);
			
			TemplateDataModel templateDataModel2 = new TemplateDataModel();
			templateDataModel2.setValue(customerAdviceModel.getAdviceContent().toString());
			templateDataModel2.setColor("#000000");
			map.put("advice", templateDataModel2);
			wxAdviceInformTemplate.setData(map);
			//		调用接口发送数据
			String url = WeixinContextModel.sendTemplateUrl;
			
//			url = url.replace("ACCESS_TOKEN", WeixinContextModel.at);
			String accessToken = weixinContextModel.getAccessToken().toString();
			url = url.replace("ACCESS_TOKEN", accessToken);
			
			
			String json = JSONObject.toJSONString(wxAdviceInformTemplate);
			System.out.println(json);
			String response = HttpClientUtil.httpPost(url,json);
			System.out.println(response);
			Map respMap = (Map) JSON.parse(response);
			
			//		System.out.println(respMap.get("errcode"));
			if(!respMap.get("errcode").toString().equals("0")) {
				pushAdviceFlag ="N";
				return pushAdviceFlag;
			}
		}
		
		return pushAdviceFlag;
	}

	@Override
	public String insertCustomerAdvice(CustomerAdviceModel customerAdviceModel) {
		String insertFlag = "Y";
		try {
			
			int a  = customerAdviceModelMapper.insertAdvice(customerAdviceModel);	
			System.out.println("a:  "+a);
			
		}catch(Exception e) {
			System.out.println("插入意见失败"); 
			insertFlag = "N";
		}
		return insertFlag;
	}

	@Override
	public String checkEmployee(String employeeId) {
		String checkEmployee = "N";
		EmployeeModel employeeModel = employeeModelMapper.selectEmployeeByEmployeeId(Integer.parseInt(employeeId));
		if(employeeModel!= null) {
			checkEmployee = "Y";
		}
		return checkEmployee;
	}

	@Override
	public List<EmployeeModel> selectEmployeeByRole(String role) {
		// TODO Auto-generated method stub
		List<EmployeeModel> list = employeeModelMapper.selectEmployeeByRole(role);
		return list;
	}

	@Override
	public String insertWeixinOrder(WeixinUserOrderModel weixinUserOrderModel) {
		// TODO Auto-generated method stub
		String insertWeixinFlag = "Y";
		
		Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String weixinOrderNo = dateformat.format(date).toString();
		String orderDate = dateformat.format(date).toString();
		weixinUserOrderModel.setCustomerOrder(weixinOrderNo);
		weixinUserOrderModel.setOrderDate(orderDate);
		
		
		int temp1 =  weixinUserOrderModelMapper.insert(weixinUserOrderModel);
		if(temp1!= 1) {
			insertWeixinFlag = "N";
			return insertWeixinFlag;
		}
		return insertWeixinFlag;
	}

	@Override
	public String pushWeixinOrderInfo(WeixinUserOrderModel weixinUserOrderModel, String role) {
		// TODO Auto-generated method stub
		String pushWeixinOrderFlag = "Y";
		
		
		

//		获取用户评价后发送给师傅人员名单
		List<EmployeeModel> list = employeeModelMapper.selectEmployeeByRole(role);
//		String employeeWeixinId = "otDRR5qp_11gjIIsWZ4i8YyYIIBQ";
//		String employeeId = "101001001";
//		循环发送给每一个管理者客户评价
		for(EmployeeModel e:list) {
			String employeeId = e.getEmployeeid().toString();
			String employeeWeixinId = e.getWeixinId().toString();
			//		生成要发送的文件格式
			WeixinAdviceInformTemplate wxAdviceInformTemplate = new WeixinAdviceInformTemplate();
			wxAdviceInformTemplate.setTemplate_id("lsXnNO0d1rRrGGikcYSg9npTG6l8uSZDoVk7IXEdKGE");
			wxAdviceInformTemplate.setTopcolor("#000000");
			wxAdviceInformTemplate.setTouser(employeeWeixinId);
			wxAdviceInformTemplate.setUrl("www.baidu.com");
			Map<String,TemplateDataModel> map = new HashMap<String,TemplateDataModel>();
			TemplateDataModel templateDataModel = new TemplateDataModel();
			templateDataModel.setValue(weixinUserOrderModel.getUserName().toString());
			templateDataModel.setColor("#000000");
			map.put("user", templateDataModel);
			
			TemplateDataModel templateDataModel1 = new TemplateDataModel();
			templateDataModel1.setValue(weixinUserOrderModel.getArea().toString());
			templateDataModel1.setColor("#000000");
			map.put("ares", templateDataModel1);
			
			TemplateDataModel templateDataModel2 = new TemplateDataModel();
			templateDataModel2.setValue(weixinUserOrderModel.getOrderRequestDate().toString());
			templateDataModel2.setColor("#000000");
			map.put("requestDate", templateDataModel2);
			wxAdviceInformTemplate.setData(map);
			TemplateDataModel templateDataModel3 = new TemplateDataModel();
			templateDataModel3.setValue(weixinUserOrderModel.getPhoneNo().toString());
			templateDataModel3.setColor("#000000");
			map.put("phoneNo", templateDataModel3);
			wxAdviceInformTemplate.setData(map);
			
			
			//		调用接口发送数据
			String url = WeixinContextModel.sendTemplateUrl;
//			url = url.replace("ACCESS_TOKEN", WeixinContextModel.at);
			String accessToken = weixinContextModel.getAccessToken().toString();
			url = url.replace("ACCESS_TOKEN", accessToken);
			String json = JSONObject.toJSONString(wxAdviceInformTemplate);
			System.out.println(json);
			String response = HttpClientUtil.httpPost(url,json);
			System.out.println(response);
			Map respMap = (Map) JSON.parse(response);
			
			//		System.out.println(respMap.get("errcode"));
			if(!respMap.get("errcode").toString().equals("0")) {
				pushWeixinOrderFlag ="N";
				return pushWeixinOrderFlag;
			}
		}
		return pushWeixinOrderFlag;
	}

	@Override
	public void insertUserToDb(String string) {
		// TODO Auto-generated method stub
		String url = WeixinContextModel.WeixinUserInfoUrl;
//		url = url.replace("ACCESS_TOKEN", WeixinContextModel.at) ;
		String accessToken = weixinContextModel.getAccessToken().toString();
		url = url.replace("ACCESS_TOKEN", accessToken);
		url = url.replace("OPENID", string) ;
		String result = HttpClientUtil.httpGet(url);
//		System.err.println(result);
		WeixinUserModel weixinUserModel = JSON.parseObject(result, WeixinUserModel.class);
//		System.out.println(weixinUserModel.getOpenId().toString());
		String checkUser =checkWeixinUserByOpenId(weixinUserModel);
		if(checkUser.equals("Y")) {
			deleteUserByOpenId(weixinUserModel);
		}
		insertUser(weixinUserModel);
		
	}

}
