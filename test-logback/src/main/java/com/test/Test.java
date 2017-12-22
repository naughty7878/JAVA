package com.test;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Test {
	
	final static  Logger logger  =  LoggerFactory.getLogger(Test.class );

	public static void main(String[] args) throws InterruptedException {
		
		logger.debug( "现在的时间是 {}" ,  new Date().toString());
		
		logger.info( " This time is {}" ,  new Date().toString());
		
		logger.warn( " This time is {}" ,  new Date().toString());
		
		logger.error( " This time is {}" ,  new Date().toString());
		
		int n = 1/0;
	
		
	}

}
