package com.hd.test.pojo;

import java.util.List;

public class DepartmentPlus {
	
	private Integer id;
	private String deptName;
	
	private List<Employee> emps;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	@Override
	public String toString() {
		return "DepartmentPlus [id=" + id + ", deptName=" + deptName + ", emps=" + emps + "]";
	}
	public List<Employee> getEmps() {
		return emps;
	}
	public void setEmps(List<Employee> emps) {
		this.emps = emps;
	}
	
}
