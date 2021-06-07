package com.test.redis.client;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Redis底层RESP协议
 */
public class TestRESP {


    @Test
    public void server() throws IOException {
        ServerSocket serverSocket = new ServerSocket(6379);
        Socket socket = serverSocket.accept();
        byte[] bytes = new byte[1024];
        int len = socket.getInputStream().read(bytes);
        System.out.println(new String(bytes, 0, len));
    }

    @Test
    public void client() {
        // 创建client
        Jedis jedis = new Jedis("127.0.0.1", 6379);
//        jedis.auth("123456");
        // 创建socket连接，发送socket流
        String ret = jedis.set("aaa", "11");
    }
}
