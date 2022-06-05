package com.test.array;

import java.util.Arrays;

/**
 * 移动零
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * 示例:
 *
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 说明:
 *
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x2ba4i/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class MoveZeroes {
    public void moveZeroes(int[] nums) {
        // 边界条件判断
        if(nums == null || nums.length < 2) {
            return;
        }

        int zero = 0;
        for (int i = 0; i < nums.length - 1; ++i) {
            if(nums[i] == 0 && nums[i + 1] != 0) {
                // 交换 nums[i - move] , nums[i + 1]
                nums[i - zero] = nums[i - zero] + nums[i + 1];
                nums[i + 1] = nums[i - zero] - nums[i + 1];
                nums[i - zero] = nums[i - zero] - nums[i + 1];
                // 交换 nums[i - move] , nums[i + 1]
                /// 计算交换慢
                //nums[i - zero] = nums[i - zero] + nums[i + 1];
                //nums[i + 1] = nums[i - zero] - nums[i + 1];
                //nums[i - zero] = nums[i - zero] - nums[i + 1];
                // 临时变量交换快
                int temp = nums[i - zero];
                nums[i - zero] = nums[i + 1];
                nums[i + 1] = temp;

                Arrays.stream(nums).forEach(System.out::print);
                System.out.println();
            }else if(nums[i] == 0 && nums[i + 1] == 0) {
                // 数量+1
                ++zero;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {0,1,0,3,12};
        new MoveZeroes().moveZeroes(nums);
    }
}
