package com.test.concurrent.pool.pool.demo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *  * @Date：2021/2/25 13:15
 **/
public class ConditionDemo {
    final static ReentrantLock reentrantLock = new ReentrantLock();
    final static Condition condition = reentrantLock.newCondition();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> t1(), "thread1");
        Thread t2 = new Thread(() -> t2(), "thread2");

        t1.setPriority(1);
        t1.start();
        //并不一定会等优先级高的线程先执行
        t2.setPriority(10);
        t2.start();
    }

    public static void t1() {
        reentrantLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "在等待被唤醒");
//                    Thread.currentThread().interrupt();
            //如果interrupted,则抛出异常
            //调用await方法后，将当前线程加入Condition等待队列中。当前线程释放锁。否则别的线程就无法拿到锁而发生死锁。
            // 自旋(while)挂起，不断检测节点是否在同步队列中了，如果是则尝试获取锁，否则挂起。
            // 当线程被signal方法唤醒，被唤醒的线程将从await()方法中的while循环中退出来，然后调用acquireQueued()方法竞争同步状态。
            //https://www.cnblogs.com/gemine/p/9039012.html

//            condition.await();

            //等待指定时间，此时不唤醒，则会在5秒后唤醒
//            condition.await(5,TimeUnit.SECONDS);

            //返回值表示剩余时间，如果在nanosTimesout之前唤醒，那么返回值 = nanosTimeout - 消耗时间，
            // 如果返回值 <= 0 ,则可以认定它已经超时了。
            long nanos = TimeUnit.SECONDS.toNanos(50);
            System.out.println(nanos);
            long x = condition.awaitNanos(nanos);
            System.out.println(x);


            System.out.println(Thread.currentThread().getName() + "恢复执行了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
            System.out.println("thead 1 执行完了");
        }
    }

    public static void t2() {
        reentrantLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "抢到了锁");
            //调用Condition的signal()方法，将会唤醒在等待队列中等待最长时间的节点（条件队列里的首节点），
            // 在唤醒节点前，会将节点移到同步队列中
            //唤醒后await会退出while循环，随后加入到同步状态的竞争当中去。
            // 成功获取到竞争的线程则会返回到await方法之前的状态。
//            condition.signal();
            System.out.println(Thread.currentThread().getName() + "唤醒其它等待的线程");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }

}


