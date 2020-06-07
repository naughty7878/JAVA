package com.test.api;

import cn.hutool.core.date.DateUtil;

public class MyMain {

    public static void main(String[] args) {
        System.out.println("===========程 序 启 动===========");

        // 当前时间字符串，格式：yyyy-MM-dd HH:mm:ss
        // DateUtil 第三方jar包中的类
        String now = DateUtil.now();
        System.out.println("当前时间：" + now);

        System.out.println("===========程 序 关 闭===========");
    }
}
