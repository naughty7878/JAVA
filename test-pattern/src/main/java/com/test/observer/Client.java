package com.test.observer;

import java.util.ArrayList;
import java.util.List;

public class Client {

    public static void main(String[] args) {
        Subject subject = new ConcreteSubject();
        subject.add(new ConcreteObserver1());
        subject.add(new ConcreteObserver2());
        subject.notifyObserver();
    }

}

// 抽象主题
abstract class Subject {
    // 观察者集合
    protected List<Observer> observers = new ArrayList<>();

    // 增加观察者方法
    public void add(Observer observer){
        observers.add(observer);
    }

    // 移除观察者方法
    public void remove(Observer observer){
        observers.remove(observer);
    }

    // 通知观察者方法
    public abstract void notifyObserver();
}

// 具体主题
class ConcreteSubject extends Subject {

    @Override
    public void notifyObserver() {
        System.out.println("具体目标发生改变...");
        System.out.println("--------------");

        for (Observer observer : observers) {
            observer.response();
        }
    }
}

// 抽象观察者
interface Observer {
    // 响应
    void response();
}

// 具体观察者1
class ConcreteObserver1 implements Observer{

    @Override
    public void response() {
        System.out.println("具体观察者1作出响应！");
    }
}

// 具体观察者2
class ConcreteObserver2 implements Observer{

    @Override
    public void response() {
        System.out.println("具体观察者2作出响应！");
    }
}