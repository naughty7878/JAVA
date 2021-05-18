package com.test.reference;

import java.lang.ref.SoftReference;

public class SoftReference02 {
    public static void main(String[] args) {
        SoftReference<byte[]> m = new SoftReference<>(new byte[1024*1024*10]);
        // m = null;
        System.out.println(m.get());
        System.gc();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(m.get());

        byte[] b = new byte[1024*1024*15];
        System.out.println(m.get());
    }
}
