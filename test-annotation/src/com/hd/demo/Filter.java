package com.hd.demo;

@Table("user")
public class Filter {
	
	@Column("id")
	private int id;
	
	@Column("user_name")
	private String userName;
	
	@Column("nick_name")
	private String nickName;
	
	@Column("age")
	private int age;
	
	@Column("city")
	private String city;
	
	@Column("email")
	private String email;
	
	@Column("mobile")
	private String mobile;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	

	public Filter() {
		super();
	}

	public Filter(int id, String userName, String nickName, int age, String city, String email, String mobile) {
		super();
		this.id = id;
		this.userName = userName;
		this.nickName = nickName;
		this.age = age;
		this.city = city;
		this.email = email;
		this.mobile = mobile;
	}

	@Override
	public String toString() {
		return "Filter [id=" + id + ", userName=" + userName + ", nickName=" + nickName + ", age=" + age + ", city="
				+ city + ", email=" + email + ", mobile=" + mobile + "]";
	}
	
	
}
