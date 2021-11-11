package com.test.springboot.rabbitmq;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestApplication2 {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    AmqpAdmin amqpAdmin;



    // 测试确认模式
    @Test
    public void testConfirm() throws InterruptedException {

        // 方法一：Message需要自己构造一个；定义消息体内容和消息头
        // rabbitTemplate.send(exchange, routingKey, message);

        // 方法二：object默认当成消息体，只需要传入发送的对象，自动序列化发送给rabbitmq
        // rabbitTemplate.convertAndSend(exchange, routingKey, object);

        Map<String, Object> map = new HashMap<>();
        map.put("msg", "hello world!");
        map.put("data", Arrays.asList(1, 2, 3));

        // 定义一个回调
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            /**
             *
             * @param correlationData 相关配置信息
             * @param ack  是否成功接收消息
             * @param cause 失败原因
             */
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                System.out.println("confirm方法被执行");
                if (ack) {
                    System.out.println("接收成功消息：" + cause);
                }else {
                    System.out.println("接收失败消息：" + cause);
                }
            }
        });

        // 对象别默认序列化以后发送出去
        rabbitTemplate.convertAndSend("exchange.direct11", "queue.news", map);

        Thread.sleep(3000);
        System.out.println("结束。。。");
    }

    // 测试退回模式
    @Test
    public void testReturn() throws InterruptedException {

        // 方法一：Message需要自己构造一个；定义消息体内容和消息头
        // rabbitTemplate.send(exchange, routingKey, message);

        // 方法二：object默认当成消息体，只需要传入发送的对象，自动序列化发送给rabbitmq
        // rabbitTemplate.convertAndSend(exchange, routingKey, object);

        Map<String, Object> map = new HashMap<>();
        map.put("msg", "hello world!");
        map.put("data", Arrays.asList(1, 2, 3));

        // 定义一个回调
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            /**
             *
             * @param correlationData 相关配置信息
             * @param ack  是否成功接收消息
             * @param cause 失败原因
             */
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                System.out.println("confirm方法被执行");
                if (ack) {
                    System.out.println("接收成功消息：" + cause);
                }else {
                    System.out.println("接收失败消息：" + cause);
                }
            }
        });

        // 设置交换机处理失败消息模式
        // true：消息到达不了队列，消息返回给生产者，默认是true
//        rabbitTemplate.setMandatory(true);

        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            /**
             *
             * @param message 消息对象
             * @param replyCode 错误吗
             * @param replyText 错误信息
             * @param exchange 交换机
             * @param routingKey 路由件
             */
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                System.out.println("returnedMessage = " + "被执行了");

                System.out.println("message = " + message);
                System.out.println("replyCode = " + replyCode);
                System.out.println("replyText = " + replyText);
                System.out.println("exchange = " + exchange);
                System.out.println("routingKey = " + routingKey);
            }
        });

        // 对象别默认序列化以后发送出去
        rabbitTemplate.convertAndSend("exchange.direct", "queue.news11", map);

        Thread.sleep(3000);
        System.out.println("结束。。。");
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