package com.huawei.service;

import java.util.HashMap;
import java.util.List;

import com.huawei.model.EmployeeModel;
import com.huawei.model.PageModel;

public interface EmployeeService {

	 int deleteByPrimaryKey(Integer id);

	    int selectCount();
	    int insertSelective(EmployeeModel record);
	    EmployeeModel selectEmployeeByEmployeeId(Integer id);
	    
		List<EmployeeModel> selectAllEmployee();

	    PageModel<EmployeeModel> findByPage(int currentPage);
	    int updateByPrimaryKey(EmployeeModel record);
	    EmployeeModel loginByEmployeeIdAndPassword(EmployeeModel record);
	    
	
	
}
