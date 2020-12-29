package com.test.path;

import org.junit.Test;

import java.io.File;
import java.nio.file.*;
import java.io.IOException;
import java.net.URI;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedList;
import java.util.List;

public class PathTest {

    @Test
    public void test1() throws IOException {
        // 使用绝据对路径
        Path path = Paths.get("/Users/h__d/Documents/git-repository/JAVA/test-io/three-nio/hello.txt");


        // File与Path之间转化
        File file = path.toFile();
        System.out.println(file.exists());

        Path p2 = file.toPath();
        System.out.println(p2);

        URI uri = file.toURI();
        String p3 = uri.getPath();
        System.out.println(p3);

        // 获取Path的相关信息
        // 使用Paths工具类的get()方法
        System.out.println("文件名：" + path.getFileName());
        System.out.println("名称元素的数量：" + path.getNameCount());
        System.out.println("父路径：" + path.getParent());
        System.out.println("根路径：" + path.getRoot());
        System.out.println("是否是绝对路径：" + path.isAbsolute());
        System.out.println("是否以给定的路径开头：" + path.startsWith("/Users/h__d"));
        System.out.println("该路径的字符串形式：" + path.toString());


        // .表示的是当前目录
        System.out.println("==========");
        Path currentDir = Paths.get(".");
        System.out.println("是否是绝对路径：" + currentDir.isAbsolute());
        System.out.println("绝对路径：" + currentDir.toAbsolutePath());

        System.out.println("==========");
        Path currentPath = Paths.get("./tomcat.png");
        System.out.println("绝对路径：" + currentPath.toAbsolutePath());
        System.out.println("执行normalize()方法之后：" + currentPath.toAbsolutePath().normalize());
        System.out.println("执行toRealPath()方法之后：" + currentPath.toRealPath());


        // ..表示父目录或者说是上一级目录：
        System.out.println("==========");
        Path path3 = Paths.get("..");
        System.out.println("原始路径格式：" + path3.toAbsolutePath());
        System.out.println("执行normalize()方法之后：" + path3.toAbsolutePath().normalize());
        System.out.println("执行toRealPath()方法之后：" + path3.toRealPath());

    }

    @Test
    public void test2() throws IOException {
        Path path = Paths.get("/Users/h__d/Documents/git-repository/JAVA/test-io/three-nio/hello.txt");
        Path path2 = Paths.get("/Users/h__d/Documents/git-repository/JAVA/test-io/three-nio/hello2.txt");

        boolean exists = Files.exists(path, new LinkOption[]{LinkOption.NOFOLLOW_LINKS});
        System.out.println("exists === " + exists);

        if(!exists) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                Files.copy(path, path2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Path dir = Paths.get("./abc");
        boolean dirExists = Files.exists(dir, new LinkOption[]{LinkOption.NOFOLLOW_LINKS});
        System.out.println("dirExists === " + dirExists);
        if(!dirExists) {
            try {
                Files.createDirectories(dir);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            try {
                Files.delete(dir);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 获取文件属性
        System.out.println("=======获取文件属性========");
        System.out.println(Files.getLastModifiedTime(path));
        System.out.println(Files.size(path));
        System.out.println(Files.isSymbolicLink(path));
        System.out.println(Files.isDirectory(path));
        System.out.println(Files.readAttributes(path, "*"));


        Path currentDir = Paths.get(".");

        DirectoryStream<Path> stream = Files.newDirectoryStream(currentDir);
        for (Path p: stream) {
            System.out.println(p.toAbsolutePath().normalize());
        }

    }

    @Test
    public void test3() throws IOException {
        Path currentDir = Paths.get(".");
        List<Path> result = new LinkedList<>();
        Files.walkFileTree(currentDir, new FindJavaVisitor(result));

        for (Path p: result) {
            System.out.println(p.getFileName());
        }
    }

    class FindJavaVisitor extends SimpleFileVisitor<Path> {
        private List<Path> result;
        public FindJavaVisitor(List<Path> result) {
            this.result = result;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            if(file.toString().endsWith(".java")) {
                result.add(file);
            }
            return FileVisitResult.CONTINUE;
        }
    }
}
