package com.test.spring.observer;

import java.util.Observable;
import java.util.Observer;

public class ObserverTest {
    public static void main(String[] args) {

        // 主题
        WeatherSubject subject = new WeatherSubject(){};

        // 添加观察者
        subject.addObserver(new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                WeatherSubject sub = (WeatherSubject) o;
                System.out.println(sub.getWeather() + ", 回家收衣服了");
            }
        });

        subject.addObserver(new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                WeatherSubject sub = (WeatherSubject) o;
                System.out.println(sub.getWeather() + ", 天气变了出去完");
            }
        });
        //数据变化
        subject.setWeather("下雨了");
        //通知观察者
        subject.notifyObservers();
    }
}

class WeatherSubject extends Observable {

    String weather;

    public void setWeather(String weather) {
        this.weather = weather;
        // 数据改变
        setChanged();
    }

    public String getWeather() {
        return weather;
    }
}