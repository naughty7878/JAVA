package com.test.sort;

import java.util.Arrays;
import java.util.Date;

/**
 * 插入排序
 * 基本思想：
 * 1、把 n 个待排序的元素看成为一个有序表和一个无序表
 * 2、开始时有序表中只包含一个元素，无序表中包含有 n-1 个元素，
 * 3、排序过程中每次从无序表中取出第一个元素，把它的排序码依次与有序表元素的最后一位开始比较，
 * 如果是逆序，则进行交换，依次比较，直到插入到适当位置，使之成为新的有序表
 */
public class InsertionSort {

    public void insertionSort(int[] arr){
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                int j1 = j - 1;
                // 比较交换
                if (arr[j1] > arr[j]) {
                    int temp = arr[j];
                    arr[j] = arr[j1];
                    arr[j1] = temp;
                } else {
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {3, 9, -1, 10, -2};
        InsertionSort insertionSort = new InsertionSort();
        insertionSort.insertionSort(arr);
        System.out.println(Arrays.toString(arr));

        int[] randomArr = new int[100000];
        for (int i = 0; i < randomArr.length; i++) {
            randomArr[i] = (int) (Math.random() * 100000);
        }

        long startTime = new Date().getTime();
        insertionSort.insertionSort(randomArr);
        System.out.println("time = " + (new Date().getTime() - startTime));
        // 测试100000个数据，插入排序耗时：2311 ms
    }
}
