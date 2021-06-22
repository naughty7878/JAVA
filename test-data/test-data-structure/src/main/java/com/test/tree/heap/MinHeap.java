package com.test.tree.heap;

import java.util.Arrays;

/**
 * 最小堆
 */
public class MinHeap {

    /**
     * 最小堆构建
     */
    public void initMinHeap(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        System.out.println("原 arr = " + Arrays.toString(arr));
        // 遍历单次上浮
        siftUpLoop(arr);
    }

    /**
     * 遍历单次上浮
     * 根据父节点进行上浮
     */
    public void siftUpLoop(int[] arr) {

        // 从最后一个代子节点的树开始
        int parent = (arr.length - 1 - 1) / 2;
        // 遍历
        for (; parent >= 0; parent--) {
            siftDown(arr, parent);
            System.out.println("arr = " + Arrays.toString(arr));
        }
    }

    /**
     * 最小堆的元素插入
     */
    public int[] insertToMinHeap(int[] arr, int val) {
        // 复制生成 lenth + 1 长度的数组
        int[] nums = Arrays.copyOf(arr, arr.length + 1);
        // 将插入元素放到数组最后
        nums[nums.length - 1] = val;

        System.out.println("新数组 nums = " + Arrays.toString(nums));

        // 循环上浮最后一个元素
        siftUp(nums, nums.length - 1);
        return nums;
    }

    /**
     * 循环上浮
     * 根据当前节点进行上浮
     */
    public void siftUp(int[] arr, int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            // 将父节点 与 当前节点  进行比较
            if (arr[parent] > arr[index]) {
                // 进行交换
                swap(arr, parent, index);
                // 循环父级
                index = parent;
            } else {
                break;
            }
        }
    }
    public int[] deletFromMinHeap(int[] arr) {
        // 将数组中最后一个元素赋值给  arr[0]
        arr[0] = arr[arr.length - 1];
        // 删除最后一个数，得到新数组
        int[] nums = Arrays.copyOf(arr,  arr.length - 1);
        System.out.println("新数组 nums = " + Arrays.toString(nums));

        // 循环下浮元素
        siftDown(nums, 0);
        return nums;
    }

    private void siftDown(int[] arr, int current) {

        while (current < arr.length / 2) {
            int left = 2 * current + 1;
            int right = 2 * current + 2;

            int min = left;
            // 存在右节点情况
            if(right < arr.length && arr[left] > arr[right]) {
                min = right;
            }
            // 将当前节点 与 两个子节点中较小的节点  进行比较
            if (arr[current] > arr[min]) {
                // 当前节点值大于较小值，则进行交换
                swap(arr, current, min);
                // 循环子节点
                current = min;
            }else {
                break;
            }
        }

    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {9, 3, 7, 6, 5, 1, 10, 2};
        MinHeap minHeap = new MinHeap();
        // 初始化最小堆
        minHeap.initMinHeap(arr);
        System.out.println("arr = " + Arrays.toString(arr));
        // 最小堆的元素插入
        int[] addToMinHeap = minHeap.insertToMinHeap(arr, 0);
        System.out.println("最小堆的元素插入 = " + Arrays.toString(addToMinHeap));
        // 最小堆的节点删除
        int[] deletMinHeap = minHeap.deletFromMinHeap(addToMinHeap);
        System.out.println("最小堆的节点删除 = " + Arrays.toString(deletMinHeap));
    }

}
