package com.test.flyweight;

import java.util.HashMap;
import java.util.Map;

public class FlyweightPattern {
    public static void main(String[] args) {
        FlyweightFactory factory = new FlyweightFactory();
        Flyweight fa = factory.getFlyweight("a");
        Flyweight fb = factory.getFlyweight("b");
        fa.operation(new UnsharedConcreteFlyweight("第1次调用a。"));
        fa.operation(new UnsharedConcreteFlyweight("第2次调用a。"));
        fa.operation(new UnsharedConcreteFlyweight("第3次调用a。"));
        fb.operation(new UnsharedConcreteFlyweight("第1次调用b。"));
        fb.operation(new UnsharedConcreteFlyweight("第2次调用b。"));
    }
}

//非享元角色
class UnsharedConcreteFlyweight {
    private String info;

    UnsharedConcreteFlyweight(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}

//抽象享元角色
interface Flyweight {
    public void operation(UnsharedConcreteFlyweight state);
}

//具体享元角色
class ConcreteFlyweight implements Flyweight {
    private String key;

    ConcreteFlyweight(String key) {
        this.key = key;
        System.out.println("具体享元" + key + "被创建！");
    }

    @Override
    public void operation(UnsharedConcreteFlyweight outState) {
        System.out.print("具体享元" + key + "被调用，");
        System.out.println("非享元信息是:" + outState.getInfo());
    }
}

//享元工厂角色
class FlyweightFactory {
    private Map<String, Flyweight> flyweightCache = new HashMap<String, Flyweight>();

    public Flyweight getFlyweight(String key) {
        Flyweight flyweight = flyweightCache.get(key);
        if (flyweight == null) {
            flyweight = new ConcreteFlyweight(key);
            flyweightCache.put(key, flyweight);
        }
        return flyweight;
    }
}