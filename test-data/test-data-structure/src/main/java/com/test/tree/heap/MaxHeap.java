package com.test.tree.heap;

import java.util.Arrays;

/**
 * 最大堆
 */
public class MaxHeap {

    /**
     * 最大堆构建
     */
    public void initMaxHeap(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
//        System.out.println("原 arr = " + Arrays.toString(arr));
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
//            System.out.println("arr = " + Arrays.toString(arr));
        }
    }

    /**
     * 最大堆的元素插入
     */
    public int[] insertToMaxHeap(int[] arr, int val) {
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
            if (arr[parent] < arr[index]) {
                // 进行交换
                swap(arr, parent, index);
                // 循环父级
                index = parent;
            } else {
                break;
            }
        }
    }
    public int[] deletFromMaxHeap(int[] arr) {
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

            int max = left;
            // 存在右节点情况
            if(right < arr.length && arr[left] < arr[right]) {
                max = right;
            }
            // 将当前节点 与 两个子节点中较大的节点  进行比较
            if (arr[current] < arr[max]) {
                // 当前节点值大于较大值，则进行交换
                swap(arr, current, max);
                // 循环子节点
                current = max;
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
        MaxHeap maxHeap = new MaxHeap();
        // 初始化最大堆
        maxHeap.initMaxHeap(arr);
        System.out.println("arr = " + Arrays.toString(arr));
        // 最大堆的元素插入
        int[] addToMaxHeap = maxHeap.insertToMaxHeap(arr, 0);
        System.out.println("最大堆的元素插入 = " + Arrays.toString(addToMaxHeap));
        // 最大堆的节点删除
        int[] deletMaxHeap = maxHeap.deletFromMaxHeap(addToMaxHeap);
        System.out.println("最大堆的节点删除 = " + Arrays.toString(deletMaxHeap));
    }

}
