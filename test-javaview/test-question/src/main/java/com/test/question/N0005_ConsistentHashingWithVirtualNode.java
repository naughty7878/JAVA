package com.test.question;

import java.util.LinkedList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 带虚拟节点的一致性Hash算法
 *
 * @author 五月的仓颉 http://www.cnblogs.com/xrq730/
 */
public class N0005_ConsistentHashingWithVirtualNode {

    /**
     * 待添加入Hash环的服务器列表
     */
    private static String[] servers = {"192.168.0.0:111", "192.168.0.1:111", "192.168.0.2:111",
            "192.168.0.3:111", "192.168.0.4:111"};

    /**
     * 真实结点列表,考虑到服务器上线、下线的场景，即添加、删除的场景会比较频繁，这里使用LinkedList会更好
     */
    private static List<String> realNodes = new LinkedList<String>();

    /**
     * 虚拟节点，key表示虚拟节点的hash值，value表示虚拟节点的名称
     */
    private static SortedMap<Integer, String> virtualNodes =
            new TreeMap<Integer, String>();

    /**
     * 虚拟节点的数目，这里写死，为了演示需要，一个真实结点对应5个虚拟节点
     */
    private static final int VIRTUAL_NODES = 5;

    static {
        // 先把原始的服务器添加到真实结点列表中
        for (int i = 0; i < servers.length; i++) {
            realNodes.add(servers[i]);
        }

        SortedMap<Integer, String> sortMap = new TreeMap<Integer, String>();

        // 再添加虚拟节点，遍历LinkedList使用foreach循环效率会比较高
        for (String str : realNodes) {
            for (int i = 0; i < VIRTUAL_NODES; i++) {
                String virtualNodeName = str + "&&VN" + String.valueOf(i);
                // 返回的hash(范围是 Integer.MIN_VALUE ~ Integer.MAX_VALUE)
                int hash = getHash(virtualNodeName);
                virtualNodes.put(hash, virtualNodeName + " %" +hash / (Integer.MAX_VALUE / 100));
            }
        }

        // 遍历查看虚拟节点
        virtualNodes.forEach((k, v) -> {
            System.out.println("虚拟服务器：" + v + ",\t对应Hash值：" + k);
        });
        System.out.println();
    }

    /**
     * 使用FNV1_32_HASH算法计算服务器的Hash值,这里不使用重写hashCode的方法，最终效果没区别
     */
    private static int getHash(String str) {
        // FNV用于散列的质数
        final int p = 16777619;
        // 初始的哈希值
        int hash = (int) 2166136261L;
        for (int i = 0; i < str.length(); i++)
            hash = (hash ^ str.charAt(i)) * p;
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;

        return hash;
    }

    /**
     * 得到应当路由到的结点
     */
    private static String getServer(String node) {
        // 得到带路由的结点的Hash值
        int hash = getHash(node);
        // 得到大于该Hash值的所有Map
        SortedMap<Integer, String> subMap = virtualNodes.tailMap(hash);

        String virtualNode = "";
        if (subMap.size() > 0) {
            // 第一个Key就是顺时针过去离node最近的那个结点
            Integer i = subMap.firstKey();
            virtualNode = subMap.get(i);
        }else {
            // 比所有服务器的hash值大，则取最小hash值的服务器
            return virtualNodes.get(virtualNodes.firstKey());
        }
        // 返回对应的虚拟节点名称，这里字符串稍微截取一下
        return virtualNode.substring(0, virtualNode.indexOf("&&"));
    }

    public static void main(String[] args) {
        String[] nodes = {"127.0.0.1:1111", "221.226.0.1:1111", "10.211.0.1:3333"};
        for (int i = 0; i < nodes.length; i++) {
            System.out.println("服务器[" + nodes[i] + "]\t的hash值为" +
                    getHash(nodes[i]) + ", 被路由到结点[" + getServer(nodes[i]) + "]");
        }
//        System.out.println("Integer.MIN_VALUE = " + Integer.toBinaryString(Integer.MIN_VALUE));
//        System.out.println("Integer.MIN_VALUE = " + Integer.MIN_VALUE);
//        System.out.println("Integer.MAX_VALUE = " + Integer.toBinaryString(Integer.MAX_VALUE));
//        System.out.println("Integer.MIN_VALUE = " + Integer.toBinaryString(Math.abs(Integer.MIN_VALUE)));
//        System.out.println("Math.abs(Integer.MIN_VALUE) = " + Math.abs(Integer.MIN_VALUE + 1));
//        System.out.println("Integer.MAX_VALUE = " + Integer.MAX_VALUE);
//        System.out.println("Integer.MAX_VALUE = " + Integer.toBinaryString(Math.abs(Integer.MAX_VALUE)));
    }
}
