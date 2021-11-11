package com.test.springboot.rabbitmq;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestApplication {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    AmqpAdmin amqpAdmin;

    @Test
    public void createExchange() {
        // 新建一个Exchange，查看Exchange接口的继承关系，可以找到实现类DirectExchange
        Exchange exchange = new DirectExchange("exchange.direct");
        // 在rabbitmq中，创建一个Exchange交换机
        amqpAdmin.declareExchange(exchange);

        // 在rabbitmq中，创建一个Queue队列
        // new Queue(队列名称, 是否持久化)
        amqpAdmin.declareQueue(new Queue("queue.news", true));

        // 在rabbitmq中，创建一个Binding绑定关系
        // new Binding(目的地, 目的类型, 交换机名称, 路由规则, 参数)
        amqpAdmin.declareBinding(new Binding("queue.news", Binding.DestinationType.QUEUE,
                "exchange.direct", "queue.news", null));
    }

    // 点对点（单播）
    @Test
    public void publisher() {

        // 方法一：Message需要自己构造一个；定义消息体内容和消息头
        // rabbitTemplate.send(exchange, routingKey, message);

        // 方法二：object默认当成消息体，只需要传入发送的对象，自动序列化发送给rabbitmq
        // rabbitTemplate.convertAndSend(exchange, routingKey, object);

        Map<String, Object> map = new HashMap<>();
        map.put("msg", "hello world!");
        map.put("data", Arrays.asList(1, 2, 3));
        // 对象别默认序列化以后发送出去
        rabbitTemplate.convertAndSend("exchange.direct", "queue.news", map);
    }

    // 接受消息
    @Test
    public void consumer() {
        // 接受消息自动反序列化成对象
        Object o = rabbitTemplate.receiveAndConvert("queue.news");
        System.out.println(o.getClass());
        System.out.println(o);
    }

    // 广播
    @Test
    public void fanout() {

        Map<String, Object> map = new HashMap<>();
        map.put("content", "hello world!");
        map.put("number", 1);
        rabbitTemplate.convertAndSend("test.fanout", "test", map);

    }
}