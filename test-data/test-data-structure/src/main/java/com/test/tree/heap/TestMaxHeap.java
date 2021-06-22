package com.test.tree.heap;

import java.util.Arrays;
import java.util.Date;

public class TestMaxHeap {

    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        MaxHeap maxHeap = new MaxHeap();
        maxHeap.initMaxHeap(arr);
        System.out.println("arr = " + Arrays.toString(arr));
        
        int[] randomArr = new int[100000];
        for (int i = 0; i < randomArr.length; i++) {
            randomArr[i] = (int) (Math.random() * 100000);
        }

        long startTime = new Date().getTime();
        maxHeap.initMaxHeap(randomArr);
        System.out.println("time = " + (new Date().getTime() - startTime));
        System.out.println("randomArr[0] = " + randomArr[0]);
//        System.out.println("randomArr = " + Arrays.toString(randomArr));
        // 测试100000个数据，堆排序耗时：2 ms
        // 测试10,000,000个数据，堆排序耗时：128 ms
    }
}
