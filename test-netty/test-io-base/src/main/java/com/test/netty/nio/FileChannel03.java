package com.test.netty.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannel03 {

    public static void main(String[] args) throws IOException {

        FileInputStream fileInputStream = new FileInputStream("hello.txt");
        FileChannel fileChannel1 = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream("hello2.txt");
        FileChannel fileChannel2 = fileOutputStream.getChannel();

        ByteBuffer buff = ByteBuffer.allocate(1024);

        int len = 0;
        // 读数据
        while ((len = fileChannel1.read(buff)) != -1) {
            // 写数据
            buff.flip();
            fileChannel2.write(buff);
            buff.clear();
        }

        fileChannel2.close();
        fileChannel1.close();
    }
}
