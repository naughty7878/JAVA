package com.test.netty.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannel02 {
    public static void main(String[] args) throws IOException {
        // 创建输入流
        FileInputStream fileInputStream = new FileInputStream("hello.txt");
        // 得到对应通道
        FileChannel fileChannel = fileInputStream.getChannel();
        // 创建缓存区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        // 通道数据写入缓冲
        fileChannel.read(buffer);
        // 打印缓冲区数据
        buffer.flip();
        System.out.println(new String(buffer.array(), 0, buffer.limit()));

        // 关闭通道
        fileChannel.close();
    }
}
