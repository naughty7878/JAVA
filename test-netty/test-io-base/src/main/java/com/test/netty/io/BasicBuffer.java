package com.test.netty.io;

import java.nio.ByteBuffer;

public class BasicBuffer {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        // 存放数据
        buffer.put("hello world".getBytes());
        // 读取数据
        byte[] buff = new byte[1024];
        buffer.flip();
        while (buffer.hasRemaining()) {
            buffer.get(buff, 0, buffer.limit());
            System.out.println(new String(buff, 0, buffer.limit()));
        }
    }

}
