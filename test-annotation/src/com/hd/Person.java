package com.hd;

public interface Person {

	public String name();
	
	public int age();
	
	@Deprecated
	public void say();
}
