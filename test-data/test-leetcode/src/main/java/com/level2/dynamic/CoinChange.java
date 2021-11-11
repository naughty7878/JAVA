package com.level2.dynamic;

import java.util.Arrays;

/**
 * 零钱兑换
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 * <p>
 * 你可以认为每种硬币的数量是无限的。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 * 示例 2：
 * <p>
 * 输入：coins = [2], amount = 3
 * 输出：-1
 * 示例 3：
 * <p>
 * 输入：coins = [1], amount = 0
 * 输出：0
 * 示例 4：
 * <p>
 * 输入：coins = [1], amount = 1
 * 输出：1
 * 示例 5：
 * <p>
 * 输入：coins = [1], amount = 2
 * 输出：2
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= coins.length <= 12
 * 1 <= coins[i] <= 231 - 1
 * 0 <= amount <= 104
 * 相关标签
 * 动态规划
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-medium/xvf0kh/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class CoinChange {
    // coins = [1, 2, 5], amount = 11
    public int coinChange(int[] coins, int amount) {
        int max = amount + 1;
        // 定义一个数组，
        // dp[i]:表示 总金额为i的最小硬币数
        // dp[<0] = 0;
        // 比如：dp[0]=0, dp[1]=1,
        // dp[2]=min(dp[2-1] + 1, dp[2-2] + 1, dp[2-5] + 1)=1,
        // dp[3]=min(dp[3-1] + 1, dp[3-2] + 1, dp[3-5] + 1)=1
        int[] dp = new int[amount + 1];
        // 填充最大值+1
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int j = 0; j < coins.length; j++) {
            for (int i = 1; i <= amount; i++) {
                // 硬币值要小于金额
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }

        }
        // 判断对后dp[amount] 最小硬币数 是否大于 amount
        // 即判断 dp[amount] 是不是还是最大值
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        int[] coins = {2, 5};
        int num = new CoinChange().coinChange(coins, 11);
        System.out.println("num = " + num);
    }
}
