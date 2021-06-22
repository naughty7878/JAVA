package com.test.netty.nio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannel01 {
    public static void main(String[] args) throws IOException {
        String str = "hello world";
        // 创建输出流
        FileOutputStream fileOutputStream = new FileOutputStream("hello.txt");
        // 得到对应通道
        FileChannel fileChannel = fileOutputStream.getChannel();
        // 创建缓存区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        // 数据写入缓冲
        buffer.put(str.getBytes());
        // 缓冲数据写入通道
        buffer.flip();
        fileChannel.write(buffer);

        // 关闭通道，通道fileChannel会关闭上级流(fileOutputStream)
        fileChannel.close();
    }
}
