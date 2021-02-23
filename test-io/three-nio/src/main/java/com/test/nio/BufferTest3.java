package com.test.nio;

import org.junit.Test;

import java.nio.ByteBuffer;

public class BufferTest3 {
    @Test
    public void test01(){
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        System.out.println("position === " + buffer.position());
        System.out.println("limit === " + buffer.limit());
        System.out.println("capacity === " + buffer.capacity());


        buffer.put("hello world!!!".getBytes());

        System.out.println("position === " + buffer.position());
        System.out.println("limit === " + buffer.limit());
        System.out.println("capacity === " + buffer.capacity());

        buffer.flip();

        System.out.println("position === " + buffer.position());
        System.out.println("limit === " + buffer.limit());
        System.out.println("capacity === " + buffer.capacity());

        byte[] bytes = new byte[5];
        int len = 0;
        while (buffer.hasRemaining()) {
            int remaining = buffer.remaining();
            len = remaining > 5 ? 5 : remaining;
            buffer.get(bytes, 0, len);
            System.out.print(new String(bytes, 0, len));
        }
        System.out.println();

        System.out.println("position === " + buffer.position());
        System.out.println("limit === " + buffer.limit());
        System.out.println("capacity === " + buffer.capacity());

        buffer.rewind();

        System.out.println("position === " + buffer.position());
        System.out.println("limit === " + buffer.limit());
        System.out.println("capacity === " + buffer.capacity());
    }
}
