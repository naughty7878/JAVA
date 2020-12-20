package com.test.socket.handler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistration;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

	/**
	 *
	 * @param registry 该对象可以调用addHandler()来注册信息处理器。
	 */
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		WebSocketHandlerRegistration handler = registry.addHandler(myWebSocketHandler(), "/myWebSocket");
		handler.addInterceptors(webSocketHandshakeInterceptor()); // 声明拦截器
		handler.setAllowedOrigins("*"); // 声明允许访问的主机列表
	}

	@Bean
	public MyWebSocketHandler myWebSocketHandler() {
		return new MyWebSocketHandler();
	}

	@Bean
	public WebSocketHandshakeInterceptor webSocketHandshakeInterceptor() {
		return new WebSocketHandshakeInterceptor();
	}

}
