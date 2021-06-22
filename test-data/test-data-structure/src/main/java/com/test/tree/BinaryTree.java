package com.test.tree;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree {


    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        TreeNode node = binaryTree.initTree();
        List<Integer> preList = binaryTree.preOrder(node);
        System.out.println("preList = " + preList);
        List<Integer> midOrder = binaryTree.midOrder(node);
        System.out.println("midOrder = " + midOrder);
        List<Integer> afterOrder = binaryTree.afterOrder(node);
        System.out.println("afterOrder = " + afterOrder);
    }

    // 先遍历左子树，再遍历右子树，最后输出父节点
    public List<Integer> afterOrder(TreeNode node) {
        List<Integer> list = new ArrayList<>();
        if (node != null) {
            list.addAll(afterOrder(node.left));
            list.addAll(afterOrder(node.right));
            list.add(node.val);
        }
        return list;
    }

    // 先遍历左子树，再输出父节点，再遍历右子树
    public List<Integer> midOrder(TreeNode node) {
        List<Integer> list = new ArrayList<>();
        if (node != null) {
            list.addAll(midOrder(node.left));
            list.add(node.val);
            list.addAll(midOrder(node.right));
        }
        return list;
    }

    // 先输出父节点，再遍历左子树和右子树
    public List<Integer> preOrder(TreeNode node) {
        List<Integer> list = new ArrayList<>();
        if (node != null) {
            list.add(node.val);
            list.addAll(preOrder(node.left));
            list.addAll(preOrder(node.right));
        }
        return list;
    }

    private TreeNode initTree() {
        //      1
        //   2      3
        // 4   5  6    7
        TreeNode node2 = new TreeNode(2, new TreeNode(4), new TreeNode(5));
        TreeNode node3 = new TreeNode(3, new TreeNode(6), new TreeNode(7));
        return new TreeNode(1, node2, node3);
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
