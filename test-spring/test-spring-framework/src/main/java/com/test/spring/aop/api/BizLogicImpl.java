package com.test.spring.aop.api;


public class BizLogicImpl implements BizLogic {

	public String save() {
		System.out.println("BizLogicImpl save ...");
		return "Logic save";
		// throw new RuntimeException();
	}

}
