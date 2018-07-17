 package com.hd.serialzable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class TestObjectSeriaDemo2 {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		
		// 序列化子类
//		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("/Users/H__D/Desktop/object2.txt"));
//		Foo2 foo2 = new Foo2();
//		oos.writeObject(foo2);
//		oos.flush();
//		oos.close();
		
		// 反序列化子类，是否递归调用父类的构造函数
//		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("/Users/H__D/Desktop/object2.txt"));
//		Foo2 foo2 = (Foo2) ois.readObject();
//		System.out.println(foo2);
//		ois.close();
		
		// 序列化子类bar2
//		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("/Users/H__D/Desktop/object3.txt"));
//		Bar2 bar2 = new Bar2();
//		oos.writeObject(bar2);
//		oos.flush();
//		oos.close();
		
		// 反序列化子类bar2，是否递归调用父类的构造函数
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("/Users/H__D/Desktop/object3.txt"));
		Bar2 bar2 = (Bar2) ois.readObject();
		System.out.println(bar2);
		ois.close();
		
		
		/**
		 * 对子类对象进行反序列化操作时
		 * 如果其父类没有实现序列化接口
		 * 那么其父类的构造函数会被调用
		 */
	}
	
}
/**
 * 一个类实现了序列化接口，其子类都可以进行序列化
 *
 */
class Foo implements Serializable {
	public Foo(){
		System.out.println("Foo...");
	}
}

class Foo1 extends Foo {
	public Foo1(){
		System.out.println("Foo1...");
	}
}

class Foo2 extends Foo1 {
	public Foo2(){
		System.out.println("Foo2...");
	}
}

class Bar {
	public Bar() {
		System.out.println("Bar...");
	}
}
class Bar1 extends Bar implements Serializable {
	public Bar1() {
		System.out.println("Bar1...");
	}
}
class Bar2 extends Bar1 {
	public Bar2() {
		System.out.println("Bar2...");
	}
}