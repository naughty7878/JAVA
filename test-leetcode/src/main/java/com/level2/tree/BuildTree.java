package com.level2.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * 从前序与中序遍历序列构造二叉树
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 相关标签
 * 树
 * 深度优先搜索
 * 数组
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-medium/xvix0d/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class BuildTree {

    private Map<Integer, Integer> indexMap = new HashMap<>();


    public TreeNode buildTree(int[] preorder, int[] inorder) {

        for (int i = 0; i < inorder.length; i++) {
            indexMap.put(inorder[i], i);
        }

        return buildTree(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    public TreeNode buildTree(int[] preorder, int[] inorder, int preLeft, int preRight, int inLeft, int inRight) {
        if (preLeft <= preRight && inLeft <= inRight) {
            Integer rootIndex = indexMap.get(preorder[preLeft]);
            TreeNode node = new TreeNode(preorder[preLeft]);
            node.left = buildTree(preorder, inorder, preLeft + 1,
                    preLeft + (rootIndex - inLeft),
                    inLeft, rootIndex - 1);
            node.right = buildTree(preorder, inorder, preLeft + (rootIndex - inLeft) + 1, preRight,
                    rootIndex + 1, inRight);
            return  node;
        }
        return null;
    }

    public static void main(String[] args) {
        // 前序遍历 preorder =[3,9,20,15,7]
        // 中序遍历 inorder = [9,3,15,20,7]
        // [ 根节点, [左子树的前序遍历结果], [右子树的前序遍历结果] ]
        // [ [左子树的中序遍历结果], 根节点, [右子树的中序遍历结果] ]
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};
        TreeNode treeNode = new BuildTree().buildTree(preorder, inorder);
        System.out.println(treeNode);
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
