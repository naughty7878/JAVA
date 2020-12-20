package com.test.reference;

import java.lang.ref.WeakReference;

public class WeakReference03 {
    public static void main(String[] args) {
        WeakReference<M> m = new WeakReference<>(new M());

        System.out.println(m.get());
        System.gc();
        System.out.println(m.get());

        ThreadLocal<M>  tl = new ThreadLocal<>();
        tl.set(new M());
        tl.remove();
    }
}
