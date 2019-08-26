package com.hd.test.mapper;

import com.hd.test.pojo.DepartmentPlus;

public interface DepartmentPlusMapper {
	
	public DepartmentPlus getDeparmentAndEmp(Integer id);
	
	public DepartmentPlus getDeparmentAndEmpMapByStep(Integer id);

}
