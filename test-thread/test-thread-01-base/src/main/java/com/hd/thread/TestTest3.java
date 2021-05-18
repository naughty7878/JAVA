package com.hd.thread;


import java.util.concurrent.*;

class Window3 extends Thread {

    static int ticket = 100;

    @Override
    public void run() {
        while (true) {
            if(ticket > 0) {
                synchronized (Window3.class){
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(ticket > 0) {
                        System.out.println(Thread.currentThread().getName() + "卖票，票号为：" + ticket);
                        ticket --;
                    }
                }
            }else {
                break;
            }
        }
    }

    public static void main(String[] args) {
        Window3 window1 = new Window3();
        Window3 window2 = new Window3();
        Window3 window3 = new Window3();

        window1.setName("窗口1");
        window2.setName("窗口2");
        window3.setName("窗口3");

        window1.start();
        window2.start();
        window3.start();
    }
}

class Window33 implements Runnable{

    int ticket = 100;

    @Override
    public void run() {
        while (true) {
            if(ticket > 0) {
                synchronized (Window33.class){
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(ticket > 0) {
                        System.out.println(Thread.currentThread().getName() + "卖票，票号为：" + ticket);
                        ticket --;
                    }
                }
            }else {
                break;
            }
        }
    }



    public static void main(String[] args) {
        Window33 w33 = new Window33();
        Thread window1 = new Thread(w33);
        Thread window2 = new Thread(w33);
        Thread window3 = new Thread(w33);

        window1.setName("窗口1");
        window2.setName("窗口2");
        window3.setName("窗口3");

        window1.start();
        window2.start();
        window3.start();
    }
}

class Window333 implements Callable<Integer>{

    int ticket = 100;

    @Override
    public Integer call() throws Exception {

        while (true) {
            if(ticket > 0) {
                synchronized (Window333.class){
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(ticket > 0) {
                        System.out.println(Thread.currentThread().getName() + "卖票，票号为：" + ticket);
                        ticket --;
                    }
                }
            }else {
                break;
            }
        }

        return ticket;
    }

    public static void main(String[] args) {
        Window333 w333 = new Window333();
        FutureTask<Integer> futureTask1 = new FutureTask(w333);
        FutureTask<Integer> futureTask2 = new FutureTask(w333);
        FutureTask<Integer> futureTask3 = new FutureTask(w333);

        Thread window1 = new Thread(futureTask1);
        Thread window2 = new Thread(futureTask2);
        Thread window3 = new Thread(futureTask3);

        window1.setName("窗口1");
        window2.setName("窗口2");
        window3.setName("窗口3");

        window1.start();
        window2.start();
        window3.start();

        try {
            window1.join();
            window2.join();
            window3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            Integer ticket1 = futureTask1.get();
            Integer ticket2 = futureTask1.get();
            Integer ticket3 = futureTask1.get();

            System.out.println("ticket1 === " + ticket1);
            System.out.println("ticket2 === " + ticket2);
            System.out.println("ticket3 === " + ticket3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}


class Window3333 implements Callable<Integer>{

    int ticket = 100;

    @Override
    public Integer call() throws Exception {

        while (true) {
            if(ticket > 0) {
                synchronized (Window3333.class){
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(ticket > 0) {
                        System.out.println(Thread.currentThread().getName() + "卖票，票号为：" + ticket);
                        ticket --;
                    }
                }
            }else {
                break;
            }
        }

        return ticket;
    }

    public static void main(String[] args) {
        Window3333 w3333 = new Window3333();
        FutureTask<Integer> futureTask1 = new FutureTask(w3333);
        FutureTask<Integer> futureTask2 = new FutureTask(w3333);
        FutureTask<Integer> futureTask3 = new FutureTask(w3333);

        ExecutorService service = Executors.newFixedThreadPool(3);

        service.submit(futureTask1);
        service.submit(futureTask2);
        service.submit(futureTask3);

        try {
            Integer ticket1 = futureTask1.get();
            Integer ticket2 = futureTask1.get();
            Integer ticket3 = futureTask1.get();

            System.out.println("ticket1 === " + ticket1);
            System.out.println("ticket2 === " + ticket2);
            System.out.println("ticket3 === " + ticket3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        service.shutdown();
    }
}


public class TestTest3 {


}
