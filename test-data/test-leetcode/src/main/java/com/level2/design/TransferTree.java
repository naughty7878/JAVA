package com.level2.design;


import java.util.*;

/**
 * 二叉树的序列化与反序列化
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 *
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 *
 * 提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,2,3,null,null,4,5]
 * 输出：[1,2,3,null,null,4,5]
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
 * 输入：root = [1,2]
 * 输出：[1,2]
 *  
 *
 * 提示：
 *
 * 树中结点数在范围 [0, 104] 内
 * -1000 <= Node.val <= 1000
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-medium/xwxa3m/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class TransferTree {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        return rserialize(root, "");
    }

    public String rserialize(TreeNode root, String str) {
        if (root == null) {
            str += "None,";
        } else {
            str += str.valueOf(root.val) + ",";
            str = rserialize(root.left, str);
            str = rserialize(root.right, str);
        }
        return str;
    }



    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] data_array = data.split(",");
        List<String> data_list = new LinkedList<String>(Arrays.asList(data_array));
        return rdeserialize(data_list);
    }

    public TreeNode rdeserialize(List<String> l) {
        if (l.get(0).equals("None")) {
            l.remove(0);
            return null;
        }

        TreeNode root = new TreeNode(Integer.valueOf(l.get(0)));
        l.remove(0);
        root.left = rdeserialize(l);
        root.right = rdeserialize(l);

        return root;
    }


    public static void main(String[] args) {
        TransferTree transferTree = new TransferTree();
        TreeNode node = transferTree.deserialize2("[1, 2, 3, null, null, 4, 5]");
        String str = transferTree.serialize(node);
        System.out.println(str);
        TreeNode node2 = transferTree.deserialize(str);
        String s = transferTree.serialize2(node2);
        System.out.println(s);
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


    public String serialize2(TreeNode root) {
        if(root == null) {
            return null;
        }
        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        // 层高
//        double v = Math.log(list.size() + 1) / Math.log(2);
//        System.out.println(v);
        Queue<TreeNode> queueOther = new LinkedList<>();
        do {
            boolean dataFlag = false;
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                if (node != null) {
                    list.add(node.val);
                    queueOther.add(node.left);
                    queueOther.add(node.right);
                    if(node.left != null || node.right != null) {
                        dataFlag = true;
                    }
                }else {
                    list.add(null);
                    queueOther.add(null);
                    queueOther.add(null);
                }
            }
            if (!dataFlag) {
                break;
            }else {
                Queue<TreeNode> tempQueue = queue;
                queue = queueOther;
                queueOther = tempQueue;
            }
        } while (true);

        return list.toString();
    }

    public TreeNode deserialize2(String data) {
        if(data == null) {
            return null;
        }else if(!data.startsWith("[") || !data.endsWith("]")) {
            return null;
        }
        String[] strings = data.substring(1, data.length() - 1).split(",");
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.valueOf(strings[0].trim()));
        queue.add(root);
        for (int i = 1; i < strings.length && !queue.isEmpty(); i += 2) {
            TreeNode temp = queue.poll();
            if(!"null".equals(strings[i].trim()) && temp != null) {
                temp.left = new TreeNode(Integer.valueOf(strings[i].trim()));
                queue.add(temp.left);
            }else {
                queue.add(null);
            }
            if(!"null".equals(strings[i+1].trim()) && temp != null) {
                temp.right = new TreeNode(Integer.valueOf(strings[i+1].trim()));
                queue.add(temp.right);
            }else {
                queue.add(null);
            }
        }
        return root;
    }
}
