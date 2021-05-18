package com.test.counter;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 计算器
 * 计算人数 +1 -1，避免出现ABA问题
 */
public class CounterDemo {

    // 原子标记引用
    AtomicStampedReference<Integer> counter = new AtomicStampedReference<>(0, 0);

    public Integer get(){
        return counter.getReference();
    }

    // 推荐1
    public void increment1(){
        int[] stamp = new int[1];
        while (true) {
            Integer value = counter.get(stamp);
            int newValue = value + 1;
            boolean flag = counter.compareAndSet(value, newValue, stamp[0], stamp[0] + 1);
            if(flag) {
                return;
            }
        }
    }

    // 推荐1
    public void decrease1(){
        int[] stamp = new int[1];
        while (true) {
            Integer value = counter.get(stamp);
            int newValue = value - 1;
            boolean flag = counter.compareAndSet(value, newValue, stamp[0], stamp[0] + 1);
            if(flag) {
                return;
            }
        }
    }

    // 推荐1
    public int increment() {
        while(true) {
            //必须先获取stamp，然后取值，顺序不能反，否则仍然会有ABA的问题
            int stamp = counter.getStamp();
            Integer value = counter.getReference();
            int newValue = value + 1;
            boolean writeOk = counter.compareAndSet(value, newValue, stamp, stamp + 1);
            if(writeOk) {
                return newValue;
            }
        }
    }

    // 推荐1
    public int decrement2() {
        while(true) {
            //必须先获取stamp，然后取值，顺序不能反，否则仍然会有ABA的问题
            int stamp = counter.getStamp();
            Integer value = counter.getReference();
            int newValue = value - 1;
            boolean writeOk = counter.compareAndSet(value, newValue, stamp, stamp + 1);
            if(writeOk) {
                return newValue;
            }
        }
    }
}
