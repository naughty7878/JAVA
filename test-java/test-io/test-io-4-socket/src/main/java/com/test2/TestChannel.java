package com.test2;

import org.junit.Test;
import sun.jvm.hotspot.runtime.ServiceThread;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.*;

public class TestChannel {


    @Test
    public void server() {
        BlockingQueue<String> queue = new LinkedBlockingDeque<>();
        ExecutorService readPool = Executors.newSingleThreadExecutor();
        ExecutorService writePool = Executors.newSingleThreadExecutor();
        Set<SelectionKey> keysSet = new CopyOnWriteArraySet<>();
        ServerSocketChannel ssChannel = null;
        Selector selector = null;
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
                    selectedKeys.remove();

                    if(keysSet.contains(key)) {continue;}
                    newKeyFlag =true;
                    // 9、判断是什么事件准备就绪
                    if (key.isAcceptable()) {// 接收就绪
                        acceptable(key);
                    } else if (key.isConnectable()) { // 接收就绪
                        // 只有客户端SocketChannel会注册该操作，当客户端调用SocketChannel.connect()时，该操作会就绪。
                        System.out.println("SocketChannel已注册");
                    } else if (key.isReadable()) {// 读就绪
                        // 将key添加到集合中，表示在处理中
                        keysSet.add(key);
                        readPool.submit(() -> {
                            try {
                                readable(queue, key);
                                // 处理完，移除key
                                keysSet.remove(key);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
                    } else if (key.isWritable()) {// 写就绪
                        writePool.submit(() -> {
                            try {
                                writable(queue, key);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
                    }
                    // 已经处理过的，取消选着键 SelectionKey sk = it.next();
//                    selectedKeys.remove();
                }
                if(!newKeyFlag) {
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
            if (ssChannel != null) {
                try {
                    ssChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


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

    private void readable(BlockingQueue<String> queue, SelectionKey key) throws IOException, InterruptedException {
        System.out.println("-------------通道可读-------------");
        // 13、获取当前选择器上"读就绪"状态的通道
        SocketChannel sChannel = (SocketChannel) key.channel();
        // 14、读取数据
        ByteBuffer lenBuffer = ByteBuffer.allocate(4);

        while (true) {
            // 获取数据长度
            int read = sChannel.read(lenBuffer);
            if(read == -1) {
                // 客户端关闭
                sChannel.close();
                key.cancel();
            }
            if (lenBuffer.position() == 0) {
                // 没有数据可以读了
                return;
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
            ByteBuffer buf = ByteBuffer.allocate(l);
            sChannel.read(buf);
            while (buf.remaining() != 0) {
                // 有出现过未读完全的情况，继续读取
                sChannel.read(buf);
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + "内容出现未读完全的情况！");
            }
            buf.flip();
            String s = new String(buf.array());
            System.out.println(s);
            queue.put(s);
            buf.clear();

            // 写回数据
            String s1 = new Date().toString() + "\n";
            int length = s1.getBytes().length;
            buf = ByteBuffer.allocate(length + 4);
            buf.putInt(length);
            buf.put(s1.getBytes());
            buf.flip();
            sChannel.write(buf);
            buf.clear();
        }

    }

    private void writable(BlockingQueue<String> queue, SelectionKey key) throws IOException, InterruptedException {
        System.out.println("-------------通道可读-------------");
        // 13、获取当前选择器上"写就绪"状态的通道
        SocketChannel sChannel = (SocketChannel) key.channel();
        // 写数据
        ByteBuffer buf = ByteBuffer.allocate(4);
        String str = queue.take();
        if (str != null) {
            // 长度
            buf.putInt(str.length());
            // 内容
            buf.put(str.getBytes());
            // 写入通道
            buf.flip();
            sChannel.write(buf);
            buf.clear();
        }
    }

    @Test
    public void client() {
        Socket socket = null;
        DataInputStream dataInputStream = null;
        DataOutputStream dataOutputStream = null;
        try {
            // 创建Socket
            socket = new Socket();
            InetAddress inetAddress = InetAddress.getByName("127.0.0.1");
            // 客户端socket不绑定端口，本地会随机产生一个端口
//            InetSocketAddress inetSocketAddress = new InetSocketAddress(inetAddress, 19994);
//            socket.bind(inetSocketAddress);
            // 连接
            socket.connect(new InetSocketAddress("127.0.0.1", 9999));

            // 获取输出流
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());


            // 循环写出数据，询问时间
            DataOutputStream finalDataOutputStream = dataOutputStream;
            new Thread(() -> {
                try {
                    while (true) {
                        // int 4个字节
                        int len = "我是客户端：现在什么时间？".getBytes().length;
                        finalDataOutputStream.writeInt(len);
                        finalDataOutputStream.write("我是客户端：现在什么时间？".getBytes());
                        finalDataOutputStream.flush();

                        Thread.sleep(3000);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

            // 接收数据
            while (true) {
                int len = dataInputStream.readInt();
                byte[] buf = new byte[len];
                int size = dataInputStream.read(buf);
                System.out.println("收到来自：服务器数据\n" + "大小为：" + len + "\t" + new String(buf, 0, size));
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (dataOutputStream != null) {
                try {
                    dataOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (dataInputStream != null) {
                try {
                    dataInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
