package com.test.spring.beanannotation.injection;

import org.springframework.stereotype.Repository;

@Repository
public class InjectionDaoImpl implements InjectionDao {

	public int save(String str) {
		System.out.println("InjectionDaoImpl: 模拟数据库保存--" + str);
		return 1; 
	}

}
