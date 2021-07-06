package com.level1.tree;

/**
 * 验证二叉搜索树
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 *
 * 假设一个二叉搜索树具有如下特征：
 *
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 示例 1:
 *
 * 输入:
 *     2
 *    / \
 *   1   3
 * 输出: true
 * 示例 2:
 *
 * 输入:
 *     5
 *    / \
 *   1   4
 *      / \
 *     3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *      根节点的值为 5 ，但是其右子节点值为 4 。
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xn08xg/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class IsValidBST {

    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    public boolean isValidBST(TreeNode root, Integer leftMin, Integer rightMax) {
        if (root != null) {
            if (root.left != null) {
                if( root.left.val >= root.val) {
                    return false;
                }
                if (leftMin != null && root.left.val <= leftMin) {
                    return false;
                }
            }
            if (root.right != null) {
                if(root.right.val <= root.val ) {
                    return false;
                }
                if(rightMax != null && root.right.val >= rightMax) {
                    return false;
                }
            }
            return isValidBST(root.left, leftMin, root.val) && isValidBST(root.right, root.val, rightMax);
        }
        return true;
    }

    public static void main(String[] args) {
        // 给定二叉树 [3,9,20,null,null,15,27]
        TreeNode node = new TreeNode(9);
        node.left = new TreeNode(3);
        node.right = new TreeNode(20);
        node.right.left = new TreeNode(6);
        node.right.right = new TreeNode(27);
        boolean flag = new IsValidBST().isValidBST(node);
        System.out.println(flag);
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
