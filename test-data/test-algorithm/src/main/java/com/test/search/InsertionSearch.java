package com.test.search;

import java.util.Arrays;
import java.util.Date;

/**
 * 差值查找
 *
 */
public class InsertionSearch {

    public int insertionSearch(int[] arr, int value) {
        return insertionSearch(arr, 0, arr.length - 1, value);
    }

    public int insertionSearch(int[] arr, int start, int end, int value) {

        if (start > end) {
            return -1;
        }
//        System.out.println((value * 1.0 - arr[start]) / (arr[end] - arr[start])  * (end - start));
        int mid = (int) (start * 1.0 +  (value * 1.0 - arr[start]) / (arr[end] - arr[start])  * (end - start));
        if (arr[mid] < value) {
            return insertionSearch(arr, mid + 1, end, value);
        } else if (arr[mid] == value) {
            return mid;
        } else {
            return insertionSearch(arr, start, mid - 1, value);
        }
    }

    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        InsertionSearch insertionSearch = new InsertionSearch();
        Arrays.sort(arr);
        int index = insertionSearch.insertionSearch(arr, 3);
        System.out.println("index = " + index);

        int[] randomArr = new int[10000000];
        for (int i = 0; i < randomArr.length; i++) {
            randomArr[i] = (int) (Math.random() * 10000000);
        }

        int v = randomArr[0];
        Arrays.sort(randomArr);
        long startTime = new Date().getTime();
        int arrIndex = insertionSearch.insertionSearch(randomArr, v);
        System.out.println("time = " + (new Date().getTime() - startTime));
        System.out.println("arrIndex = " + arrIndex);
//        System.out.println("randomArr = " + Arrays.toString(randomArr));
        // 测试100000个数据，桶排序耗时：61 ms
        // 测试10,000,000个数据，计数排序耗时：3435 ms
    }

}
