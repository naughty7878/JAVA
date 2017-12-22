package com.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TestFile {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		File oldFile = new File("//Users//H__D//Desktop//123.txt");
		if(oldFile.exists())
			System.out.println("文件存在!");
		FileInputStream is = new FileInputStream(oldFile);
		
		byte[] b = new byte[1024];
 		
		int n = is.read(b);
		
		while(n>0)
		{
			sb.append(new String(b));
			n = is.read(b);
		}
		String str =sb.toString();
		
		
		str = str.replaceAll("\n", "\",\"");
		System.out.println(str);
		
	}

}
