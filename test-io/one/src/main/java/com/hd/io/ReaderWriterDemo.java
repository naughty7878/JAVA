package com.hd.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class ReaderWriterDemo {
	
	public static void main(String[] args) throws IOException {
		
		InputStreamReader isr = new InputStreamReader(new FileInputStream("/Users/H__D/Desktop/日记.txt"));
		OutputStreamWriter outw = new OutputStreamWriter(new FileOutputStream("/Users/H__D/Desktop/日记2txt"));
		
		int c;
//		while ((c = isr.read()) != -1) {
//			System.out.print((char)c);
//		}
	
		char[] buf = new char[100];
		while ((c = isr.read(buf, 0, buf.length)) != -1) {
			String str = new String(buf, 0, c);
			outw.write(str);
			System.out.println(str);
		}
		outw.flush();
		outw.close();
		isr.close();
	}
}
