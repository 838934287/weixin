package com.huawei.quartz;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.huawei.model.AccessTokenModel;
import com.huawei.model.ErrorEntityModel;

import com.huawei.model.WeixinContextModel;
import com.huawei.util.JsonUtil;

@Component
public class RefreshAccessTokenTask {
	
	@Value("${ACCESS_TOKEN_URL}")
	private String ACCESS_TOKEN_URL;
	@Value("${APPID}")
	private String APPID;
	@Value("${APPSECRET}")
	private String APPSECRET;
	
	public void refreshToken() {	
//		WeixinContext.setAccessToken(at);
		CloseableHttpClient client = null;
		HttpGet get = null;
		CloseableHttpResponse resp = null;
		try {
			client = HttpClients.createDefault();
			String url = ACCESS_TOKEN_URL;
			url = url.replace("APPID", APPID);
			url = url.replace("APPSECRET", APPSECRET);
			get = new HttpGet(url);
			resp = client.execute(get);
				int statusCode = resp.getStatusLine().getStatusCode();
				if (statusCode >=200 && statusCode<300){
					HttpEntity entity = resp.getEntity();
					String content = EntityUtils.toString(entity,"UTF-8");
					System.out.println(content);
					
					try {
						AccessTokenModel at = (AccessTokenModel)JsonUtil.string2Obj(content,AccessTokenModel.class);
						WeixinContextModel.setAccessToken(at.getAccess_token().toString());
					} catch (Exception e) {
						ErrorEntityModel err = (ErrorEntityModel)JsonUtil.string2Obj(content, ErrorEntityModel.class);
						System.out.println("获取Token异常:"+ err.getErrmsg()+","+err.getErrorcode());
						refreshToken();
						
					}
					
//					System.out.println(at.getAccess_token()+" , "+at.getExpires_in());
					
					
					
				}
				System.out.print(statusCode);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
				if(resp!=null)
						resp.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}  

				try {
				if(client!=null)
						client.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
				
			}
		
	}

}
