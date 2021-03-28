package com.test.concurrent.lock.demo;

import com.sun.deploy.util.UpdateCheck;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 如何使用:
 * 创建一个读写锁* 它是一个读写融为一体的锁，在使用的时候，需要转换*
 * private ReentrantReadWriteLock rwLock=new ReentrantReadWriteLock();复制代码
 * 获取读锁和释放读锁
 * // 获取读锁rwLock.readLock().lock();// 释放读锁rwLock.readLock().unlock();复制代码
 * 获取写锁和释放写锁
 * // 创建一个写锁rwLock.writeLock().lock();// 写锁 释放rwLock.writeLock().unlock();复 制代码
 * Java中的读写锁:ReentrantReadWriteLock
 */
public class ReentrantReadWriteLockTest {

    static volatile int flag = -1;

    @Test
    public void test1() {
//        System.out.println((1 << 16) - 1);
//        System.out.println(Integer.toBinaryString(65538 & (1 << 16) - 1));

        System.out.println(Integer.toBinaryString(16));
        System.out.println(Integer.toHexString(16));
        System.out.println(0b101);//二进制:5 （0b开头的）
        System.out.println(0e1011);//0.0
        System.out.println(011);//八进制:9  (0开头的)
        System.out.println(11);//十进制:11
        System.out.println(0x11C);//十六进制:284 （0x开头的）

    }

    public static void main(String[] args) throws InterruptedException {

        Thread.sleep(3000);
        new Thread() {
            @Override
            public void run() {
                Cache.put("1", "a");
            }
        }.start();
        Thread.sleep(1000);
        new Thread() {
            @Override
            public void run() {
                Cache.get("1");
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                Cache.get("1");
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                Cache.get("1");
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                Cache.get("1");
            }
        }.start();
        Thread.sleep(1000);
        new Thread() {
            @Override
            public void run() {
                Cache.put("2", "b");
            }
        }.start();


//        new Thread(){
//            @Override
//            public void run() {
//                Cache.put("2", "b");
//            }
//        }.start();
//        Cache.put("1", "a");
//        Cache.put("2", "b");
//        Cache.put("3", "c");
//        Cache.get("1");
//        Cache.get("2");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            flag = scanner.nextInt();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Cache {
    static Map<String, Object> map = new HashMap<>();
    static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    static Lock r = rwl.readLock();
    static Lock w = rwl.writeLock();


    // 获取一个key对应的value
    public static final Object get(String key) {
        r.lock();
        try {
            try {
                while (ReentrantReadWriteLockTest.flag != 1) {
                    System.out.println("flag = " + ReentrantReadWriteLockTest.flag);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return map.get(key);
        } finally {
            r.unlock();
        }
    }

    // 设置key对应的value，并返回旧的value
    public static final Object put(String key, Object value) {
        w.lock();
        try {

            return map.put(key, value);
        } finally {
            w.unlock();
        }
    }

    // 清空所有的内容
    public static final void clear() {
        w.lock();
        try {
            map.clear();
        } finally {
            w.unlock();
        }
    }
}