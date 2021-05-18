package com.test.concurrent.tools;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author ：图灵-杨过
 * @date：2019/7/11
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description :可用于流量控制，限制最大的并发访问数
 */
public class SemaphoreRunner {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);
        for (int i=0;i<10;i++){
            new Thread(new Task(semaphore,"yangguo+"+i)).start();
        }
    }

    static class Task extends Thread{
        Semaphore semaphore;

        public Task(Semaphore semaphore,String tname){
            super(tname);
            this.semaphore = semaphore;
            //this.setName(tname);
        }

        @Override
        public void run() {
            try {
                //semaphore.acquireUninterruptibly();
                semaphore.acquire();//获取公共资源

                System.out.println(Thread.currentThread().getName()+":aquire() at time:"+System.currentTimeMillis());
                Thread.sleep(5000);

                semaphore.release();

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
