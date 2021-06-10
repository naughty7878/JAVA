package com.test.linklist.circle;

/**
 * 约瑟夫问题介绍
 *      Josephu 问题为：
 *      设编号为 1，2，… n 的 n 个人围坐一圈，约定编号为 k（1<=k<=n）的人从 1 开始报数，
 *      数 到 m 的那个人出列，它的下一位又从 1 开始报数，数到 m 的那个人又出列，依次类推，
 *      直到所有人出列为止，由 此产生一个出队编号的序列。
 */
public class CircleLinkedDemo {
    public static void main(String[] args) {
        // 测试
        CircleLinkedList circleLinkedList = new CircleLinkedList();
        circleLinkedList.addBoy(5);
        circleLinkedList.showBoy();

        System.out.println("===============");

        circleLinkedList.out(1,2,5);
    }
}
