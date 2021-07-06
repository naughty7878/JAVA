package com.level2.design;

public class Main {
    public static void main(String[] args) {
        double res = log(2, 32);
        System.out.println(res);
        res = log(2, 10);
        System.out.println(res);
    }

    public static double log(int basement, int n){
        return Math.log(n) / Math.log(basement);
    }
}