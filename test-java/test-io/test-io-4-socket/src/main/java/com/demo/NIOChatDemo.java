package com.demo;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.*;
import java.util.concurrent.*;

/**
 * NIO 聊天是demo
 * 用户可以登陆聊天室，发送消息，和收到其他人发送的消息
 *
 * 用户第一次登录，需要设置自己的用户名
 */
public class NIOChatDemo {

    Selector selector = null;
    ServerSocketChannel ssChannel = null;

    ExecutorService readPool = Executors.newFixedThreadPool(10);
    Set<SelectionKey> keysSet = new CopyOnWriteArraySet<>();
    Map<String, String> nameMap = new ConcurrentHashMap<>();

    /**
     * 服务器
     */
    @Test
    public void server() {

        try {
            // 创建ServerSocketChannel
            // 1、获取通道
            ssChannel = ServerSocketChannel.open();
            // 2、切换非阻塞模式
            ssChannel.configureBlocking(false);
            // 3、绑定连接
            InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 9999);
            ssChannel.bind(inetSocketAddress);

            // 4、获取选择器
            selector = Selector.open();
            // 5、将通道注册到选择器上，指定监听事件
            // SelectionKey.OP_ACCEPT: 接收
            ssChannel.register(selector, SelectionKey.OP_ACCEPT);

            // 6、轮询式的获取选择器上已经"准备就绪"的事件
            while (true) {
                // 调用NIO选择器，选取方法
                // 阻塞到至少有一个通道在你注册的事件上就绪了
                int readyNum = selector.select();
                if (readyNum == 0) continue;

                // 7、获取当前选择器中所注册的"选择键（已就绪的监听事件）"
                Set<SelectionKey> selected = selector.selectedKeys();
                Iterator<SelectionKey> selectedKeys = selected.iterator();

                boolean newKeyFlag = false;
                // 迭代获取
                while (selectedKeys.hasNext()) {
                    // 8、获取准备就绪的事件的key
                    SelectionKey key = selectedKeys.next();
                    // 将key从集合中移除
                    selectedKeys.remove();
                    if(keysSet.contains(key)) {continue;}
                    newKeyFlag =true;

                    // 9、判断是什么事件准备就绪
                    if (key.isAcceptable()) {// 接收就绪
                        acceptable(key);
                    } else if (key.isConnectable()) { // 接收就绪
                        // 只有客户端SocketChannel会注册该操作，当客户端调用SocketChannel.connect()时，该操作会就绪。
//                        System.out.println("SocketChannel已注册");
                    } else if (key.isReadable()) {// 读就绪
                        // 将key添加到集合中，表示在处理中
                        keysSet.add(key);
                        readPool.submit(() -> {
                            try {
                                readable(nameMap, key);
                                // 处理完，移除key
                                keysSet.remove(key);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
                    }
                }
                if(!newKeyFlag) {
                    // 没有新key，表示其他key都在处理中
                    Thread.sleep(1000);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (selector != null) {
                try {
                    selector.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ssChannel != null) {
                try {
                    ssChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 接收连接
     */
    private void acceptable(SelectionKey key) {
//        System.out.println("-------------通道可接收-------------");
        SocketChannel sChannel = null;
        try {
            // 10、若"接收就绪"，就获取客户端的连接
            Selector selector = key.selector();
            ServerSocketChannel server = (ServerSocketChannel) key.channel();
            sChannel = server.accept();

            // 11、切换非阻塞模式
            sChannel.configureBlocking(false);
            // 12、将该客户端通道注册到选择器上
            sChannel.register(selector, SelectionKey.OP_READ);
        } catch (IOException e) {
            e.printStackTrace();
            if (sChannel != null && sChannel.isOpen()) {
                try {
                    sChannel.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    /**
     * 读取客户端数据
     */
    private void readable(Map<String, String> nameMap, SelectionKey key) throws IOException, InterruptedException {
//        System.out.println("-------------通道可读-------------");
        // 13、获取当前选择器上"读就绪"状态的通道
        SocketChannel sChannel = (SocketChannel) key.channel();

        // 14、读取数据
        ByteBuffer lenBuffer = ByteBuffer.allocate(4);
        ByteBuffer contentBuffer = ByteBuffer.allocate(1024);

        String nameKey = sChannel.getRemoteAddress().toString();
        String name = nameMap.get(nameKey);

        while (true) {
            // 获取数据长度
            String content = receiveData(key, lenBuffer, contentBuffer);
            if(content == null) {
                return;
            }

            String result = null;
            // 获取名称
            if(name == null) {
                nameMap.put(nameKey, content + "(" + nameKey +")：");
                result = "欢迎登录-" + content;
                System.out.println(content + " - 登录服务器");
                sendData(result, sChannel);
            }else {
                // 发给其他用户数据
                result = name + content;
                sendToOther(key, result);
            }
        }

    }

    private void sendToOther(SelectionKey key, String result) throws IOException {

        Selector selector = key.selector();
        for (SelectionKey sk : selector.keys()){
            SelectableChannel channel = sk.channel();
            if (channel instanceof SocketChannel && key != sk) {
                SocketChannel dest = (SocketChannel) channel;
                sendData(result, dest);
            }
        }
    }

    /**
     * 客户端
     */
    @Test
    public void client() {

        Set<SelectionKey> keysSet = new CopyOnWriteArraySet<>();

        SocketChannel sChannel = null;
        Selector selector = null;
        try {
            // 创建SocketChannel
            sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9999));
            sChannel.configureBlocking(false);
            selector = Selector.open();
            sChannel.register(selector, SelectionKey.OP_READ);

            // 先数据线程
            clientWrite(sChannel);

            while (true) {
                int readyNum = selector.select();
                if (readyNum == 0) continue;

                Set<SelectionKey> selected = selector.selectedKeys();
                Iterator<SelectionKey> selectedKeys = selected.iterator();

                boolean newKeyFlag = false;
                // 迭代获取
                while (selectedKeys.hasNext()) {
                    // 8、获取准备就绪的事件的key
                    SelectionKey key = selectedKeys.next();
                    // 将key从集合中移除
                    selectedKeys.remove();
                    if(keysSet.contains(key)) {continue;}
                    newKeyFlag =true;

                    if (key.isReadable()) {// 读就绪
                        clientRead(key);
                    }
                }
                if (!newKeyFlag) {
                    // 没有新key，表示其他key都在处理中
                    // 则休眠一会
                    Thread.sleep(1000);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (selector != null) {
                try {
                    selector.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (sChannel != null) {
                try {
                    sChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void clientWrite(SocketChannel sChannel) {
        // 写数据线程
        SocketChannel finalSChannel = sChannel;
        new Thread(() -> {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("请输入用户名：");
                while (scanner.hasNext()) {
                    String content = scanner.next();
                    sendData(content, finalSChannel);
                }
            } catch (IOException  e) {
                e.printStackTrace();
            } finally {
                try {
                    finalSChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    private void clientRead(SelectionKey key) throws IOException, InterruptedException {
        // 13、获取当前选择器上"读就绪"状态的通道


        // 14、读取数据
        ByteBuffer lenBuffer = ByteBuffer.allocate(4);
        ByteBuffer contentBuffer = ByteBuffer.allocate(1024);

        while (true) {
            String s = receiveData(key, lenBuffer, contentBuffer);
            if (s == null) return;
            System.out.println(s);
        }
    }

    private String receiveData(SelectionKey key, ByteBuffer lenBuffer, ByteBuffer contentBuffer) throws IOException, InterruptedException {
        SocketChannel sChannel = (SocketChannel) key.channel();

        // 获取数据长度
        int read = sChannel.read(lenBuffer);
        if(read == -1) {
            // 客户端关闭
            sChannel.close();
            key.cancel();
        }
        if (lenBuffer.position() == 0) {
            // 没有数据可以读了
            return null;
        }
        while (lenBuffer.remaining() != 0) {
            // 有出现过未读取到4个字节的情况，继续读取
            sChannel.read(lenBuffer);
            Thread.sleep(10);
            System.out.println(Thread.currentThread().getName() + "长度出现未读完全的情况！");
        }
        // 得到内容字节长度
        lenBuffer.flip();
        int l = lenBuffer.asIntBuffer().get();
        lenBuffer.clear();
        // 内容缓存
        sChannel.read(contentBuffer);
        while (contentBuffer.position() != l) {
            // 有出现过未读完全的情况，继续读取
            sChannel.read(contentBuffer);
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + "内容出现未读完全的情况！");
        }
        contentBuffer.flip();
        String content = new String(contentBuffer.array(), 0, contentBuffer.limit());
        contentBuffer.clear();
        return content;
    }

    // 发送数据
    private void sendData(String result, SocketChannel dest) throws IOException {
        int length = result.getBytes().length;
        ByteBuffer buf = ByteBuffer.allocate(length + 4);
        buf.putInt(length);
        buf.put(result.getBytes());
        buf.flip();
        dest.write(buf);
        buf.clear();
    }
}
