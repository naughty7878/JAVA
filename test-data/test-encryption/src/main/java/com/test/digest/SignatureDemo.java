package com.test.digest;

import com.sun.org.apache.xml.internal.security.utils.Base64;
import com.test.rsa.RSADemo;

import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

/**
 * 数字签名
 */
public class SignatureDemo {
    public static void main(String[] args) throws Exception {
        String input = "Hello World";
        // 加密算法
        String algorithm = "RSA";
        // 获取密钥
        PrivateKey privateKey = RSADemo.getPrivateKey(algorithm, "test-encryption/a.pri");
        PublicKey publicKey = RSADemo.getPublicKey(algorithm, "test-encryption/a.pub");
        // 获取签名
        String signature = getSignature(input, "sha256withrsa", privateKey);
        System.out.println("signature = " + signature);
        // 校验签名
        boolean verify = verifySignature(input, "sha256withrsa", publicKey, signature);
        System.out.println("verify = " + verify);
    }

    private static boolean verifySignature(String input, String algorithm, PublicKey publicKey, String signatureData) throws Exception {
        // 获取签名对象
        Signature signature = Signature.getInstance(algorithm);
        // 初始化校验
        signature.initVerify(publicKey);
        // 传入原文
        signature.update(input.getBytes());
        // 校验
        byte[] decode = Base64.decode(signatureData.getBytes());
        boolean verify = signature.verify(decode);
        return verify;
    }

    private static String getSignature(String input, String algorithm, PrivateKey privateKey) throws Exception {
        // 获取签名对象
        Signature signature = Signature.getInstance(algorithm);
        // 初始化签名
        signature.initSign(privateKey);
        // 传入原文
        signature.update(input.getBytes());
        // 签名
        byte[] sign = signature.sign();
        // Base64编码
        String encode = Base64.encode(sign);
        return encode;
    }
}
