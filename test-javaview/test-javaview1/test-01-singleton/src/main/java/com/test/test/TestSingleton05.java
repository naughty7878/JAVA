package com.test.test;

import com.test.singleton.Singleton04;
import com.test.singleton.Singleton05;

import java.util.concurrent.*;

public class TestSingleton05 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 测试线程安全问题
        Callable<Singleton05> c = new Callable<Singleton05>() {
            public Singleton05 call() throws Exception {
                return Singleton05.getInstance();
            }
        };

        ExecutorService es = Executors.newFixedThreadPool(2);
        Future<Singleton05> f1 = es.submit(c);
        Future<Singleton05> f2 = es.submit(c);

        Singleton05 s1 = f1.get();
        Singleton05 s2 = f2.get();

        System.out.println(s1 == s2);
        System.out.println(s1);
        System.out.println(s2);

        es.shutdown();

    }
}
