package com.test._00_;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * jdk6中：
 * -XX:PermSize=6m -XX:MaxPermSize=6m -Xms6m -Xmx6m
 *
 * jdk8中：
 * -XX:MetaspaceSize=6m -XX:MaxMetaspaceSize=6m -Xms6m -Xmx6m
 * @author shkstart  shkstart@126.com
 * @create 2020  0:36
 */
public class StringTest3 {
    public static void main(String[] args) {
        //使用Set保持着常量池引用，避免full gc回收常量池行为
        Set<String> set = new HashSet<String>();
        //在short可以取值的范围内足以让6MB的PermSize或heap产生OOM了。
        short i = 0;
        while(true){
            set.add(String.valueOf(System.currentTimeMillis()).intern());
        }
    }

//    @Test
//    public void test1() {
//        String str1 = "abc";
//        String str2 = "abc";
//        System.out.println(str1 == str2);
//    }
//
//    @Test
//    public void test2() {
//        String str1 = "abc";
//        String str2 = new String("abc");
//        System.out.println(str1 == str2);
//    }

//    @Test
//    public void test3() {
//        String str1 = "abc";
//        String str2 = "a" + "b" + "c";
//        System.out.println(str1 == str2);
//    }
//
//    @Test
//    public void test4() {
//        String str1 = "abc";
//        String str2 = "ab" + new String("c");
//        String intern = str2.intern();
//        System.out.println(str1 == str2);
//        System.out.println(str1 == intern);
//    }

    @Test
    public void test5() {
        String str1 = "ab" + new String("c");
        str1.intern();
        String str2 = "abc";
        System.out.println(str1 == str2);
    }
}
