package com.hd.demo;

/**
 * 使用同步机制将单例模式中的懒汉式改为线程安全的
 */
public class SingetonDemo {

    public static void main(String[] args) {
        new Thread() {
            @Override
            public void run() {
                Bank instance = Bank.getInstance();
                System.out.println(instance);
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                Bank instance = Bank.getInstance();
                System.out.println(instance);
            }
        }.start();
    }
}

class Bank {
    private Bank() {
    }

    private static Bank instance = null;

    public static Bank getInstance() {
        // 方式一：效率稍差
//        synchronized (Bank.class) {
//            if (instance == null) {
//                instance = new Bank();
//            }
//            return instance;
//        }

        // 方式二：效率高
        if (instance == null) {
            synchronized (Bank.class) {
                instance = new Bank();
            }
        }
        return instance;
    }
}
