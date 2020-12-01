package com.hd.thread;


import java.util.concurrent.*;

class Window2 implements Callable {
    private int ticket = 100;

    @Override
    public Object call() throws Exception {
        while (true) {
            synchronized (this) {

                if(ticket > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "卖票，票号为：" + ticket);
                    ticket --;
                } else {
                    break;
                }
            }
        }
        return ticket;
    }
}
public class TestTest2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Window2 w = new Window2();
        FutureTask f1 = new FutureTask(w);
        FutureTask f2 = new FutureTask(w);
        FutureTask f3 = new FutureTask(w);
        FutureTask f4 = new FutureTask(w);
        FutureTask f5 = new FutureTask(w);


        ExecutorService executorService = Executors.newFixedThreadPool(5);

        Future<?> submit1 = executorService.submit(f1);
        Future<?> submit2 = executorService.submit(f2);
        Future<?> submit3 = executorService.submit(f3);
        Future<?> submit4 = executorService.submit(f4);
        Future<?> submit5 = executorService.submit(f5);

//        Thread t1 = new Thread(f1);
//        Thread t2 = new Thread(f2);
//        Thread t3 = new Thread(f3);
//        Thread t4 = new Thread(f4);
//        Thread t5 = new Thread(f5);
//
//        t1.setName("窗口1");
//        t2.setName("窗口2");
//        t3.setName("窗口3");
//        t4.setName("窗口4");
//        t5.setName("窗口5");
//
//        t1.start();
//        t2.start();
//        t3.start();
//        t4.start();
//        t5.start();
//
//        Object o1 = f1.get();
//        Object o2 = f2.get();
//        Object o3 = f3.get();
//        Object o4 = f4.get();
//        Object o5 = f5.get();
        System.out.println("o1:" + f1.get() );
        System.out.println("o2:" + f2.get() );
        System.out.println("o3:" + f3.get() );
        System.out.println("o4:" + f4.get() );
        System.out.println("o5:" + f5.get() );


        executorService.shutdown();
    }
}
