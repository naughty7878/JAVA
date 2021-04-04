package com.hd.io;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilterWriter;
import java.io.IOException;

public class FileReaderWriterDemo {

	
	public static void main(String[] args) throws IOException {
		
		FileReader fr = new FileReader("/Users/H__D/Desktop/日记.txt");
		FileWriter fw = new FileWriter("/Users/H__D/Desktop/日记3.txt", true);
		
		int c;
		char[] buf = new char[100];
		while ((c = fr.read(buf, 0, buf.length)) != -1 ) {
			String str = new String(buf, 0, c);
			fw.write(buf, 0, c);
			System.out.println(str);
			
		}
		
		fw.flush();
		fw.close();
		fr.close();
	}
}
