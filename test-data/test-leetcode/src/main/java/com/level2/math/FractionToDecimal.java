package com.level2.math;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 分数到小数
 * 给定两个整数，分别表示分数的分子 numerator 和分母 denominator，以 字符串形式返回小数 。
 * <p>
 * 如果小数部分为循环小数，则将循环的部分括在括号内。
 * <p>
 * 如果存在多个答案，只需返回 任意一个 。
 * <p>
 * 对于所有给定的输入，保证 答案字符串的长度小于 104 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：numerator = 1, denominator = 2
 * 输出："0.5"
 * 示例 2：
 * <p>
 * 输入：numerator = 2, denominator = 1
 * 输出："2"
 * 示例 3：
 * <p>
 * 输入：numerator = 2, denominator = 3
 * 输出："0.(6)"
 * 示例 4：
 * <p>
 * 输入：numerator = 4, denominator = 333
 * 输出："0.(012)"
 * 示例 5：
 * <p>
 * 输入：numerator = 1, denominator = 5
 * 输出："0.2"
 *  
 * <p>
 * 提示：
 * <p>
 * -231 <= numerator, denominator <= 231 - 1
 * denominator != 0
 * 相关标签
 * 哈希表
 * 数学
 * 字符串
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-medium/xwm8ne/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class FractionToDecimal {
    public String fractionToDecimal(int numerator, int denominator) {

        boolean flag = true;
        if (numerator == 0) return "0";
        long numerator2 = 0l;
        long denominator2 = 0l;
        if (numerator >= 0) {
            numerator2 = numerator;
        }else {
            numerator2 =  -1l * numerator;
            flag = flag ? false: true;
        }
        if (denominator >= 0) {
            denominator2 = denominator;
        }else {
            denominator2 = -1l * denominator;
            flag = flag ? false: true;
        }

        StringBuilder sb = new StringBuilder();
        // 计算整数部分
        long a = numerator2 / denominator2;
        long b = numerator2 % denominator2;
        if (!flag) {
            sb.append("-");
        }
        sb.append(a);

        Map<Long, Long> map = new LinkedHashMap<>();
        Long cycleFlag = 0l;
        while (b != 0) {
            long i = b * 10;
            long aa = i / denominator2;
            b = i % denominator2;
            if (map.get(i) != null) {
                cycleFlag = i;
                break;
            }
            map.put(i, aa);
        }

        if (map.size() > 0) {
            sb.append(".");
            if (cycleFlag > 0) {
                for (Map.Entry<Long, Long> entry : map.entrySet()) {
                    Long key = entry.getKey();
                    if (cycleFlag.equals(key)) {
                        sb.append("(");
                    }
                    sb.append(entry.getValue());
                }
                sb.append(")");
            } else {
                for (Map.Entry<Long, Long> entry : map.entrySet()) {
                    sb.append(entry.getValue());
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String fractionToDecimal = new FractionToDecimal()
                .fractionToDecimal(1,
                        2);
        System.out.println("fractionToDecimal = " + fractionToDecimal);
    }
}
