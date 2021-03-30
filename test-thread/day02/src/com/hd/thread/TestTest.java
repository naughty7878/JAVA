package com.hd.thread;


import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class NewWindow implements Callable {

    private int ticket = 100;

    private Lock lock = new ReentrantLock();

    @Override
    public Object call() throws Exception {
        while (true) {
            lock.lock();
            try {
                if (ticket > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "窗口：售票，票号为：" + ticket);
                    ticket--;
                } else {
                    System.out.println(Thread.currentThread().getName() + "解锁break");
                    break;
                }
            } finally {
                if(ticket <= 0) {
                    System.out.println(Thread.currentThread().getName() + "解锁" + ticket);
                }
                lock.unlock();
            }

        }
        return ticket;
    }
}


public class TestTest {
    public static void main(String[] args) {
        System.out.println("多线程");

        ExecutorService service = Executors.newFixedThreadPool(5);


        NewWindow w = new NewWindow();

        FutureTask futureTask1 = new FutureTask(w);
        FutureTask futureTask2 = new FutureTask(w);
        FutureTask futureTask3 = new FutureTask(w);
        FutureTask futureTask4 = new FutureTask(w);
        FutureTask futureTask5 = new FutureTask(w);

        service.submit(futureTask1);
        service.submit(futureTask2);
        service.submit(futureTask3);
        service.submit(futureTask4);
        service.submit(futureTask5);

        service.shutdown();
//        Thread t1 = new Thread(futureTask1);
//        Thread t2 = new Thread(futureTask2);
//        Thread t3 = new Thread(futureTask3);
//        Thread t4 = new Thread(futureTask4);
//        Thread t5 = new Thread(futureTask5);
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

        try {
            Object o1 = futureTask1.get();
            Object o2 = futureTask2.get();
            Object o3 = futureTask3.get();
            Object o4 = futureTask4.get();
            Object o5 = futureTask5.get();
            System.out.println("窗口1返回时：" + o1);
            System.out.println("窗口2返回时：" + o2);
            System.out.println("窗口3返回时：" + o3);
            System.out.println("窗口4返回时：" + o4);
            System.out.println("窗口5返回时：" + o5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
