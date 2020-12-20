package com.hd.io;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DosDemo {

	
	public static void main(String[] args) throws IOException {
		
		String fileStr = "/Users/H__D/Desktop/doc.txt";
		
		DataOutputStream dos = new DataOutputStream(new FileOutputStream(fileStr));
		dos.writeInt(-10);
		dos.write(10);
		dos.writeLong(10l);
		dos.writeDouble(10.5);
		dos.writeUTF("中国");
		
		dos.close();
	}
	
}
