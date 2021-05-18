package com.test.demo;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 一个基于线程池技术的简单Web服务器
 */
public class SimpleHttpServer {
    // 处理HttpRequest 的线程池
    static ThreadPool<HttpRequestHandler> threadPool = new DefaultThreadPool<HttpRequestHandler>(1);
    // SimpleHttpServer 的根路径
    static String basePath;
    static ServerSocket serverSocket;
    // 服务监听端口
    static int port = 8080;

    public static void setPort(int port) {
        if (port > 0) {
            SimpleHttpServer.port = port;
        }
    }

    public static void setBasePath(String basePath) {
        if (basePath != null && new File(basePath).exists() && new File(basePath).isDirectory()) {
            SimpleHttpServer.basePath = basePath;
        }

    }

    // 启动 SimpleHttpServer
    public static void start() throws Exception {
        serverSocket = new ServerSocket(port);
        Socket socket = null;
        while ((socket = serverSocket.accept()) != null) {
            // 接受一个客户短 Socket，生成一个HttpRequestHandler，放入线程池中执行
            threadPool.execute(new HttpRequestHandler(socket));
        }
        serverSocket.close();
    }

    static class HttpRequestHandler implements Runnable {

        private Socket socket;

        public HttpRequestHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            String line = null;
            BufferedReader br = null;
            BufferedReader read = null;
            PrintWriter out = null;
            InputStream in = null;
            try {
                read = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String header = read.readLine();
                System.out.println("header = " + header);
                // 由相对路径计算出绝对路径
                String filePath = basePath + header.split(" ")[1];
                System.out.println("filePath = " + filePath);
                out = new PrintWriter(socket.getOutputStream());
                // 如果请求资源的后缀为jpg 或者 ico，则读取资源并输出
                if(filePath.endsWith("jpg") || filePath.endsWith("ico")){
                    in = new FileInputStream(filePath);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    int i = 0;
                    while ((i = in.read()) != -1) {
                        baos.write(i);
                    }
                    byte[] array = baos.toByteArray();
                    out.println("HTTP/1.1 200 OK");
                    out.println("Server: Molly");
                    out.println("Content-Type: image/jpeg");
                    out.println("Content-Length: " + array.length);
                    out.println("");
                    socket.getOutputStream().write(array,0, array.length);
                    System.out.println(array.length);
                } else {
                    br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
                    out = new PrintWriter(socket.getOutputStream());
                    out.println("HTTP/1.1 200 OK");
                    out.println("Server: Molly");
                    out.println("Content-Type: text/html; charset=UTF-8");
                    out.println("");
                    while ((line = br.readLine()) != null){
                        out.println(line);
                    }
                }
                out.flush();
            } catch (IOException e) {
                out.println("HTTP/1.1 500");
                out.println("");
                out.flush();
            } finally {
//                close(br, in, read, out, socket);
            }
        }
    }

    // 关闭流或者 Socket
    private static void close(Closeable... closeables) {
        if(closeables != null) {
            for (Closeable closeable : closeables) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
