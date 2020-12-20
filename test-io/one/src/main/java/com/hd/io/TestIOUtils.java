package com.hd.io;

import java.io.File;
import java.io.IOException;

public class TestIOUtils {

	public static void main(String[] args) throws IOException {
		
		//IOUtils.printHex("/Users/H__D/Desktop/1.png");
		
		//IOUtils.printHexByByteArray("/Users/H__D/Desktop/1.png");
		
		//IOUtils.copyFile("/Users/H__D/Desktop/1.png", "/Users/H__D/Desktop/2.png");
		
		//IOUtils.copyFileByBuffer(new File("/Users/H__D/Desktop/1.png"), new File("/Users/H__D/Desktop/3.png"));
		
		//IOUtils.copyFile("/Users/H__D/Desktop/1.mp3", "/Users/H__D/Desktop/2.mp3");//25450
		//IOUtils.copyFileDate("/Users/H__D/Desktop/1.mp3", "/Users/H__D/Desktop/3.mp3");//26807
		//IOUtils.copyFileByBuffer("/Users/H__D/Desktop/1.mp3", "/Users/H__D/Desktop/4.mp3");//22423
		//IOUtils.copyFileByByteArray("/Users/H__D/Desktop/1.mp3", "/Users/H__D/Desktop/5.mp3");//290
		//IOUtils.copyFileByBufferArray("/Users/H__D/Desktop/1.mp3", "/Users/H__D/Desktop/6.mp3");//233
		
		long start = System.currentTimeMillis();
		
		IOUtils.copyFileByByteArray("/Users/H__D/Desktop/1.mp3", "/Users/H__D/Desktop/5.mp3");//78
		
		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}
}
