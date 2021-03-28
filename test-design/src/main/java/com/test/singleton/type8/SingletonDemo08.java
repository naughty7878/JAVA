package com.test.singleton.type8;

public class SingletonDemo08 {

    public static void main(String[] args) {
        Singleton instance = Singleton.INSTANCE;
        Singleton instance2 = Singleton.INSTANCE;
        // true
        System.out.println(instance == instance2);
        System.out.println(instance.hashCode());
        System.out.println(instance2.hashCode());
        instance.sayOK();
    }
}

// 使用枚举，可以实现单例，推荐
enum Singleton {

    // 属性
    INSTANCE;

    public void sayOK() {
        System.out.println("ok~");
    }
}