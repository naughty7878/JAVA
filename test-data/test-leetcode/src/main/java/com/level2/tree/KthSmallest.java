package com.level2.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉搜索树中第K小的元素
 * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：root = [3,1,4,null,2], k = 1
 * 输出：1
 * 示例 2：
 *
 *
 * 输入：root = [5,3,6,2,4,null,null,1], k = 3
 * 输出：3
 *  
 *
 *  
 *
 * 提示：
 *
 * 树中的节点数为 n 。
 * 1 <= k <= n <= 104
 * 0 <= Node.val <= 104
 *  
 *
 * 进阶：如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化算法？
 *
 * 相关标签
 * 树
 * 深度优先搜索
 * 二叉搜索树
 * 二叉树
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-medium/xvuyv3/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class KthSmallest {
    public int kthSmallest(TreeNode root, int k) {
        List<TreeNode> list = new ArrayList<>();
        treeToList(root, list);
//        list.sort((o1, o2) -> o1.val - o2.val);
        if (1 <= k && k <= list.size()) {
            return list.get(k - 1).val;
        }
        return -1;
    }

    public void treeToList(TreeNode root,  List<TreeNode> list ) {
        if (root != null) {
            treeToList(root.left, list);
            list.add(root);
            treeToList(root.right, list);
        }
    }

    public static void main(String[] args) {
        // root = [3,1,4,null,2], k = 1
        TreeNode root = new TreeNode(3, new TreeNode(1, null, new TreeNode(2)), new TreeNode(4));
        int kthSmallest = new KthSmallest().kthSmallest(root, 1);
        System.out.println("kthSmallest = " + kthSmallest);
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
