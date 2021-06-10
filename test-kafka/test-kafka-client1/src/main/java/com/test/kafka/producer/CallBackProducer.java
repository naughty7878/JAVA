package com.test.kafka.producer;

import com.test.kafka.partitioner.MyPartitioner;
import org.apache.kafka.clients.producer.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class CallBackProducer {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

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

        // 添加分区器
        props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, "com.test.kafka.partitioner.MyPartitioner");

        // 拦截器链
        List<String> interceptors = new ArrayList<>();
        interceptors.add("com.test.kafka.interceptor.TimeInterceptor");
        interceptors.add("com.test.kafka.interceptor.CounterInterceptor");
        props.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, interceptors);

        // 创建生产者对象
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        // 发送数据
        for (int i = 0; i < 10; i++) {
//            producer.send(new ProducerRecord<>("second", "value~~~~" + i), (metadata, exception) -> {
            Future<RecordMetadata> future = producer.send(new ProducerRecord<String, String>("second", String.valueOf(i), "value~~~~" + i), (metadata, exception) -> {
                // 正常返回 exception 为空
                if (exception == null) {
                    System.out.println("partition:" + metadata.partition() + "\toffset:" + metadata.offset());
                } else {
                    exception.printStackTrace();
                }
            });

            // 同步发送策略
            RecordMetadata metadata = future.get();
            System.out.println(metadata);
        }
        // 关闭连接
        producer.close();

    }
}
