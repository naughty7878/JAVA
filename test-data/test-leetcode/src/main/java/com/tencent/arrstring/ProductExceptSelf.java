package com.tencent.arrstring;

import java.util.Arrays;

/**
 * 除自身以外数组的乘积
 * 给你一个长度为n的整数数组nums，其中n > 1，返回输出数组output，其中 output[i]等于nums中除nums[i]之外其余各元素的乘积。
 *
 * 
 *
 * 示例:
 *
 * 输入: [1,2,3,4]
 * 输出: [24,12,8,6]
 * 
 *
 * 提示：题目数据保证数组之中任意元素的全部前缀元素和后缀（甚至是整个数组）的乘积都在 32 位整数范围内。
 *
 * 说明: 请不要使用除法，且在O(n) 时间复杂度内完成此题。
 *
 * 进阶：
 * 你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
 *
 * 相关标签
 * 数组
 * 前缀和
 *
 * Java
 *
 * 智能模式
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/tencent/xxfcwh/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class ProductExceptSelf {

    public int[] productExceptSelf(int[] nums) {
        int length = nums.length;
        int[] answer = new int[length];

        // answer[i] 表示索引 i 左侧所有元素的乘积
        // 因为索引为 '0' 的元素左侧没有元素， 所以 answer[0] = 1
        answer[0] = 1;
        for (int i = 1; i < length; i++) {
            answer[i] = nums[i - 1] * answer[i - 1];
        }

        // R 为右侧所有元素的乘积
        // 刚开始右边没有元素，所以 R = 1
        int R = 1;
        for (int i = length - 1; i >= 0; i--) {
            // 对于索引 i，左边的乘积为 answer[i]，右边的乘积为 R
            answer[i] = answer[i] * R;
            // R 需要包含右边所有的乘积，所以计算下一个结果时需要将当前值乘到 R 上
            R *= nums[i];
        }
        return answer;
    }


    public static void main(String[] args) {
        int[] nums =  new int[]{1,2,3,4};
        int[] output = new ProductExceptSelf().productExceptSelf(nums);
        System.out.println("output = " + Arrays.toString(output));
    }



    public int[] productExceptSelf01(int[] nums) {
        int len = nums.length;
        int[] output = new int[len];
        for (int i = 0; i < len; i++) {
            int temp = 1;
            for (int j = 0; j < len; j++) {
                if (i != j) {
                    temp *= nums[j];
                }
            }
            output[i] = temp;
        }
        return output;
    }
}
