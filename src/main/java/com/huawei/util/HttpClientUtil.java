package com.huawei.util;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {
	
	
	

	public static String httpGet(String url) {
		String result = null;
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet get = new HttpGet(url);
		
		CloseableHttpResponse resp;
		try {
			resp = client.execute(get);
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
		}
		return null;
	}
	
	public static String httpPost(String url, String content) {
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
		
//		Post发送数据
		CloseableHttpResponse resp;
		try {
			resp = client.execute(post);
			int statusCode = resp.getStatusLine().getStatusCode();
			if (statusCode >= 200 && statusCode < 300) {
				HttpEntity respEntity = resp.getEntity();
				result = EntityUtils.toString(respEntity, "UTF-8");
//				System.out.println(result);
			}

			return result;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	

}
