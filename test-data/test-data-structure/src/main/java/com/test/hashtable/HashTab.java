package com.test.hashtable;

public class HashTab {

    Node[] table;
    // 大小
    int capcity;

    public HashTab(int capcity) {
        this.table = new Node[capcity];
    }

    public String get(Integer key) {
        int index = key % table.length;
        Node node = table[index];
        while (node != null) {
            if (node.key == key) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }

    public void put(Integer key, String value){
        int index = key % table.length;
        Node node = table[index];
        if (node == null) {
            table[index] = new Node(key, value);
        }else {
            boolean existFlag = false;
            while (true) {
                if (node.key == key) {
                    node.value = value;
                    existFlag = true;
                    break;
                }
                if (node.next != null) {
                    node = node.next;
                } else {
                    break;
                }
            }
            if (!existFlag) {
                node.next = new Node(key, value);
            }
        }
    }

    public void list() {
        for (int i = 0; i < table.length; i++) {
            Node node = table[i];
            while (node != null) {
                System.out.println(node.value);
                node = node.next;
            }
        }
    }

    public static void main(String[] args) {
        HashTab hashTab = new HashTab(8);
        hashTab.put(1, "aaa");
        hashTab.put(2, "bbb");
        hashTab.put(3, "ccc");
        hashTab.put(4, "ddd");

        hashTab.put(9, "eee");
        hashTab.put(10, "fff");
        hashTab.list();
        System.out.println(hashTab.get(9));
    }
}


class Node {
    public int key;
    public String value;
    public Node next;

    public Node(int key, String value) {
        this.key = key;
        this.value = value;
    }
}
