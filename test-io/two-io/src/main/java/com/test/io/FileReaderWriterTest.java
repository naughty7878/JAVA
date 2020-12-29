package com.test.io;

import java.io.*;

public class FileReaderWriterTest {
    public static void main(String[] args) {
        FileReaderWriterTest test = new FileReaderWriterTest();
        test.testFileReaderFileWriter();

    }

    /**
     * 将hello.txt文件内容读入程序中，并输出控制台
     * 1、read() 返回读入的一个字符。如果达到文件末尾了，返回-1
     * 2、异常处理：为了保证流资源一定可以执行关闭操作，需要使用到try-catch-finally 处理
     * 3、读入文件一定要存在，否则就会包FileNotFoundException
     */
    public void testFileReader() {
        // 1、实例化File类对象
        File file = new File("hello.txt");
//        System.out.println(file.getAbsolutePath());
//        System.out.println(file.exists());
        // 2、提供具体流
        FileReader fileReader = null;

        try {
            fileReader = new FileReader(file);

            // 3、数据读取
            // read():返回读入的一个字符。如果达到文件末尾了，返回-1
//        int data = fileReader.read();
//        while (data != -1) {
//            System.out.print((char)data);
//            data = fileReader.read();
//        }
            // ---优化
            int data;
            while ((data = fileReader.read()) != -1) {
                System.out.print((char) data);
            }
        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            // 4、关闭流
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 对read()操作升级，使用read的重载方法
     */
    public void testFileReader2() {
        // 1、File类的实例化
        File file = new File("hello.txt");


        FileReader fileReader = null;
        try {
            // 2、FileReader流的实例化
            fileReader = new FileReader(file);

            // 3、读入操作
            // read(char[] cbuf)返回每次读入cbuf数组中的字符个数，如果达到文件末尾，返回-1
            char[] cbuf = new char[5];
            int data;
            while ((data = fileReader.read(cbuf)) != -1) {
                System.out.print(new String(cbuf, 0, data));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4、资源的关闭
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 从内存中写出数据到硬盘的文件里
     * 说明：
     * 1、输出操作，对应的file可以不存在，
     * 如果不存在，在输出的过程中，会自动创建此文件
     * 如果文件存在
     * 使用输出流构造器
     * FileWriter(File file, boolean append)，append为true，可以在原文件上追加
     * FileWriter(File file)，覆盖原文件
     * 2、
     */
    public void testFileWriter() {
        // 1、提供File类对象，并指明写出的文件
        File file = new File("hello2.txt");

        // 2、提供FileWriter的对象，用于数据的写出
        FileWriter fileWriter = null;
        try {
            // 第二个参数，是否在原文件上追加内容，默认为false
            fileWriter = new FileWriter(file, true);

            // 3、写出的操作
            fileWriter.write("I have a dream!\n");
            fileWriter.write("you need to have a dream!\n");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4、流资源的关闭
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    public void testFileReaderFileWriter() {
        // 1、创建File类的对象，指明读入和写出的文件
        File srcFile = new File("hello.txt");
        File destFile = new File("hello2.txt");

        // 字符流无法处理图片文件等字节文件
//        File srcFile = new File("tomcat.png");
//        File destFile = new File("tomcat2.png");


        // 2、创建输入流和输出流的对象
        FileReader fileReader = null;
        FileWriter fileWriter = null;
        try {
            fileReader = new FileReader(srcFile);
            fileWriter = new FileWriter(destFile);

            // 3、数据的读入和写出操作
            char[] cbuf = new char[5];
            int len;
            while ((len = fileReader.read(cbuf)) != -1) {
                fileWriter.write(cbuf, 0, len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            // 4、关闭流资源
            if(fileWriter != null ){
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
