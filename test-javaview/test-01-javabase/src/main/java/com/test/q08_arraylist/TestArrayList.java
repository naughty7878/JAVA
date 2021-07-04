package com.test.q08_arraylist;

import org.junit.Test;
import org.omg.CORBA.SetOverrideType;

import java.util.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

/**
 * ArrayList是线程不安全,请编写一个不安全的案例并给出解决方案
 * 方案1：Vector
 * 方案2：
 */
public class TestArrayList {


//    static volatile List<String> list = new ArrayList<>(); // 非安全
//    static volatile List<String> list = new Vector<>(); // 安全
//    static volatile List<String> list = Collections.synchronizedList(new ArrayList<>()); // 安全
//    static volatile List<String> list = new CopyOnWriteArrayList<>(); // 安全
    static volatile Set<String> list = new CopyOnWriteArraySet<String>(); // 安全
    static CountDownLatch countDownLatch = new CountDownLatch(100);

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {

                for (int j = 0; j < 1000; j++) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    String str = Thread.currentThread().getName() + "-----" + j;
                    boolean add = list.add(str);
                    if(add == false) {
                        System.out.println(Thread.currentThread().getName() + "--添加失败");
                    }
                }
                System.out.println(Thread.currentThread().getName() + "--添加完成");
                countDownLatch.countDown();
            }).start();

        }
        countDownLatch.await();
        System.out.println(list.size());
//        cyclicBarrier.await();

    }

    @Test
    public void test1(){
//        List<String> list = new ArrayList<>();
        Map<String, String> map = new HashMap<>();

        String s = map.putIfAbsent("key1", "val1");
        System.out.println(s);
        System.out.println(map.size());

        String value = map.computeIfAbsent("key1", a -> a);
        System.out.println(value);
        System.out.println(map.size());



        String orDefault = map.getOrDefault("b", "b");
        System.out.println(orDefault);
        System.out.println(map);
    }
}
