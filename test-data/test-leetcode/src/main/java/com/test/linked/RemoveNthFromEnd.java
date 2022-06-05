package com.test.linked;

/**
 * 删除链表的倒数第N个节点
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 * <p>
 * 进阶：你能尝试使用一趟扫描实现吗？
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 * 示例 2：
 * <p>
 * 输入：head = [1], n = 1
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：head = [1,2], n = 1
 * 输出：[1]
 *  
 * <p>
 * 提示：
 * <p>
 * 链表中结点的数目为 sz
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xn2925/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class RemoveNthFromEnd {

    // 方法二：双指针
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 边界条件判断
        if(head == null) {
            return null;
        }
        ListNode dummy = new ListNode(0, head);

        ListNode first = head;
        ListNode second = dummy;
        // 双指找到相差n的间隔
        while (first != null && n-- > 0 ) {
            first = first.next;
        }
        if(n > 0) {
            // 说明n大于链表长度
            return head;
        }else {
            // 后移，直到 first 为空
            while (first != null) {
                first = first.next;
                second = second.next;
            }
        }

        // 最后 second.next 为要删除的节点
        second.next = second.next.next;
        return dummy.next;
    }

    public static void main(String[] args) {
        //  head = [1,2,3,4,5], n = 2
        ListNode head = new ListNode(1);
        ListNode next1 = new ListNode(2);
        ListNode next2 = new ListNode(3);
        ListNode next3 = new ListNode(4);
        ListNode next4 = new ListNode(5);
        head.next = next1;
        next1.next = next2;
        next2.next = next3;
        next3.next = next4;
        ListNode temp = new RemoveNthFromEnd().removeNthFromEnd(head, 6);
        if(temp == null) {
            System.out.println(temp);
            return;
        }
        do {
            System.out.println(temp.val);
        } while ((temp = temp.next) != null);
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

    // 方法一
//    public ListNode removeNthFromEnd(ListNode head, int n) {
//        // 边界条件判断
//        if(head == null) {
//            return null;
//        }
//        // 倒数第N个节点
//        ListNode nth = head;
//        ListNode tail = nth;
//        ListNode pre = null;
//        // 遍历链表
//        while (nth != null) {
//            int i = n;
//            // 找到倒数第n个节点
//            while (i > 0) {
//                tail = tail.next;
//                if(tail == null) {
//                    // 说明找到了倒数第N个节点
//                    break;
//                }
//                i--;
//            }
//            // 说明找到了倒数第N个节点remove
//            if(tail == null) {
//                break;
//            }
//            if(nth == null){
//                // 如果为空，说明未找到
//                return head;
//            }
//            // 后移
//            pre = nth;
//            nth = nth.next;
//            tail = nth;
//        }
//
//        // 移除nth
//        if(nth.next == null) {
//            if(pre == null) {
//                return null;
//            }else {
//                pre.next = null;
//            }
//        } else {
//            nth.val =  nth.next.val;
//            nth.next = nth.next.next;
//        }
//        return head;
//    }
}


