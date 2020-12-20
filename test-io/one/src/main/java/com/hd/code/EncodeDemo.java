package com.hd.code;

import java.io.UnsupportedEncodingException;

public class EncodeDemo {

	
	public static void main(String[] args) throws UnsupportedEncodingException {
		
		String str = "中国abc";
		byte[] bytes = str.getBytes();
	
		for (byte b : bytes) {
			// 把字节（转换成了int）以16进制的方式显示
			System.out.print(Integer.toHexString(b & 0xff) + "   ");
		}
		System.out.println();
		
		//gbk编码中文占用2个字节，英文占用1个字节
		byte[] bytes2 = str.getBytes("gbk");
		for (byte b : bytes2) {
			System.out.print(Integer.toHexString(b & 0xff) + "   ");
		}
		System.out.println();
		System.out.println(new String(bytes2, "gbk"));
		
		//utf-8编码中文占用3个字节，英文占用1个字节
		byte[] bytes3 = str.getBytes("utf-8");
		for (byte b : bytes3) {
			System.out.print(Integer.toHexString(b & 0xff) + "   ");
		}
		System.out.println();
		System.out.println(new String(bytes3, "utf-8"));
		
		//java是双字节编码
		//utf-16be编码中文占用2个字节，英文占用2个字节
		byte[] bytes4 = str.getBytes("utf-16be");
		for (byte b : bytes4) {
			System.out.print(Integer.toHexString(b & 0xff) + "   ");
		}
		System.out.println();
		System.out.println(new String(bytes4, "utf-16be"));
		
		
		/**
		 * 文本文件 就是字节序列
		 * 可以是任意编码的字节序列
		 * 如果我们在中文机器上直接创建文本文件，那么改文本文件只认识ansi编码
		 */
		
	}
	
}
