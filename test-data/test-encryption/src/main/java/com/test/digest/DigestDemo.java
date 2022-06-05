package com.test.digest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 消息摘要算法，为了防止篡改
 */
public class DigestDemo {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        // 原文
        String input = "Hello World";
        //  算法
        String algorithm = "MD5";
        String MD5 = getDigest(input, algorithm);
        // 使用base64进行转码
//        String encode = Base64.encode(digest1);
//        System.out.println("encode = " + encode);
        System.out.println("MD5 = " + MD5);
        System.out.println("MD5.length() = " + MD5.length());

        String sha1 = getDigest(input, "SHA-1");
        System.out.println("sha1 = " + sha1);
        System.out.println("sha1.length() = " + sha1.length());

        String sha256 = getDigest(input, "SHA-256");
        System.out.println("sha256 = " + sha256);
        System.out.println("sha256.length() = " + sha256.length());

        String sha512 = getDigest(input, "SHA-512");
        System.out.println("sha512 = " + sha512);
        System.out.println("sha512.length() = " + sha512.length());
    }

    private static String getDigest(String input, String algorithm) throws NoSuchAlgorithmException {
        // 创建消息摘要对象
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        // 执行消息摘要算法
        byte[] digest1 = digest.digest(input.getBytes());
        System.out.println("密文的字节长度:" + digest1.length);
        // 转16进制
        return toHex(digest1);
    }

    private static String toHex(byte[] input) {
        StringBuilder sb = new StringBuilder();
        for (byte b : input) {
            // 转成 16进制
            String s = Integer.toHexString(b & 0xff);
            //System.out.println(s);
            if (s.length() == 1){
                // 如果生成的字符只有一个，前面补0
                s = "0"+s;
            }
            sb.append(s);
        }
        System.out.println(sb.toString());
        return sb.toString();
    }
}
