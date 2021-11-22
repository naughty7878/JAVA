package com.test.springboot.sharding.encrypt;

import org.apache.shardingsphere.encrypt.strategy.impl.MD5Encryptor;
import org.apache.shardingsphere.encrypt.strategy.impl.AESEncryptor;
import org.apache.shardingsphere.encrypt.strategy.spi.Encryptor;

import java.util.Properties;

/**
 * @author chengheng
 * @description TODO
 * @date 2021/11/20
 */
public final class CustomShardingEncryptor implements Encryptor {
    @Override
    public void init() {
        System.out.println("CustomShardingEncryptor#init()...");
    }

    @Override
    public String encrypt(Object plaintext) {
        return "abc" + plaintext.toString();
    }

    @Override
    public Object decrypt(String ciphertext) {
        return ciphertext.substring(2);
    }

    @Override
    public String getType() {
        return "CUST";
    }

    @Override
    public Properties getProperties() {
        return null;
    }

    @Override
    public void setProperties(Properties properties) {
        AESEncryptor aesEncryptor = new AESEncryptor();
    }
}
