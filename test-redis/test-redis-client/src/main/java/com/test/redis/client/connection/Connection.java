package com.test.redis.client.connection;

import com.test.redis.client.protocol.RedisProtocol;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 传输层
 * 负责连接的建立，数据发送与接收
 */
public class Connection {


    private Socket socket;
    private String host;
    private int port;
    private OutputStream outputStream;
    private InputStream inputStream;

    public Connection(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public Connection connection() {
        try {
            if (!isConnected()) {
                socket = new Socket(host, port);
                outputStream = socket.getOutputStream();
                inputStream = socket.getInputStream();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    // 判断是否连接
    private boolean isConnected() {
        return this.socket != null &&
                this.socket.isBound() &&
                !this.socket.isClosed() &&
                socket.isConnected();

    }

    public Connection sendCommand(RedisProtocol.Command command, byte[]... args) {
        connection();
        RedisProtocol.sendCommand(outputStream, command, args);
        return this;
    }

    public String getStatusReply() {
        try {
            byte[] bytes = new byte[1024];
            int len = inputStream.read(bytes);
            return new String(bytes, 0, len);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
