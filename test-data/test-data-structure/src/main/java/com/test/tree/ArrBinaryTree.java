package com.test.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 顺序二叉树存储
 */
public class ArrBinaryTree {

    public List<Integer> preOrder(int[] arr) {
        return preOrder(arr, 0);
    }

    // 前序遍历
    private List<Integer> preOrder(int[] arr, int index) {
        List<Integer> list = new ArrayList<>();
        if(index < arr.length) {
            list.add(arr[index]);
            list.addAll(preOrder(arr, 2 * index + 1));
            list.addAll(preOrder(arr, 2 * index + 2));
        }
        return list;
    }

    public List<Integer> midOrder(int[] arr) {
        return midOrder(arr, 0);
    }

    // 中序遍历
    private List<Integer> midOrder(int[] arr, int index) {
        List<Integer> list = new ArrayList<>();
        if(index < arr.length) {
            list.addAll(midOrder(arr, 2 * index + 1));
            list.add(arr[index]);
            list.addAll(midOrder(arr, 2 * index + 2));
        }
        return list;
    }

    // 前序遍历转化
    public TreeNode preOrderConvert(int[] arr) {
        return preOrderConvert(arr, 0);
    }

    private TreeNode preOrderConvert(int[] arr, int index) {
        if(index < arr.length) {
            TreeNode treeNode = new TreeNode(arr[index]);
            treeNode.left = preOrderConvert(arr, 2 * index + 1);
            treeNode.right = preOrderConvert(arr, 2 * index + 2);
            return treeNode;
        }
        return null;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree();
        // 前序遍历
        List<Integer> proList = arrBinaryTree.preOrder(arr);
        System.out.println("proList = " + proList);
        // 中序遍历
        List<Integer> midList = arrBinaryTree.midOrder(arr);
        System.out.println("midList = " + midList);

        // 转化成树，前序遍历转化
        TreeNode treeNode = arrBinaryTree.preOrderConvert(arr);
        System.out.println("treeNode = " + treeNode);
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
