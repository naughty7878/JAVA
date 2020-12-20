package com.test.spring.aop.schema.advice.service;

import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.stereotype.Service;

@Service
public class InvokeService {

	public void invoke() {
		System.out.println("InvokeService invoke ...");
	}
	
	public void invokeException() {
		throw new PessimisticLockingFailureException("");
	}
}
