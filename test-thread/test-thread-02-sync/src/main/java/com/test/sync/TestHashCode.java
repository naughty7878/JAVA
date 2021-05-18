package com.test.sync;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

    public class TestHashCode {
        public static void main(String[] args) throws InterruptedException {
            TimeUnit.SECONDS.sleep(5);
            Object obj = new Object();

            synchronized (obj) {
                System.out.println("t1 获取偏向锁成功，开始执行代码");
                System.out.println(ClassLayout.parseInstance(obj).toPrintable());
                obj.hashCode();
                try {
                    //等待对象头信息改变
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("hashCode() 调用后");
                System.out.println(ClassLayout.parseInstance(obj).toPrintable());
            }
        }
    }
