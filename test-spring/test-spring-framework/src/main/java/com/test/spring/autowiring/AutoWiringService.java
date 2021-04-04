 package com.test.spring.autowiring;

public class AutoWiringService {
	
	private AutoWiringDAO autoWiringDAO;
	
	public AutoWiringService() {
	}

//	public AutoWiringService(AutoWiringDAO autoWiringDAO) {
//		this.autoWiringDAO = autoWiringDAO;
//	}
//	 
//	public void setAutoWiringDAO(AutoWiringDAO autoWiringDAO) {
//		this.autoWiringDAO = autoWiringDAO;
//	}

	public void say(String word) {
		System.out.println("AutoWiringService：" + word);
		autoWiringDAO.say(word);
	}
}
