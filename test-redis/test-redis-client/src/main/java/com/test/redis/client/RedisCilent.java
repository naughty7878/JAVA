package com.test.redis.client;

import com.test.redis.client.connection.Connection;
import com.test.redis.client.protocol.RedisProtocol;

public class RedisCilent {


    private Connection connection;

    public RedisCilent(String host, int port) {
        connection = new Connection(host, port);
    }


    public String set(String key, String value) {
        connection.sendCommand(RedisProtocol.Command.SET, key.getBytes(), value.getBytes());
        return connection.getStatusReply();
    }


    public String get(String key) {
        connection.sendCommand(RedisProtocol.Command.GET, key.getBytes());
        return connection.getStatusReply();
    }


    public static void main(String[] args) {
        RedisCilent redisCilent = new RedisCilent("127.0.0.1", 6379);
        System.out.println(redisCilent.set("aa", "11"));
        System.out.println("-----------------");
        System.out.println(redisCilent.get("aa"));
        System.out.println("-----------------");
    }
}