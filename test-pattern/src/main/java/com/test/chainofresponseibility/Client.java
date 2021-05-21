package com.test.chainofresponseibility;

public class Client {

    public static void main(String[] args) {
        // 组装责任链
        Handler handler1 = new ConcreteHandler1();
        Handler handler2 = new ConcreteHandler2();
        handler1.setNext(handler2);

        // 提交请求
        handler1.handleRequest("two");
    }
}

// 抽象处理者
abstract class Handler {
    private Handler next;

    public Handler getNext() {
        return next;
    }

    public void setNext(Handler next) {
        this.next = next;
    }

    // 处理请求的方法
    public abstract void handleRequest(String request);
}

// 具体处理者角色1
class ConcreteHandler1 extends Handler {

    @Override
    public void handleRequest(String request) {
        if ("one".equals(request)) {
            System.out.println("具体处理者1负责处理该请求");
        } else {
            if (getNext() != null) {
                System.out.println("具体处理者1转发了该请求");
                getNext().handleRequest(request);
            }else {
                System.out.println("没有人处理该请求");
            }
        }
    }
}

// 具体处理中角色2
class ConcreteHandler2 extends Handler {

    @Override
    public void handleRequest(String request) {
        if ("two".equals(request)) {
            System.out.println("具体处理者2负责处理该请求");
        } else {
            if (getNext() != null) {
                System.out.println("具体处理者2转发了该请求");
                getNext().handleRequest(request);
            }else {
                System.out.println("没有人处理该请求");
            }
        }
    }
}