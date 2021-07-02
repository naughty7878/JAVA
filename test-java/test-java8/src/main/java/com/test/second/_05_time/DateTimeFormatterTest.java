package com.test.second._05_time;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

public class DateTimeFormatterTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        ExecutorService pool = Executors.newFixedThreadPool(8);

        List<Future<LocalDate>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Future<LocalDate> future = pool.submit(new Callable<LocalDate>() {
                @Override
                public LocalDate call() throws Exception {
                    return LocalDate.parse("20201211", formatter);
                }
            });
            list.add(future);
        }

        for (Future<LocalDate> dateFuture : list) {
            System.out.println("dateFuture.get() = " + dateFuture.get().toString());
        }

        pool.shutdown();
    }

    @Test
    public void test1() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter basicIsoDate = DateTimeFormatter.BASIC_ISO_DATE;

        String format1 = now.format(basicIsoDate);
        System.out.println("format1 = " + format1);

        String format2 = now.format(DateTimeFormatter.ISO_DATE_TIME);
        System.out.println("format2 = " + format2);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String format3 = now.format(dateTimeFormatter);
        System.out.println("format3 = " + format3);

        LocalDateTime parse = LocalDateTime.parse(format3, dateTimeFormatter);
        System.out.println("parse = " + parse);
    }

}
