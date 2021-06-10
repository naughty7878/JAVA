package com.test.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class FirstProducer {
    public static void main(String[] args) throws InterruptedException {

        // 创建生产者配置信息
        Properties props = new Properties();

        // kafka 集群，broker-list
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092,127.0.0.1:9093,127.0.0.1:9094");
        // ACK应答级别
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        // 重试次数
        props.put(ProducerConfig.RETRIES_CONFIG, 1);
        // 批次大小 16k
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        // 等待时间 1ms
        props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        // RecordAccumulator 缓冲区大小  32m
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
        // 序列化器
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

        // 创建生产者对象
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        // 发送数据
        for (int i = 0; i < 1000; i++) {
            producer.send(new ProducerRecord<>("first", "~~value~~~~" + i));
            Thread.sleep(500);
        }

        // 关闭连接
        producer.close();
    }
}
