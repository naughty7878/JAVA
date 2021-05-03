package com.hd.test.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.hd.test.pojo.Employee;

public interface EmployeeMapper {
	
	public Employee getEmployeeById(Integer id);

	public Employee getEmployeeById2(Integer id);
	
	@Select("select id, last_name lastName, gender, email from employee where id = #{id}")
	public Employee getEmployeeById3(Integer id);
	
	// 新增
	public Integer insertEmployee(Employee employee);
	
	// 新增并返回id
	public Integer insertEmployeeReturnId(Employee employee);
	
	// 修改
	public boolean updateEmployee(Employee employee);
	
	// 删除
	public Integer deleteEmployeeById(Integer id);
	
	public Employee testGetParam(String field);
	
	// 动态SQL
	public List<Employee> testConditionIf(Employee employee);
	
	public List<Employee> testConditionIfWhere(Employee employee);
	
	public List<Employee> testConditionIfTrim(Employee employee);
	
	public List<Employee> testConditionChoose(Employee employee);
	
	public List<Employee> testConditionForeach(@Param("ids")List<Integer> ids);
	
	public boolean testConditionSet(Employee employee);
	
	public List<Employee> testInnerParameter(String tableName);
	
}
