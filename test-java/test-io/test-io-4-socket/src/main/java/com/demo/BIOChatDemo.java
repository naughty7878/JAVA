package com.demo;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;


/**
 * BIO 聊天是demo
 * 用户可以登陆聊天室，发送消息，和收到其他人发送的消息
 *
 * 用户第一次登录，需要设置自己的用户名
 */
public class BIOChatDemo {

    // 发数据到用户流集合
    Map<String, BufferedWriter> map = new ConcurrentHashMap<>();

    @Test
    public void server() {
        ServerSocket serverSocket = null;
        try {
            // 创建ServerSocket
            serverSocket = new ServerSocket();
            InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 9999);
            serverSocket.bind(inetSocketAddress);

            while (true) {
                // 阻塞，获取连接
                Socket socket = serverSocket.accept();
                new Thread(() -> {
                    BufferedReader bufferedReader = null;
                    BufferedWriter bufferedWriter = null;
                    String username = null;
                    try {
                        // 获取输入流
                        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                        // 接收数据
                        String s = bufferedReader.readLine();
                        username = s;
                        String address = socket.getInetAddress().getHostAddress();
                        int port = socket.getPort();
                        map.put(username, bufferedWriter);
                        String prefix = username + "(" + address + ":" + port +")：";
                        System.out.println("客户端：" + prefix +" 登录");
                        bufferedWriter.write("欢迎登录-" + prefix +"\n");
                        bufferedWriter.flush();

                        while ((s = bufferedReader.readLine()) != null) {
                            // 广播给其他客户端
                            for (Map.Entry<String, BufferedWriter> entry : map.entrySet()) {
                                String k = entry.getKey();
                                BufferedWriter bw = entry.getValue();
                                // 不发给自己
                                if (!prefix.startsWith(k)) {
                                    bw.write(prefix + s + "\n");
                                    bw.flush();
                                }
                            }
                        }
                        System.out.println("客户端：" + prefix +" 断开连接");
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {

                        // 移除队列
                        if(map.keySet().contains(username)) {
                            map.remove(username);
                        }
                        // 关闭资源
                        if (bufferedWriter != null) {
                            try {
                                bufferedWriter.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
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

                }).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Test
    public void client() {
        Socket socket = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
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
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            // 循环写出数据，询问时间
            BufferedWriter finalBufferedWriter = bufferedWriter;
            new Thread(() -> {
                try {
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("请输入用户名：");
                    while (scanner.hasNext()) {
                        finalBufferedWriter.write(scanner.next() +"\n");
                        finalBufferedWriter.flush();
                    }
                } catch (IOException  e) {
                    e.printStackTrace();
                }
            }).start();

            // 接收数据
            String s = null;
            // 输入不知道什么时候到达末尾，所以这里会阻塞
            while ((s = bufferedReader.readLine()) != null) {
                System.out.println(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
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
