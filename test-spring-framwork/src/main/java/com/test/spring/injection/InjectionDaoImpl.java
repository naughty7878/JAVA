package com.test.spring.injection;

public class InjectionDaoImpl implements InjectionDao {

	public int save(String str) {
		System.out.println("InjectionDaoImpl: 模拟数据库保存--" + str);
		return 1; 
	}
}