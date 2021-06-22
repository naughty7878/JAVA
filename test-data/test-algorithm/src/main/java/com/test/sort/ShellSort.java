package com.test.sort;

import java.util.Arrays;
import java.util.Date;

/**
 * 希尔排序
 * 基本思想：
 * 1、希尔排序按照增量将数组进行分组，对每组使用直接插入排序算法排序；
 * 2、随着增量逐渐减少，每组包含的关键词越来越多，当增量减至 1 时，整个文件恰被分成一组，算法便终止
 * 3、增量计算为 上一次的增量除以2，比如 数组长为10，第一次增量 10/2 = 5，第二次：5/2 = 2，第三次：2/2 = 1
 */
public class ShellSort {

    public void shellSort(int[] arr){
//        System.out.println("原始数组：   " + Arrays.toString(arr));

//        int count = 0;
        int step = arr.length;
        while ((step = step / 2) > 0) {

            for (int i = 0; i < step; i++) {
                // 这个循环就是一个插入排序
                for (int j = i + step; j < arr.length; j = j + step) {
                    // 插入
                    for (int k = j; k >= step; k = k - step) {
                        int tempK = k - step;
                        // 比较交换
                        if (arr[tempK] > arr[k]) {
                            int temp = arr[k];
                            arr[k] = arr[tempK];
                            arr[tempK] = temp;
                        } else {
                            break;
                        }
                    }
                }
            }
//            System.out.println("第 " + (++count) + " 次排序：" + Arrays.toString(arr));
        }

    }

    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        ShellSort shellSort = new ShellSort();
        shellSort.shellSort(arr);
        System.out.println(Arrays.toString(arr));

        int[] randomArr = new int[10000000];
        for (int i = 0; i < randomArr.length; i++) {
            randomArr[i] = (int) (Math.random() * 10000000);
        }
//        int[] ints = Arrays.copyOf(randomArr, 10000000);
//        QuickSort quickSort = new QuickSort();

        long startTime = new Date().getTime();
        shellSort.shellSort(randomArr);
        System.out.println("time = " + (new Date().getTime() - startTime));

//        long startTime2 = new Date().getTime();
//        quickSort.quickSort(ints);
//        System.out.println("time = " + (new Date().getTime() - startTime2));
//        System.out.println("randomArr = " + Arrays.toString(randomArr));
        // 测试100000个数据，希尔（插入）排序耗时：24 ms
        // 测试10,000,000个数据，希尔（插入）排序耗时：5574 ms

    }

}
