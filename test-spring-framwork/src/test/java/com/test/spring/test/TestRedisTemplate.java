package com.test.spring.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.springframework.data.redis.core.RedisTemplate;

import com.test.spring.autowiring.AutoWiringService;
import com.test.spring.interfaces.OneInterface;

@RunWith(BlockJUnit4ClassRunner.class)
public class TestRedisTemplate extends UnitTestBase{
	
	public TestRedisTemplate() {
		super("classpath*:spring-redis.xml");
	}
	
	
	@Test
	public void testRedis() {
		System.out.println("===");
		RedisTemplate<String, Object> redisTemplate= getBean("redisTemplate");
		this.redisTemplate = redisTemplate;
		
		System.out.println(set("sex", "女"));
		System.out.println("===");
		System.out.println(get("sex"));
	}
	
	RedisTemplate<String, Object> redisTemplate;
	/** 
     * 普通缓存获取 
     * @param key 键 
     * @return 值 
     */  
    public Object get(String key){  
        return key==null?null:redisTemplate.opsForValue().get(key);  
    }  
      
    /** 
     * 普通缓存放入 
     * @param key 键 
     * @param value 值 
     * @return true成功 false失败 
     */  
    public boolean set(String key,Object value) {  
         try {  
            redisTemplate.opsForValue().set(key, value);  
            return true;  
        } catch (Exception e) {  
            e.printStackTrace();  
            return false;  
        }  
          
    }  

}
