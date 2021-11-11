package com.level2.sort;

import java.util.Arrays;

/**
 * 在排序数组中查找元素的第一个和最后一个位置
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * <p>
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * <p>
 * 进阶：
 * <p>
 * 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 * 示例 2：
 * <p>
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 * 示例 3：
 * <p>
 * 输入：nums = [], target = 0
 * 输出：[-1,-1]
 *  
 * <p>
 * 提示：
 * <p>
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * nums 是一个非递减数组
 * -109 <= target <= 109
 * 相关标签
 * 数组
 * 二分查找
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-medium/xv4bbv/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class SearchRange {

    // 二分查找法，找到左边界
    // 二分查找法，找到右边界
    public int[] searchRange(int[] nums, int target) {

        int[] result = {-1, -1};
        if (nums.length > 0) {
            result[0] = searchLeftTarget(nums, target, 0, nums.length - 1);
            result[1] = searchRightTarget(nums, target, 0, nums.length - 1);
        }
        return result;
    }

    // -> nums[end] == target && (start - 1 < 0 || nums[start] < target)
    // -> left = end
    private int searchLeftTarget(int[] nums, int target, int start, int end) {

        //  nums[end] == target
        while (end > 0) {
            int mid = (end + start) / 2;
            if (nums[mid] < target) {
                if (mid == start) {
                    break;
                }
                start = mid;
            }else {
                end = mid;
            }

        }
        if (nums[end] == target) {
            return end;
        }
        return -1;
    }

    private int searchRightTarget(int[] nums, int target, int start, int end) {

        while (start < nums.length - 1) {
            int mid = (end + start + 1) / 2;
            if (nums[mid] > target) {
                if (mid == end) {
                    break;
                }
                end = mid;
            } else {
                start = mid;
            }
        }
        if (nums[start] == target) {
            return start;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {5,7,7,8,8,10};
        int[] searchRange = new SearchRange().searchRange(nums, 8);
        System.out.println("Arrays.toString(searchRange) = " + Arrays.toString(searchRange));
    }

    // 先二分查找到目标元素索引，在循环向左，向右找到边界
    public int[] searchRange0(int[] nums, int target) {

        int[] result = {-1, -1};
        if (nums.length > 0) {
            int index = searchTarget0(nums, target, 0, nums.length - 1);
            if (index != -1) {
                result[0] = index;
                result[1] = index;
                while (result[0] > 0 && nums[result[0] - 1] == target) {
                    result[0]--;
                }
                while (result[1] < nums.length - 1 && nums[result[1] + 1] == target) {
                    result[1]++;
                }
            }
        }
        return result;
    }

    private int searchTarget0(int[] nums, int target, int start, int end) {
        int mid = (end + start) / 2;
        if (nums[mid] == target) {
            return mid;
        } else if (nums[mid] > target && mid - 1 >= start) {
            return searchTarget0(nums, target, start, mid - 1);
        } else if (nums[mid] < target && mid + 1 <= end) {
            return searchTarget0(nums, target, mid + 1, end);
        }
        return -1;
    }
}
