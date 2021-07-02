package com.test.second._01_lambda;

import org.junit.Test;

import java.util.function.Consumer;
import java.util.function.Function;

public class LambdaTest02 {

    // 语法格式一：无参数，无返回值
    @Test
    public void test1() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World!");
            }
        };

        System.out.println("-------------------");

        Runnable r2 = () -> {
            System.out.println("Hello World!");
        };

        new Thread(r2).start();
    }

    // 语法格式二：有一个参数
    @Test
    public void test2() {
        Consumer<String> com = (String x) -> {
            System.out.println(x);
        };
        System.out.println("-------------------");
        com.accept("hello world");
    }

    // 语法格式三：只有一个参数，小括号可以省略
    @Test
    public void test3() {
        Consumer<String> com = x -> {
            System.out.println(x);
        };
        System.out.println("-------------------");
        com.accept("hello world");
    }

    // 语法格式四：多条语句
    @Test
    public void test4() {
        Consumer<String> com = x -> {
            System.out.println(1+x);
            System.out.println(2+x);
            System.out.println(3+x);
        };
        System.out.println("-------------------");
        com.accept("hello world");
    }

    // 语法格式五：只有一条语句，retuan 和 大括号都可以省略
    @Test
    public void test5() {
        Function<Integer, Integer> function = x -> x + 1;
        System.out.println("-------------------");
        Integer apply = function.apply(1);
        System.out.println("apply = " + apply);
    }

    // 语法格式六：参数列表的参数声明 可以省略
    @Test
    public void test6() {
        Consumer<String> com = x -> System.out.println(x);
        System.out.println("-------------------");
        com.accept("hello world");
    }

}
