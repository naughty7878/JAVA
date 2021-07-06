package com.level1.dynamic;

/**
 * 打家劫舍
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：[1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 * 示例 2：
 *
 * 输入：[2,7,9,3,1]
 * 输出：12
 * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 400
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnq4km/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class Rob {

    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        // 第i-1间房屋偷到的钱
        int first = nums[0];
        // 第i间房屋偷到的钱
        int second = Math.max(nums[0], nums[1]);
        for (int i = 2; i < length; i++) {
            int temp = second;
            // 比较 上上次 + 现在的  与 上次偷到的钱
            second = Math.max(first + nums[i], second);
            first = temp;
        }
        return second;
    }


    public static void main(String[] args) {
        int[] nums = {2,7,9,3,1};
        int rob = new Rob().rob(nums);
        System.out.println(rob);
    }

    // 方法1
    public int rob1(int[] nums) {
        if (nums.length == 0) {
            return nums[0];
        }
        return rob1(nums, 0);
    }

    private int rob1(int[] nums, int start) {
        if(start >= nums.length) {
            return 0;
        }
        int first = nums[start] + rob1(nums, start+2);
        int send = rob1(nums, start + 1);
        return Math.max(first, send);
    }
}
