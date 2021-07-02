package com.test.rpc.customer;


import com.sun.org.apache.xml.internal.security.Init;
import com.test.rpc.netty.NettyClient;
import com.test.rpc.service.HelloService;

public class ClientBootstrap {

    public static void main(String[] args) throws Exception {

        // 创建一个消费者
        NettyClient customer = new NettyClient();

        // 获取代理对象
        HelloService service = (HelloService) customer.getBean(HelloService.class, HelloService.MSG_PREFIX);

        int i = 0;
        for (; ; ) {
            Thread.sleep(2 * 1000);
            // 通过代理对象调用服务提供者的方法(服务)
            String res = service.hello("hello world " + ++i);
            System.out.println("调用的结果 res= " + res);
        }
    }
}
