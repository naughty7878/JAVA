package com.test.io;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * RandomAccessFile的使用
 * 1、RandomAccessFile直接继承与Java.lang.Object类，实现类DataInput和Output两个接口
 * 2、RandomAccessFile既可以作为一个输入流，又可以作为一个输出流
 * 3、如果RandomAccessFile作为输出流时，写出到的文件如果不存在，则在执行过程中自动创建
 * 如果写出到的文件存在，则会对原有文件内容进行覆盖。（默认情况下，从头开始）
 */
public class RandomAccessFileTest {

    public static void main(String[] args) {
        RandomAccessFileTest test = new RandomAccessFileTest();
        test.test3();
    }


    public void test1() {
        RandomAccessFile raf1 = null;
        RandomAccessFile raf2 = null;
        try {
            raf1 = new RandomAccessFile("hello.txt", "r");
            raf2 = new RandomAccessFile("hello2.txt", "rw");

            byte[] buff = new byte[5];
            int len;
            while ((len = raf1.read(buff)) != -1) {
                raf2.write(buff, 0, len);
                System.out.print(new String(buff, 0, len));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (raf2 != null) {
                try {
                    raf2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (raf1 != null) {
                try {
                    raf1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }


    public void test2() {
        RandomAccessFile raf2 = null;
        try {
            raf2 = new RandomAccessFile("hello2.txt", "rw");
            // 将指针调到角标为3的位置
            raf2.seek(3);
            raf2.write("xyz".getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (raf2 != null) {
                try {
                    raf2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 使用RandomAccessFile实现插入效果
     */
    public void test3() {
        RandomAccessFile raf2 = null;
        ByteArrayOutputStream bos =null;
        try {
            raf2 = new RandomAccessFile("hello2.txt", "rw");
            // 将指针调到角标为3的位置
            raf2.seek(3);

//            // 方式一，使用StringBuilder
//            StringBuilder sb =new StringBuilder("NBA");
//            byte[] buff = new byte[5];
//            int len;
//            while ((len = raf2.read(buff)) != -1) {
//                sb.append(new String(buff, 0, len));
//            }

            // 方式二，使用ByteArrayOutputStream
            bos = new ByteArrayOutputStream();
            bos.write("CBA".getBytes());
            byte[] buff = new byte[5];
            int len;
            while ((len = raf2.read(buff)) != -1) {
                bos.write(buff, 0, len);
            }

            // 指针指回插入位置
            raf2.seek(3);
            raf2.write(bos.toByteArray());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (raf2 != null) {
                try {
                    raf2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
