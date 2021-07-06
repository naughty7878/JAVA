package com.level2.other;

import java.util.HashMap;
import java.util.Map;

/**
 * LRU 缓存
 * LRU是Least Recently Used的缩写，即最近最少使用
 * <p>
 * 设计和构建一个“最近最少使用”缓存，该缓存会删除最近最少使用的项目。缓存应该从键映射到值(允许你插入和检索特定键对应的值)，并在初始化时指定最大容量。当缓存被填满时，它应该删除最近最少使用的项目。
 * <p>
 * 它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 * <p>
 * 获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。
 * <p>
 * 示例:
 * <p>
 * LRUCache cache = new LRUCache( 2 ); //缓存容量
 * <p>
 * cache.put(1, 1);
 * cache.put(2, 2);
 * cache.get(1);       // 返回  1
 * cache.put(3, 3);    // 该操作会使得密钥 2 作废
 * cache.get(2);       // 返回 -1 (未找到)
 * cache.put(4, 4);    // 该操作会使得密钥 1 作废
 * cache.get(1);       // 返回 -1 (未找到)
 * cache.get(3);       // 返回  3
 * cache.get(4);       // 返回  4
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lru-cache-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * 方法二：HashMap + 双向链表;
 */
public class LRUCache3 {

    int capacity;
    int size;

    Map<Integer, DLinkedNode> dict;
    DLinkedNode head;
    DLinkedNode tail;

    public LRUCache3(int capacity) {
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
            // 移除
            removeNode(node);
            // 移到队尾
            moveToTail(node);

            return node.val;
        }
        return -1;
    }



    public void put(int key, int value) {
        DLinkedNode node = dict.get(key);
        if (node != null) {
            node.val = value;
            // 移除
            removeNode(node);
        }else {
            node = new DLinkedNode(key, value);
            dict.put(key, node);
            size++;
        }
        // 移到队尾
        moveToTail(node);

        // 移除多余的
        if(size > capacity) {
            DLinkedNode remove = head.next;
            removeNode(remove);

            dict.remove(remove.key);
        }
    }

    private void moveToTail(DLinkedNode node) {
        tail.pre.next = node;
        node.pre = tail.pre;
        node.next = tail;
        tail.pre = node;
    }

    private void removeNode(DLinkedNode node) {
        DLinkedNode pre = node.pre;
        DLinkedNode next = node.next;
        pre.next = next;
        next.pre = pre;
    }

    public static void main(String[] args) {
        LRUCache3 cache = new LRUCache3(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));// 返回  1
        cache.put(3, 3);    // 该操作会使得密钥 2 作废
        System.out.println(cache.get(2));// 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得密钥 1 作废
        System.out.println(cache.get(1));// 返回 -1 (未找到)
        System.out.println(cache.get(3));// 返回 3
        System.out.println(cache.get(4));// 返回 4
    }

    static class DLinkedNode {
        int key;
        int val;
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
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
