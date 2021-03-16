package com.test.jvm;

public class IHaveNatives {

    public native void methodNative1(Object x);

    public native static long methodNative2();

    private native synchronized float methodNative3(Object o);

    native void methodNative4(int[] ary) throws Exception;

}
