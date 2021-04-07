package com.test.jvm;

import org.openjdk.jol.info.ClassLayout;

/**
 * 启用指针压缩:-XX:+UseCompressedOops，禁止指针压缩:-XX:-UseCompressedOops
 */
public class ObjectTest {
    public static void main(String[] args) {

        // 普通对象
        Object obj = new Object();
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());

        // 数组对象
        int[] arr = new int[1];
        System.out.println(ClassLayout.parseInstance(arr).toPrintable());

        // person 对象
        Person p = new Person();
        System.out.println(ClassLayout.parseInstance(p).toPrintable());


        // 引用对象数组
        Person[] arr2 = new Person[10];
        System.out.println(ClassLayout.parseInstance(arr2).toPrintable());

        // Long 对象
        Long l = new Long(1);
        System.out.println(ClassLayout.parseInstance(l).toPrintable());

        // Integer 对象
        Integer i = new Integer(1);
        System.out.println(ClassLayout.parseInstance(i).toPrintable());

    }
}

class Person{
    private int id;
    private String name;

    private boolean flag;

}