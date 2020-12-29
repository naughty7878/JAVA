package com.test.nio2;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

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
public class TestNonBlockingNIO {

    // 服务端
    // 此处异常，需要进行try-catch块处理，本例是为了查看方便
    @Test
    public void server() throws IOException, InterruptedException {
        // 1、获取通道
        ServerSocketChannel ssChannel = ServerSocketChannel.open();
        // 2、切换非阻塞模式
        ssChannel.configureBlocking(false);
        // 3、绑定连接
        ssChannel.bind(new InetSocketAddress(9898));
        // 4、获取选择器
        Selector selector = Selector.open();
        // 5、将通道注册到选择器上，指定监听事件
        // SelectionKey.OP_ACCEPT: 接收
        ssChannel.register(selector, SelectionKey.OP_ACCEPT);

        // 6、轮询式的获取选择器上已经"准备就绪"的事件
        while (true) {
            Thread.sleep(100);
            int readyNum = selector.select();
            if (readyNum == 0) {
                continue;
            }
            System.out.println("就绪事件数量：" + readyNum);

            // 7、获取当前选择器中所注册的"选择键（已就绪的监听事件）"
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> it = selectionKeys.iterator();

            // 迭代获取
            while (it.hasNext()) {
                // 8、获取准备就绪的事件的key
                SelectionKey key = it.next();

                System.out.println("key ==== " + key);
                // 9、判断是什么事件准备就绪
                if (key.isAcceptable()) {// 接收就绪
                    System.out.println("-------------通道可接收-------------");
                    // 10、若"接收就绪"，就获取客户端的连接
                    SocketChannel sChannel = ssChannel.accept();
                    // 11、切换非阻塞模式
                    sChannel.configureBlocking(false);
                    // 12、将该通道注册到选择器上
                    sChannel.register(selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {// 读就绪
                    System.out.println("-------------通道可读-------------");
                    // 13、获取当前选择器上"读就绪"状态的通道
                    SocketChannel sChannel = (SocketChannel) key.channel();
                    // 14、读取数据
                    ByteBuffer buf = ByteBuffer.allocate(1024);
                    int len = 0;
                    while ((len = sChannel.read(buf)) != -1) {
                        buf.flip();
                        System.out.println(new String(buf.array(), 0, len));
                        buf.clear();
                    }
                    sChannel.close();
                }
                // 已经处理过的，取消选着键 SelectionKey sk = it.next();
                it.remove();
            }

        }

    }

    // 客户端
    // 此处异常，需要进行try-catch块处理，本例是为了查看方便
    @Test
    public void client() throws IOException {
        // 1、获取通道
        SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));

        // 2、切换非阻塞模式
        sChannel.configureBlocking(false);

        // 3、指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);

        // 4、发送数据给服务端
        buf.put(new Date().toString().getBytes());

        buf.flip();

        sChannel.write(buf);
        buf.clear();

        // 5、关闭通道
        sChannel.close();
    }

    // 客户端
    // 此处异常，需要进行try-catch块处理，本例是为了查看方便
    @Test
    public void client2() throws IOException {
        // 1、获取通道
        SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));

        // 2、切换非阻塞模式
        sChannel.configureBlocking(false);

        // 3、指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);

        // 4、发送数据给服务端
        Scanner scan = new Scanner(System.in);

        System.out.println("请输入：");
        while (scan.hasNext()) {

            String str = scan.next();
            String content = new Date().toString() + "----" + str;
            System.out.println(content);
            buf.put(content.getBytes());
            buf.flip();
            sChannel.write(buf);
//            sChannel.shutdownInput();
            buf.clear();
            System.out.println("-------");
        }

        // 5、关闭通道
        sChannel.close();
    }

}
