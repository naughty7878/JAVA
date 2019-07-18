package com.test.mybatis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.mybatis.dao.EmployeeMapper;
import com.test.mybatis.pojo.Employee;

@Service
@Transactional
public class EmployeeService {
	
	@Autowired
	private EmployeeMapper employeeMapper;

	public List<Employee> getEmps() {
		return employeeMapper.getEmployee();
	}

}
