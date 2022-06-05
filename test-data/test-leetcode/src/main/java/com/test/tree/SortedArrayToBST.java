package com.test.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 将有序数组转换为二叉搜索树
 * 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。
 *
 * 高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：nums = [-10,-3,0,5,9]
 * 输出：[0,-3,9,-10,null,5]
 * 解释：[0,-10,5,null,-3,null,9] 也将被视为正确答案：
 *
 * 示例 2：
 *
 *
 * 输入：nums = [1,3]
 * 输出：[3,1]
 * 解释：[1,3] 和 [3,1] 都是高度平衡二叉搜索树。
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * nums 按 严格递增 顺序排列
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xninbt/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class SortedArrayToBST {

    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBST(nums, 0, nums.length-1);
    }

    public TreeNode sortedArrayToBST(int[] nums, int start, int end) {
        // 获取中心点
        int center = (end + start) / 2;
        TreeNode node = new TreeNode(nums[center]);

        // 获取左节点
        if (start < center) {
            node.left =  sortedArrayToBST(nums, start, center-1);
        }

        // 获取右节点
        if (center < end) {
            node.right =  sortedArrayToBST(nums, center+1, end);
        }

        return node;
    }

    public static void main(String[] args) {
        int[] nums = {-10,-3,0,5,9};
        SortedArrayToBST obj = new SortedArrayToBST();
        TreeNode treeNode = obj.sortedArrayToBST(nums);
        obj.print(treeNode);
    }

    public void print(TreeNode root) {

        List<List<Integer>> ret = new ArrayList<>();
        if (root == null) {
            return;
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
                System.out.print(node.val + ", ");
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            System.out.println();
            ret.add(list);
        }
        return;

    }

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *      int val;
     *      TreeNode left;
     *      TreeNode right;
     *      TreeNode() {}
     *      TreeNode(int val) { this.val = val; }
     *      TreeNode(int val, TreeNode left, TreeNode right) {
     *          this.val = val;
     *          this.left = left;
     *          this.right = right;
     *      }
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
