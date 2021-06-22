package com.test.netty.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannel04 {

    public static void main(String[] args) throws IOException {

        FileInputStream fileInputStream = new FileInputStream("hello.txt");
        FileChannel fileChannel1 = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream("hello2.txt");
        FileChannel fileChannel2 = fileOutputStream.getChannel();

        // 通道传输
        fileChannel2.transferFrom(fileChannel1, 0, fileChannel1.size());

        fileChannel2.close();
        fileChannel1.close();
    }
}
