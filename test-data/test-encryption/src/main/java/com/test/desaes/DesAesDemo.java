package com.test.desaes;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * 对称加密
 */
public class DesAesDemo {
    public static void main(String[] args) throws Exception {
        // 原文
        String input = "Hello World";
        // 定义key
        // 如果使用DES加密，密钥必须是8位
        String key = "12345678";
        // 算法
        String transformation = "DES";
        // 加密类型
        String algorithm = "DES";
        // 加密
        String encryptDES = encryptDES(input, key, transformation, algorithm);
        System.out.println("encryptDES = " + encryptDES);
        // 解密
        String decryptDES = decryptDES(encryptDES, key, transformation, algorithm);
        System.out.println("decryptDES = " + decryptDES);
    }

    // 解密
    private static String decryptDES(String input, String key, String transformation, String algorithm) throws Exception {
        // 创建加密对象
        Cipher cipher = Cipher.getInstance(transformation);
        // 创建加密规则
        // 第一个参数：表示key的字节
        // 第二个参数：表示加密的类型
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), algorithm);
        // 进行加密初始化
        // 第一个参数表示模式：解密
        // 第二个参数表示加密规则
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        // 调用加密方法
        // 参数：表示原文的字节数组
        byte[] decode = Base64.decode(input);
        byte[] bytes = cipher.doFinal(decode);
//        String encode = Base64.encode(bytes);
//        System.out.println("encode = " + encode);
        return new String(bytes);
    }

    // 加密
    private static String encryptDES(String input, String key, String transformation, String algorithm) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        // 创建加密对象
        Cipher cipher = Cipher.getInstance(transformation);
        // 创建加密规则
        // 第一个参数：表示key的字节
        // 第二个参数：表示加密的类型
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), algorithm);
        // 进行加密初始化
        // 第一个参数表示模式：加密
        // 第二个参数表示加密规则
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        // 调用加密方法
        // 参数：表示原文的字节数组
        byte[] bytes = cipher.doFinal(input.getBytes());
        String encode = Base64.encode(bytes);
//        System.out.println("encode = " + encode);
        return encode;

//        for (byte aByte : bytes) {
//            System.out.println("aByte = " + aByte);
//        }
//        // 打印秘文
//        System.out.println("bytes = " + new String(bytes));
    }
}
