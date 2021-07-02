package com.test.second._04_default_method;

import org.junit.Test;

public class DefaultMethodTest {

    @Test
    public void test1() {
        new MyInterface(){}.hello();
    }

    static interface MyInterface {
        default void hello() {
            System.out.println("hello world");
        }
    }
}


