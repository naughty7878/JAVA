package com.test.interpreter;

import org.nfunk.jep.JEP;

public class JepClient {
    public static void main(String[] args) {
        JEP jep = new JEP();
        // 定义要计算的数据表达式
        String 存款利息 = "本金*利率*时间";
        // 给相关变量赋值
        jep.addVariable("本金", 10000);
        jep.addVariable("利率", 0.038);
        jep.addVariable("时间", 2);
        // 解析表达式
        jep.parseExpression(存款利息);
        // 计算
        Double accrual = jep.getValue();
        System.out.println("存款利息：" + accrual);
    }
}
