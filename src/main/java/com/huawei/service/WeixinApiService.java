package com.huawei.service;

import com.huawei.model.WeixinUserModel;

public interface WeixinApiService {
	
	
	WeixinUserModel getWeixinUser(String openId);
	
	

}
