package com.level2.sort;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 颜色分类
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 *
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [2,0,2,1,1,0]
 * 输出：[0,0,1,1,2,2]
 * 示例 2：
 *
 * 输入：nums = [2,0,1]
 * 输出：[0,1,2]
 * 示例 3：
 *
 * 输入：nums = [0]
 * 输出：[0]
 * 示例 4：
 *
 * 输入：nums = [1]
 * 输出：[1]
 *  
 *
 * 提示：
 *
 * n == nums.length
 * 1 <= n <= 300
 * nums[i] 为 0、1 或 2
 *  
 *
 * 进阶：
 *
 * 你可以不使用代码库中的排序函数来解决这道题吗？
 * 你能想出一个仅使用常数空间的一趟扫描算法吗
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-medium/xvg25c/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class SortColors {

    public void sortColors(int[] nums) {
        int first = 0;
        int second = nums.length - 1;
        for (int i = 0; i <= second; i++) {
            if (nums[i] == 0) {
                if(i == first) {
                    continue;
                }else {
                    int temp = nums[first];
                    nums[first] = nums[i];
                    nums[i] = temp;
                    first++;
                    i--;
                }
            }else if(nums[i] == 1) {

            }else {
                if(i == second) {
                    return;
                }else {
                    int temp = nums[second];
                    nums[second] = nums[i];
                    nums[i] = temp;
                    second--;
                    i--;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {2,0,2,1,1,0};
        new SortColors().sortColors(nums);
        List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());
        System.out.println(list);
    }
}
