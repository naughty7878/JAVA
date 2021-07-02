package com.test.rpc.service;

//这个是接口，是服务提供方和 服务消费方都需要
public interface HelloService {

    // 这里定义协议头
    public static final String MSG_PREFIX = "Prefix#";

    String hello(String mes);
}
