package com.test.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 线索化二叉树
 */
public class ThreadBinaryTree {

    // 最后处理过的节点，即当前处理节点的前一个处理节点
    private TreeNode preNode;

    // 中序线索化
    public void midOrderThreadedNodes(TreeNode node) {
        if (node != null) {
            // 1、先线索化左子树
            midOrderThreadedNodes(node.left);
            // 2、线索化当前节点
            if (node.left == null) {
                node.leftType = 1;
                node.left = preNode;
            }
            if (preNode != null && preNode.right == null) {
                preNode.rightType = 1;
                preNode.right = node;
            }
            // 更新preNode节点
            preNode = node;
            // 3、再线索化右子树
            midOrderThreadedNodes(node.right);
        }
    }

    // 遍历中序线索化二叉树
    public List<Integer> midOrderThreadList(TreeNode treeNode) {
        List<Integer> list = new ArrayList<>();
        // 找到leftType = 1 && left == null 的节点
        // 这个节点就是第一个线索化的节点
        TreeNode node = treeNode;
        while (node != null) {

            while (node.leftType == 0) {
                node = node.left;
            }

            list.add(node.val);

            while (node.rightType == 1) {
                node = node.right;
                list.add(node.val);
            }

            node = node.right;
        }
        return list;
    }

    public List<Integer> midOrder(TreeNode node) {
        List<Integer> list = new ArrayList<>();
        if (node != null) {
            list.addAll(midOrder(node.left));
            list.add(node.val);
            list.addAll(midOrder(node.right));
        }
        return list;
    }
    
    public static void main(String[] args) {
        ThreadBinaryTree threadBinaryTree = new ThreadBinaryTree();
        TreeNode treeNode = threadBinaryTree.initTree();
        List<Integer> midOrder = threadBinaryTree.midOrder(treeNode);
        System.out.println("midOrder = " + midOrder);

        threadBinaryTree.midOrderThreadedNodes(treeNode);
        System.out.println("treeNode = " + treeNode);

        List<Integer> orderThreadList = threadBinaryTree.midOrderThreadList(treeNode);
        System.out.println("orderThreadList = " + orderThreadList);
    }

    private TreeNode initTree() {
        //      1
        //   2      3
        // 4   5  6
        TreeNode node2 = new TreeNode(2, new TreeNode(4), new TreeNode(5));
        TreeNode node3 = new TreeNode(3, new TreeNode(6), null);
        return new TreeNode(1, node2, node3);
    }


    static class TreeNode {
        int val;
        // 左指针类型，0树节点指向类型 1线索化指针,前驱节点
        int leftType;
        TreeNode left;
        // 右 指针类型，0树节点指向类型 1线索化指针,后驱节点
        int rightType;
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
