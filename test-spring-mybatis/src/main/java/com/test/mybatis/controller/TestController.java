package com.test.mybatis.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.mybatis.pojo.Employee;
import com.test.mybatis.service.EmployeeService;

@Controller
public class TestController {
	

	@Autowired
	EmployeeService employeeService;
	
	@RequestMapping("/getEmps")
	public String emps(Map<String,Object> map){
		List<Employee> emps = employeeService.getEmps();
		System.out.println(emps.size());
		map.put("allEmps", emps);
		return "list";
	}

}
