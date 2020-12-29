package com.test.io;

import java.io.*;

/**
 * 结论：
 * 1、对于文本文件（.txt,.java,.c,.cpp），使用字符流处理
 * 2、对于非文本文件(.jpg,.mp3,.mp4,.avi,.doc,.ppt)，使用字节流文件
 */
public class FileInputOutputStreamTest {
    public static void main(String[] args) {
        FileInputOutputStreamTest test = new FileInputOutputStreamTest();
        test.testFileOutputStream();

    }

    public void testFileInputStream1() {
        // 1、实例化File类对象
        File file = new File("hello.txt");
        // 2、提供具体流
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            // 3、数据读取
            // read():返回读入的一个字符。如果达到文件末尾了，返回-1
            int data;
            while ( (data=fis.read()) != -1) {
                System.out.print((char) data);
            }

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            // 4、关闭流
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void testFileInputStream() {
        // 1、造文件
        File file = new File("hello.txt");

        // 2、造输入流
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);

            // 3、读数据
            byte[] buffer = new byte[5];
            int len;
            while ((len = fileInputStream.read(buffer)) != -1) {
                System.out.print(new String(buffer, 0, len));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4、关闭流资源
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void testFileOutputStream() {
        // 1、提供File类对象，并指明写出的文件
        File file = new File("hello2.txt");

        // 2、提供FileOutputStream的对象，用于数据的写出
        FileOutputStream fos = null;
        try {
            // 第二个参数，是否在原文件上追加内容，默认为false
            fos = new FileOutputStream(file, true);

            // 3、写出的操作
            fos.write("I have a Dream".getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4、流资源的关闭
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void testFileInputOutputStream() {
        // 1、创建File类的对象，指明读入和写出的文件
        File srcFile = new File("tomcat.png");
        File destFile = new File("tomcat2.png");


        // 2、创建输入流和输出流的对象
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            fileInputStream = new FileInputStream(srcFile);
            fileOutputStream = new FileOutputStream(destFile);

            // 3、数据的读入和写出操作
            byte[] cbuf = new byte[1024];
            int len;
            while ((len = fileInputStream.read(cbuf)) != -1) {
                fileOutputStream.write(cbuf, 0, len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            // 4、关闭流资源
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }


    }
}
