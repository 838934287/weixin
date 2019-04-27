package com.huawei.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huawei.dao.EmployeeModelMapper;
import com.huawei.model.EmployeeModel;
import com.huawei.model.PageModel;
import com.huawei.service.EmployeeService;



@Service("EmployeeService")
public class EmployeeServiceImpl implements EmployeeService {
	@Resource(name = "employeeModelMapper")
	private EmployeeModelMapper employeeModelMapper;
	@Override
	public int deleteByPrimaryKey(Integer employeeId) {
		// TODO Auto-generated method stub
		return employeeModelMapper.deleteByPrimaryKey(employeeId);
	}

	@Override
	public int selectCount() {
		// TODO Auto-generated method stub
		return employeeModelMapper.selectCount();
	}

	@Override
	public int insertSelective(EmployeeModel record) {
		// TODO Auto-generated method stub
		return employeeModelMapper.insertSelective(record);
	}

	@Override
	public EmployeeModel selectEmployeeByEmployeeId(Integer employeeId) {
		// TODO Auto-generated method stub
		return employeeModelMapper.selectEmployeeByEmployeeId(employeeId);
	}

	@Override
	public List<EmployeeModel> selectAllEmployee() {
		// TODO Auto-generated method stub
		return employeeModelMapper.selectAllEmployee();
	}

	@Override
	public int updateByPrimaryKey(EmployeeModel record) {
		// TODO Auto-generated method stub
		return employeeModelMapper.updateByPrimaryKey(record);
	}

	@Override
	public EmployeeModel loginByEmployeeIdAndPassword(EmployeeModel record) {
		// TODO Auto-generated method stub
		return employeeModelMapper.loginByEmployeeIdAndPassword(record);
	}
	
	@Override
	public PageModel<EmployeeModel> findByPage(int currentPage) {
		// TODO Auto-generated method stub
		HashMap<String,Object> map = new HashMap<String,Object>();
		PageModel<EmployeeModel> pageModel = new PageModel<EmployeeModel>();
		//封装当前页数
		pageModel.setCurrPage(currentPage);
		//每页显示的数据
		int pageSize=5;
		pageModel.setPageSize(pageSize);
		//封装总记录数
		int totalCount = employeeModelMapper.selectCount();
		pageModel.setTotalCount(totalCount);
		//封装总页数
		double tc = totalCount;
		Double num =Math.ceil(tc/pageSize);//向上取整
		pageModel.setTotalPage(num.intValue());
		
		map.put("start",(currentPage-1)*pageSize);
		map.put("size", pageModel.getPageSize());
		//封装每页显示的数据
		List<EmployeeModel> lists = employeeModelMapper.findByPage(map);
		pageModel.setLists(lists);
		
		return pageModel;	
	}
	
	
	
}
