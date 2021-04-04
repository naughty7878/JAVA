package com.hd.file;

import java.io.File;
import java.io.IOException;

public class FileDemo {
	
	public static void main(String[] args) throws IOException {
		
		File file = new File("/Users/H__D/Desktop/abc");
		// 判断文件是否存在
		if(!file.exists()){
			file.mkdir();//file.mkdirs()多级目录
		}else {
			//file.delete();
		}
		
		//判断是否是一个目录
		System.out.println(file.isDirectory());
		//判断是否是一个文件
		System.out.println(file.isFile());
		
		File file2 = new File("/Users/H__D/Desktop/日记.txt");
		if(!file2.exists()){
			file2.createNewFile();
		}
		System.out.println(file2);
		System.out.println(file2.getAbsolutePath());
		System.out.println(file2.getName());
		System.out.println(file2.getParent());
		System.out.println(file2.getParentFile());
	}
}








