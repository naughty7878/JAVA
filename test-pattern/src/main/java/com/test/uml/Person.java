package com.test.uml;

public abstract class Person {
    public String name;
    private int age;
    protected double weight;
    double height;
    public static char sex;

    public void eat(Object food){}

    protected void drink(){}

    private void walk(){}

    void run(){}

    public abstract void study();

    public boolean openMac(){
        return false;
    }

    public static void playGame(){}
}
