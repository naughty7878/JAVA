package com.test.concurrent.tools.countdown.demo;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(3);

        new Thread(new Task(semaphore, 3,"线程-"+1)).start();
        Thread.sleep(200);
        new Thread(new Task(semaphore, 1,"线程-"+2)).start();
        Thread.sleep(200);
        new Thread(new Task(semaphore, 1,"线程-"+3)).start();
        Thread.sleep(200);
        new Thread(new Task(semaphore, 2,"线程-"+4)).start();
    }

    static class Task extends Thread{
        Integer num;
        Semaphore semaphore;

        public Task(Semaphore semaphore,Integer num, String tname){
            super(tname);
            this.semaphore = semaphore;
            this.num = num;
            //this.setName(tname);
        }

        @Override
        public void run() {
            try {
                //semaphore.acquireUninterruptibly();
                semaphore.acquire(num);//获取公共资源

                System.out.println(Thread.currentThread().getName()+":aquire() at time:"+System.currentTimeMillis());
                Thread.sleep(10000);

                semaphore.release(num);

                /*if(semaphore.tryAcquire(500,TimeUnit.MILLISECONDS)){
                    System.out.println(Thread.currentThread().getName()+":aquire() at time:"+System.currentTimeMillis());
                    Thread.sleep(5000);
                    semaphore.release();//释放公共资源
                }else{
                    fallback();
                }*/

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void fallback(){
            System.out.println("降级");
        }
    }
}
