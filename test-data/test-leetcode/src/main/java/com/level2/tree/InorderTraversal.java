package com.level2.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树的中序遍历
 * 给定一个二叉树的根节点 root ，返回它的 中序 遍历。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,null,2,3]
 * 输出：[1,3,2]
 * 示例 2：
 *
 * 输入：root = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：root = [1]
 * 输出：[1]
 * 示例 4：
 *
 *
 * 输入：root = [1,2]
 * 输出：[2,1]
 * 示例 5：
 *
 *
 * 输入：root = [1,null,2]
 * 输出：[1,2]
 *  
 *
 * 提示：
 *
 * 树中节点数目在范围 [0, 100] 内
 * -100 <= Node.val <= 100
 *  
 *
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-medium/xv7pir/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class InorderTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        if(root != null) {
            TreeNode temp = root;
            while (true) {

                while (true) {
                    if (temp.left == null) {
                        list.add(temp.val);
                        break;
                    }else {
                        TreeNode left = temp.left;
                        temp.left = null;
                        // 存起来
                        stack.add(temp);
                        // 继续往左找
                        temp = left;

                    }
                }
                // 找右节点
                if(temp.right != null){
                    temp = temp.right;
                } else if (temp.right == null && stack.size() > 0) {
                    temp = stack.pop();
                } else {
                    break;
                }
            }
        }
        return list;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        List<Integer> list = new InorderTraversal().inorderTraversal(root);
        System.out.println(list);
    }



    public List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root != null) {
            List<Integer> leftList = inorderTraversal1(root.left);
            List<Integer> rightList = inorderTraversal1(root.right);
            list.addAll(leftList);
            list.add(root.val);
            list.addAll(rightList);
        }
        return list;
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
