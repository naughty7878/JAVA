package com.test.io;

import java.io.*;

/**
 * 缓冲流
 BufferedInputStream,
 BufferedOutputStream,
 BufferedReader,
 BufferedWriter

    能够提高读写的速度，因为内部提供流缓存区
 */
public class BufferedTest  {
    public static void main(String[] args) {
        BufferedTest test = new BufferedTest();
        test.bufferedStreamTest();
    }

    public void bufferedStreamTest(){
        // 1、造文件
        File srcFile = new File("hello.txt");
        File destFile = new File("hello2.txt");

        // 2、造流
        // 2.1、造节点流
        FileInputStream fis = null;
        FileOutputStream fos = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(destFile);

            // 2.2、造缓冲流
            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fos);

            // 3、复制的细节：读取、写入
            byte[] buffer = new byte[1024];
            int len;
            while ((len = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, len);

                // 刷新缓冲区，write()方法会自动调用
//                bos.flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            // 4、资源关闭
            // 要求：先关闭外层的流，在关闭内层的流
            if(bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // 说明：在关闭外层流的同时，内层流也会自动的进行关闭
            // 关闭内层流的操作，可以忽略
//        fos.close();
//        fis.close();
        }
    }
}
