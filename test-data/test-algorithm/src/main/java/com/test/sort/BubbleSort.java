package com.test.sort;

import java.util.Arrays;
import java.util.Date;

/**
 * 冒泡排序
 * 基本思想：
 * 1、通过对待排序序列从前向后（从下标较小的元素开始），依次比较相邻元素的值，若发现逆序则交换
 * 2、第一轮结束时，最后一个就最大的
 * 3、重复1-2步骤
 * 4、第 i 轮结束时，倒数第 i 个就是第 i 大的数
 */
public class BubbleSort {

    // 冒泡排序
    public int[] bubbleSort(int[] arr) {
//        System.out.println("原始数组：   " + Arrays.toString(arr));

        // 排序次数
        for (int i = 0; i < arr.length - 1; i++) {
            // 一次排序中，进行比较交换
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
//            System.out.println("第 " + (i + 1) + " 次排序：" + Arrays.toString(arr));
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = {3, 9, -1, 10, -2};

        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.bubbleSort(arr);
//        bubbleSort.bubbleSortS(arr);

        int[] randomArr = new int[100000];
        for (int i = 0; i < randomArr.length; i++) {
            randomArr[i] = (int) (Math.random() * 100000);
        }
//
        long startTime = new Date().getTime();
        bubbleSort.bubbleSort(randomArr);
        System.out.println("time = " + (new Date().getTime() - startTime));
        // 测试100000个数据，普通冒泡排序耗时：19214 ms
        // 测试100000个数据，优化冒泡排序耗时：19500 ms
        // 说明：加了判断的优化冒泡排序，增加了计算步骤，增加了耗时
    }


    // 优化冒泡排序方法
    public int[] bubbleSortS(int[] arr) {
//        System.out.println("原始数组：   " + Arrays.toString(arr));

        // 一次排序中是否交换标识
        boolean flag = false;

        // 排序次数
        for (int i = 0; i < arr.length - 1; i++) {
            // 一次排序中，进行比较交换
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    flag = true;
                }
            }
//            System.out.println("第 " + (i + 1) + " 次排序：" + Arrays.toString(arr));
            // 未交换表示已经得到结果
            if (!flag) {
                break;
            }
        }
        return arr;
    }
}
