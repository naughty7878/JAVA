package com.tencent.linked;

/**
 * 合并K个排序链表
 * 给你一个链表数组，每个链表都已经按升序排列。
 * <p>
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 * 示例 2：
 * <p>
 * 输入：lists = []
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：lists = [[]]
 * 输出：[]
 * <p>
 * <p>
 * 提示：
 * <p>
 * k == lists.length
 * 0 <= k <= 10^4
 * 0 <= lists[i].length <= 500
 * -10^4 <= lists[i][j] <= 10^4
 * lists[i] 按 升序 排列
 * lists[i].length 的总和不超过 10^4
 * 相关标签
 * 链表
 * 分治
 * 堆（优先队列）
 * 归并排序
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/tencent/x5ode6/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class MergeKLists {


    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }else if(lists.length == 1) {
            return lists[0];
        } else {
            ListNode[] listNodes = new ListNode[(lists.length + 1) / 2];
            while (lists.length > 1) {
                for (int i = 0; i < listNodes.length; i++) {
                    if (i * 2 + 1 < lists.length) {
                        listNodes[i] = mergeTwoLists(lists[i * 2], lists[i * 2 + 1]);
                    } else if (i * 2 < lists.length){
                        listNodes[i] = lists[i * 2];
                    }
                }
                lists = listNodes;
                listNodes = new ListNode[(lists.length + 1) / 2];
            }
        }
        return lists[0];
    }

    public ListNode mergeTwoLists(ListNode a, ListNode b) {
        if (a == null || b == null) {
            return a != null ? a : b;
        }
        ListNode head = new ListNode(0);
        ListNode tail = head, aPtr = a, bPtr = b;
        while (aPtr != null && bPtr != null) {
            if (aPtr.val < bPtr.val) {
                tail.next = aPtr;
                aPtr = aPtr.next;
            } else {
                tail.next = bPtr;
                bPtr = bPtr.next;
            }
            tail = tail.next;
        }
        tail.next = (aPtr != null ? aPtr : bPtr);
        return head.next;
    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1, new ListNode(4, new ListNode(5)));
        ListNode listNode2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        ListNode listNode3 = new ListNode(2, new ListNode(6));
        ListNode[] lists = new ListNode[]{listNode1, listNode2, listNode3};
        ListNode listNode = new MergeKLists().mergeKLists(lists);
        while (listNode != null) {
            System.out.println("listNode.val = " + listNode.val);
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
