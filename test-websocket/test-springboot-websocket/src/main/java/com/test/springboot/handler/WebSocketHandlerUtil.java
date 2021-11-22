package com.test.springboot.handler;

import org.springframework.web.socket.WebSocketSession;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class WebSocketHandlerUtil {

    public static ConcurrentMap<String, WebSocketSession> webSocketMap = new ConcurrentHashMap<>();

    public static void put(String key, WebSocketSession webSocketSession) {
        webSocketMap.put(key, webSocketSession);
    }

    public static WebSocketSession get(String key) {
        return webSocketMap.get(key);
    }

    public static void remove(String key) {
        webSocketMap.remove(key);
    }

    public static Collection<WebSocketSession> getValues() {
        return webSocketMap.values();
    }

    public static int getOnlineCount() {
        return webSocketMap.size();
    }
}