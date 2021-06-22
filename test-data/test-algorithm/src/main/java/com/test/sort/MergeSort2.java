package com.test.sort;

import java.util.Arrays;
import java.util.Date;

public class MergeSort2 {

    public void mergeSort(int[] arr) {
//        System.out.println("原始数组：   " + Arrays.toString(arr));
        int len = arr.length;
        int lastIndex = len - 1;
        int mid = 0;
        while (mid < lastIndex) {
            int last = -1;
            // mid 相当于步长
            while (last < lastIndex) {
//                System.out.println(Arrays.toString(arr));
                int start1 = last + 1;
                int end1 = start1 + mid;
                int start2 = end1 + 1;
                int end2 = start2 + mid;
                last = end2;

                if (end2 <= lastIndex) {
                    mergeSort(arr, start1, end1, start2, end2);
                } else if (end2 > lastIndex && lastIndex >= start2) {
                    mergeSort(arr, start1, end1, start2, lastIndex);
                } else {
                    continue;
                }
            }

            mid = (mid + 1) * 2 - 1;
        }
    }

    private void mergeSort(int[] arr, int start1, int end1, int start2, int end2) {
        int[] temp = new int[(end1 - start1 + 1) + (end2 - start2 + 1)];
        int i = start1;
        int j = start2;
//        int len = arr.length;
        for (int t = 0; t < temp.length; t++) {
            if (i <= end1 && j <= end2) {
                if (arr[i] > arr[j]) {
                    temp[t] = arr[j];
                    j++;
                } else {
                    temp[t] = arr[i];
                    i++;
                }
            } else if (i <= end1 && j > end2) {
                temp[t] = arr[i];
                i++;
            } else if (i > end1 && j <= end2) {
                temp[t] = arr[j];
                j++;
            }
        }
        int n = 0;
        for (int k = start1; k <= end1; k++) {
            arr[k] = temp[n++];
        }
        for (int l = start2; l <= end2; l++) {
            arr[l] = temp[n++];
        }
    }


    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        MergeSort2 mergeSort = new MergeSort2();
        mergeSort.mergeSort(arr);
        System.out.println(Arrays.toString(arr));

        int[] randomArr = new int[100000];
        for (int i = 0; i < randomArr.length; i++) {
            randomArr[i] = (int) (Math.random() * 100000);
        }

        long startTime = new Date().getTime();
        mergeSort.mergeSort(randomArr);
        System.out.println("time = " + (new Date().getTime() - startTime));
//        System.out.println("randomArr = " + Arrays.toString(randomArr));
        // 测试100000个数据，归并排序耗时：43 ms
        // 测试10,000,000个数据，归并排序耗时：2481 ms
    }

}
