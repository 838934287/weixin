package com.huawei.dao;

import java.util.List;

import com.huawei.model.MessageModel;

public interface HandleMessageMapper {
	
	
	List<MessageModel> selectMessage();

	public void insertMessage(MessageModel message) ;

}
