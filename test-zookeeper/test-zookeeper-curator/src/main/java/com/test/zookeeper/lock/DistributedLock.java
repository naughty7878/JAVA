package com.test.zookeeper.lock;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class DistributedLock {

    private static String zookeeperConnectionString = "120.78.189.168:12181,120.78.189.168:12182,120.78.189.168:12183";

    private static int sessionTimeout = 30 * 2000;

    private static CuratorFramework client;

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    private static ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void init() throws IOException, InterruptedException {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        client = CuratorFrameworkFactory.newClient(zookeeperConnectionString, retryPolicy);
        client.start();
    }


    // 分布式锁：公平锁
    @Test
    public void test2() throws Exception {
        InterProcessMutex lock = new InterProcessMutex(client, "/distributedLock");
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    lock.acquire();
                    // do some work inside of the critical section here
                    System.out.println(Thread.currentThread().getName() + "：获取的锁");
                    Thread.sleep(10000);
                    countDownLatch.countDown();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        lock.release();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, "线程-" + i).start();
        }

        countDownLatch.await();
        System.out.println("所有任务执行完成");
    }




    // 分布式锁：读写锁
    @Test
    public void test1() throws Exception {
        InterProcessReadWriteLock lock = new InterProcessReadWriteLock(client, "/distributedLock");
        InterProcessMutex readLock = lock.readLock();
        InterProcessMutex writeLock = lock.writeLock();
        CountDownLatch countDownLatch = new CountDownLatch(10);
        AtomicInteger atomicInteger = new AtomicInteger();
        for (int i = 0; i < 10; i++) {

            new Thread(() -> {
                int flage = atomicInteger.getAndIncrement();
                try {
                    if(flage % 2 == 0) {
                        readLock.acquire();
                    }else {
                        writeLock.acquire();
                    }
                    // do some work inside of the critical section here
                    if(flage % 2 == 0) {
                        System.out.println(Thread.currentThread().getName() + "：获取的读锁\t" + new Date());
                    }else {
                        System.out.println(Thread.currentThread().getName() + "：获取的写锁\t" + new Date());
                    }
                    Thread.sleep(2000);
                    countDownLatch.countDown();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if(flage % 2 == 0) {
                            readLock.release();
                        }else {
                            writeLock.release();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, "线程-" + i).start();
        }

        countDownLatch.await();
        System.out.println("所有任务执行完成");
    }
}
