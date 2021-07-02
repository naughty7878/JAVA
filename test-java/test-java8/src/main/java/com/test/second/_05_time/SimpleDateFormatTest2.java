package com.test.second._05_time;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

public class SimpleDateFormatTest2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>(){
            @Override
            protected SimpleDateFormat initialValue() {
                return new SimpleDateFormat("yyyyMMdd");
            }
        };

//        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

        ExecutorService pool = Executors.newFixedThreadPool(8);

        List<Future<Date>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Future<Date> future = pool.submit(new Callable<Date>() {
                @Override
                public Date call() throws Exception {
                    return threadLocal.get().parse("20211221");
                }
            });
            list.add(future);
        }

        for (Future<Date> dateFuture : list) {
            System.out.println("dateFuture.get() = " + dateFuture.get().toString());
        }

        pool.shutdown();
    }


}
