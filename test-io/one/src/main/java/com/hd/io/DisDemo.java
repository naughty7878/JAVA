package com.hd.io;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class DisDemo {
	public static void main(String[] args) throws IOException {
		
		String fileStr = "/Users/H__D/Desktop/doc.txt";
		
		DataInputStream dis = new DataInputStream(new FileInputStream(fileStr));
		
		
		System.out.println(dis.readInt());
		System.out.println(dis.read());
		System.out.println(dis.readLong());
		System.out.println(dis.readDouble());
		System.out.println(dis.readUTF());
		
		dis.close();
		
	}
}
