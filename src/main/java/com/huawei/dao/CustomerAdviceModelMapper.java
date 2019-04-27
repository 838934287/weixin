package com.huawei.dao;

import com.huawei.model.CustomerAdviceModel;
import com.huawei.model.EmployeeModel;

import org.apache.ibatis.annotations.Param;

public interface CustomerAdviceModelMapper {
    int deleteByPrimaryKey(@Param("id") Long id, @Param("employeeId") String employeeId);


    int insertSelective(CustomerAdviceModel record);

    CustomerAdviceModel selectByPrimaryKey(@Param("id") Long id, @Param("employeeId") String employeeId);

    int updateByPrimaryKeySelective(CustomerAdviceModel record);

    int updateByPrimaryKey(CustomerAdviceModel record);
    int insertAdvice(CustomerAdviceModel customerAdviceModel);


}