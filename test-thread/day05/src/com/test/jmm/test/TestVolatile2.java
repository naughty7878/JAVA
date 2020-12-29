package com.test.jmm.test;

public class TestVolatile2 {

    public static volatile int counter = 1;

    public static void main(String[] args){
        counter = 2;
        System.out.println(counter);
    }

}
