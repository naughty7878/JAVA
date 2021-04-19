package com.test.base01.ari;

import org.junit.Test;

/*
运算符之五：位运算符 （了解）

结论：
1. 位运算符操作的都是整型的数据
2. << ：在一定范围内，每向左移1位，相当于 * 2
   >> :在一定范围内，每向右移1位，相当于 / 2



面试题：最高效方式的计算2 * 8 ？  2 << 3  或 8 << 1
*/
public class BitTest {

    public static void main(String[] args) {
        int i = 21;
        System.out.println("i = " + Integer.toBinaryString(i) + "\t\t\t\t值为：" + i);

        System.out.println("i << 2 : " + Integer.toBinaryString(i << 2) +
           "\t\t值为：" + (i << 2));
        System.out.println("i >> 3 : " + Integer.toBinaryString(i >> 3) +
                "\t\t\t值为：" + (i >> 3));

        // >>> 无符号位移，空缺位都用0补
        System.out.println("-21 = " + Integer.toBinaryString(-21 ) +
                "\t\t\t值为：" + (-21 ));
        System.out.println("-21 >> 3 : " + Integer.toBinaryString(-21 >> 3) +
                "\t\t\t值为：" + (-21 >> 3));
        System.out.println("-21 >>> 3 : " + Integer.toBinaryString(-21 >>> 3) +
                "\t\t\t值为：" + (-21 >>> 3));

        int m = 12;
        int n = 5;
        System.out.println("m : " + Integer.toBinaryString(m));
        System.out.println("n : " + Integer.toBinaryString(n));
        // &与 二进制位进行&运算，只有1&1时结果是1，否则是0;
        System.out.println("m & n :" + Integer.toBinaryString(m & n));
        // ｜或 二进制位进行 | 运算，只有0 | 0时结果是0，否则是1;
        System.out.println("m | n :" + Integer.toBinaryString(m | n));
        // ^ 异或
        // 相同二进制位进行 ^ 运算，结果是0;1^1=0 , 0^0=0
        // 不相同二进制位 ^ 运算结果是1。1^0=1 , 0^1=1
        System.out.println("m ^ n :" + Integer.toBinaryString(m ^ n));
        System.out.println("1 ^ -1 :" + Integer.toBinaryString(1 ^ -1));

        // ~取反
        // 正数取反，各二进制码按补码各位取反
        // 负数取反，各二进制码按补码各位取反
        System.out.println("~12 :" + Integer.toBinaryString(~12));
        // -12 反码 ： 11111111111111111111111111110011
        // -12 补码 ： 11111111111111111111111111110100
        // 取反：1011
        System.out.println("~-12 :" + Integer.toBinaryString(~-12) + "\t值为：" + (~-12));
    }

    /**
     * 交换2个变量的值
     */
    @Test
    public void test01(){
        int num1 = 10;
        int num2 = 20;

        // 方式一：定义临时变量的方式
        // 推荐方式
//        int temp = num1;
//        num1 = num2;
//        num2 = temp;
//        System.out.println("num1 = " + num1 + "\tnum2 = " + num2);

        // 方式二：不用临时变量
        // 好处：不用定义临时变量
        // 弊端：1、相加操作可能超出存储范围    2、有局限性：只能适用于数值类型
//        num1 = num1 + num2;
//        num2 = num1 - num2;
//        num1 = num1 - num2;
//        System.out.println("num1 = " + num1 + "\tnum2 = " + num2);

        // 方式三：使用位运算符
        // 有局限性：只能适用于数值类型
        num1 = num1 ^ num2;
        num2 = num1 ^ num2;
        num1 = num1 ^ num2;
        System.out.println("num1 = " + num1 + "\tnum2 = " + num2);
    }
}
