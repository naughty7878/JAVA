package com.test.file;

import java.io.File;

public class FileMethodTest {
    public static void main(String[] args) {
        // 相对路径
        File file1 = new File("hello.txt");
        // 绝对路径
        File file2 = new File("/Users/H__D/Desktop/日记.txt");

        System.out.println(file1.getAbsolutePath());
        System.out.println(file1.getPath());
        System.out.println(file1.getName());
        System.out.println(file1.getParent());
        // 文件长度
        System.out.println(file1.length());
        // 最后一次修改时间
        System.out.println(file1.lastModified());

        System.out.println("-----------");

        System.out.println(file2.getAbsolutePath());
        System.out.println(file2.getPath());
        System.out.println(file2.getName());
        System.out.println(file2.getParent());
        System.out.println(file2.length());
        System.out.println(file2.lastModified());

        System.out.println("》》》》》》》》》");

        File file3 = new File("/Users/H__D/Desktop");
        String[] list = file3.list();
        for (String s: list){
            System.out.println(s);
        }

        File[] listFiles = file3.listFiles();
        for (File f: listFiles){
            System.out.println(f);
        }

        System.out.println("~~~~~~~~~~~~~");
        File file4 = new File("/Users/H__D/Desktop/日记.txt");
        boolean renameTo = file4.renameTo(new File("/Users/H__D/Desktop/日记2.txt"));
        System.out.println(renameTo);
    }
}
