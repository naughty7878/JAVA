package com.test.nio;

import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

/**
 * 1、通道（Channel）：
 *  用于源节点与目标节点的连接。在Java NIO中负责缓冲区数据的传输。
 *  Channel本身不存储数据，因此需要缓冲区进行传输
 *
 * 2、通道的主要实现类
 *  java.nio.channels.Channel 接口
 *      ｜-- FIleChannel
 *      ｜-- SocketChannel
 *      ｜-- ServerSocketChannel
 *      ｜-- DatagramChannel
 *
 * 3、获取通道
 *    1）Java 针对支持通道的类提供类 getChannel() 方法
 *      本地IO：
 *      FileInputStream/FileOutputStream
 *      RandomAccessFile
 *
 *      网络IO：
 *      Socket
 *      ServerSocket
 *      DatagramSocket
 *
 *    2）在JDK 1.7 中的 NIO.2 针对各个通道提供类静态方法 open()
 *    3）在JDK 1.7 中的 NIO.2 的Files 工具类的 newByteChannel()
 *
 * 4、通道之间的数据传输
 *      transferFrom()
 *      transferTo()
 *
 * 5、分散（Scatter）与聚集（Gather）
 * 分散读取（Scattering Reads）：将通道中的数据分散到多个缓冲区中
 * 聚集写入（Gathering Writes）：将多个缓冲区中的数据聚集到通道中
 *
 * 6、字符集：Charset
 * 编码：字符串 -> 字节数组
 * 解码：字节数组 -> 字符串
 */
public class ChannelTest {


    // 1】利用通道完成文件的复制
    @Test
    public void test1(){

        FileInputStream fis = null;
        FileOutputStream fos = null;

        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            fis = new FileInputStream("tomcat.png");
            fos = new FileOutputStream("tomcat2.png");

            // 1、获取通道
            inChannel = fis.getChannel();
            outChannel = fos.getChannel();

            // 2、分配指定大小的缓存区
            ByteBuffer buf = ByteBuffer.allocate(1024);

            // 3、将通道中的数据存入缓冲区中
            int len;
            while ((len = inChannel.read(buf)) != -1) {
                buf.flip(); // 切换成读取数据的模式
                // 4、将缓冲区中的数据写入通道中
                outChannel.write(buf);
                buf.clear(); // 清空缓冲区
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 5、关闭通道
            if(outChannel != null) {
                try {
                    outChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(inChannel != null) {
                try {
                    inChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 2】使用直接缓冲区完成文件的复制（内存映射文件）
    @Test
    public void test2() throws IOException {
        // READ 读文件
        FileChannel inChannel = FileChannel.open(Paths.get("tomcat.png"), StandardOpenOption.READ);
        // WRIT 写文件 CREATE_NEW 文件存在就报错，不存在就创建 CREATE 覆盖
        FileChannel outChannel = FileChannel.open(Paths.get("tomcat2.png"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);

        // 内存映射文件，直接缓冲区内存在物理内存中
        MappedByteBuffer inMapappedBuf = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outMapappedBuf = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

        // 直接对缓冲区进行数据的读写操作
        byte[] dst = new byte[1024];
        ByteBuffer buf;
        int len = 0;
        while (inMapappedBuf.hasRemaining()){
            len = dst.length > inMapappedBuf.remaining() ? inMapappedBuf.remaining() : dst.length;
//            System.out.println(len);
            inMapappedBuf.get(dst, 0, len) ;
            outMapappedBuf.put(dst, 0, len);
        }
    }

    // 3】通道之间数据传输(也是直接缓冲区方式)
    @Test
    public void test3() throws IOException {
        // READ 读文件
        FileChannel inChannel = FileChannel.open(Paths.get("tomcat.png"), StandardOpenOption.READ);
        // WRIT 写文件 CREATE_NEW 文件存在就报错，不存在就创建 CREATE 存在覆盖，不存在创建
        FileChannel outChannel = FileChannel.open(Paths.get("tomcat2.png"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);

//        inChannel.transferTo(0, inChannel.size(), outChannel);
        outChannel.transferFrom(inChannel, 0, inChannel.size());

        outChannel.close();
        inChannel.close();
    }

    // 4】分散和聚集
    @Test
    public void test4() {
        FileChannel inRafChannel = null;
        FileChannel outRafChannel = null;
        try {
            RandomAccessFile inRaf = new RandomAccessFile("hello.txt", "r");
            RandomAccessFile outRaf = new RandomAccessFile("hello2.txt", "rw");

            // 1、获取通道
            inRafChannel = inRaf.getChannel();
            outRafChannel = outRaf.getChannel();

            // 2、分配指定大小的缓冲区
            ByteBuffer buf1 = ByteBuffer.allocate(5);
            ByteBuffer buf2 = ByteBuffer.allocate(10);

            // 3、分散读取
            ByteBuffer[] bufs = {buf1, buf2};
            long len;
            while ((len = inRafChannel.read(bufs)) != -1){
                for (ByteBuffer byteBuffer : bufs) {
                    // 切换读模式
                    byteBuffer.flip();
                    System.out.print(new String(byteBuffer.array(), 0, byteBuffer.limit()));
                }
//                System.out.println();

                // 4、聚集写入
                outRafChannel.write(bufs);

                for (ByteBuffer byteBuffer : bufs) {
                    // 清除缓存
                    byteBuffer.clear();
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outRafChannel != null) {
                try {
                    outRafChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inRafChannel != null) {
                try {
                    inRafChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 5】字符集
    @Test
    public void test5(){
        SortedMap<String, Charset> map = Charset.availableCharsets();

        Set<Map.Entry<String, Charset>> set = map.entrySet();

        for (Map.Entry<String, Charset> entry: set) {
            System.out.println(entry.getKey() + " === " + entry.getValue());
        }
    }

    // 6】测试字符集
    @Test
    public void test6(){

        try {

            Charset cs1 = Charset.forName("UTF-8");
            // 获取编码器
            CharsetEncoder ce = cs1.newEncoder();

            // 获取解码器
            CharsetDecoder cd = cs1.newDecoder();

            CharBuffer cBuf = CharBuffer.allocate(1024);
            cBuf.put("我是中国人");
            cBuf.flip();

            // 编码
            ByteBuffer bBuf = ce.encode(cBuf);
            for (int i = 0; i < 12 && i < bBuf.limit(); i ++ ) {
                System.out.println(bBuf.get());
            }

            // 解码
            bBuf.flip();
            CharBuffer cBuf2 = cd.decode(bBuf);
            System.out.println(cBuf2.toString());

            System.out.println("-----------------");

            // 编码解码不一致
            bBuf.flip();
            Charset cs2 = Charset.forName("GBK");
            CharBuffer cBuf3 = cs2.decode(bBuf);
            System.out.println(cBuf3.toString());
        } catch (CharacterCodingException e) {
            e.printStackTrace();
        }

    }
}
