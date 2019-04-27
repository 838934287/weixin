package com.test;

import java.io.IOException;
import java.nio.charset.UnsupportedCharsetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
//import org.junit.jupiter.api.Test;
import org.junit.Test;

import org.junit.runner.RunWith;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.huawei.model.WeixinContextModel;
import com.huawei.quartz.RefreshAccessTokenTask;
import com.huawei.service.impl.WeixinServiceImpl;
import com.huawei.util.HttpClientUtil;
import com.huawei.util.JsonUtil;
import com.huawei.util.MessageHandle;
import com.huawei.util.SecurityKit;
import com.alibaba.fastjson.JSON;
import com.huawei.dao.WeixinUserOrderModelMapper;
import com.huawei.model.*;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"classpath:spring-mvc.xml","classpath:mybatis-spring.xml","classpath:mybatis-config.xml"})

public class TestInit {
//	@Resource(name = "weixinServiceImpl")
	@Autowired
	private WeixinServiceImpl weixinServiceImpl;
	
	
	@Test
	public void testSha1() {
		String str = SecurityKit.sha1("Hello");
		System.out.println(str);
	}
	
//	@Value("${ACCESS_TOKEN_URL}")
//	private String ACCESS_TOKEN_URL;
//	@Value("${APPID}")
//	private String APPID;
//	@Value("${APPSECRET}")
//	private String APPSECRET;
	

	
 
	@Test
	public void testHttpClient() {
		try {
		CloseableHttpClient client = HttpClients.createDefault();
		String url = WeixinContextModel.ACCESS_TOKEN_URL;
		System.out.println(WeixinContextModel.APPID);
		url = url.replace("APPID", WeixinContextModel.APPID);
		url = url.replace("APPSECRET", WeixinContextModel.APPSECRET);
		HttpGet get = new HttpGet(url);
			CloseableHttpResponse resp = client.execute(get);
			int statusCode = resp.getStatusLine().getStatusCode();
			if (statusCode >=200 && statusCode<300){
				HttpEntity entity = resp.getEntity();
				String content = EntityUtils.toString(entity,"UTF-8");
				System.out.println(content);
				AccessTokenModel at = (AccessTokenModel)JsonUtil.string2Obj(content,AccessTokenModel.class);
				System.out.println(at.getAccess_token()+" , "+at.getExpires_in());
			}
			System.out.print(statusCode);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	
	@Test
	public void testMenu() {
		try {
			List<WeixinMenuModel> wms = new ArrayList<WeixinMenuModel>();
			WeixinMenuModel wm1 = new WeixinMenuModel();
			wm1.setId(1);
			wm1.setName("小桐商城");
			wm1.setType("view");
			wm1.setUrl("http://bca49v.natappfree.cc/weixin/Info1.jsp");
			wms.add(wm1);
			
			WeixinMenuModel wm2 = new WeixinMenuModel();
			wm2.setId(2);
			wm2.setName("仁杏科技");
			List<WeixinMenuModel> wm2Sub = new ArrayList<WeixinMenuModel>();
			wm1 = new WeixinMenuModel();
			wm1.setName("联系我们");
			wm1.setType("view");
			wm1.setUrl("http://bca49v.natappfree.cc/weixin/CustomerContact.jsp");
			wm1.setKey("A001");
			wm2Sub.add(wm1);
			
			wm1 = new WeixinMenuModel();
			wm1.setName("服务评价");
			wm1.setType("view");
			wm1.setUrl("http://bca49v.natappfree.cc/weixin/CustomerAdvice.jsp");
			wm1.setKey("A0002");
			wm2Sub.add(wm1);
			
			wm1 = new WeixinMenuModel();
			wm1.setName("在线下单");
			wm1.setType("view");
			wm1.setUrl("http://bca49v.natappfree.cc/weixin/CustomerOrder.jsp");
			wm1.setKey("A0003");
			wm2Sub.add(wm1);
			
			
			
			wm2.setSub_button(wm2Sub);
			
			wms.add(wm2);
			Map<String,List<WeixinMenuModel>> map = new HashMap<String,List<WeixinMenuModel>>();
			map.put("button", wms);
			System.out.println(JsonUtil.obj2String(map));
			String json = JsonUtil.obj2String(map);
					
			CloseableHttpClient client = HttpClients.createDefault();
			String url = WeixinContextModel.MENU_ADD;
			
			url = url.replace("ACCESS_TOKEN", WeixinContextModel.at);
			
			HttpPost post = new HttpPost(url);
			post.addHeader("Content-Type", "application/json");
			StringEntity entity = new StringEntity(json,ContentType.create("application/json", "utf-8"));
			post.setEntity(entity);
			CloseableHttpResponse resp = client.execute(post);
			int sc = resp.getStatusLine().getStatusCode();
			if (sc>=200&&sc<300) {
				System.out.println(EntityUtils.toString(resp.getEntity()));
			}
		} catch (UnsupportedCharsetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testMsg() {
		Map<String,String> map = new HashMap<String,String>();
		map.put("123", "123");
		map.put("456", "456");
		map.put("abc", "abc");
		String str;
		try {
			str = MessageHandle.map2xml(map);
			System.out.println(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	public String httpGet(String url) {
		String content = null;
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet get = new HttpGet(url);
		CloseableHttpResponse resp = null;
		try {
			resp = client.execute(get);
			int statusCode = resp.getStatusLine().getStatusCode();
			if (statusCode >=200 && statusCode<300){
				HttpEntity entity = resp.getEntity();
				content = EntityUtils.toString(entity,"UTF-8");
//				System.out.println(content);
			}
			
			return content;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				resp.close();
				client.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public String httpPost(String url,String content) {
		String result = null;
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);
		
		// 发送内容设置
		/**
		 * 包装成一个Entity对象
		 */
		StringEntity entity = new StringEntity(content, "UTF-8");
		/**
		 * 设置请求的内容
		 */
		post.setEntity(entity);
		/**
		 * 设置请求的报文头部的编码
		 */
		post.setHeader(new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8"));
		/**
		 * 设置请求的报文头部的编码
		 */
		post.setHeader(new BasicHeader("Accept", "text/plain;charset=utf-8"));
		
		CloseableHttpResponse resp = null;
		try {
			resp = client.execute(post);
			int statusCode = resp.getStatusLine().getStatusCode();
			if (statusCode >=200 && statusCode<300){
				HttpEntity respEntity = resp.getEntity();
				result = EntityUtils.toString(respEntity,"UTF-8");
//				System.out.println(content);
			}
			
			return result;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				resp.close();
				client.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		return null;
		
	}
	
	/// <summary>
	/// 获取关注者列表openid
	//access_token	是	调用接口凭证
	//next_openid	是	第一个拉取的OPENID，不填默认从头开始拉取
//	@Test
	public List getAllUserOpenId() {
		
		    //if (string.IsNullOrEmpty(nextopenid))
		    //    nextopenid = "";
		String AccessToken = WeixinContextModel.at;
		
		String url = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN";
		url = url.replace("ACCESS_TOKEN", AccessToken) ;
		
		String result = httpGet(url);
		Map map = (Map) JSON.parse(result);
		Map dataMap = (Map) map.get("data");
		List list = (List) dataMap.get("openid");
//		System.out.println(list.get(0).toString());
//		System.out.println(result);
		
		return list;
		 
		
	}
	
	@Test
	public void getAllWeixinUserInfo() {
		
		String AccessToken = WeixinContextModel.at;
		
		
		List<String> list = getAllUserOpenId();
		for(String OPENID : list) {
			
//			String OPENID = "odS5I6MQDfL9-3qP1X_rc9VzOue4";
			
			
			String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
			url = url.replace("ACCESS_TOKEN", AccessToken) ;
			url = url.replace("OPENID", OPENID) ;
			String result = httpGet(url);
			System.err.println(result);
			WeixinUserModel weixinUserModel = JSON.parseObject(result, WeixinUserModel.class);
			String nickName = weixinUserModel.getNickname().toString();
			String nName = nickName.replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", "*");
			weixinUserModel.setNickname(nName);
			System.out.println(weixinUserModel.getOpenId().toString());
			String checkUser =weixinServiceImpl.checkWeixinUserByOpenId(weixinUserModel);
			if(checkUser.equals("Y")) {
				weixinServiceImpl.deleteUserByOpenId(weixinUserModel);
			}
			weixinServiceImpl.insertUser(weixinUserModel);
//			System.out.println(result);
		}
		
		
	}
	
	
	
	
	@Test
	public void sendMsg() {
		
		Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		System.out.println(dateformat.format(date));
		
		
		/**
		 * Test 
		 */
//		System.out.println("sendMsg");
//		String url = WeixinContextModel.ACCESS_TOKEN_URL;
//		System.out.println(WeixinContextModel.APPID);
//		url = url.replace("APPID", WeixinContextModel.APPID);
//		url = url.replace("APPSECRET", WeixinContextModel.APPSECRET);
//		String content = httpGet(url);
//		System.err.println(content);
		
		
		
	}
	
	
	
	
}
