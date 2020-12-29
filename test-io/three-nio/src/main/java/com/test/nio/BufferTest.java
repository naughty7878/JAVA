package com.test.nio;

import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * 1、缓冲区（Buffer）：
 *      在Java NIO 中负责数据的存取，缓冲区九是数组，用于存储不同数据类型的数据
 * 根据数据类型不同（boolean除外），提供了相应类型的缓冲区
 * ByteBuffer
 * CharBuffer
 * ShortBuffer
 * IntBuffer
 * LongBuffer
 * FloatBuffer
 * DoubleBuffer
 *
 * 2、缓冲区存取数据的两个核心方法：
 * put() ：存入数据到缓冲区中
 * get()：获取缓冲区中的数据
 *
 * 3、缓冲区中的四个核心属性：
 * capacity：容量，表示缓冲区中最大存储数据的容量。一旦声明不能改变
 * limit：界限，表示缓冲区中可以操作数据的大小，代表了当前缓冲区中一共有多少数据。。（limit 后数据不能进行读写）
 * position：位置，表示缓冲区中正在操作数据的位置，Position会自动由相应的 get( )和 put( )函数更新
 * mark：标记，一个备忘位置。用于记录当前position位置，可以通过过reset() 恢复到 mark 的位置。
 *
 * 规律
 * mark <= position <= limit <= capacity
 *
 * 4、直接缓冲区与非直接缓冲区
 * 非直接缓冲区：通过allocate() 方法分配，将缓冲区建立在 JVM 的内存中
 * 直接缓冲区：通过allocateDirect() 方法分配，将缓冲区建立在物理内存中，可以提高效率
 *
 *
 */
public class BufferTest {

    /**
     * 测试常用方法
     */
    @Test
    public void test1(){
        // 1、分配一个指定大小的缓存区
        ByteBuffer buf = ByteBuffer.allocate(5);

        System.out.println("----------allocate()---------");
        System.out.println("position == " + buf.position());
        System.out.println("limit == " + buf.limit());
        System.out.println("capacity == " + buf.capacity());


        // 2、利用put() 存入数据到缓冲区区
        buf.put("abcde".getBytes());

        System.out.println("----------put()---------");
        System.out.println("position == " + buf.position());
        System.out.println("limit == " + buf.limit());
        System.out.println("capacity == " + buf.capacity());

        // 3、利用flip() 切换读取数据模式
        buf.flip();

        System.out.println("----------flip()---------");
        System.out.println("position == " + buf.position());
        System.out.println("limit == " + buf.limit());
        System.out.println("capacity == " + buf.capacity());

        // 4、利用get() 读取缓冲区中的数据
        byte[] dst = new byte[buf.limit()];
        buf.get(dst);

        System.out.println("----------get()---------");
        System.out.println("position == " + buf.position());
        System.out.println("limit == " + buf.limit());
        System.out.println("capacity == " + buf.capacity());

        System.out.println(new String(dst, 0, dst.length));

        // 5、利用rewind() 回到读模式（可重复读数据）
        buf.rewind();

        System.out.println("----------rewind()---------");
        System.out.println("position == " + buf.position());
        System.out.println("limit == " + buf.limit());
        System.out.println("capacity == " + buf.capacity());

        // 6、利用clear() 清空缓冲区，当是缓冲区的数据还在，可看源码
        buf.clear();

        System.out.println("----------clear()---------");
        System.out.println("position == " + buf.position());
        System.out.println("limit == " + buf.limit());
        System.out.println("capacity == " + buf.capacity());

        // 测试数据并未被清空
        System.out.println((char)buf.get());

    }

    /**
     * 测试mark 方法
     */
    @Test
    public void test2(){
        ByteBuffer buf = ByteBuffer.allocate(1024);
        buf.put("abcdefg".getBytes());
        buf.flip();

        byte[] dst = new byte[buf.limit()];
        buf.get(dst, 0, 2);
        System.out.println(new String(dst, 0, 2));
        System.out.println("position === " + buf.position());

        buf.mark();

        buf.get(dst, 2, 2);
        System.out.println(new String(dst, 2, 2));
        System.out.println("position === " + buf.position());

        // 重置到mark的位置
        buf.reset();
        System.out.println("position === " + buf.position());


        // 判断缓冲区中是否还有剩余数据
        if(buf.hasRemaining()) {
            // 获取缓冲区中，可以操作的数据数量
            System.out.println("remaining === " + buf.remaining());
        }
    }

    /**
     * 直接缓冲区与非直接缓冲区
     */
    @Test
    public void test3(){
        ByteBuffer buf = ByteBuffer.allocateDirect(1024);

        System.out.println(buf.isDirect());
    }
}
