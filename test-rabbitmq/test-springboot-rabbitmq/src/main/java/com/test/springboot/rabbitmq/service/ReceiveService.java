package com.test.springboot.rabbitmq.service;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Map;

//@Service
public class ReceiveService {

    // 监听rabbitmq队列 "queue.news"
    @RabbitListener(queues = "queue.news")
    // 接收到对象
    public void receive(Map map){
        System.out.println("收到消息：" + map);
    }

    // 接收到Message
    @RabbitListener(queues = "hd.news")
    public void receive02(Message message){
        System.out.println("收到消息：" + message);
        System.out.println("message.getMessageProperties()：" + message.getMessageProperties());
        System.out.println("message.getBody()：" + message.getBody());

    }


}
