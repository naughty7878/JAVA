package com.test.sync;

public class Synchronized {

    public static volatile boolean flag = true;
    public static volatile int num = 0;

    //synchronized关键字可放于方法返回值前任意位置，本示例应当注意到sleep()不会释放对监视器的锁定
    //实例方法
    public synchronized void instanceMethod() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " ==== instanceMethod" + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //静态方法
    public synchronized static void staticMethod() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " ==== staticMethod" + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void thisMethod() {
        //this对象
        synchronized (this) {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " ==== thisMethod" + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void classMethod() {
        //class对象
        synchronized (Synchronized.class) {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " ==== classMethod" + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void anyObject() {
        //任意对象
        synchronized ("anything") {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " ==== anyObject" + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

//    public static void main(String[] args) {
//        final Synchronized syn = new Synchronized();
//        for (int i = 0; i < 10; i++) {
//            new Thread() {
//                @Override
//                public void run() {
//                    syn.thisMethod();
//                }
//            }.start();
//            new Thread() {
//                @Override
//                public void run() {
//                    syn.instanceMethod();
//                }
//            }.start();
//        }
//    }

    public static void main(String[] args) {
        final Synchronized syn = new Synchronized();
        for (int i = 0; i < 10; i++) {
            new Thread() {
                @Override
                public void run() {
                    syn.staticMethod();
                }
            }.start();
            new Thread() {
                @Override
                public void run() {
                    syn.classMethod();
                }
            }.start();
        }
    }
}