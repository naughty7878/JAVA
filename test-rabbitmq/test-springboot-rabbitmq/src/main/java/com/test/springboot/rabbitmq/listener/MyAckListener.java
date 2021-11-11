package com.test.springboot.rabbitmq.listener;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;

public class MyAckListener implements ChannelAwareMessageListener {

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {

        // 消息id
        long deliveryTag = message.getMessageProperties().getDeliveryTag();

        byte[] body = message.getBody();
        System.out.println("body = " + new String(body));

        // 消息签收
        System.out.println("签收消息 = " + deliveryTag);
        //  true 确认所有消息，包括提供的交付标签； false 仅确认提供的交付标签
        channel.basicAck(deliveryTag, false);
    }
}
