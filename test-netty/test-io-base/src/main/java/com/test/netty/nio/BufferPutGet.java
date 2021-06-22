package com.test.netty.nio;

import java.nio.ByteBuffer;

public class BufferPutGet {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        // put
        buffer.putInt(1);
        buffer.putChar('a');
        buffer.putLong(123l);
        buffer.put("我是谁".getBytes());

        // get
        buffer.flip();
        System.out.println(buffer.getInt());
        System.out.println(buffer.getChar());
        System.out.println(buffer.getLong());

        byte[] bytes = new byte[1024];
        int len = buffer.limit() - buffer.position();
        buffer.get(bytes, 0, len);
        System.out.println(new String(bytes, 0, len));
    }
}
