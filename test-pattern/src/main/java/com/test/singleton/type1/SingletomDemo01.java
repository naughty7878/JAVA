package com.test.singleton.type1;

public class SingletomDemo01 {

    public static void main(String[] args) {
        Singleton instatnce1 = Singleton.getInstance();
        Singleton instatnce2 = Singleton.getInstance();
        System.out.println(instatnce1.hashCode());
        System.out.println(instatnce2.hashCode());
        System.out.println(instatnce1 == instatnce2);
    }

}


// 饿汉式（静态变量）
class Singleton {

    // 1.构造器私有化，外部不能new
    private Singleton() {
    }
    // 2.本类内部创建对象实例
    private final static Singleton instance = new Singleton();
    // 3.提供一个公有的静态方法，返回实例对象
    public static Singleton getInstance() {
        return instance;
    }
}