package com.test.reference;

import java.io.IOException;
import java.lang.ref.Reference;

public class NormalReference01 {
    public static void main(String[] args) throws IOException {
        M m = new M();
        m = null;
        // 垃圾回收
        System.gc();
        System.in.read();

    }
}
