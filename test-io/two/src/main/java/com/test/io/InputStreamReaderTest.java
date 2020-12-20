package com.test.io;

import java.io.*;

/**
 * 处理流之二
 * 1、转化流：属于字符流
 * InputStreamReader：将一个字节的输入流转化为字符的输入流
 * OutputStreamWriter：将一个字符的输出流转化为字节的输出流
 * <p>
 * 2、作用：提供字节流与字符流之间的转化
 * <p>
 * 3、解码：字节、字节数组 ---> 字符数组、字符串
 * 编码：字符数组、字符串 ---> 字节、字节数组
 *
 * 4、常见的编码表
 *  ASCII:美国标准信息交换码。  用一个字节的7位可以表示。
 *  ISO8859-1:拉丁码表。欧洲码表  用一个字节的8位表示。
 *  GB2312:中国的中文编码表。最多两个字节编码所有字符
 *  GBK:中国的中文编码表升级，融合了更多的中文文字符号。最多两个字节编码
 *  Unicode:国际标准码，融合了目前人类使用的所有字符。为每个字符分配唯一的
 * 字符码。所有的文字都用两个字节来表示。
 *  UTF-8:变长的编码方式，可用1-4个字节来表示一个字符。
 */
public class InputStreamReaderTest {

    public static void main(String[] args) {
        InputStreamReaderTest test = new InputStreamReaderTest();
        test.test2();
    }

    public void test1() {
        FileInputStream fis = null;
        InputStreamReader isr = null;

        try {
            fis = new FileInputStream("hello.txt");
            //        InputStreamReader isr = new InputStreamReader(fis); // 使用系统默认的字符集
            // 参数2：指明了字符集
            isr = new InputStreamReader(fis, "utf-8");


            char[] cbuf = new char[5];
            int len;
            while ((len = isr.read(cbuf)) != -1) {
                String str = new String(cbuf, 0, len);
                System.out.print(str);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }


    }

    public void test2() {
        InputStreamReader isr = null;
        OutputStreamWriter osw = null;

        try {
            FileInputStream fis = new FileInputStream("hello.txt");
            FileOutputStream fos = new FileOutputStream("hello2.txt");

            isr = new InputStreamReader(fis, "utf-8");
            osw = new OutputStreamWriter(fos, "gbk");

            char[] cbuf = new char[5];
            int len;
            while ((len = isr.read(cbuf)) != -1) {
                String str = new String(cbuf, 0, len);
                System.out.print(str);
                osw.write(str);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(osw != null){
                try {
                    osw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }


    }

}
