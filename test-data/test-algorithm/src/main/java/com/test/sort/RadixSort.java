package com.test.sort;

import java.util.Arrays;
import java.util.Date;

/**
 * 基数排序
 * 基本思想：
 * 1、先将数据按个位数排序，放入桶中
 * 2、然后将数据按十位数排序，放入桶中
 * 3、让后将数据按百位数排序，放入桶中
 * 4、依次类推，最后取出来的数据就是 按序排列的
 */
public class RadixSort {

    public void radixSort(int[] arr) {
//        System.out.println("原始数组：   " + Arrays.toString(arr));
        // 获取数组中最大值
        int max = getMax(arr);

        // 桶
        int[][] bucket = new int[10][arr.length];
        // 桶元素数量
        int[] bucketElementCounts = new int[10];
        // 数组下标
        int index = 0;

        // 遍历
        for (int i = 1; i <= max; i = i * 10) {
            // 遍历数组, 放入桶
            for (int j = 0; j < arr.length; j++) {
                int r = arr[j] / i % 10;
                bucket[r][bucketElementCounts[r]] = arr[j];
                bucketElementCounts[r] ++;
            }
//            System.out.println(Arrays.toString(bucketElementCounts));

            // 遍历桶，将数据放入数组
            index = 0;
            for (int k = 0; k < 10; k++) {
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    arr[index++] = bucket[k][l];
                }
                bucketElementCounts[k] = 0;
            }
//            System.out.println(Arrays.toString(bucketElementCounts));
        }
    }

    private int getMax(int[] arr) {
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }


    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        RadixSort radixSort = new RadixSort();
        radixSort.radixSort(arr);
        System.out.println(Arrays.toString(arr));

        int[] randomArr = new int[10000000];
        for (int i = 0; i < randomArr.length; i++) {
            randomArr[i] = (int) (Math.random() * 10000000);
        }

        long startTime = new Date().getTime();
        radixSort.radixSort(randomArr);
        System.out.println("time = " + (new Date().getTime() - startTime));
//        System.out.println("randomArr = " + Arrays.toString(randomArr));
        // 测试100000个数据，基数排序耗时：50 ms
        // 测试10,000,000个数据，基数排序耗时：1085 ms
    }

}
