package com.test.search;

/**
 * 线性查找
 * 步骤：
 * 1、遍历数组查找
 */
public class SeqSearch {

    public int seqSearch(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (value == arr[i]) {
                return i;
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        // {1, 9, 11, -1, 34, 89}
        int[] arr = {1, 9, 11, -1, 34, 89};
        int i = new SeqSearch().seqSearch(arr, 34);
        System.out.println("i = " + i);

    }


}
