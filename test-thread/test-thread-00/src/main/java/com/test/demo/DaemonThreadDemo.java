package com.test.demo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class DaemonThreadDemo {

	
	public static void main(String[] args) {
		System.out.println("进入主线程：" + Thread.currentThread().getName());
		
		DaemonThread daemonThread = new DaemonThread();
		Thread t = new Thread(daemonThread);
		t.setDaemon(true);//设置为守护线程
		t.start();
		
		Scanner sc = new Scanner(System.in);
		sc.next();
		
		System.out.println("退出主线程：" + Thread.currentThread().getName());
		
	}
	
	
}



class DaemonThread implements Runnable{
	@Override
	public void run() {
		System.out.println("进入守护线程：" + Thread.currentThread().getName());
	
		try {
			writeToFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("推出守护线程：" + Thread.currentThread().getName());
		
	}

	private void writeToFile() throws IOException, InterruptedException {
		File file = new File("/Users/H__D/Desktop/doc.txt");
		
		FileOutputStream out = new FileOutputStream(file, true);
		int count = 0;
		while(count < 999) {
			out.write(("\r\nword" + count).getBytes());
			System.out.println("守护线程" + Thread.currentThread().getName() + "  向文件中写入了word" + count ++);
			Thread.sleep(1000);
		}
	}
}





















