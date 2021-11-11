package com.test.springboot.rabbitmq.service;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class MyReceiveService {


    // 接收到Message
    // 手动签收
    @RabbitListener(queues = "queue.news", ackMode = "MANUAL")
    public void receive01(Channel channel, Message message) throws IOException {
        System.out.println("收到消息：" + message);
        System.out.println("message.getMessageProperties()：" + message.getMessageProperties());
        System.out.println("message.getBody()：" + message.getBody());


        // 消息id
        long deliveryTag = message.getMessageProperties().getDeliveryTag();

        try {
            byte[] body = message.getBody();
            System.out.println("body = " + new String(body));

            // 消息签收
            System.out.println("签收消息 = " + deliveryTag);
            //  true 确认所有消息，包括提供的交付标签； false 仅确认提供的交付标签
            channel.basicAck(deliveryTag, false);
        } catch (IOException e) {
            e.printStackTrace();

            // 业务处理失败，拒绝签收这条消息
            //最后一个参数是：是否重回队列
            channel.basicNack(deliveryTag, false, true);
        }
    }

    // 接收到Message
//    @RabbitListener(queues = "queue.news2")
    public void receive02(Channel channel, Message message) throws IOException {
        System.out.println("收到消息：" + message);
        System.out.println("message.getMessageProperties()：" + message.getMessageProperties());
        System.out.println("message.getBody()：" + message.getBody());


        // 消息id
        long deliveryTag = message.getMessageProperties().getDeliveryTag();

        try {
            byte[] body = message.getBody();
            System.out.println("body = " + new String(body));

            // 消息签收
            System.out.println("签收消息 = " + deliveryTag);
            //  true 确认所有消息，包括提供的交付标签； false 仅确认提供的交付标签
            channel.basicAck(deliveryTag, false);
        } catch (IOException e) {
            e.printStackTrace();

            // 业务处理失败，拒绝签收这条消息
            //最后一个参数是：是否重回队列
            channel.basicNack(deliveryTag, false, true);
        }
    }

}
