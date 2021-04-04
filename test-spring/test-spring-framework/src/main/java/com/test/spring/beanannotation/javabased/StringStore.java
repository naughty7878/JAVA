package com.test.spring.beanannotation.javabased;

public class StringStore implements Store {
	public void init() {
		System.out.println("This is StringStore.init");
	}
	
	public void destory() {
		System.out.println("This is StringStore.destory");
	}
}
