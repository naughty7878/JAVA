package com.test.singleton;

/**
 * 懒汉式：
 *   延迟创建这个实例对象
 *
 * （1）构造器私有化
 * （2）用一个静态变量保存这个实例
 * （3）提供一个静态方法，获取这个实例对象
 */
public class Singleton04 {
    private static Singleton04 instance;

    private Singleton04(){}

    public static Singleton04 getInstance() {
        if(instance == null) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            instance = new Singleton04();
        }
        return instance;
    }
}
