package com.test.math.linked;

/**
 * 旋转链表
 * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动k个位置。
 *
 * 
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[4,5,1,2,3]
 * 示例 2：
 *
 *
 * 输入：head = [0,1,2], k = 4
 * 输出：[2,0,1]
 * 
 *
 * 提示：
 *
 * 链表中节点的数目在范围 [0, 500] 内
 * -100 <= Node.val <= 100
 * 0 <= k <= 2 * 109
 * 相关标签
 * 链表
 * 双指针
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/tencent/x5pmxc/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class RotateRight {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) return null;
        ListNode tail = head;
        int count = 1;
        while (tail.next != null) {
            tail = tail.next;
            count ++;
        }
        k = count - (k % count);


        // 首尾相连
        tail.next = head;

        ListNode newTail = tail;
        ListNode newHead = head;

        while (k > 0) {
            newTail = newTail.next;
            newHead = newHead.next;
            k--;
        }
        newTail.next = null;

        return newHead;
    }

    public static void main(String[] args) {

        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3,
                new ListNode(4, new ListNode(5)))));
        int k = 6;

        ListNode node = new RotateRight().rotateRight(head, k);
        while (node != null) {
            System.out.println("node.val = " + node.val);
            node = node.next;
        }
    }

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */

    private static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
