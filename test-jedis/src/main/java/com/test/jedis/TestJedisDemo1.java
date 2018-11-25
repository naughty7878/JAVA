package com.test.jedis;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class TestJedisDemo1 {

	/**
	 * 单实例连接redis数据库
	 * @Description TODO
	 * @author H__D
	 * @date 2018年7月5日
	 */
	@Test
	public void run1() {
		
		Jedis jedis = new Jedis("localhost", 17001);
		jedis.set("sex", "男");
		System.out.println(jedis.get("sex"));
	}

	/**
	 * Jedis连接池
	 * @Description TODO
	 * @author H__D
	 * @date 2018年7月5日
	 */
	@Test
	public void RUN2() {
		
		// 1、设置连接池的配置对象
		JedisPoolConfig config = new JedisPoolConfig();
		// 设置池中最大的连接数量（可选）
		config.setMaxTotal(50);
		// 设置空闲时池中保有的最大连接数（可选）
		config.setMaxIdle(10);
		
		// 2、设置连接池对象
		JedisPool pool = new JedisPool(config, "localhost", 17001);
		
		// 3、从池中获取连接对象
		Jedis jedis = pool.getResource();
		jedis.auth("admin");
		System.out.println(jedis.get("sex"));
		
		// 4、连接池归还
		jedis.close();
	}
}
