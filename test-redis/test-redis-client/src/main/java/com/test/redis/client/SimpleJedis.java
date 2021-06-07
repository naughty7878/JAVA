package com.test.redis.client;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class SimpleJedis {

    private Socket socket = null;

    public SimpleJedis() {
        try {
            socket = new Socket("127.0.0.1", 6379);

            socket.setReuseAddress(true);
            socket.setKeepAlive(true);
            socket.setTcpNoDelay(true);
            socket.setSoLinger(true, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String set(final String key, String value) throws IOException {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("*3").append("\r\n");

        stringBuilder.append("$3").append("\r\n");

        stringBuilder.append("SET").append("\r\n");

        stringBuilder.append("$").append(key.length()).append("\r\n");

        stringBuilder.append(key).append("\r\n");

        stringBuilder.append("$").append(value.length()).append("\r\n");

        stringBuilder.append(value).append("\r\n");
        socket.getOutputStream().write(stringBuilder.toString().getBytes());

        InputStream inputStream = socket.getInputStream();
        byte b[] = new byte[1024];
        int len = inputStream.read(b);
        return new String(b, 0, len);
    }

    public String get(final String key) throws IOException {

        StringBuilder sb = new StringBuilder();

        sb.append("*2").append("\r\n");

        sb.append("$3").append("\r\n");

        sb.append("GET").append("\r\n");

        sb.append("$").append(key.length()).append("\r\n");

        sb.append(key).append("\r\n");

        socket.getOutputStream().write(sb.toString().getBytes());

        InputStream inputStream = socket.getInputStream();
        byte b[] = new byte[1024];
        int len = inputStream.read(b);
        return new String(b, 0, len);
    }

    public static void main(String[] args) throws IOException {
        SimpleJedis myJedis = new SimpleJedis();

        System.out.println(myJedis.set("aa", "11"));
        System.out.println("-----------------");
        System.out.println(myJedis.get("aa"));
        System.out.println("-----------------");
    }
}