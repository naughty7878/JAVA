package com.test.jmm.test;

import com.test.jmm.util.UnsafeInstance;

public class ReorderTest {

    private static int x = 0, y = 0;
    private static int a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        for (;;){
            i++;
            x = 0; y = 0;
            a = 0; b = 0;
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    a = 1;
                    x = b;
                }
            });

            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    b = 1;
                    y = a;
                }
            });

            t1.start();
            t2.start();
            t1.join();
            t2.join();

            // 只有重排序的情况下，才会出现 0，0的结果
            // 即x = b ， y = a 比 a = 1，b = 1 先执行的情况下才会出现
            if(x == 0 && y == 0) {
                String result = "第" + i + "次\n x=" + x + ", y=" + y + ", a=" + a + ", b=" + b;
                System.out.println(result);
                break;
            }
        }
    }
}
