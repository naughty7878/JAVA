package com.test.demo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PicTest {
    public static void main(String[] args) {
        PicTest test = new PicTest();
        test.copyPic2();
    }

    /**
     * 图片加密
     */
    public void copyPic(){
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream("tomcat.png");
            fos = new FileOutputStream("tomcat3.png");

            // 操作
            byte[] buffer = new byte[20];
            int len;
            while((len = fis.read(buffer)) != -1){
                for (int i = 0; i < len; i++) {
                    // 异或操作，进行加密
                    buffer[i] = (byte) (buffer[i] ^ 5);
                }
                fos.write(buffer, 0, len);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void copyPic2(){
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream("tomcat3.png");
            fos = new FileOutputStream("tomcat4.png");

            // 操作
            byte[] buffer = new byte[20];
            int len;
            while((len = fis.read(buffer)) != -1){
                for (int i = 0; i < len; i++) {
                    // 异或操作，进行加密
                    buffer[i] = (byte) (buffer[i] ^ 5);
                }
                fos.write(buffer, 0, len);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
