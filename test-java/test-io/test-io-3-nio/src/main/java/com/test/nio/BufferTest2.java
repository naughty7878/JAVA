package com.test.nio;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.ByteBuffer;

public class BufferTest2 {

    public static void main(String[] args) {
        BufferTest2 test = new BufferTest2();
        test.test1();
    }

    @Test
    public void test1() {

        // 申请一个非直接缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        System.out.println("-----------FIRST------------");
        System.out.println(buffer.mark());
        System.out.println("position === " + buffer.position());
        System.out.println("limit === " + buffer.limit());
        System.out.println("capacity === " + buffer.capacity());

        // 利用put()，将数据存入缓冲区
        buffer.put("hello world".getBytes());

        System.out.println("-----------SECOND------------");
        System.out.println(buffer.mark());
        System.out.println("position === " + buffer.position());
        System.out.println("limit === " + buffer.limit());
        System.out.println("capacity === " + buffer.capacity());

        // 利用flip()，切换到读的模式
        buffer.flip();

        System.out.println("-----------THIRD------------");
        System.out.println(buffer.mark());
        System.out.println("position === " + buffer.position());
        System.out.println("limit === " + buffer.limit());
        System.out.println("capacity === " + buffer.capacity());


        // get() 获取缓存数据，haiRemainning是否还有剩余数据，remaining剩余数据长度
        byte[] dst = new byte[5];
        int len;
        while (buffer.hasRemaining()) {
            len = dst.length > buffer.remaining() ? buffer.remaining() : dst.length;
            buffer.get(dst, 0, len);
            System.out.print(new String(dst, 0, len));
        }
        System.out.println();

        System.out.println("-----------FOURTH------------");
//        System.out.println(buffer.mark());
        System.out.println("position === " + buffer.position());
        System.out.println("limit === " + buffer.limit());
        System.out.println("capacity === " + buffer.capacity());


        // reset 重置到mark的位置
        buffer.reset();

        System.out.println("-----------FIFTH------------");
        System.out.println(buffer.mark());
        System.out.println("position === " + buffer.position());
        System.out.println("limit === " + buffer.limit());
        System.out.println("capacity === " + buffer.capacity());


        // rewind 恢复到读模式，可以重复读数据
        buffer.rewind();
        while (buffer.hasRemaining()) {
            len = dst.length > buffer.remaining() ? buffer.remaining() : dst.length;
            buffer.get(dst, 0, len);
            System.out.print(new String(dst, 0, len));
        }
        System.out.println();

        System.out.println("-----------SIXTH------------");
        System.out.println(buffer.mark());
        System.out.println("position === " + buffer.position());
        System.out.println("limit === " + buffer.limit());
        System.out.println("capacity === " + buffer.capacity());

        // clear()，清空缓冲区，实际上并没有删除数据
        buffer.clear();

        System.out.println("-----------SEVENTH------------");
        System.out.println(buffer.mark());
        System.out.println("position === " + buffer.position());
        System.out.println("limit === " + buffer.limit());
        System.out.println("capacity === " + buffer.capacity());
        System.out.println((char) buffer.get());



    }
}
