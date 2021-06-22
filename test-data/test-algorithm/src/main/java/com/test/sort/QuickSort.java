package com.test.sort;

import java.util.Arrays;
import java.util.Date;

/**
 * 快速排序
 * 基本思想：
 * 1、先从数列中取出一个数作为基准数。
 * 2、分区过程，将比这个数大的数全放到它的右边，小于或等于它的数全放到它的左边。
 * 3、重复1-2步骤
 * 4、再对左右区间重复第二步，直到各区间只有一个数。
 */
public class QuickSort {

    public void quickSort(int[] arr){
//        System.out.println("原始数组：   " + Arrays.toString(arr));
        quickSort(arr, 0, arr.length - 1);
    }

    public void quickSort(int[] arr, int start, int end){
        // 本例采用的就是 区间第一数为基准数
        int base = arr[start];
        int left = start;
        int right = end;

        while (left < right) {

            // 找小于等于 base 的数
            while (left < right && arr[right] > base) {
                right--;
            }
            // 找大于 base 的数
            while (left < right && arr[left] <= base) {
                left++;
            }

            if (left < right) {
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
            }
        }

        // 移动base
        int temp = arr[right];
        arr[right] = base;
        arr[start] = temp;

        // 剩下2组排序
        if (right - 1 > start) {
            quickSort(arr, start, right - 1);
        }
        if (right + 1 < end) {
            quickSort(arr, right + 1, end);
        }

    }

    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        QuickSort quickSort = new QuickSort();
        quickSort.quickSort(arr);
        System.out.println(Arrays.toString(arr));

        int[] randomArr = new int[10000000];
        for (int i = 0; i < randomArr.length; i++) {
            randomArr[i] = (int) (Math.random() * 10000000);
        }

        long startTime = new Date().getTime();
        quickSort.quickSort(randomArr);
        System.out.println("time = " + (new Date().getTime() - startTime));
//        System.out.println("randomArr = " + Arrays.toString(randomArr));
        // 测试100000个数据，快速排序耗时：22 ms
        // 测试10,000,000个数据，快速排序耗时：1539 ms
    }
}
