package com.test.rsa;

import com.sun.org.apache.xml.internal.security.utils.Base64;
import com.test.utils.FileUtils;

import javax.crypto.Cipher;
import java.io.File;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * 非对称加密
 */
public class RSADemo {
    public static void main(String[] args) throws Exception {

        String input = "Hello World";

        // 加密算法
        String algorithm = "RSA";

        //生成密钥对并保存在本地文件中
//        generateKeyToFile(algorithm, "test-encryption/a.pub", "test-encryption/a.pri");
        // 从本地文件中获取密钥
        PrivateKey privateKey = getPrivateKey(algorithm, "test-encryption/a.pri");
        PublicKey publicKey = getPublicKey(algorithm, "test-encryption/a.pub");

        // 私钥加密 - 公钥解密
        String encode = encryptRSA(input, algorithm, privateKey);
        System.out.println("encode = " + encode);
        String decode = decryptRSA(encode, algorithm, publicKey);
        System.out.println("decode = " + decode);

        // 公钥加密 - 私钥解密
//        String encode = encryptRSA(input, algorithm, publicKey);
//        System.out.println("encode = " + encode);
//        String decode = decryptRSA(encode, algorithm, privateKey);
//        System.out.println("decode = " + decode);



//        // 私钥进行解密(无法解密)
//        cipher.init(Cipher.DECRYPT_MODE, privateKey);
//        byte[] bytes1 = cipher.doFinal(bytes);
//        System.out.println("new String(bytes1) = " + new String(bytes1));

//        // 公钥进行解密
//        cipher.init(Cipher.DECRYPT_MODE, publicKey);
//        byte[] bytes1 = cipher.doFinal(bytes);
//        System.out.println("new String(bytes1) = " + new String(bytes1));

    }

    public static PublicKey getPublicKey(String algorithm, String pubPath) throws Exception {
        // 将文件内容转为字符串
        String publicKeyString = FileUtils.readFileToString(new File(pubPath));
        // 获取密钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        // 构建密钥规范 进行Base64解码
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.decode(publicKeyString));
        // 生成公钥
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }

    public static PrivateKey getPrivateKey(String algorithm, String priPath) throws Exception {
        // 将文件内容转为字符串
        String publicKeyString = FileUtils.readFileToString(new File(priPath));
        // 获取密钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        // 构建密钥规范 进行Base64解码
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.decode(publicKeyString));
        // 生成公钥
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }

    private static void generateKeyToFile(String algorithm, String pubPath, String priPath) throws Exception {
        // 创建密钥生成器对象
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
        // 生成密钥对象
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        // 生成密钥
        // 私钥
        PrivateKey privateKey = keyPair.getPrivate();
        // 公钥
        PublicKey publicKey = keyPair.getPublic();
        // 获取私钥字节数组
        byte[] privateKeyEncoded = privateKey.getEncoded();
        // 获取公钥字节数组
        byte[] publicKeyEncoded = publicKey.getEncoded();
        // 对密钥字节数组进行base64编码
        String privateKeyString = Base64.encode(privateKeyEncoded);
        String publicKeyString = Base64.encode(publicKeyEncoded);

        FileUtils.writeStringToFile(new File(priPath), privateKeyString);
        FileUtils.writeStringToFile(new File(pubPath), publicKeyString);
//        // 打印私钥
//        System.out.println("privateKeyString = " + privateKeyString);
//        // 打印公钥
//        System.out.println("publicKeyString = " + publicKeyString);

    }

    private static String encryptRSA(String input, String algorithm, Key key) throws Exception {
        // 创建加密对象
        // 参数表示加密算法
        Cipher cipher = Cipher.getInstance(algorithm);
        // 初始化加密
        // 第一个参数：加密的模式
        // 第二个参数：使用私钥进行加密
        cipher.init(Cipher.ENCRYPT_MODE, key);
        // 私钥加密
        byte[] bytes = cipher.doFinal(input.getBytes());
        String encode = Base64.encode(bytes);
        return encode;
    }

    private static String decryptRSA(String input, String algorithm, Key key) throws Exception {
        // 创建加密对象
        // 参数表示加密算法
        Cipher cipher = Cipher.getInstance(algorithm);
        // 初始化加密
        // 第一个参数：加密的模式
        // 第二个参数：使用私钥进行加密
        cipher.init(Cipher.DECRYPT_MODE, key);
        // 密钥解密
        byte[] decode = Base64.decode(input);
        byte[] bytes = cipher.doFinal(decode);
        return new String(bytes);
    }
}
