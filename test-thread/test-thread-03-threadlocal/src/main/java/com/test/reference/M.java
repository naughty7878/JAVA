package com.test.reference;

public class M {
    @Override
    protected void finalize() throws Throwable {
        System.out.println("M 被回收了......finalize");
        super.finalize();
    }
}
