package com.test.sort;

import java.util.Arrays;
import java.util.Date;

/**
 * 归并排序
 * 基本思想：
 * 1、先将数组分成左右两部分
 * 2、对左右两部分，分别进行排序，排序方法递归，使用步骤1
 * 3、然后对左右两部分，进行归并排序
 *    3.1 设定两个指针，最初位置分别为两个已经排序序列的起始位置
 *    3.2 比较两个指针所指向的元素，选择相对小的元素放入到合并空间，并移动指针到下一位置
 *    3.3 重复步骤 3.2 直到某一指针达到序列尾
 *    3.4 将另一序列剩下的所有元素直接复制到合并序列尾。
 */
public class MergeSort {

    public void mergeSort(int[] arr) {
//        System.out.println("原始数组：   " + Arrays.toString(arr));
        mergeSort(arr, 0, arr.length - 1, new int[arr.length]);
    }

    // 分+合方法
    public void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2; // 中间索引
            // 向左递归进行分解
            mergeSort(arr, left, mid, temp);
            // 向右递归进行分解
            mergeSort(arr, mid + 1, right, temp);
            // 合并
            merge(arr, left, mid, right, temp);
        }
    }

    // 合并的方法
    /**
     *
     * @param arr   排序的原始数组
     * @param left  左边有序序列的初始索引
     * @param mid   中间索引
     * @param right 右边索引
     * @param temp  做中转的数组
     */
    public void merge(int[] arr, int left, int mid, int right, int[] temp) {

        int i = left; // 初始化i, 左边有序序列的初始索引
        int j = mid + 1; // 初始化j, 右边有序序列的初始索引
        int t = 0; // 指向temp数组的当前索引

        // (一)
        // 先把左右两边(有序)的数据按照规则填充到temp数组
        // 直到左右两边的有序序列，有一边处理完毕为止
        while (i <= mid && j <= right) {// 继续
            // 如果左边的有序序列的当前元素，小于等于右边有序序列的当前元素
            // 即将左边的当前元素，填充到 temp数组
            // 然后 t++, i++
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                t += 1;
                i += 1;
            } else { // 反之,将右边有序序列的当前元素，填充到temp数组
                temp[t] = arr[j];
                t += 1;
                j += 1;
            }
        }

        // (二)
        // 把有剩余数据的一边的数据依次全部填充到temp
        while (i <= mid) { // 左边的有序序列还有剩余的元素，就全部填充到temp
            temp[t] = arr[i];
            t += 1;
            i += 1;
        }

        while (j <= right) { // 右边的有序序列还有剩余的元素，就全部填充到temp
            temp[t] = arr[j];
            t += 1;
            j += 1;
        }

        // (三)
        // 将temp数组的元素拷贝到arr
        // 注意，并不是每次都拷贝所有
        t = 0;
        int tempLeft = left; //
        // 第一次合并 tempLeft = 0 , right = 1 //第二次： tempLeft = 2 right = 3 //第三次： tL=0 ri=3
        // 最后一次 tempLeft = 0 right = 7
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            t += 1;
            tempLeft += 1;
        }

    }






    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        MergeSort mergeSort = new MergeSort();
        mergeSort.mergeSort(arr);
        System.out.println(Arrays.toString(arr));

        int[] randomArr = new int[10000000];
        for (int i = 0; i < randomArr.length; i++) {
            randomArr[i] = (int) (Math.random() * 10000000);
        }

        long startTime = new Date().getTime();
        mergeSort.mergeSort(randomArr);
        System.out.println("time = " + (new Date().getTime() - startTime));
//        System.out.println("randomArr = " + Arrays.toString(randomArr));
        // 测试100000个数据，归并排序耗时：28 ms
        // 测试10,000,000个数据，归并排序耗时：2003 ms
    }

}
