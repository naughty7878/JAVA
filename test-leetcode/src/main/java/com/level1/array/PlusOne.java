package com.level1.array;

import java.util.Arrays;

/**
 * 加一
 * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
 *
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 *
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：digits = [1,2,3]
 * 输出：[1,2,4]
 * 解释：输入数组表示数字 123。
 * 示例 2：
 *
 * 输入：digits = [4,3,2,1]
 * 输出：[4,3,2,2]
 * 解释：输入数组表示数字 4321。
 * 示例 3：
 *
 * 输入：digits = [0]
 * 输出：[1]
 *  
 *
 * 提示：
 *
 * 1 <= digits.length <= 100
 * 0 <= digits[i] <= 9
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x2cv1c/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class PlusOne {
    public int[] plusOne(int[] digits) {
        int len = digits.length;
        for(int i = len - 1; i >= 0; i--) {
            digits[i]++;
            digits[i] %= 10;
            if(digits[i]!=0)
                return digits;
        }
        digits = new int[len + 1];
        digits[0] = 1;
        return digits;
    }

    public static void main(String[] args) {
        int[] digits = {8,9,4,5,0,0,7,9};
        int[] ints = new PlusOne().plusOne(digits);
        Arrays.stream(ints).forEach(System.out::print);
    }

    // 方法二
    public int[] plusOne1(int[] digits) {
        if(digits[0] == 0) {
            digits[0] += 1;
        } else if(digits[digits.length -1] == 9) {
            int[] arr = new int[digits.length + 1];
            // 倒过来循环
            int y = 1;
            for (int i = digits.length - 1; i >= 0; --i) {
                arr[i+1] = (digits[i] + y) % 10;
                if(arr[i+1] == 0 && digits[i] == 9) {
                    y = 1;
                }else {
                    y = 0;
                }
            }
            arr[0] = y;
            if(y == 0) {
                return Arrays.copyOfRange(arr, 1, arr.length);
            }
            return arr;
        }else {
            digits[digits.length -1] += 1;
        }
        return digits;
    }
}
