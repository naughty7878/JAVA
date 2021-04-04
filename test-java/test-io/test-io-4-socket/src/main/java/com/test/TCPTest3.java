package com.test;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 实现TCP的网络编程
 * 例题3：从客户端发送文件给服务端，服务端保存到本地。并返回“发送成功”给客户端。
 */
public class TCPTest3 {

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

        // 关闭数据输出
        socket.shutdownOutput();

        InputStream is = socket.getInputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer2 = new byte[1024];
        int len2;
        while ((len2 = is.read(buffer2)) != -1){
            baos.write(buffer2, 0, len2);
        }

        System.out.println(baos.toString());

        // 5.关闭资源
        baos.close();
        is.close();

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
        FileOutputStream fos = new FileOutputStream("beauty3.jpg");

        byte[] buffer = new byte[1024];
        int len;
        while ((len = is.read(buffer)) != -1){
            fos.write(buffer, 0, len);
        }

        System.out.println("图片传输完成");

        OutputStream os = socket.getOutputStream();
        os.write("你好，美女，照片我已收到，非常漂亮！".getBytes());

        os.close();
        fos.close();
        is.close();
        socket.close();
        ss.close();
    }
}
