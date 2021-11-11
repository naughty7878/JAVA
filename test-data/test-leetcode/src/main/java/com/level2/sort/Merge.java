package com.level2.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 合并区间
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2：
 *
 * 输入：intervals = [[1,4],[4,5]]
 * 输出：[[1,5]]
 * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 *  
 *
 * 提示：
 *
 * 1 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 104
 * 相关标签
 * 数组
 * 排序
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-medium/xv11yj/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class Merge {
    public int[][] merge(int[][] intervals) {
        // 排序，以区间第一个元素排序
        sort(intervals, 0, intervals.length - 1);

        // 遍历数组
        List<int[]> list = new ArrayList<int[]>();
        int[] temp =  intervals[0];
        list.add(temp);
        for (int i = 0; i < intervals.length; i++) {
            if (temp[1] < intervals[i][0]) {
                temp = new int[]{intervals[i][0], intervals[i][1]};
                list.add(temp);
            }
            if (temp[1] < intervals[i][1]) {
                temp[1] = intervals[i][1];
            }
        }
        return list.toArray(new int[list.size()][]);
    }

    public void sort(int[][] intervals, int start, int end) {
        if (start >= end) {
            return;
        }
        int index = start + 1;
        int base = intervals[start][0];
        for (int i = index; i <= end; i++) {
            if (intervals[i][0] < base) {
                swap(intervals, i, index);
                index++;
            }
        }
        swap(intervals, start, index - 1);

        if (start < index - 1) {
            sort(intervals, start, index - 2);
        }
        if (end > index - 1) {
            sort(intervals, index, end);
        }
    }

    private void swap(int[][] intervals, int left, int right) {
        int[] tem = intervals[left];
        intervals[left] = intervals[right];
        intervals[right] = tem;
    }

    public static void main(String[] args) {
//        int[][] intervals = new int[][]{{1,3},{2,6},{8,10},{15,18}};
        int[][] intervals = new int[][]{{2,3},{5,5},{2,2},{3,4},{3,4}};
        int[][] merge = new Merge().merge(intervals);
        for (int i = 0; i < merge.length; i++) {
            System.out.println("Arrays.toString(merge["+i+"]) = " + Arrays.toString(merge[i]));
        }

    }
}
