package com.test.springboot.socket;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class WebSocketMapUtil {

    public static ConcurrentMap<String, MyWebSocket> webSocketMap = new ConcurrentHashMap<>();

    public static void put(String key, MyWebSocket myWebSocket) {
        webSocketMap.put(key, myWebSocket);
    }

    public static MyWebSocket get(String key) {
        return webSocketMap.get(key);
    }

    public static void remove(String key) {
        webSocketMap.remove(key);
    }

    public static Collection<MyWebSocket> getValues() {
        return webSocketMap.values();
    }

    public static int getOnlineCount() {
        return webSocketMap.size();
    }
}