package com.test.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.FileVisitOption;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ChannelTest2 {

    public static void main(String[] args) {
        final ChannelTest2 test = new ChannelTest2();
        new Thread(){
            @Override
            public void run() {
                long start = System.currentTimeMillis();
                test.test1();
                long end1 = System.currentTimeMillis();
                System.out.println("first === " + (end1 - start));
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                long start = System.currentTimeMillis();
                test.test2();
                long end1 = System.currentTimeMillis();
                System.out.println("second === " + (end1 - start));
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                long start = System.currentTimeMillis();
                test.test3();
                long end1 = System.currentTimeMillis();
                System.out.println("third === " + (end1 - start));
            }
        }.start();
    }

    // 通道数据交换
    public void test3(){
        FileChannel outChannel = null;
        FileChannel inChannel = null;
        try {
            // 读文件
            inChannel = FileChannel.open(Paths.get("tomcat.png"), StandardOpenOption.READ);
            // 写文件
            outChannel = FileChannel.open(Paths.get("tomcat3.png"), StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.READ);

            outChannel.transferFrom(inChannel, 0, inChannel.size());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outChannel != null) {
                try {
                    outChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inChannel != null) {
                try {
                    inChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 使用直接缓冲区复制文件
    public void test2() {
        FileChannel outChannel = null;
        FileChannel inChannel = null;
        try {
            // 读文件
            inChannel = FileChannel.open(Paths.get("tomcat.png"), StandardOpenOption.READ);
            // 写文件
            outChannel = FileChannel.open(Paths.get("tomcat2.png"), StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.READ);

            // 内存映射文件
            MappedByteBuffer inMap = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
            MappedByteBuffer outMap = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

            // 操作缓冲区
            byte[] buf = new byte[5];
            int len;
            while (inMap.hasRemaining()) {
                len = inMap.remaining() > buf.length ? buf.length : inMap.remaining();
                inMap.get(buf, 0, len);
                outMap.put(buf, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outChannel != null) {
                try {
                    outChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inChannel != null) {
                try {
                    inChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 复制文件 利用Chanel根Buffer
    private void test1() {
        FileChannel fosChannel = null;
        FileChannel fisChannel = null;
        try {
            FileInputStream fis = new FileInputStream("tomcat.png");
            FileOutputStream fos = new FileOutputStream("tomcat1.png");

            fisChannel = fis.getChannel();
            fosChannel = fos.getChannel();

            ByteBuffer buffer = ByteBuffer.allocate(5);

            int len;
            while ((len = fisChannel.read(buffer)) != -1) {
                buffer.flip();
                fosChannel.write(buffer);
                buffer.clear();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fosChannel != null) {
                try {
                    fosChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (fisChannel != null) {
                try {
                    fisChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
