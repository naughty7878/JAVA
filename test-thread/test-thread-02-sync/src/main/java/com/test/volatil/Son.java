package com.test.volatil;

public class Son {
    int age = 10;
    public Son(){
        System.out.println(age);
        age = 20;
    }

    @Override
    public String toString() {
        return "Son{" +
                "age=" + age +
                '}';
    }

    public static void main(String[] args) {
        Son son = new Son();
        System.out.println(son);
    }
}
