package com.test.netty.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MMappedByteBuffer {
    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("hello.txt", "rw");
        FileChannel channel = randomAccessFile.getChannel();

        // 映射到缓冲区
        // mappedByteBuffer 实际类型 DirectByteBuffer：直接内存缓冲区
        java.nio.MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);

        // 操作缓冲区
        mappedByteBuffer.put(1, (byte) 9);
        mappedByteBuffer.put(2, (byte) 8);
//        mappedByteBuffer.put(10, (byte) 8);

        // 关闭通道
        channel.close();
    }
}
