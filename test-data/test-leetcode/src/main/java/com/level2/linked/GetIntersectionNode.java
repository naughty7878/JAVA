package com.level2.linked;

import java.util.HashMap;
import java.util.Map;

/**
 * 相交链表
 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表没有交点，返回 null 。
 * <p>
 * 图示两个链表在节点 c1 开始相交：
 * <p>
 * <p>
 * <p>
 * 题目数据 保证 整个链式结构中不存在环。
 * <p>
 * 注意，函数返回结果后，链表必须 保持其原始结构 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
 * 输出：Intersected at '8'
 * 解释：相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。
 * 从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。
 * 在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
 * 输出：Intersected at '2'
 * 解释：相交节点的值为 2 （注意，如果两个链表相交则不能为 0）。
 * 从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。
 * 在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
 * 示例 3：
 * <p>
 * <p>
 * <p>
 * 输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
 * 输出：null
 * 解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。
 * 由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
 * 这两个链表不相交，因此返回 null 。
 *  
 * <p>
 * 提示：
 * <p>
 * listA 中节点数目为 m
 * listB 中节点数目为 n
 * 0 <= m, n <= 3 * 104
 * 1 <= Node.val <= 105
 * 0 <= skipA <= m
 * 0 <= skipB <= n
 * 如果 listA 和 listB 没有交点，intersectVal 为 0
 * 如果 listA 和 listB 有交点，intersectVal == listA[skipA + 1] == listB[skipB + 1]
 *  
 * <p>
 * 进阶：你能否设计一个时间复杂度 O(n) 、仅用 O(1) 内存的解决方案？
 * <p>
 * 相关标签
 * 链表
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-medium/xv02ut/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class GetIntersectionNode {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        ListNode temp1 = headA;
        ListNode temp2 = headB;
        while (temp1 != temp2) {
            temp1 = temp1 == null ? headB : temp1.next;
            temp2 = temp2 == null ? headA : temp2.next;
        }
        return temp1;
    }


    public static void main(String[] args) {
        // listA = [4,1,8,4,5], listB = [5,0,1,8,4,5]
        ListNode headA = new ListNode(4);
        ListNode node2 = new ListNode(1);
        ListNode node3 = new ListNode(8);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        headA.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        ListNode headB = new ListNode(5);
        ListNode node22 = new ListNode(0);
        ListNode node33 = new ListNode(1);
        headB.next = node22;
        node22.next = node33;
        node33.next = node3;

        ListNode node = new GetIntersectionNode().getIntersectionNode(headA, headB);
        System.out.println("node = " + node);
    }


    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {

        ListNode temp = headA;
        Map<ListNode, ListNode> map = new HashMap<>();
        while (temp != null) {
            map.put(temp, temp);
            temp = temp.next;
        }
        temp = headB;
        while (temp != null) {
            ListNode node = map.get(temp);
            if (node != null) {
                break;
            }
            temp = temp.next;
        }
        return temp;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) {
 * val = x;
 * next = null;
 * }
 * }
 */
