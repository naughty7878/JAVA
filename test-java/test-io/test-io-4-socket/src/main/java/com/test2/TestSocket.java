package com.test2;

import org.junit.Test;

import java.io.*;
import java.net.*;
import java.nio.Buffer;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class TestSocket {

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
                    try {
                        // 获取输入流
                        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                        // 接收数据
                        String s = null;
                        // 输入不知道什么时候到达末尾，所以这里会阻塞
                        while ((s = bufferedReader.readLine()) != null) {
                            System.out.println("收到来自：客户端 " + socket.getInetAddress() + " ,端口：" + socket.getPort()
                                    + " 的数据：\n" + s);
                            bufferedWriter.write(new Date().toString() + "\t\n");
                            bufferedWriter.flush();
                        }
                        System.out.println("结束：客户端 " + socket.getInetAddress() + " ,端口：" + socket.getPort());


                    } catch (IOException e) {
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
    public void client1() {
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
                    while (true) {
                        Thread.sleep(3000);
                        finalBufferedWriter.write("我是客户端：现在什么时间？\t\n");
                        finalBufferedWriter.flush();
                    }
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

            // 接收数据
            String s = null;
            // 输入不知道什么时候到达末尾，所以这里会阻塞
            while ((s = bufferedReader.readLine()) != null) {
                System.out.println("收到来自：服务器数据\n" + s);
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
