package com.test.jvm;

class Father {
    public static int A = 1;
    static {
        A = 2;
    }
}

class Son extends Father {
    public static int B = A;
}

public class ClinitTest2 {
    public static void main(String[] args) {
        System.out.println(Son.B);
    }
}
