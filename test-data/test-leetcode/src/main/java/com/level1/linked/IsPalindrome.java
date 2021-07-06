package com.level1.linked;

/**
 * 回文链表
 * 请判断一个链表是否为回文链表。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1->2
 * 输出: false
 * 示例 2:
 * <p>
 * 输入: 1->2->2->1
 * 输出: true
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnv1oc/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class IsPalindrome {
    public boolean isPalindrome(ListNode head) {
        if(head.next == null) {
            return true;
        }

        ListNode fast = head, slow = head;
        //通过快慢指针找到中点
        while (fast != null && fast.next != null) {
            fast = fast.next.next;

            if(fast != null && fast.next != null) {
                // 反转slow区的值, slow指针不变
                ListNode temp = slow.next;
                slow.next = temp.next;
                temp.next = head;
                head = temp;
                System.out.println("slow = " + slow.val);
            }
        }
        //如果fast不为空，说明链表的长度是奇数个
        if (fast != null) {
            slow = slow.next;
        }

        ListNode node = head;
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }

        // fast 重新循环前半段
        fast = head;
        // 继续遍历slow
        while ((slow = slow.next) != null) {
            if (fast.val == slow.val) {
                fast = fast.next;
                continue;
            }
            return false;
        }

        // 是回文
        return true;
    }

    public void change(ListNode head, ListNode first) {

    }

    public static void main(String[] args) {
        ListNode node6 = new ListNode(1);
        ListNode node5 = new ListNode(2, node6);
        ListNode node4 = new ListNode(3, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);
        ListNode node = node1;
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
        boolean palindrome = new IsPalindrome().isPalindrome(node6);
        System.out.println(palindrome);
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
