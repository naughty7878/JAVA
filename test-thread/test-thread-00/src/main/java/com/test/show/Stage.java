package com.test.show;

public class Stage {
	
	public static void main(String[] args) {

		Thread actor = new Actor();
		actor.setName("Mr.Thread");

		actor.start();
		
		Thread actress = new Thread(new Actress());
		actress.setName("Ms.Runnable");
		
		actress.start();
	}
}
