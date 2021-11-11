package com.level1.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 两个数组的交集 II
 * 给定两个数组，编写一个函数来计算它们的交集。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2,2]
 * 示例 2:
 *
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[4,9]
 *  
 *
 * 说明：
 *
 * 输出结果中每个元素出现的次数，应与元素在两个数组中出现次数的最小值一致。
 * 我们可以不考虑输出结果的顺序。
 * 进阶：
 *
 * 如果给定的数组已经排好序呢？你将如何优化你的算法？
 * 如果 nums1 的大小比 nums2 小很多，哪种方法更优？
 * 如果 nums2 的元素存储在磁盘上，内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x2y0c2/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class Intersect {

    public int[] intersect(int[] nums1, int[] nums2) {
        if(nums1.length > nums2.length) {
            return intersect(nums2, nums1);
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums1) {
            int count = map.getOrDefault(n, 0);
            map.put(n, ++count);
        }
        int[] intersetc = new int[nums1.length];
        int index = 0;
        for (int m : nums2) {
            int count = map.getOrDefault(m, 0);
            if (count > 0) {
                intersetc[index++] = m;
                count --;
                if(count > 0) {
                    map.put(m, count);
                }else {
                    map.remove(m);
                }
            }
        }
        return Arrays.copyOfRange(intersetc, 0, index);
    }

    public static void main(String[] args) {
        int[] nums1 = {1,2,2,1}, nums2 = {2,2};
        int[] intersect = new Intersect().intersect(nums1, nums2);
        Arrays.stream(intersect).forEach(System.out::println);
    }
}
