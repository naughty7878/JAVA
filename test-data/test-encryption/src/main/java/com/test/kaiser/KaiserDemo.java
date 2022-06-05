package com.test.kaiser;

/**
 * 凯撒加密
 */
public class KaiserDemo {
    public static void main(String[] args) {
        // 定义原文
        String input = "Hello World";
        // 把原文右移动3位
        int key = 3;
        String encryption = encryption(input, key);
        System.out.println("encryption = " + encryption);
        String decryption = decryption(encryption, key);
        System.out.println("decryption = " + decryption);
    }


    /**
     * 解密
     * @param input
     * @return
     */
    private static String decryption(String input, int key) {

        char[] chars = input.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char aChar : chars) {
            sb.append((char)(aChar - key));
        }
        return sb.toString();
    }

    /**
     * 加密
     * @param input
     * @return
     */
    private static String encryption(String input, int key) {

        char[] chars = input.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char aChar : chars) {
            sb.append((char)(aChar + key));
        }
        return sb.toString();
    }
}
