package com.level2.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树的锯齿形层次遍历
 * 给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回锯齿形层序遍历如下：
 *
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-medium/xvle7s/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class ZigzagLevelOrder {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();

        LinkedList<TreeNode> leftQueue = new LinkedList<>();
        LinkedList<TreeNode> rightQueue = new LinkedList<>();
        if (root != null) {
            leftQueue.add(root);
        }
        while (leftQueue.size() > 0 || rightQueue.size() > 0) {
            List<Integer> itemList = new ArrayList<>();
            while (leftQueue.size() > 0) {
                TreeNode node = leftQueue.pollFirst();
                itemList.add(node.val);
                if (node.left != null) {
                    rightQueue.addLast(node.left);
                }
                if (node.right != null) {
                    rightQueue.addLast(node.right);
                }
            }
            if(itemList.size() > 0) {
                lists.add(itemList);
            }

            List<Integer> itemList2 = new ArrayList<>();
            while (rightQueue.size() > 0) {
                TreeNode node = rightQueue.pollLast();
                itemList2.add(node.val);
                if (node.right != null) {
                    leftQueue.addFirst(node.right);
                }
                if (node.left != null) {
                    leftQueue.addFirst(node.left);
                }
            }
            if(itemList2.size() > 0) {
                lists.add(itemList2);
            }
        }
        return lists;
    }


    public static void main(String[] args) {
        // [3,9,20,null,null,15,7]
        TreeNode left = new TreeNode(9);
        TreeNode right = new TreeNode(20, new TreeNode(15), new TreeNode(7));
        TreeNode node = new TreeNode(3, left, right);
        List<List<Integer>> lists = new ZigzagLevelOrder().zigzagLevelOrder(node);
        System.out.println("lists = " + lists);
    }
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
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
