package com.demo;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

public class TestOther {


    @Test
    public void test3() throws IOException {
        BlockingQueue<String> queue = new LinkedBlockingDeque<>();
        System.out.println(Math.abs("test-abc".hashCode()) % 50);
    }

    @Test
    public void test2() throws IOException {
        String abc = "abc";
        int length = Integer.MAX_VALUE;
        System.out.println("length = " + length);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream output = new DataOutputStream(baos);
        output.writeInt(length);

        System.out.println(baos.toByteArray().length);
//
//        baos.write(length);
//        try {
//            baos.write("dbc".getBytes());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println(baos.toByteArray().length);
//        System.out.println(baos.toString());
    }

    @Test
    public void test() {
        Map<String, String> map = new ConcurrentHashMap<>();
        map.put("zzzz", "abc");
        map.put("xxxxx", "abc");
        map.put("ccccc", "abc");

        while (true) {
            System.out.println("开始遍历" + map.size());
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String k = entry.getKey();
                String v = entry.getValue();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                map.put(new Date().toString(), "abc");
            }

            System.out.println("结束遍历" + map.size());
        }

    }

    @org.junit.Test
    public void server() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String next = scanner.next();
            System.out.println(next);
        }

    }


    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        String var = "ABC";

    }


}
