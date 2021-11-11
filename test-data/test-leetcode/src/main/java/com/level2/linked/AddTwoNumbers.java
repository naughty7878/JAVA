package com.level2.linked;

import com.level1.linked.ReverseList;

/**
 * 两数相加
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 *
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 *
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 * 示例 2：
 *
 * 输入：l1 = [0], l2 = [0]
 * 输出：[0]
 * 示例 3：
 *
 * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * 输出：[8,9,9,9,0,0,0,1]
 *  
 *
 * 提示：
 *
 * 每个链表中的节点数在范围 [1, 100] 内
 * 0 <= Node.val <= 9
 * 题目数据保证列表表示的数字不含前导零
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-medium/xvw73v/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode first = new ListNode(0);
        ListNode last = new ListNode(0);
        first.next = last;
        ListNode pre = null;
        int v = 0;
        while (l1 != null || l2 != null) {
            v = v > 9 ? 1 : 0;
            if(l1 != null) {
                v += l1.val;
                l1 = l1.next;
            }
            if(l2 != null) {
                v += l2.val;
                l2 = l2.next;
            }
            last.val = v % 10;
            pre = last;
            last.next = new ListNode(0);
            last = last.next;
        }
        if (v > 9) {
            last.val = 1;
        }else {
            if (pre != null) {
                pre.next = null;
            }
        }
        return first.next;
    }

    public static void main(String[] args) {
        AddTwoNumbers obj = new AddTwoNumbers();

        // l1 = [2,4,3], l2 = [5,6,4]
        ListNode l1 = new ListNode(2,
                        new ListNode(4,
                        new ListNode(3)));
        ListNode l2 = new ListNode(5,
                new ListNode(6,
                        new ListNode(4)));
        ListNode listNode = obj.addTwoNumbers(l1, l2);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
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
    static class ListNode {
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


