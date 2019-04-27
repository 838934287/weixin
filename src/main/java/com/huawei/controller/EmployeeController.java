package com.huawei.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.huawei.model.EmployeeModel;
import com.huawei.service.EmployeeService;

@Controller
public class EmployeeController {
	
	@Resource
	private EmployeeService employeeService;
	
	/**
	 * 用户登陆
	 */

	@RequestMapping("/employeeLogin")
	public String login(@RequestParam("employeeId")int employeeId,
			@RequestParam("password")String password,Model model) throws Exception{
		EmployeeModel employeeModel = new EmployeeModel();
		employeeModel.setEmployeeid(employeeId);
		employeeModel.setPassword(password);
		EmployeeModel result = employeeService.loginByEmployeeIdAndPassword(employeeModel);
		if(result!=null){
			//登录成功
			List<EmployeeModel> lists = employeeService.selectAllEmployee();
			model.addAttribute("userLists", lists);//回显用户信息
			model.addAttribute("currentEmployee", result.getUsername());	
			return "redirect:employeeMain";
		}
		return "error";
	}
	@RequestMapping("/employeeMain")
	public String  main(@RequestParam(value="currentPage",defaultValue="1",required=false)int currentPage,Model model){
		model.addAttribute("pagemsg", employeeService.findByPage(currentPage));//回显分页数据
		return "EmployeeMain";
	}
	/**
	 * 跳到编辑页面
	 * @param currentPage
	 * @param model
	 * @return
	 */
	@RequestMapping("/employeeEdit")
	public String editpage(@RequestParam("employeeId") int employeeId,Model model){
		EmployeeModel employeeModel =employeeService.selectEmployeeByEmployeeId(employeeId);
		model.addAttribute("returnEmployee", employeeModel);
		return "EmployeeEdit";
	}
	/**
	 * 保存用户数据
	 * @return
	 */
	@RequestMapping("/employeeSave")
	public String save(EmployeeModel employeeModel){
		System.out.println(employeeModel.toString());
		EmployeeModel checkEmployeeModel = employeeService.selectEmployeeByEmployeeId(employeeModel.getEmployeeid().intValue());
		if(checkEmployeeModel==null){
			//id为null是保存
			employeeService.insertSelective(employeeModel);
		}else{
			//有id值为修改
			employeeService.updateByPrimaryKey(employeeModel);
		}	
		return "redirect:employeeMain";
	}
	/**
	 * 删除用户数据
	 * @param id
	 * @return
	 */
	@RequestMapping("/employeeDelete")
	public String delete(@RequestParam("employeeId") int employeeId){
		employeeService.deleteByPrimaryKey(employeeId);
		return "redirect:employeeMain";
	}
	/**
	 * 添加一个用户数据
	 * @return
	 */
	@RequestMapping("/employeeAdd")
	public String add(Model model){
		model.addAttribute("returnEmployee", new EmployeeModel());
		return "EmployeeEdit";
	}

	
	
	
	
	

}
