package com.hd.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class IOUtils {

	/**
	 * 读取指定文件内容，按照16进制输出到控制台
	 * 并且每输出10个byte换行
	 * @Description TODO
	 * @author H__D
	 * @date 2018年7月17日
	 * @param fileName
	 * @throws IOException 
	 */
	public static void printHex(String fileName) throws IOException{
		
		// 1、把文件作为字节流作为读操作
		FileInputStream fin = new FileInputStream(fileName);
		int b;
		int i = 1;
		while ((b = fin.read()) != -1) {
			System.out.print(Integer.toHexString(b) + "  ");
			if(i++ % 10 == 0) {
				System.out.println();
			}
		}
		
	}
	
	public static void printHexByByteArray(String fileName) throws IOException {
		
		FileInputStream fin = new FileInputStream(fileName);
		byte[] buf = new byte[1024];
		
		int bytes = fin.read(buf);
		for (int i = 0; i < bytes; i++) {
			if(i % 10 == 0) {
				System.out.println();
			}
			System.out.print(Integer.toHexString(buf[i] & 0xff) + "  ");
			
		}
		
	}

	
	public static void copyFile(String fileName, String newFileName) throws IOException {
		
		FileInputStream in = new FileInputStream(fileName);
		FileOutputStream out = new FileOutputStream(newFileName);
		
		
		int i = 0;
		while ((i = in.read()) != -1) {
			
			out.write(i);
			out.flush();
		}
		
		out.close();
		in.close();
		
	}
	
	public static void copyFileDate(String fileName, String newFileName) throws IOException {
		
		DataInputStream in = new DataInputStream(new FileInputStream(fileName)) ;
		DataOutputStream out = new DataOutputStream(new FileOutputStream(newFileName));
		
		
		int i = 0;
		while ((i = in.read()) != -1) {
			
			out.write(i);
			out.flush();
		}
		
		out.close();
		in.close();
		
	}
	
	
	public static void copyFileByByteArray(String fileName, String newFileName) throws IOException {
		
		FileInputStream in = new FileInputStream(fileName);
		FileOutputStream out = new FileOutputStream(newFileName);
		
		byte[] buf = new byte[100];
		int i = 0;
		while ((i = in.read(buf, 0, buf.length)) != -1) {
			
			out.write(buf, 0, i);
			out.flush();
		}
		
		out.close();
		in.close();
		
	}
	
	
	public static void copyFileByBuffer(String file, String newFile) throws IOException{
		
		BufferedInputStream bin = new BufferedInputStream(new FileInputStream(file));
		BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(newFile));
		
		int c;
		while ((c = bin.read()) != -1) {
			bout.write(c);
			bout.flush();
		}
		bout.close();
		bin.close();
		
	}
	
	public static void copyFileByBufferArray(String file, String newFile) throws IOException{
		
		BufferedInputStream bin = new BufferedInputStream(new FileInputStream(file));
		BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(newFile));
		
		byte[] buf = new byte[100];
		int c;
		while ((c = bin.read(buf, 0, buf.length)) != -1) {
			bout.write(buf, 0, c);
			bout.flush();
		}
		bout.close();
		bin.close();
		
	}
}
