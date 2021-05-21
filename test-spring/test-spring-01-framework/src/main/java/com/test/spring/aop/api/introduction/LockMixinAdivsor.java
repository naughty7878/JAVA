package com.test.spring.aop.api.introduction;

import org.springframework.aop.support.DefaultIntroductionAdvisor;

public class LockMixinAdivsor extends DefaultIntroductionAdvisor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public LockMixinAdivsor() {
		super(new LockMixin(), Lockable.class);
	}

}
