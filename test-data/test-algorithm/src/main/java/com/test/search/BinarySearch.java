package com.test.search;

import com.test.sort.BucketSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * 二分查找
 * 步骤：
 * 1、确定数组中间的下标
 * 2、比较查找数与中间数的值，确认查找数在中间数的左边还是右边
 * 3、在确认的范围内，继续重复1-2步骤
 * 4、知道查找到数，进行返回
 */
public class    BinarySearch {

    public int binarySearch(int[] arr, int value) {
        return binarySearch(arr, 0, arr.length - 1, value);
    }

    public int binarySearch(int[] arr, int start, int end, int value) {

        if (start > end) {
            return -1;
        }

        int mid = (start + end) / 2;
        if (arr[mid] < value) {
            return binarySearch(arr, mid + 1, end, value);
        } else if (arr[mid] == value) {
            return mid;
        } else {
            return binarySearch(arr, start, mid - 1, value);
        }
    }

    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        BinarySearch binarySearch = new BinarySearch();
        Arrays.sort(arr);
        int index = binarySearch.binarySearch(arr, 3);
        System.out.println("index = " + index);

        int[] randomArr = new int[10000000];
        for (int i = 0; i < randomArr.length; i++) {
            randomArr[i] = (int) (Math.random() * 10000000);
        }

        int v = randomArr[0];
        Arrays.sort(randomArr);
        long startTime = new Date().getTime();
        int arrIndex = binarySearch.binarySearch(randomArr, v);
        System.out.println("time = " + (new Date().getTime() - startTime));
        System.out.println("arrIndex = " + arrIndex);
//        System.out.println("randomArr = " + Arrays.toString(randomArr));
        // 测试100000个数据，桶排序耗时：61 ms
        // 测试10,000,000个数据，计数排序耗时：3435 ms
    }
}
