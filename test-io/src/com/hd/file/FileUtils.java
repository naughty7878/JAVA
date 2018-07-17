package com.hd.file;

import java.io.File;
import java.io.IOException;

/**
 * 列出File的一些常用操作
 * 比如：过滤、遍历等
 * @ClassName FileUtils 
 * @Description TODO
 * @author H__D
 * @date 2018年7月17日
 *
 */
public class FileUtils {

	/**
	 * 列出指定目录下（包括其子目录）的所有文件
	 * @Description TODO
	 * @author H__D
	 * @date 2018年7月17日
	 * @param dir
	 * @throws IOException
	 */
	public static void listDirectory(File dir)throws IOException{
		if(dir == null || !dir.exists()) {
			throw new IllegalArgumentException("目录" + dir + "不存在");
		}
		if(!dir.isDirectory()) {
			throw new IllegalArgumentException(dir + "不是一个目录");
		}
		/*
		 * 方法一
		String[] fileNames = dir.list();
		for (String string : fileNames) {
			System.out.println(string);
			File f = new File(dir.getAbsolutePath() + File.separator + string);
			if(f.isDirectory()) {
				FileUtils.listDirectory(f);
			}
		}
		*/
		/*
		 * 方法2
		 */
		File[] listFiles = dir.listFiles();
		for (File file : listFiles) {
			System.out.println(file);
			if (file.isDirectory()) {
				FileUtils.listDirectory(file);
			}
		}
	}
}
