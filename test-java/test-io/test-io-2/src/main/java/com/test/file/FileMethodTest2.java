package com.test.file;

import java.io.File;
import java.io.IOException;

public class FileMethodTest2 {
    public static void main(String[] args) throws IOException {
        File file1 = new File("/Users/H__D/Desktop/日记.txt");
        // 创建文件，如果存在则不创建
        file1.createNewFile();

        System.out.println(file1.isDirectory());
        System.out.println(file1.isFile());
        System.out.println(file1.exists());
        System.out.println(file1.canRead());
        System.out.println(file1.canWrite());
        System.out.println(file1.isHidden());

        System.out.println("-----------------");

        File file2 = new File("/Users/H__D/Desktop");
        file1.createNewFile();

        System.out.println(file2.isDirectory());
        System.out.println(file2.isFile());
        System.out.println(file2.exists());
        System.out.println(file2.canRead());
        System.out.println(file2.canWrite());
        System.out.println(file2.isHidden());

        System.out.println("-----------------2");

        File file3 = new File("/Users/H__D/Desktop/a");
        if(!file3.exists()) {
            file3.mkdir();
            System.out.println("file3创建成功");
        }else {
            file3.delete();
            System.out.println("file3删除成功");
        }

        File file4 = new File("/Users/H__D/Desktop/b/b");
        if(!file4.exists()) {
            file4.mkdirs();
            System.out.println("file4创建成功");
        }else {
            file4.delete();
            System.out.println("file4删除成功");
        }
    }
}
