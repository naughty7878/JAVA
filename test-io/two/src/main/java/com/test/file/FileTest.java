package com.test.file;

import java.io.File;

/**
 * File类的使用
 * 1、File类的一个对象，代表一个文件或一个文件目录（俗称：文件夹）
 * 2、File类声明在java.io包下
 * 3、路径分隔符
 *    windows：\\
 *    unix: /
 *    File.separator
 *
 */
public class FileTest {
    /**
     * 1、如何创建File类的实例
     *    File(String filePath)
     *    File(String parentPath, String childPath)
     *    File(File parentFile, String childPath)
     *
     * 2、相对路径：相较于某个路径下，指明的路径
     *    绝对路径：包含盘符在内的文件或文件目录的路径
     */
    public static void main(String[] args) {

        // 构造方法1
        // 相对路径
        File file1 = new File("hello.txt");
        // 绝对路径
        File file2 = new File("/Users/H__D/Desktop/日记.txt");
        System.out.println(file1);
        System.out.println(file2);

        // 路径分隔符
        System.out.println(File.separator);

        // 构造方法2
        File file4 = new File("/Users/H__D/Desktop", "abc");
        System.out.println(file4);

        // 构造器方法4
        File file5 = new File(file4, "a.txt");
        System.out.println(file5);
    }
}
