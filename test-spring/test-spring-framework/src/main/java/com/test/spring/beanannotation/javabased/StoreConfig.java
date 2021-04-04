package com.test.spring.beanannotation.javabased;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:config.xml")
public class StoreConfig {
	
	@Value("${jdbc.url}")
	private String url;
	@Value("${jdbc.password}")
	private String password;
	@Value("${jdbc.username}")
	private String username;
	 
	@Bean
	public MyDriverManager myDriverManager() {
		return new MyDriverManager(url, username, password);
	}
	
	/**
	 * @Bean注解默认名称是 方法名称
	 * @return
	 */
	@Bean(name="store", initMethod = "init", destroyMethod = "destory")
	public Store getStringStore() {
		return new StringStore();
	}

} 
