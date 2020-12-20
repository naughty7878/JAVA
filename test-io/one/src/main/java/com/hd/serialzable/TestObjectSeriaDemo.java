package com.hd.serialzable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class TestObjectSeriaDemo {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		String file = "/Users/H__D/Desktop/object.txt";
		// 1、对象序列化
		//TestObjectSeriaDemo.oos(file);
		
		
		// 2、对象反序列化
		//TestObjectSeriaDemo.ois(file);
		
		// 3、测试transient
		TestObjectSeriaDemo.tran(file);
	}
	
	public static void oos(String file) throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
		Student stu = new Student("001", "小明", 18);
		oos.writeObject(stu);
		oos.flush();
		oos.close();
	}
	
	public static void ois(String file) throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
		Student stu = (Student) ois.readObject();
		System.out.println(stu);
	}
	
	public static void tran(String file) throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
		Student stu = (Student) ois.readObject();
		System.out.println(stu);
	}
}
