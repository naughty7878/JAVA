package com.test.file;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Date;

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

    @Test
    public void test0(){
        // 创建File
        File file0 = new File("/Users/h__d/Documents/git-repository/JAVA/test-io/two-io/hello.txt");

        System.out.println("是否存在：" + file0.exists());
        System.out.println("是否是文件：" + file0.isFile());
        System.out.println("是否是目录：" + file0.isDirectory());
        System.out.println("绝对路径：" + file0.getAbsolutePath());
        System.out.println("相对路径：" + file0.getPath());
        System.out.println("文件名：" + file0.getName());
        System.out.println("上级目录：" + file0.getParent());
        System.out.println("打印：" + file0.toString());
        System.out.println("是否可读：" + file0.canRead());
        System.out.println("是否可写：" + file0.canWrite());
        System.out.println("是否隐藏：" + file0.isHidden());
        System.out.println("系统路径分割符：" + File.separator);
        System.out.println("文件长度：" + file0.length());
        System.out.println("最后一次修改时间：" + new Date(file0.lastModified()));
        System.out.println("上级目录：" + file0.getParentFile());

        // 重命名
        File file1 = new File("abc.txt");
        if(!file1.exists()){
            try {
                file1.createNewFile();
                file0.renameTo(file1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            int i = file0.compareTo(file1);
            System.out.println(i);
        }



    }
}
