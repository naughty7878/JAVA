package com.test.nio2;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 一、使用NIO 完成网络通信的三个核心：
 * <p>
 * 1、通道（Channel）：负责链接
 * ｜--SelectableChannel
 * ｜--SocketChannel
 * ｜--ServerSocketChannel
 * ｜--DatagramChannel
 * <p>
 * ｜--Pipe.SinkChannel
 * ｜--Pipe.SourceChannel
 * <p>
 * 2、缓冲区（Buffer）：负责数据的存取
 * <p>
 * 3、选择器（选择器）：是SelectableChannel 的多路复用器。用于监控 SelectableChannel 的IO状况
 */
public class TestBlockingNIO {

    public static void main(String[] args) {
        TestBlockingNIO test = new TestBlockingNIO();
        // 服务端线程
        new Thread(){
            @Override
            public void run() {
                test.server();
            }
        }.start();

        try {
            // 休眠3s
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 客户端线程
        new Thread(){
            @Override
            public void run() {
                test.client();
            }
        }.start();
    }

    // 客户端
    @Test
    public void client() {
        SocketChannel sChannel = null;
        FileChannel inChannel = null;
        try {
            // 1、获取通道
            sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));


            inChannel = FileChannel.open(Paths.get("tomcat.png"), StandardOpenOption.READ);

            // 2、分配指定大小的缓冲数据
            ByteBuffer buf = ByteBuffer.allocate(1024);

            // 3、读取本地文件，并发送到服务端去
            while (inChannel.read(buf) != -1) {
                buf.flip();
                sChannel.write(buf);
                buf.clear();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if (sChannel != null) {
                try {
                    sChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inChannel != null) {
                try {
                    inChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 服务端
    @Test
    public void server() {
        // 1、获取通道
        ServerSocketChannel ssChannel = null;
        FileChannel outChannel = null;
        SocketChannel sChannel = null;
        try {
            ssChannel = ServerSocketChannel.open();

            outChannel = FileChannel.open(Paths.get("tomcat2.png"), StandardOpenOption.READ, StandardOpenOption.CREATE, StandardOpenOption.WRITE);

            // 2、绑定连接端口
            ssChannel.bind(new InetSocketAddress(9898));

            // 3、获取客户端连接的通道
            sChannel = ssChannel.accept();

            // 4、分配指定大小缓冲区
            ByteBuffer buf = ByteBuffer.allocate(1024);

            // 5、连接客户端的数据，并保存到本地
            while (sChannel.read(buf) != -1) {
                buf.flip();
                outChannel.write(buf);
                buf.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 6、关闭通道
            if(outChannel != null) {
                try {
                    outChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(sChannel != null) {
                try {
                    sChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(ssChannel != null) {
                try {
                    ssChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
