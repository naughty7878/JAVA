package com.test.concurrent.map;

import org.junit.Test;

import java.io.ObjectStreamClass;
import java.io.ObjectStreamField;
import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapTest {

    @Test
    public void test0(){
        int num = -3;
        System.out.println(Integer.toBinaryString(-3));
        int tmp = 1<<31; //用于计算的临时变量

        System.out.println();
        for(int i=0; i<Integer.SIZE-1; i++) {
            //System.out.print(number &(tmp>>>=1));
            System.out.print( (num & ( tmp = tmp >>> 1 ) )>0? 1:0);
        }
        System.out.println();
    }

    @Test
    public void test1(){
        Map map = new ConcurrentHashMap(55);
//        map.put(1, "a");
//        map.put(2, "b");
//        map.put(3, "c");
//        map.remove(1);
        System.out.println(map.size());
        int j = 0;
        for(int i = 0; i < 70; i++) {
            map.put(j += 16, i+"");
        }
        j = 0;
//        for(int i = 0; i < 70; i++) {
//            if(i >= 8) {
//                System.out.println(1);
//            }
//            map.remove(j += 16);
//        }
        System.out.println(16 - (16 >>> 2));
//        map.remove(32);
//        System.out.println(map);
//        System.out.println("16 -> 2 = " + Integer.toBinaryString(16));
//        int zerNum = Integer.numberOfLeadingZeros(16);
//        System.out.println("zerNum = " + zerNum);
//        System.out.println("(1 << (16 - 1)) = " + Integer.toBinaryString((1 << (16 - 1))));
//        int rs = Integer.numberOfLeadingZeros(16) | (1 << (16 - 1));
//        System.out.println("rs = " + rs);
//        System.out.println("rs -> 2 = " + Integer.toBinaryString(rs));
//        // (rs << 16) + 2)
//        int sc = (rs << 16) + 2;
//        System.out.println("sc = " + sc);
//        System.out.println("sc = " + Integer.toBinaryString(sc));

        // (NCPU > 1) ? (n >>> 3) / NCPU : n)
//        System.out.println(16 >>> 3);
//        System.out.println(((4 > 1) ? (16 >>> 3) / 4 : 16) );
    }

    @Test
    public void test2(){

        // ObjectStreamClass 序列化的类的描述符
        //实例化Calendar的ObjectStreamClass
        ObjectStreamClass o_sc = ObjectStreamClass.lookup(Calendar.class);
        System.out.println(o_sc.getName());

        // ObjectStreamField 序列化的类的字段的描述
        //通过使用getField()方法是获取字段
        //日历中的值
        ObjectStreamField field1 = o_sc.getField("isTimeSet");
        ObjectStreamField field2 = o_sc.getField("fields");

        //通过使用getType()方法就是返回
        // 获取字段的类型。
        System.out.println("field1.getType(): " + field1.getType());
        System.out.println("field2.getType(): " + field2.getType());

        // 返回描述此字段的字符串
        System.out.println("field1.toString(): " + field1.toString());
        System.out.println("field1.toString(): " + field2.toString());

    }
}
