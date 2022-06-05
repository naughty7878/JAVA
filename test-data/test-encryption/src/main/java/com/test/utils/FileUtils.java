package com.test.utils;

import java.io.*;

public class FileUtils {

    public static String writeStringToFile(File destFile, String content) throws Exception {
        // 1、创建File类的对象，指明读入和写出的文件
//        File srcFile = new File("hello.txt");
//        File destFile = new File("hello2.txt");

        // 2、创建输入流和输出流的对象
//        FileReader fileReader = null;
        StringBuilder stringBuilder = new StringBuilder();
        FileWriter fileWriter = null;
        try {
//            fileReader = new FileReader(destFile);
            fileWriter = new FileWriter(destFile);

            // 3、数据的读入和写出操作
//            char[] cbuf = new char[1024];
//            int len;
//            while ((len = fileReader.read(cbuf)) != -1) {
//                stringBuilder.append(new String(cbuf, 0, len));
//                fileWriter.write(cbuf, 0, len);
//            }
            fileWriter.write(content);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
//
            // 4、关闭流资源
            if(fileWriter != null ){
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
//            if (fileReader != null) {
//                try {
//                    fileReader.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
        }
        return stringBuilder.toString();
    }

    public static String readFileToString(File srcFile) throws Exception {
        // 1、创建File类的对象，指明读入和写出的文件
//        File srcFile = new File("hello.txt");
//        File destFile = new File("hello2.txt");

        // 2、创建输入流和输出流的对象
        FileReader fileReader = null;
        StringBuilder stringBuilder = new StringBuilder();
//        FileWriter fileWriter = null;
        try {
            fileReader = new FileReader(srcFile);
//            fileWriter = new FileWriter(destFile);

            // 3、数据的读入和写出操作
            char[] cbuf = new char[1024];
            int len;
            while ((len = fileReader.read(cbuf)) != -1) {
                stringBuilder.append(new String(cbuf, 0, len));
//                fileWriter.write(cbuf, 0, len);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
//
//            // 4、关闭流资源
//            if(fileWriter != null ){
//                try {
//                    fileWriter.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return stringBuilder.toString();
    }
}
