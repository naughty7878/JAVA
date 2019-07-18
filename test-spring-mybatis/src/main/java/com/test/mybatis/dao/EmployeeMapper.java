package com.test.mybatis.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.test.mybatis.pojo.Employee;

@Repository
public interface EmployeeMapper {
	
	public List<Employee> getEmployee();
	
	
	public boolean add(Employee employee);
	
}
