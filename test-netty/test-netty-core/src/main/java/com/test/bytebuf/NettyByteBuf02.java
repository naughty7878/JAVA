package com.test.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

public class NettyByteBuf02 {

    public static void main(String[] args) {
        // 创建ByteBuf
        ByteBuf byteBuf = Unpooled.copiedBuffer("hello world", CharsetUtil.UTF_8);
        
        // 相关方法
        if(byteBuf.hasArray()) {
            // 转字符串
            String content = new String(byteBuf.array(), CharsetUtil.UTF_8);
            System.out.println("content = " + content);
            System.out.println("byteBuf = " + byteBuf);

            System.out.println("byteBuf.arrayOffset() = " + byteBuf.arrayOffset());
            System.out.println("byteBuf.readerIndex() = " + byteBuf.readerIndex());
            System.out.println("byteBuf.writerIndex() = " + byteBuf.writerIndex());
            System.out.println("byteBuf.capacity() = " + byteBuf.capacity());

            System.out.println("byteBuf.getByte(0) = " + byteBuf.getByte(0));

            System.out.println("byteBuf.readableBytes() = " + byteBuf.readableBytes());

            // 按照某个范围读取
            System.out.println("byteBuf.getCharSequence() = " + byteBuf.getCharSequence(1, 5,CharsetUtil.UTF_8));
            System.out.println("byteBuf.getCharSequence() = " + byteBuf.getCharSequence(5, 9,CharsetUtil.UTF_8));
        }
        
    }
}
