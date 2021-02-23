package com.test.io;

import org.junit.Test;

import java.io.*;
import java.util.Scanner;

/**
 * 其他流的使用
 * 1、标准的输入、输出流
 * 2、打印流
 * 3、数据流
 */
public class OtherStreamTest {
    public static void main(String[] args) {
        OtherStreamTest test = new OtherStreamTest();
        test.testDataInputStream();
    }

    /**
     * 标准的输入、输出流
     * 1、System.in：标准的输入流，默认从键盘输入
     * 2、System.out：标准的输出流，默认输出是控制台
     * 3、System类setIn/setOut方法重新指定输入/输出流
     *
     */
    public void testStandStream()  {

//        // 方法一 使用Scanner
//        Scanner scan = new Scanner(System.in);
//        // 从键盘接收数据
//
//        // next方式接收字符串
//        System.out.println("next方式接收：");
//        // 判断是否还有输入
//        if (scan.hasNext()) {
//            String str1 = scan.next();
//            System.out.println("输入的数据为：" + str1);
//        }
//        scan.close();


        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                System.out.println("请输入字符串：");
                String data = br.readLine();
                if("e".equalsIgnoreCase(data) || "exit".equalsIgnoreCase(data)) {
                    System.out.println("程序结束");
                    break;
                }

                String upperCase = data.toUpperCase();
                System.out.println(upperCase);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    /**
     * 打印流：PrintStream 和 PrintWriter
     */
    @Test
    public void testPrintStream(){

        PrintStream ps = null;
        try {
            FileOutputStream fos = new FileOutputStream("p.txt");
            ps = new PrintStream(fos, true);

            if(ps != null) {
                System.setOut(ps);
            }

            for(int i = 0; i < 255; i ++){
                System.out.print((char)i);
                System.out.print("\t");
                if(i % 10 == 0) {
                    System.out.println();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(ps != null) {
                ps.close();
            }
        }
    }

    /**
     * 数据流 DataInputStream 和 DataOutputStream
     * 作用：用于读取和写出基本数据类型的变量或字符串
     *
     */
    public void testDataOutputStream(){
        DataOutputStream dos = null;
        try {
            dos = new DataOutputStream(new FileOutputStream("d.txt"));

            dos.writeUTF("中国人");
            dos.flush(); // 刷新操作，一定执行就会将数据写入文件
            dos.writeInt(12);
            dos.writeDouble(1.1);
            dos.writeBoolean(true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(dos != null) {
                try {
                    dos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void testDataInputStream(){
        DataInputStream dis = null;
        try {
            dis = new DataInputStream(new FileInputStream("d.txt"));

            String str = dis.readUTF();
            System.out.println(str);
            int num = dis.readInt();
            System.out.println(num);
            double dou = dis.readDouble();
            System.out.println(dou);
            boolean b = dis.readBoolean();
            System.out.println(b);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(dis != null) {
                try {
                    dis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Test
    public void test0(){
        DataOutputStream dos = null;
        try {
            dos = new DataOutputStream(new FileOutputStream("d.txt"));
            dos.write(1);
            dos.writeBoolean(true);
            dos.writeDouble(1.1);
            dos.writeChars("abc");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(dos != null) {
                try {
                    dos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Test
    public void test1(){
        DataInputStream dis = null;
        try {
            dis = new DataInputStream(new FileInputStream("d.txt"));
            System.out.println(dis.read());
            System.out.println(dis.readBoolean());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(dis != null) {
                try {
                    dis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
