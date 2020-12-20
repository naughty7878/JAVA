package com.test.singleton;

/**
 * 在内部类被加载和初始化时，才创建INSTANCE实例对象
 * 静态内部类不会自动随着外部类的加载和初始化而初始化，它是要独立去加载和初始化的
 * 因为是在内部类加载和初始化时，创建的，因此是线程安全的
 */
public class Singleton06 {

    private Singleton06(){}

    private static class Inner{
        private static Singleton06 instance = new Singleton06();
    }

    public Singleton06 getInstance(){
        return Inner.instance;
    }
}
