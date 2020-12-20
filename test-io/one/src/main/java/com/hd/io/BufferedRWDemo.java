package com.hd.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class BufferedRWDemo {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("/Users/H__D/Desktop/日记.txt"));
		BufferedWriter bw = new BufferedWriter(new FileWriter("/Users/H__D/Desktop/日记4.txt"));
		PrintWriter pw = new PrintWriter("/Users/H__D/Desktop/日记5.txt");
		String str = null;
		while ((str = br.readLine()) != null) {
			System.out.println(str);
			bw.write(str);
			bw.write("\n");
			
			pw.println(str);
			
		}
		bw.flush();
		bw.close();
		
		pw.flush();
		pw.close();
		
		br.close();
		
	}
}
