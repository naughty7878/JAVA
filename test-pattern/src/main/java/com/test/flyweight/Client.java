package com.test.flyweight;

import java.util.HashMap;
import java.util.Map;

public class Client {
    public static void main(String[] args) {

        FlyweightFactory factory = new FlyweightFactory();
        Flyweight f01 = factory.getFlyweight("100");
        f01.operate("a");
        Flyweight f02 = factory.getFlyweight("100");
        f02.operate("b");
        Flyweight f22 = factory.getFlyweight("200");
        f22.operate("c");
    }
}


//抽象享元角色
abstract class Flyweight {
    //内部状态
    public final String intrinsicState;
    protected String extrinsicState;
    //外部状态

    public Flyweight(String intrinsicState) {
        this.intrinsicState = intrinsicState;
    }

    //定义业务操作
    public abstract void operate(String extrinsicState);

}

//具体享元角色
class ConcreteFlyweight extends Flyweight {

    public ConcreteFlyweight(String intrinsicState) {
        super(intrinsicState);
    }

    // 根据外部状态进行逻辑处理
    @Override
    public void operate(String extrinsicState) {
        this.extrinsicState = extrinsicState;
        System.out.printf("具体Flyweight(内部状态：%s，外部状态：%s)\n", intrinsicState, extrinsicState);
    }
}

//非享元角色
class UnsharedConcreteFlyweight extends Flyweight {

    public UnsharedConcreteFlyweight(String intrinsicState) {
        super(intrinsicState);
    }

    @Override
    public void operate(String extrinsicState) {
        this.extrinsicState = extrinsicState;
        System.out.printf("不共享的具体Flyweight(内部状态：%s，外部状态：%s)\n", intrinsicState, extrinsicState);
    }
}


//享元工厂角色
class FlyweightFactory {

    // 定义一个池容器
    private static HashMap<String, Flyweight> pool = new HashMap<>();

    public Flyweight getFlyweight(String key) {

        Flyweight flyweight = null;
        int num = Integer.parseInt(key);
        if (num >= Byte.MIN_VALUE && num <= Byte.MAX_VALUE) {
            flyweight = (Flyweight) pool.get(key);
            if(flyweight == null) {
                flyweight = new ConcreteFlyweight(key);
                pool.put(key, flyweight);
            }
        } else {
            flyweight = new UnsharedConcreteFlyweight(key);
        }
        return flyweight;
    }
}