package com.test;

import org.junit.Test;

import java.io.IOException;
import java.net.*;

import static java.net.InetAddress.getByName;

/**
 * UDP协议的网络编程
 */
public class UDPTest {

    // 发送端
    @Test
    public void sender() throws IOException {
        DatagramSocket socket = new DatagramSocket();

        String str = "我是UDP方式发送的导弹";
        byte[] data = str.getBytes();

        DatagramPacket packet = new DatagramPacket(data,
                0,
                data.length,
                InetAddress.getLocalHost(), 9090);

        socket.send(packet);

        socket.close();
    }

    // 接收端
    @Test
    public void receiver() throws IOException {
        DatagramSocket socket = new DatagramSocket(9090);

        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer, 0, buffer.length);

        socket.receive(packet);

        System.out.println(new String(packet.getData(), 0, packet.getLength()));

        socket.close();
    }
}
