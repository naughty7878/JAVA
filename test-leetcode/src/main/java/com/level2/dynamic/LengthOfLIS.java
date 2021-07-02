package com.level2.dynamic;

import java.util.ArrayList;
import java.util.List;

/**
 * 最长上升子序列
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * <p>
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 * <p>
 *  
 * 示例 1：
 * <p>
 * 输入：nums = [10,9,2,5,3,7,101,18]
 * 输出：4
 * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 * 示例 2：
 * <p>
 * 输入：nums = [0,1,0,3,2,3]
 * 输出：4
 * 示例 3：
 * <p>
 * 输入：nums = [7,7,7,7,7,7,7]
 * 输出：1
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 2500
 * -104 <= nums[i] <= 104
 *  
 * <p>
 * 进阶：
 * <p>
 * 你可以设计时间复杂度为 O(n2) 的解决方案吗？
 * 你能将算法的时间复杂度降低到 O(n log(n)) 吗?
 * 相关标签
 * 数组
 * 二分查找
 * 动态规划
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-medium/xwhvq3/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class LengthOfLIS {

    public int lengthOfLIS(int[] nums) {
        int len = 1, n = nums.length;
        if (n == 0) {
            return 0;
        }
        int[] d = new int[n + 1];
        d[len] = nums[0];
        for (int i = 1; i < n; ++i) {
            if (nums[i] > d[len]) {
                d[++len] = nums[i];
            } else {
                int l = 1, r = len, pos = 0; // 如果找不到说明所有的数都比 nums[i] 大，此时要更新 d[1]，所以这里将 pos 设为 0
                while (l <= r) {
                    int mid = (l + r) >> 1;
                    if (d[mid] < nums[i]) {
                        pos = mid;
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
                d[pos + 1] = nums[i];
            }
        }
        return len;
    }

    public int lengthOfLIS2(int[] nums) {

        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            boolean addFlag = false;
            int size = list.size();
            for (int j = 0; j < size; j++) {
                List<Integer> temp = list.get(j);
                // 判断开头元素是否相同
                if (temp.get(0) == nums[i] ) {
                    addFlag = true;
                    break;
                }
                int tempSize = temp.size();
                if (temp.get(tempSize - 1) < nums[i]) {
                    List<Integer> addList = new ArrayList<>();
                    addList.addAll(temp);
                    addList.add(nums[i]);
                    list.add(addList);
                    addFlag = true;
                }
            }
            // 加入集合
            if (!addFlag) {
                List<Integer> sub = new ArrayList<>();
                sub.add(nums[i]);
                list.add(sub);
            }
        }

        List<Integer> maxLength = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).size() > maxLength.size()) {
                maxLength = list.get(i);
            }
        }
        System.out.println("list = " + list);
        System.out.println("maxLength = " + maxLength);
        return maxLength.size();
    }

    public static void main(String[] args) {
        // [10,9,2,5,3,7,101,18] => [2,3,7,101]
        int[] nums = {10,9,2,5,3,7,101,18};
        int length = new LengthOfLIS().lengthOfLIS(nums);
        System.out.println("length = " + length);
    }
}
