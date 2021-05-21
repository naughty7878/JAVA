package com.test.strategy;

/**
 * @author h__d
 */
public class Client {
    public static void main(String[] args) {
        Context context = new Context();
        Strategy a = new ConcreteStrategyA();
        context.setStrategy(a);
        context.strategyMethod();
        System.out.println("-----------------");
        Strategy b = new ConcreteStrategyB();
        context.setStrategy(b);
        context.strategyMethod();
    }
}

// 抽象策略类
interface Strategy {
    public void strategyMethod();   // 策略方法
}

// 具体策略类A
class ConcreteStrategyA implements Strategy {
    @Override
    public void strategyMethod() {
        System.out.println("具体策略A的策略方法被访问！");
    }
}

// 具体策略类A
class ConcreteStrategyB implements Strategy {
    @Override
    public void strategyMethod() {
        System.out.println("具体策略B的策略方法被访问！");
    }
}

class Context {
    private Strategy strategy;

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public void strategyMethod(){
        strategy.strategyMethod();
    }
}