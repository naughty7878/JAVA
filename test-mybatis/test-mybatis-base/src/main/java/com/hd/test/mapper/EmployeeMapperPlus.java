package com.hd.test.mapper;

import com.hd.test.pojo.Employee;
import com.hd.test.pojo.EmployeePlus;

public interface EmployeeMapperPlus {
	
	public Employee getEmployeeBys(Integer id);

	
	public EmployeePlus getMyEmployeeDept(Integer id);
	
	public EmployeePlus getMyEmployeeByStep(Integer id);
	
	public EmployeePlus getMyEmpDis(Integer id);
	
}
