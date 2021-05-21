package com.test.spring.injection;

public class InjectionServiceImpl implements InjectionService {

	private InjectionDao injectionDao;
	
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

	public void setInjectionDao(InjectionDao injectionDao) {
		this.injectionDao = injectionDao;
	}

}