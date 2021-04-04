package com.test.spring.beanannotation.injection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InjectionServiceImpl implements InjectionService {

	// @Autowired
	private InjectionDao injectionDao;
	
	@Autowired
	public void InjectionServiceImpl(InjectionDao injectionDao) {
		this.injectionDao = injectionDao;
	}
	
//	@Autowired
//	public void setInjectionDao(InjectionDao injectionDao) {
//		this.injectionDao = injectionDao;
//	}

	public InjectionServiceImpl() {
	}
	 
	public InjectionServiceImpl(InjectionDao injectionDao) {
		this.injectionDao = injectionDao;
	}
	
	public int save(String str) {
		System.out.println("InjectionServiceImpl：业务逻辑保存--"+ str);
		injectionDao.save(str);
		return 1;
	}
}
