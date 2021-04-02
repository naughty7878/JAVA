package com.test.concurrent.forkjoin.demo;

public class Test {

    private static final int SEED_INCREMENT = 0x9e3779b9;

    // Lower and upper word masks
    private static final long SP_MASK    = 0xffffffffL;
    private static final long UC_MASK    = ~SP_MASK;

    // Active counts
    private static final int  AC_SHIFT   = 48;
    private static final long AC_UNIT    = 0x0001L << AC_SHIFT;
    private static final long AC_MASK    = 0xffffL << AC_SHIFT;

    // Total counts
    private static final int  TC_SHIFT   = 32;
    private static final long TC_UNIT    = 0x0001L << TC_SHIFT;
    private static final long TC_MASK    = 0xffffL << TC_SHIFT;
    private static final long ADD_WORKER = 0x0001L << (TC_SHIFT + 15); // sign

    // runState bits: SHUTDOWN must be negative, others arbitrary powers of two
    private static final int  RSLOCK     = 1;
    private static final int  RSIGNAL    = 1 << 1;
    private static final int  STARTED    = 1 << 2;
    private static final int  STOP       = 1 << 29;
    private static final int  TERMINATED = 1 << 30;
    private static final int  SHUTDOWN   = 1 << 31;


    // Bounds
    static final int SMASK        = 0xffff;        // short bits == max index
    static final int MAX_CAP      = 0x7fff;        // max #workers - 1
    static final int EVENMASK     = 0xfffe;        // even short bits
    static final int SQMASK       = 0x007e;        // max 64 (even) slots

    // Masks and units for WorkQueue.scanState and ctl sp subfield
    static final int SCANNING     = 1;             // false when running tasks
    static final int INACTIVE     = 1 << 31;       // must be negative
    static final int SS_SEQ       = 1 << 16;       // version count

    // Mode bits for ForkJoinPool.config and WorkQueue.config
    static final int MODE_MASK    = 0xffff << 16;  // top half of int
    static final int LIFO_QUEUE   = 0;
    static final int FIFO_QUEUE   = 1 << 16;
    static final int SHARED_QUEUE = 1 << 31;


    // 任务运行状态
    volatile int status; // accessed directly by pool and workers
    static final int DONE_MASK   = 0xf0000000;  // mask out non-completion bits
    static final int NORMAL      = 0xf0000000;  // must be negative
    static final int CANCELLED   = 0xc0000000;  // must be < NORMAL
    static final int EXCEPTIONAL = 0x80000000;  // must be < CANCELLED
    static final int SIGNAL      = 0x00010000;  // must be >= 1 << 16
//    static final int SMASK       = 0x0000ffff;  // short bits for tags

    public static void main(String[] args) {
        System.out.println(0xf + 15 * 16);
        System.out.println(1 << 16);
        System.out.println("SMASK = " + Integer.toBinaryString(SMASK));
        System.out.println("MAX_CAP = " +Integer.toBinaryString(MAX_CAP));
        System.out.println("EVENMASK = " +Integer.toBinaryString(EVENMASK));
        System.out.println("SQMASK = " + Integer.toBinaryString(SQMASK));
        System.out.println();
        System.out.println("AC_SHIFT = " + Integer.toBinaryString(AC_SHIFT));
        System.out.println("AC_UNIT = " + Long.toBinaryString(AC_UNIT));
        System.out.println("AC_MASK = " + Long.toBinaryString(AC_MASK));
        System.out.println();
        System.out.println("TC_SHIFT = " + Integer.toBinaryString(TC_SHIFT));
        System.out.println("TC_UNIT = " + Long.toBinaryString(TC_UNIT));
        System.out.println("TC_MASK = " + Long.toBinaryString(TC_MASK));
        System.out.println("ADD_WORKER = " + Long.toBinaryString(0x0001L << (TC_SHIFT + 15)));
        System.out.println();
        System.out.println("DONE_MASK = " + Integer.toBinaryString(DONE_MASK));
        System.out.println("NORMAL = " + Integer.toBinaryString(NORMAL));
        System.out.println("CANCELLED = " + Integer.toBinaryString(CANCELLED));
        System.out.println("EXCEPTIONAL = " + Integer.toBinaryString(EXCEPTIONAL));
        System.out.println("SIGNAL = " + Integer.toBinaryString(SIGNAL));
        System.out.println();
        System.out.println("SP_MASK = " + Long.toBinaryString(SP_MASK));
        System.out.println("UC_MASK = " + Long.toBinaryString(UC_MASK));
        System.out.println();



        int np = Runtime.getRuntime().availableProcessors();
        long ctl = ((np << AC_SHIFT) & AC_MASK) | ((np << TC_SHIFT) & TC_MASK);
        System.out.println("np = " + np);
        System.out.println("ctl = " + ctl);
        System.out.println("ctl = " + Long.toBinaryString(ctl));
        System.out.println("r = " + Long.toBinaryString(-1640531527));
        System.out.println("r & m & sqmark = " + Long.toBinaryString(-1640531527 & 7 & SQMASK));
        System.out.println("j = " + ((((8192 - 1) & (1 << 12)) << 2) + 16));
        // -4 -4 0 0
        System.out.println("ctl = " + Long.toBinaryString(-844442110001152l));
        System.out.println("ctl = " + Long.toBinaryString(-844442110001152l).length());
        System.out.println("ctl = " + Long.toBinaryString(-844442110001152l).substring(0,16));
        System.out.println("ctl = " + Long.toBinaryString(-844442110001152l).substring(0,32));
        System.out.println("ctl = " + Long.toBinaryString(-844442110001152l).substring(32));
        System.out.println("nc = " + Long.toBinaryString(-562962838323200l));
        System.out.println("nc = " + Long.toBinaryString(-562962838323200l).length());
        System.out.println("nc = " + Long.toBinaryString(-562962838323200l).substring(0,16));
        System.out.println("nc = " + Long.toBinaryString(-562962838323200l).substring(0,32));
        System.out.println("nc = " + Long.toBinaryString(-562962838323200l).substring(32));
        System.out.println("ctl = " + Long.toBinaryString(4294967296l));
        System.out.println("ctl = " + Long.toBinaryString(4294967296l).length());
        System.out.println("ctl = " + Long.toBinaryString(4294967296l).substring(0,16));
        System.out.println("ctl = " + Long.toBinaryString(4294967296l).substring(0,32));
        System.out.println("ctl = " + Long.toBinaryString(4294967296l).substring(32));


        System.out.println(Long.toBinaryString(((long)-4) << 48)) ;
        System.out.println( (((long)-4) << 48) >> 48);
        System.out.println("SEED_INCREMENT = " + Integer.toBinaryString(SEED_INCREMENT));
        System.out.println("SEED_INCREMENT.length = " + Integer.toBinaryString(SEED_INCREMENT).length());

        System.out.println("3 & -1  = " + Integer.toBinaryString(3 & -1));
        System.out.println("65535 = " + Integer.toBinaryString(65535));
    }
}
