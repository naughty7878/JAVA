package com.test.proxy.staticX;

// 实现具体的业务功能
public class TeacherDao implements ITeacherDao {

	@Override
	public void teach() {
		System.out.println(" 老师授课中  。。。。。");
	}

}