package com.test.concurrent.pool.pool.demo;

import java.util.concurrent.atomic.AtomicInteger;

public class Test {
    // 统计位的大小，即29
    private static final int COUNT_BITS = Integer.SIZE - 3;

    // 线程池可以设置的最大数量	（29个1）
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;


    // ctl 是对线程池的运行状态和线程池中有效线程的数量进行控制的一个字段，
// 它包含两 部分的信息: 线程池的运行状态 (runState) 和线程池内有效线程的数量 (workerCount)，
// 使用了Integer类型来保存，高3位保存runState，低29位保存 workerCount。
// COUNT_BITS 就是29，CAPACITY就是1左移29位减1(29个1)，这个常 量表示workerCount的上限值，大约是5亿
// 初始化work数量，同时初始化线程状态位RUNNING
    private final AtomicInteger ctl = new AtomicInteger(RUNNING | 0);


    // runState 高3位保存
// RUNNING：线程运行中状态，（高3位：111）
    private static final int RUNNING    = -1 << COUNT_BITS;

    // SHUTDOWN：线程运行中状态，（高3位：000）
    private static final int SHUTDOWN   =  0 << COUNT_BITS;

    // STOP：线程运行中状态，（高3位：001）
    private static final int STOP       =  1 << COUNT_BITS;

    // TIDYING：线程运行中状态，（高3位：010）
    private static final int TIDYING    =  2 << COUNT_BITS;

    // TERMINATED：结束状态，（高3位：011）
    private static final int TERMINATED =  3 << COUNT_BITS;

    public static void main(String[] args) {
//        System.out.println( 1 << 31 - 1);
//        System.out.println(Integer.MAX_VALUE);

        int COUNT_BITS = 29;
        System.out.println("COUNT_BITS = " + COUNT_BITS);

        int RUNNING   = -1 << COUNT_BITS;
        System.out.println("RUNNING = " + Integer.toBinaryString(RUNNING));
        System.out.println("RUNNING.length() = " + Integer.toBinaryString(RUNNING).length());

        System.out.println("RUNNING | 0 = " + Integer.toBinaryString(RUNNING | 0));

//        System.out.println(Integer.toBinaryString(2));
//        System.out.println(Integer.toBinaryString(-2));

        System.out.println(RUNNING > SHUTDOWN);
        System.out.println(RUNNING);
        System.out.println(SHUTDOWN);
    }




    @org.junit.Test
    public void test1(){
        //jump from outer loop
        outer:
        for(int i=0;i<5;i++){
            System.out.println("~~~~~~");
            for(int j=0;j<10;j++){
                if(j==5) {
                    break outer;
                }
                System.out.print("*");
            }
            System.out.print("\r\n");
            System.out.println("------");
        }
    }





    // 装箱 和 开箱 ctl 的方法
// 获取 线程池的运行状态 (runState)
    private static int runStateOf(int c)     { return c & ~CAPACITY; }
    // 获取 线程池内有效线程的数量 (workerCount)
    private static int workerCountOf(int c)  { return c & CAPACITY; }
    // 组合 运行状态 (runState) 和 有效线程的数量 (workerCount)
    private static int ctlOf(int rs, int wc) { return rs | wc; }





    /*
     * Bit field accessors that don't require unpacking ctl.
     * These depend on the bit layout and on workerCount being never negative.
     */

    private static boolean runStateLessThan(int c, int s) {
        return c < s;
    }

    private static boolean runStateAtLeast(int c, int s) {
        return c >= s;
    }
    // 判断线程是否在运行
    private static boolean isRunning(int c) {
        return c < SHUTDOWN;
    }
}
