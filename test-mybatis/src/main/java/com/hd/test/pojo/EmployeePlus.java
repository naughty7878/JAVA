package com.hd.test.pojo;

public class EmployeePlus {

	private Integer id;
	private String lastName;
	private String gender;
	private String email;
	
	private Department dept;
	
	public EmployeePlus() {
		// TODO Auto-generated constructor stub
	}
	
	
	public EmployeePlus(String lastName, String gender, String email) {
		super();
		this.lastName = lastName;
		this.gender = gender;
		this.email = email;
	}
	
	
	
	public EmployeePlus(Integer id, String lastName, String gender, String email) {
		super();
		this.id = id;
		this.lastName = lastName;
		this.gender = gender;
		this.email = email;
	}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public Department getDept() {
		return dept;
	}


	public void setDept(Department dept) {
		this.dept = dept;
	}


	@Override
	public String toString() {
		return "EmployeePlus [id=" + id + ", lastName=" + lastName + ", gender=" + gender + ", email=" + email
				+ ", dept=" + dept + "]";
	}
	
	
}
