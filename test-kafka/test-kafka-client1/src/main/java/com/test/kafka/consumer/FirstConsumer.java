package com.test.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;


import java.util.Arrays;
import java.util.Properties;

public class FirstConsumer {

    public static void main(String[] args) {
        // 创建配置信息
        Properties props = new Properties();

        // kafka 集群，broker-list
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092,127.0.0.1:9093,127.0.0.1:9094");
        // 开启自动提交
//        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        // 开启手动提交
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);

        // 自动提交延迟
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        // 反序列化
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        // 重置offset，满足条件下，可以重置重置offset，从最早的消息开始消费
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        // 消费者组
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "test-my-group");

        // 创建消费者
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);

        // 订阅主题
        consumer.subscribe(Arrays.asList("first", "second"));

        while (true) {
            // 拉取消息
            ConsumerRecords<String, String> consumerRecords = consumer.poll(100);
            System.out.println("数量：" + consumerRecords.count());
            // 遍历
            for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                System.out.println(consumerRecord.key() + "----" + consumerRecord.value());
            }

            // 手动同步提交，当前线程会阻塞直到 offset 提交成功
            consumer.commitSync();
        }

//        // 关闭
//        consumer.close();
    }
}
