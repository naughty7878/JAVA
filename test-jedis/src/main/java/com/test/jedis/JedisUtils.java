package com.test.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisUtils {

	// 1、定义一个连接池对象
	private final static JedisPool POOL;

	static {
		// 初始化
		// 1、设置连接池的配置对象
		JedisPoolConfig config = new JedisPoolConfig();
		// 设置池中最大的连接数量（可选）
		config.setMaxTotal(50);
		// 设置空闲时池中保有的最大连接数（可选）
		config.setMaxIdle(10);

		// 2、设置连接池对象
		POOL = new JedisPool(config, "120.78.189.168", 6379);
	}
	
	/**
	 * 从连接池中获取连接
	 * @Description TODO
	 * @author H__D
	 * @date 2018年7月5日
	 * @return
	 */
	public static Jedis getJedis() {
		return POOL.getResource();
	}
}
