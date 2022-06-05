package com.test.base64;

import com.sun.org.apache.xml.internal.security.utils.Base64;

public class TestBase64 {
    public static void main(String[] args) {
        // 当字节不够3个字节，需要使用 = 进行补齐
        // 1 表示一个字节，不够3个字节
        System.out.println(Base64.encode("1".getBytes()));
        System.out.println(Base64.encode("12".getBytes()));
        System.out.println(Base64.encode("123".getBytes()));
    }
}
