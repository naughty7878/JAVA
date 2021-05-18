package com.hd.thread;

public class ThreadOrder {

    static volatile int num = 1;

    public static void main(String[] args) throws InterruptedException {

        Thread a = new Thread(){

            @Override
            public void run() {
                while (num != 1) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("这是A线程");
                num = 2;
            }
        };

        Thread b = new Thread(){
            @Override
            public void run() {
                while (num != 2) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("这是B线程");
                num = 3;
            }
        };

        Thread c = new Thread(){
            @Override
            public void run() {
                while (num != 3) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("这是C线程");
            }
        };

        // 方案一：使用join方法
//        a.start();
//        a.join();
//        b.start();
//        b.join();
//        c.start();
//        c.join();

        // 方案二：使用单线程连接池
//        ExecutorService service = Executors.newSingleThreadExecutor();
//        service.execute(a);
//        service.execute(b);
//        service.execute(c);

        a.start();
        b.start();
        c.start();
    }
}
