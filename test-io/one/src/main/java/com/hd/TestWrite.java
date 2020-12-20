package com.hd;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TestWrite {
	
	public static void main(String[] args) throws IOException {
		
		String str = "hello world!";
		OutputStream os = new FileOutputStream(new File("/Users/H__D/Desktop/write.txt"));
		byte[] bytes = str.getBytes();
		os.write(bytes, 0, bytes.length);
		
		InputStream is = new FileInputStream(new File("/Users/H__D/Desktop/write.txt"));
		byte[] b = new byte[20];
		is.read(b);
		System.out.println(b.length);
		
		System.out.println(new String(b));
		
	}
	
	
	
	
}
