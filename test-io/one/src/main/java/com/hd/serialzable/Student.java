package com.hd.serialzable;

import java.io.Serializable;
import java.util.ArrayList;

public class Student implements Serializable {

	private String id;

	private String name;

	// transient 该元素不会进行jvm默认的序列化
	// 可以自己完成序列化
	private transient int age;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", age=" + age + "]";
	}

	public Student() {
		super();
	}

	public Student(String id, String name, int age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
	}

	// 参照ArrayList自己序列化，ArrayList源码中也用到transient
	private void writeObject(java.io.ObjectOutputStream s) throws java.io.IOException {
		// 1、将jvm默认能序列化中的元素序列化
		s.defaultWriteObject();

		// 2、自己完成age序列化
		s.writeInt(age);
	}

	// 参照ArrayList自己反序列化，ArrayList源码中也用到transient
	private void readObject(java.io.ObjectInputStream s) throws java.io.IOException, ClassNotFoundException {

		// 1、将jvm默认能序列化中的元素反序列化
		s.defaultReadObject();

		// 2、自己完成age反序列化
		this.age = s.readInt();
	}
}
