package com.test.test;

import com.test.singleton.Singleton04;

import java.util.concurrent.*;

public class TestSingleton04 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        Singleton04 s1 = Singleton04.getInstance();
//        Singleton04 s2 = Singleton04.getInstance();
//
//        System.out.println(s1 == s2);
//        System.out.println(s1);
//        System.out.println(s2);

        // 测试线程安全问题
        Callable<Singleton04> c = new Callable<Singleton04>() {
            public Singleton04 call() throws Exception {
                return Singleton04.getInstance();
            }
        };

        ExecutorService es = Executors.newFixedThreadPool(2);
        Future<Singleton04> f1 = es.submit(c);
        Future<Singleton04> f2 = es.submit(c);

        Singleton04 s1 = f1.get();
        Singleton04 s2 = f2.get();

        System.out.println(s1 == s2);
        System.out.println(s1);
        System.out.println(s2);

        es.shutdown();

    }
}
