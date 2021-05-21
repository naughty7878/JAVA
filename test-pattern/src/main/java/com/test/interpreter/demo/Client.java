package com.test.interpreter.demo;

public class Client {
    public static void main(String[] args) {
        Context context = new Context();
        context.parse("abc+cba");
        System.out.println(context.getValue());
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
        // abc ==> 123，将abc解析成123
        char[] chars = info.toCharArray();
        StringBuffer sb = new StringBuffer();
        for (char c : chars) {
            sb.append(c - 'a' + 1);
        }
        return Integer.parseInt(sb.toString());
    }
}

// 非终结表达式
class NonTerminalExpression implements  AbstractExpression {

    private AbstractExpression exp1;
    private AbstractExpression exp2;

    @Override
    public Object interpret(String info) {
        // 非对终结符表达式的处理
        String[] strings = info.split("[+]");
        exp1 = new TerminalExpression();
        exp2 = new TerminalExpression();
        int value = (Integer) exp1.interpret(strings[0]) + (Integer)exp2.interpret(strings[1]);
        return value;
    }
}

// 环境类
class Context {
    private AbstractExpression exp;
    private String info;

    public void parse(String info){
        // 解析信息
        if (info.indexOf('+') > -1) {
            exp = new NonTerminalExpression();
        }else {
            exp = new TerminalExpression();
        }
        this.info = info;
    }

    public Object getValue() {
        // 调用相关表达式类的解释方法
        return exp.interpret(info);
    }
}