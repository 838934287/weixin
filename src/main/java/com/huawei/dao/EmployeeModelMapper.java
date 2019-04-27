package com.huawei.dao;

import java.util.HashMap;
import java.util.List;

import com.huawei.model.EmployeeModel;

public interface EmployeeModelMapper {
    int deleteByPrimaryKey(Integer employeeId);
    int selectCount();    
    int insert(EmployeeModel record);
    int insertSelective(EmployeeModel record);
    EmployeeModel selectEmployeeByEmployeeId(Integer employeeId);
    int updateByPrimaryKeySelective(EmployeeModel record);
    int updateByPrimaryKey(EmployeeModel record);
	EmployeeModel selectEmployeeById(int employee);
	List<EmployeeModel> selectEmployeeByRole(String role);
	List<EmployeeModel> selectAllEmployee();
    List<EmployeeModel> findByPage(HashMap<String,Object> map);
    EmployeeModel loginByEmployeeIdAndPassword(EmployeeModel record);
    
    
	
	
}