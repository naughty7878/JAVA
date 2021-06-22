package com.level2.other;

import java.util.HashMap;
import java.util.LinkedList;
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
 * 方法二：HashMap + LinkedList;
 */
public class LRUCache2 {

    int capacity;

    Map<Integer, Integer> dict;
    LinkedList<Integer> list;

    public LRUCache2(int capacity) {
        this.capacity = capacity;
        dict = new HashMap<>();
        list = new LinkedList<>();
    }

    public int get(int key) {
        Integer value = dict.get(key);
        if (value != null) {
            int index = list.indexOf(key);
            if (index != -1) {
                list.remove(index);
            }
            list.addLast(key);
            return value;
        }
        return -1;
    }

    public void put(int key, int value) {
        int index = list.indexOf(key);
        if (index != -1) {
            list.remove(index);
        }
        list.add(key);
        dict.put(key, value);
        // 移除多余的
        if(list.size() > capacity) {
            Integer remove = list.removeFirst();
            dict.remove(remove);
        }
    }

    public static void main(String[] args) {
        LRUCache2 cache = new LRUCache2(2);
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

}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
