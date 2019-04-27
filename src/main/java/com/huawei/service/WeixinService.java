package com.huawei.service;

import java.sql.SQLException;
import java.util.List;

import com.huawei.model.CustomerAdviceModel;
import com.huawei.model.EmployeeModel;
import com.huawei.model.MessageModel;
import com.huawei.model.WeixinUserModel;
import com.huawei.model.WeixinUserOrderModel;


public interface WeixinService {


	List<MessageModel> selectMessage();

	void insertMessage(MessageModel message);
	String checkWeixinUserByOpenId(WeixinUserModel weixinUserModel);
	void deleteUserByOpenId(WeixinUserModel weixinUserModel);

	void insertUser(WeixinUserModel weixinUserModel);

	String insertCustomerAdvice(CustomerAdviceModel customerAdviceModel);

	String checkEmployee(String employeeId);

	List<EmployeeModel> selectEmployeeByRole(String role);

	String pushAdviceToEmployee(CustomerAdviceModel customerAdviceModel, String role);

	String insertWeixinOrder(WeixinUserOrderModel weixinUserOrderModel);

	String pushWeixinOrderInfo(WeixinUserOrderModel weixinUserOrderModel, String role);

	void insertUserToDb(String string);

}
