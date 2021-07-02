package com.test.sync;

public class Test {
    public static void main(String[] args) {
        //验证可见性
        Data data = new Data();
        

        new Thread(() -> {
            while (data.number==0){
//                System.out.println(Thread.currentThread().getName()+data.number);
            }
            System.out.println(Thread.currentThread().getName()+data.number);
        },"A").start();

        try {
            Thread.sleep(900);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"进入"+data.number);
            data.add60();
            System.out.println(Thread.currentThread().getName()+"更新"+data.number);
        },"B").start();
    }
}


class Data{
    int number = 0;
    public  void add60(){
       int a =  number+60;
       number = a;
    }
}