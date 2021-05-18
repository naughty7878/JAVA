package com.test.counter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Counter {

    private int num = 0;
    AtomicInteger atomicNum = new AtomicInteger(0);

    public static void main(String[] args) {
        final Counter cas = new Counter();
        List<Thread>  list = new ArrayList<>(600);
        for (int i = 0; i < 100; i++ ){
            Thread t = new Thread(){
                @Override
                public void run() {
                    for (int j = 0; j < 10000; j ++){
                        cas.count();
                        cas.safeCount();
                    }
                }
            };
            list.add(t);
        }

        for (Thread t : list) {
            t.start();
        }
        // 等待所有线程执行完成
        for (Thread t : list) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(cas.num);
        System.out.println("num == " + cas.num);
        System.out.println("aNum == " + cas.atomicNum);
    }

    /**
     * 使用CAS实现线程安全计数器
     */
    private void safeCount(){
        for(;;){
            int i = atomicNum.get();
            boolean suc = atomicNum.compareAndSet(i, ++i);
            if(suc) {
                break;
            }
        }
    }

    /**
     * 非线程安全计数器
     */
    private void count(){
        num ++;
    }
}
