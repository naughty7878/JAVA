package com.level2.other;

import java.util.HashMap;
import java.util.Map;

/**
 * LFU 缓存
 * 请你为 最不经常使用（LFU）缓存算法设计并实现数据结构。
 *
 * 实现 LFUCache 类：
 *
 * LFUCache(int capacity) - 用数据结构的容量 capacity 初始化对象
 * int get(int key) - 如果键存在于缓存中，则获取键的值，否则返回 -1。
 * void put(int key, int value) - 如果键已存在，则变更其值；如果键不存在，请插入键值对。当缓存达到其容量时，则应该在插入新项之前，使最不经常使用的项无效。在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，应该去除 最近最久未使用 的键。
 * 注意「项的使用次数」就是自插入该项以来对其调用 get 和 put 函数的次数之和。使用次数会在对应项被移除后置为 0 。
 *
 * 为了确定最不常使用的键，可以为缓存中的每个键维护一个 使用计数器 。使用计数最小的键是最久未使用的键。
 *
 * 当一个键首次插入到缓存中时，它的使用计数器被设置为 1 (由于 put 操作)。对缓存中的键执行 get 或 put 操作，使用计数器的值将会递增。
 *
 *  
 *
 * 示例：
 *
 * 输入：
 * ["LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4], [1], [3], [4]]
 * 输出：
 * [null, null, null, 1, null, -1, 3, null, -1, 3, 4]
 *
 * 解释：
 * // cnt(x) = 键 x 的使用计数
 * // cache=[] 将显示最后一次使用的顺序（最左边的元素是最近的）
 * LFUCache lFUCache = new LFUCache(2);
 * lFUCache.put(1, 1);   // cache=[1,_], cnt(1)=1
 * lFUCache.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
 * lFUCache.get(1);      // 返回 1
 *                       // cache=[1,2], cnt(2)=1, cnt(1)=2
 * lFUCache.put(3, 3);   // 去除键 2 ，因为 cnt(2)=1 ，使用计数最小
 *                       // cache=[3,1], cnt(3)=1, cnt(1)=2
 * lFUCache.get(2);      // 返回 -1（未找到）
 * lFUCache.get(3);      // 返回 3
 *                       // cache=[3,1], cnt(3)=2, cnt(1)=2
 * lFUCache.put(4, 4);   // 去除键 1 ，1 和 3 的 cnt 相同，但 1 最久未使用
 *                       // cache=[4,3], cnt(4)=1, cnt(3)=2
 * lFUCache.get(1);      // 返回 -1（未找到）
 * lFUCache.get(3);      // 返回 3
 *                       // cache=[3,4], cnt(4)=1, cnt(3)=3
 * lFUCache.get(4);      // 返回 4
 *                       // cache=[3,4], cnt(4)=2, cnt(3)=3
 *  
 *
 * 提示：
 *
 * 0 <= capacity, key, value <= 104
 * 最多调用 105 次 get 和 put 方法
 *  
 *
 * 进阶：你可以为这两种操作设计时间复杂度为 O(1) 的实现吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lfu-cache
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LFUCache {

    int capacity;
    int size;
    int minfreq = 1;

    Map<Integer, DLinkedNode> dict;
    DLinkedNode head;
    DLinkedNode tail;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        dict = new HashMap<>();
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        DLinkedNode node = dict.get(key);
        if (node != null) {
            node.count++;
            // 判断是否往后移
            checkMove(node);

            // 清空计数器
            clear();
            return node.val;
        }
        return -1;
    }

    private void clear() {
        DLinkedNode node = head;
        while (node != null) {
            node.count = 0;
            node = node.next;
        }
    }

    private void checkMove(DLinkedNode node) {

        DLinkedNode next = node.next;
        if(next != null && next.count <= node.count && next != tail) {
            // 交换
            node.pre.next = next;
            if (next.next != null) {
                next.next.pre = node;
            }

            node.next = next.next;
            next.pre = node.pre;
            node.pre = next;
            next.next = node;

            checkMove(node);
        }
        return;
    }


    public void put(int key, int value) {
        DLinkedNode node = dict.get(key);
        if (node != null) {
            node.val = value;
            node.count++;
        }else {
            node = new DLinkedNode(key, value);
            dict.put(key, node);
            size++;
            node.count = minfreq;
            // 添加到对头
            head.next.pre = node;
            node.next = head.next;
            head.next = node;
            node.pre = head;
        }

        checkMove(node);

        // 移除多余的
        if(size > capacity) {
            DLinkedNode remove = head.next;
            removeNode(remove);

            dict.remove(remove.key);
        }
    }



    private void removeNode(DLinkedNode node) {
        DLinkedNode pre = node.pre;
        DLinkedNode next = node.next;
        pre.next = next;
        next.pre = pre;
    }

    public static void main(String[] args) {
        LFUCache lFUCache = new LFUCache(2);
        lFUCache.put(1, 1);   // cache=[1,_], cnt(1)=1
        lFUCache.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
        System.out.println(lFUCache.get(1));       // 返回 1
        // cache=[1,2], cnt(2)=1, cnt(1)=2
        lFUCache.put(3, 3);   // 去除键 2 ，因为 cnt(2)=1 ，使用计数最小
        // cache=[3,1], cnt(3)=1, cnt(1)=2
        System.out.println(lFUCache.get(2));      // 返回 -1（未找到）
        System.out.println(lFUCache.get(3));      // 返回 3
        // cache=[3,1], cnt(3)=2, cnt(1)=2
        lFUCache.put(4, 4);   // 去除键 1 ，1 和 3 的 cnt 相同，但 1 最久未使用
        // cache=[4,3], cnt(4)=1, cnt(3)=2
        System.out.println(lFUCache.get(1));      // 返回 -1（未找到）
        System.out.println(lFUCache.get(3));      // 返回 3
        // cache=[3,4], cnt(4)=1, cnt(3)=3
        System.out.println(lFUCache.get(4));      // 返回 4
        // cache=[3,4], cnt(4)=2, cnt(3)=3

    }

    static class DLinkedNode {
        int key;
        int val;
        int count;
        DLinkedNode pre;
        DLinkedNode next;

        DLinkedNode() {
        }

        DLinkedNode(int key, int val) {
            this.key = key;
            this.val = val;
        }

        DLinkedNode(int val, DLinkedNode left, DLinkedNode right) {
            this.val = val;
            this.pre = left;
            this.next = right;
        }
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */