package com.hd.io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputStreamDemo {

	public static void main(String[] args) throws IOException {
		
		FileOutputStream fout = new FileOutputStream("/Users/H__D/Desktop/fout.txt");
		fout.write('A');//写出了'A'的低八位
		
		int a = 10;
		fout.write(a >>> 24);
		fout.write(a >>> 16);
		fout.write(a >>> 8);
		fout.write(a);
		
		fout.close();
		
		
	}
}
