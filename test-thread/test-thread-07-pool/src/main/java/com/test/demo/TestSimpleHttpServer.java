package com.test.demo;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;

public class TestSimpleHttpServer {

    @Test
    public void server() {
        try {
            URL resource = Thread.currentThread().getContextClassLoader().getResource("");
            System.out.println(resource.getPath());
            SimpleHttpServer.setBasePath("/Users/h__d/Documents/git-repository/JAVA/test-thread/test-thread-07-pool/target/classes");
            SimpleHttpServer.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    这里涉及到的异常，应该使用try-catch-finally处理
     */
    @Test
    public void client() throws IOException {
        // 1.新建Socket
        Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 8080);
        // 2.获取一个输出流，用于输出数据
        OutputStream os = socket.getOutputStream();
        // 3.写出数据的操作
        os.write("beauty.jpg".getBytes());

//        // 2、输出流
//        OutputStream os = socket.getOutputStream();
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
}
