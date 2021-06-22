package com.test.sort;

import java.util.Arrays;
import java.util.Date;

/**
 * 计数排序
 * 基本思想：
 * 1、获取数组中最大元素的值，并创建max+1个桶
 * 2、遍历数组，以数组元素的值作为下标，在对应桶中计数
 * 3、遍历桶，以桶的下标为值，给数组赋值，即得到有序数组
 */
public class CountingSort {

    public void countingSort(int[] arr) {
//        System.out.println("原始数组：   " + Arrays.toString(arr));
        // 获取数组中最大值
        int max = getMax(arr);
        // 桶
        int[] bucket = new int[max + 1];

        for (int i = 0; i < arr.length; i++) {
            // 计数
            bucket[arr[i]] ++;
        }

        int index = 0;
        for (int j = 0; j < bucket.length; j++) {
            while (bucket[j] != 0) {
                arr[index++] = j;
                bucket[j]--;
            }
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
        CountingSort countingSort = new CountingSort();
        countingSort.countingSort(arr);
        System.out.println(Arrays.toString(arr));

        int[] randomArr = new int[10000000];
        for (int i = 0; i < randomArr.length; i++) {
            randomArr[i] = (int) (Math.random() * 10000000);
        }

        long startTime = new Date().getTime();
        countingSort.countingSort(randomArr);
        System.out.println("time = " + (new Date().getTime() - startTime));
//        System.out.println("randomArr = " + Arrays.toString(randomArr));
        // 测试100000个数据，计数排序耗时：13 ms
        // 测试10,000,000个数据，计数排序耗时：358 ms
    }
}
