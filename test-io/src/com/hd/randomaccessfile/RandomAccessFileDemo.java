package com.hd.randomaccessfile;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

public class RandomAccessFileDemo {

	public static void main(String[] args) throws IOException {
		File demo = new File("demo");
		if(!demo.exists()) {
			demo.mkdir();
		}
		File file = new File(demo, "raf.dat");
		if(!file.exists()) {
			file.createNewFile();
		}
		
		RandomAccessFile raf = new RandomAccessFile(file, "rw");
		raf.write('A');//只能写一个字符
		System.out.println(raf.getFilePointer());
		raf.write('B');
		
		int i = 0x7ffffff;
		//用write方法每次只能写一个字符
//		raf.write(i >>> 24);//高8位
//		raf.write(i >>> 16);
//		raf.write(i >>> 8);
//		raf.write(i);
//		System.out.println(raf.getFilePointer());
		
		//可以直接写一个int 
		raf.writeInt(i);
		
		String s = "中";
		byte[] utf8 = s.getBytes("utf-8");
		raf.write(utf8);
		System.out.println(raf.getFilePointer());
		
		//读文件，必须把指针移到头部
		raf.seek(0);
		//一次性读取，把文件中的内容都读到字节数组中
		byte[] buf = new byte[(int)raf.length()];
		raf.read(buf);
		System.out.println(Arrays.toString(buf));
		System.out.println(new String(buf));
		
		raf.close();
 	}
}
