package com.test.sort;

import java.util.Arrays;
import java.util.Date;

/**
 * 桶排序
 * 基本思想：
 * 1、获取数组中最大元素的值，并创建max+1个桶
 * 2、遍历数组，以数组元素的值作为下标，在对应桶中计数
 * 3、遍历桶，以桶的下标为值，给数组赋值，即得到有序数组
 */
public class BucketSort {

    public void bucketSort(int[] arr) {
//        System.out.println("原始数组：   " + Arrays.toString(arr));
        // 获取数组中最大值
        int minValue = arr[0];
        int maxValue = arr[0];
        for (int value : arr) {
            if (value < minValue) {
                minValue = value;
            } else if (value > maxValue) {
                maxValue = value;
            }
        }

        // 桶
        int[][] buckets = new int[(int) (Math.floor(maxValue/100) + 1)][0];

        // 分配放入桶
        for (int i = 0; i < arr.length; i++) {
            int floor = (int) Math.floor((arr[i] - minValue) / 100);
            buckets[floor] = arrAppend(buckets[floor], arr[i]);
        }

        int index = 0;
        for (int j = 0; j < buckets.length; j++) {
            if (buckets[j].length > 0) {
                // 插入排序
                for (int k = 1; k < buckets[j].length; k++) {
                    for (int l = k; l > 0; l--) {
                        if (buckets[j][l] < buckets[j][l - 1]) {
                            // 交换
                            int temp = buckets[j][l];
                            buckets[j][l] = buckets[j][l - 1];
                            buckets[j][l - 1] = temp;
                        }else {
                            break;
                        }
                    }
                }
                //  取出
                for (int m = 0; m < buckets[j].length; m++) {
                    arr[index++] = buckets[j][m];
                }
            }
        }
    }

    private int[] arrAppend(int[] arr, int value) {
        arr = Arrays.copyOf(arr, arr.length + 1);
        arr[arr.length - 1] = value;
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        BucketSort bucketSort = new BucketSort();
        bucketSort.bucketSort(arr);
        System.out.println(Arrays.toString(arr));

        int[] randomArr = new int[10000000];
        for (int i = 0; i < randomArr.length; i++) {
            randomArr[i] = (int) (Math.random() * 10000000);
        }

        long startTime = new Date().getTime();
        bucketSort.bucketSort(randomArr);
        System.out.println("time = " + (new Date().getTime() - startTime));
//        System.out.println("randomArr = " + Arrays.toString(randomArr));
        // 测试100000个数据，桶排序耗时：61 ms
        // 测试10,000,000个数据，计数排序耗时：3435 ms
    }
}
