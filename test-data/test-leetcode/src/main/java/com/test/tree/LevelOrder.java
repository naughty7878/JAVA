package com.test.tree;

import java.util.*;

public class LevelOrder {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        if (root == null) {
            return ret;
        }

        // 利用队列
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int currentSize = queue.size();
            for (int i = 0; i < currentSize; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            ret.add(list);
        }
        return ret;
    }

    public static void main(String[] args) {
        // 给定二叉树 [3,9,20,null,null,15,7]
        TreeNode node = new TreeNode(3);
        node.left = new TreeNode(9);
        node.right = new TreeNode(20);
        node.right.left = new TreeNode(15);
        node.right.right = new TreeNode(7);
        List<List<Integer>> lists = new LevelOrder().levelOrder(node);
        lists.stream().forEach(
                System.out::println
        );
    }

    // 方法1
    public List<List<Integer>> levelOrder1(TreeNode root) {
        List<List<Integer>> rootList = new ArrayList<>();
        List<TreeNode> treeNodes = levelList(rootList, Arrays.asList(root));
        while (treeNodes.size() > 0) {
            treeNodes  = levelList(rootList, treeNodes);
        }
        return rootList;
    }

    private List<TreeNode> levelList(List<List<Integer>> rootList, List<TreeNode> treeNodes) {
        List<Integer> list = new ArrayList<>();
        List<TreeNode> nodeList = new ArrayList<>();
        for (TreeNode node: treeNodes) {
            if(node == null) {
                continue;
            }
            list.add(node.val);
            nodeList.add(node.left);
            nodeList.add(node.right);
        }
        if(list.size() > 0) {
            rootList.add(list);
        }
        return nodeList;
    }

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int val) { this.val = val; }
     *     TreeNode(int val, TreeNode left, TreeNode right) {
     *         this.val = val;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     */
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
