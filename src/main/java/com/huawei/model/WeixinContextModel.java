package com.huawei.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WeixinContextModel {
	public static final String at = "18_PGITdLJxnDHyHPGGfGDn3dXPLcVEfYVjJzj1O2eQkK3f_IXH3jWPRV4pEfx1-HiTcOrBeFgsXorU-rw7qO6aKizaUxbW2dZ1U5BUIO3uo0J9MgT-4Wr0FxjjIVJeXMCn1byYGrm0ynNOQA5jTZEbAFAYEJ";
	
	public static final String token = "HI123456";
	public static final String ACCESS_TOKEN_URL="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
//	TEST
	public static final String APPID="wxa7f896e87e77d822";
	public static final String APPSECRET = "bcab5007948764de5c6936f02444a769";
//	PROD
//	public static final String APPID="wx17a3b7907e89f8ee";
//	public static final String APPSECRET = "7a42db916451993b38cc32766285d85f";
	
	
	public static final String MENU_ADD = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	public static final String TEMPLATE_MSG_URL ="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
	
	public static final String WeixinUserInfoUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	public static final String WeixinUserOpenIdUrl = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN";
	
	
	public static final String sendTemplateUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
	
	
	
	private static String accessToken;
	public static String getAccessToken() {
		return accessToken;
	}

	public static void setAccessToken(String accessToken) {
		WeixinContextModel.accessToken = accessToken;
	}
	

}
