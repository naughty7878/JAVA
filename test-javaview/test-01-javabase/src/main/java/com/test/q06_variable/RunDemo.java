package com.test.q06_variable;

public class RunDemo {
    static int s;
    int i;
    int j;

    {
        int i = 1;
        i++;
        j++;
        s++;
    }

    public void test(int j) {
        j++;
        i++;
        s++;

    }

    public static void main(String[] args) {
        RunDemo obj1 = new RunDemo();
        RunDemo obj2 = new RunDemo();
        obj1.test(10);
        obj1.test(20);
        obj2.test(40);
        System.out.println(obj1.i + "," + obj1.j + "," + obj1.s);
        System.out.println(obj2.i + "," + obj2.j + "," + obj2.s);
    }
}
