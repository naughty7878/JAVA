package com.test.redis.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.net.UnknownHostException;


@Configuration
public class RedisConfig {

    /**
     * 在 RedisAutoConfiguration 配置类中注入的
     * 1、RedisTemplate：
     *  处理Object类型的key和value数据
     *  使用的是默认的序列化器：JdkSerializationRedisSerializer
     * 效果：
     *  "我"  =====JdkSerializationRedisSerializer======> 二进制数据，可读性差
     *
     * 2、StringRedisTemplate:
     *  处理String类型的key和value数据
     *  使用的是默认的序列化器：StringRedisSerializer
     * 效果：
     *  "我"  =====StringRedisSerializer======> "我"
     *
     * 如果想将数据以json的方式保存在redis中，需要自己注入一个redisTemplate，且此redisTemplate使用json的序列化器。
     */

    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory)
            throws UnknownHostException {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        // 设置默认的序列化器，修改默认的序列化器，会对所有的key和value有效
//        template.setDefaultSerializer(RedisSerializer.json());
        // 设置key的序列化器为字符串序列化器
        template.setKeySerializer(RedisSerializer.string());
        template.setHashKeySerializer(RedisSerializer.string());

        // 设置value的序列化器为json序列化器
        template.setValueSerializer(RedisSerializer.json());
        template.setHashValueSerializer(RedisSerializer.json());

        return template;
    }

}
