package com.test.jvm;

import org.junit.Test;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class CreateObjectTest {

    // 1、new 关键字
    @Test
    public void testNew(){
        Employee emp = new Employee();
    }

    // 2、Class的newInstance() 反射的方式
    @Test
    public void testNewINstance() throws IllegalAccessException, InstantiationException {
        // 通过反射传教对象
        Employee emp = Employee.class.newInstance();
        System.out.println(emp);
    }

    // 3、Constructor的newInstance(XXX)，也是反射的方式
    @Test
    public void testConstructor() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        // 通过反射得到构造器
        Constructor<Employee> constructor = Employee.class.getConstructor();
        // 通过构造器创建对象
        Employee emp = constructor.newInstance();
        System.out.println(emp);
    }

    // 4、使用clone()（克隆）
    @Test
    public void testClone() throws CloneNotSupportedException {
        Employee emp = new Employee();
        emp.setId(1);
        emp.setName("小白");
        System.out.println(emp.hashCode());

        // 克隆对象
        Object emp2 = emp.clone();
        System.out.println(emp2);
        System.out.println(emp2.hashCode());
    }


    // 5、使用反序列化
    @Test
    public void testSeri() throws IOException, ClassNotFoundException {
        Employee emp = new Employee();
        emp.setId(1);
        emp.setName("小白");
        System.out.println(emp.hashCode());

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("emp.bat"));
        ObjectInputStream ois  = new ObjectInputStream(new FileInputStream("emp.bat"));

        oos.writeObject(emp);
        // 反序列化获取对象
        Employee emp2 = (Employee) ois.readObject();
        System.out.println(emp2);
        System.out.println(emp2.hashCode());

        // 关闭流
        ois.close();
        oos.close();
    }
}
