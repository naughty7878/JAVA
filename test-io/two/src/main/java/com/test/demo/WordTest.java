package com.test.demo;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class WordTest {
    public static void main(String[] args) {

        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            br = new BufferedReader(new FileReader("hello.txt"));
            bw = new BufferedWriter(new FileWriter("wordtotal.txt"));


            char[] cbuf = new char[5];
            int len;
            Map<Character, Integer> map = new HashMap<Character, Integer>();
            while ((len = br.read(cbuf)) != -1) {
                // 统计
                for(int i = 0; i < len; i++ ){
                    Character c = cbuf[i];
                    if(map.get(c) == null) {
                        map.put(c, 1);
                    } else {
                        map.put(c, map.get(c) + 1);
                    }
                }
            }

            // 输出
            for(Character ch: map.keySet()) {
                switch (ch) {
                    case ' ':
                        bw.write("空格键=" + map.get(ch));
                        break;
                    case '\n':
                        bw.write("换行符=" + map.get(ch));
                        break;
                    case '\t':
                        bw.write("tab键=" + map.get(ch));
                        break;
                    case '\r':
                        bw.write("回车键=" + map.get(ch));
                        break;
                    default:
                        bw.write(ch + "=" + map.get(ch));
                        break;
                }
                bw.newLine();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }





    }
}
