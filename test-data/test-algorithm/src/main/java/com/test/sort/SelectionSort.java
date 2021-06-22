package com.test.sort;

import java.util.Arrays;
import java.util.Date;

/**
 * 选择排序
 * 基本思想：
 * 1、首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置。
 * 2、再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
 * 3、重复第二步，直到所有元素均排序完毕。
 */
public class SelectionSort {

    public void selectionSort(int[] arr){
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            // 找出最小数下标
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[minIndex] < arr[j] ? minIndex : j;
            }
            // 交换
            if (minIndex != i) {
                int temp = arr[minIndex];
                arr[minIndex] = arr[i];
                arr[i] = temp;
            }
        }
    }

    public static void main(String[] args) {
        // [3, 9, -1, 10, -2]
        int[] arr = {3, 9, -1, 10, -2};
        SelectionSort selectionSort = new SelectionSort();
        selectionSort.selectionSort(arr);
        System.out.println(Arrays.toString(arr));

        int[] randomArr = new int[100000];
        for (int i = 0; i < randomArr.length; i++) {
            randomArr[i] = (int) (Math.random() * 100000);
        }

        long startTime = new Date().getTime();
        selectionSort.selectionSort(randomArr);
        System.out.println("time = " + (new Date().getTime() - startTime));
        // 测试100000个数据，选择排序耗时：5071 ms

    }
}
