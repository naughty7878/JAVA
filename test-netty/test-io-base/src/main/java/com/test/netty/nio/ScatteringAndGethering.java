package com.test.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

public class ScatteringAndGethering {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(9000);
        serverSocketChannel.bind(inetSocketAddress);

        ByteBuffer[] buffers = new ByteBuffer[2];
        buffers[0] = ByteBuffer.allocate(3);
        buffers[1] = ByteBuffer.allocate(5);

        // 客户端连接
        SocketChannel socketChannel = serverSocketChannel.accept();
        int messageLenth = 8;
        while (true) {
            int byteRead = 0;
            while (byteRead < messageLenth) {
                long len = socketChannel.read(buffers);
                byteRead += len;
                System.out.println("byteRead = " + byteRead);

                Arrays.asList(buffers).stream().map(buffer ->
                        "position == " + buffer.position() + ", limit == " + buffer.limit()
                ).forEach(System.out::println);
            }

            Arrays.asList(buffers).forEach(
                    buffer -> buffer.flip()
            );

            long byteWirte = 0;
            while (byteWirte < messageLenth) {
                long lenWirte = socketChannel.write(buffers);
                byteWirte += lenWirte;
            }

            Arrays.asList(buffers).forEach(
                    buffer -> buffer.clear()
            );

            System.out.println("byteRead = " + byteRead +
                    ", nbyteWirte = " + byteWirte +
                    ", nmessageLenth = " + messageLenth);
        }
    }
}
