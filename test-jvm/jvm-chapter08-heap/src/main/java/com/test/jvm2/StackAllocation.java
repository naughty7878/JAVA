package com.test.jvm2;

/**
 * 栈上分配测试
 * 参数：-Xmx256m -Xms256m -XX:-DoEscapeAnalysis -XX:+PrintGCDetails
 *
 * DoEscapeAnalysis默认开启
 * -XX:-DoEscapeAnalysis：关闭逃逸分析
 */
public class StackAllocation {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        for (int i = 0; i < 100000000; i++) {
            alloc();
        }
        // 查看执行时间
        long end = System.currentTimeMillis();
        System.out.println("花费的时间为： " + (end - start) + " ms");
        // 为了方便查看堆内存中对象个数，线程sleep
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }

    private static void alloc() {
        User user = new User();//未发生逃逸
    }

    static class User {
        String name = "xx";
        Integer age = 18;
    }
}