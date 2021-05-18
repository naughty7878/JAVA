package com.test.jmm.tmodel;

public class ThreadModel {

    public static void main(String[] args) {

        for (int i=0;i<200;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while(true){
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
    }

}
