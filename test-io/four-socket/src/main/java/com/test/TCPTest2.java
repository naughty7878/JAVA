package com.test;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 实现TCP的网络编程
 * 例题2：客户端发送文件给服务端，服务端将文件保存在本地。
 */
public class TCPTest2 {

    /*
    这里涉及到的异常，应该使用try-catch-finally处理
     */
    @Test
    public void client() throws IOException {
        // 1.新建Socket
        Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 9090);
        // 2、输出流
        OutputStream os = socket.getOutputStream();
        // 3、文件流
        FileInputStream fis = new FileInputStream("beauty.jpg");
        // 4.输出
        byte[] buffer = new byte[1024];
        int len;
        while ((len = fis.read(buffer)) != -1) {
            os.write(buffer, 0, len);
        }
        // 5.关闭资源
        fis.close();
        os.close();
        socket.close();
    }

    /*
    这里涉及到的异常，应该使用try-catch-finally处理
     */
    @Test
    public void server() throws IOException {
        ServerSocket ss = new ServerSocket(9090);
        Socket socket = ss.accept();

        InputStream is = socket.getInputStream();
        FileOutputStream fos = new FileOutputStream("beauty2.jpg");

        byte[] buffer = new byte[1024];
        int len;
        while ((len = is.read(buffer)) != -1){
            fos.write(buffer, 0, len);
        }

        fos.close();
        is.close();
        socket.close();
        ss.close();
    }
}
