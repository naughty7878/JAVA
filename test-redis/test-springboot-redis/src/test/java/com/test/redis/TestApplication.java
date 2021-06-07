package com.test.redis;

import com.test.redis.entity.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestApplication {

    @Autowired
    ApplicationContext context;

    // 处理Object类型的key和value数据
    @Autowired
    RedisTemplate redisTemplate;

    // 处理String类型的key和value数据
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    // 使用 redisTemplate 处理对象
    @Test
    public void terst1(){
        Employee emp = new Employee(1, "小明");
        redisTemplate.opsForValue().set("emp", emp);
        Employee emp2 = (Employee) redisTemplate.opsForValue().get("emp");
        System.out.println(emp2);
    }

    // 使用 stringRedisTemplate 处理字符串
    @Test
    public void test2(){
        stringRedisTemplate.opsForValue().set("msg", "hello world");
        String msg = stringRedisTemplate.opsForValue().get("msg");
        System.out.println(msg);
    }
}
