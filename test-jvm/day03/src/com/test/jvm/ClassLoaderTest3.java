package com.test.jvm;

public class ClassLoaderTest3 {
    public static void main(String[] args) throws ClassNotFoundException {

        //1.Class.forName().getClassLoader()
        ClassLoader classLoader = Class.forName("java.lang.String").getClassLoader();
        System.out.println(classLoader);// String 类由启动类加载器加载，我们无法获取

        //2.Thread.currentThread().getContextClassLoader()
        ClassLoader threadClassLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(threadClassLoader);

        //3.ClassLoader.getSystemClassLoader().getParent()
        ClassLoader parentClassLoader = ClassLoader.getSystemClassLoader().getParent();
        System.out.println(parentClassLoader);

    }
}

















