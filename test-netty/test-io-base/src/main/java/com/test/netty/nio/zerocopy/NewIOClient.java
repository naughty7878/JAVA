package com.test.netty.nio.zerocopy;

import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

// transferTo: 14, 19, 8, 20, 15, 11, 9, 17, 8, 8 = 12
// write : 89, 91, 95, 71, 60, 124, 71, 126, 81, 88 = 89
public class NewIOClient {
    public static void main(String[] args) throws Exception {

        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 7001));
        String filename = "xxxx.zip";

        //得到一个文件channel
        FileChannel fileChannel = new FileInputStream(filename).getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(4096);
        long readCount;
        long transferCount = 0;

        //准备发送
        long startTime = System.currentTimeMillis();

        // 方式一：普通read/write传输 89ms
        while ((readCount = fileChannel.read(buffer)) >= 0) {
            transferCount += readCount;
            buffer.flip();
            socketChannel.write(buffer);
            buffer.clear();
        }

        // 方式二：transferTo 底层使用到零拷贝 12ms
        //在linux下一个transferTo 方法就可以完成传输
        //在windows 下 一次调用 transferTo 只能发送8m , 就需要分段传输文件, 而且要主要
        //传输时的位置 =》 课后思考...
//        long transferCount = fileChannel.transferTo(0, fileChannel.size(), socketChannel);

        System.out.println("发送的总的字节数 =" + transferCount + " 耗时:" + (System.currentTimeMillis() - startTime));

        //关闭
        fileChannel.close();

    }
}
