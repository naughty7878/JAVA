package com.test.interpreter;

public class Client {
    public static void main(String[] args) {

    }
}

// 抽象表达式
interface AbstractExpression {
    // 解释方法
    Object interpret(String info);
}

// 终结表达式
class TerminalExpression implements AbstractExpression {

    @Override
    public Object interpret(String info) {
        // 对终结符表达式的处理
        return null;
    }
}

// 非终结表达式
class NonTerminalExpression implements  AbstractExpression {

    private AbstractExpression exp1;
    private AbstractExpression exp2;

    @Override
    public Object interpret(String info) {
        // 非对终结符表达式的处理
        return null;
    }
}

// 环境类
class Context {
    private AbstractExpression exp;

    public void parse(String info){
        // 解析信息
    }

    public Object getValue() {
        // 调用相关表达式类的解释方法
        return null;
    }
}