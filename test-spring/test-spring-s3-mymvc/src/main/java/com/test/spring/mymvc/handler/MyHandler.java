package com.test.spring.mymvc.handler;

import java.lang.reflect.Method;

public class MyHandler {

    // 请求url地址
    private String url;
    // 后台控制器
    private Object controller;
    // 控制器中指定的方法
    private Method method;

    public MyHandler(String url, Object controller, Method method) {
        this.url = url;
        this.controller = controller;
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Object getController() {
        return controller;
    }

    public void setController(Object controller) {
        this.controller = controller;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
