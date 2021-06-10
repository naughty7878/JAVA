package com.test.linklist.circle;

/**
 * 创建一个环形的单向链表
 */
public class CircleLinkedList {
    // 创建一个first节点，当前没有编号
    private Boy first = null;
    // 添加小孩节点，构成一个环形的链表
    public void addBoy(int nums) {
        // nums 做一个数据校验
        if(nums < 1) {
            System.out.println("nums 的值不正确");
            return;
        }

        Boy curBoy = null; // 辅助指针，帮助构建环形链表
        // 使用for来创建我们的环形链表
        for(int i = 1; i <= nums; i++) {
            // 根据编号，创建小孩节点
            Boy boy = new Boy(i);
            // 如果是第一个小孩
            if(i == 1) {
                first = boy;
                first.setNext(boy); // 够成环
                curBoy = boy; // 让curBoy指向第一个小孩
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    // 遍历当前的环形链表
    public void showBoy(){
        // 判断链表是否为空
        if (first == null) {
            System.out.println("没有任何小孩～");
            return;
        }
        // 因为first不能动，因此我们仍然使用一个辅助指针完成遍历
        Boy curBoy = first;
        while (true) {
            System.out.printf("小孩的编号%d \n", curBoy.getNo());
            if(curBoy.getNext() == first) {
                break;
            }
            curBoy = curBoy.getNext(); // curBoy后移
        }
    }

    /**
     * 根据用户的输入，计算出小孩出圈的顺序
     * @param startNo 表示从第几个小孩开始数数
     * @param countNum 表示数几下
     * @param nums 表示最初优几个小孩在圈中
     */
    public void out(int startNo, int countNum, int nums){

        // 先对数据进行校验
        if (first == null || startNo < 1 || startNo > nums)
            System.out.println("参数输入有误，请重新输入～～");
        // 移动first
        for (int i = 1; i < startNo; i ++) {
            first = first.getNext();
        }

        Boy helper = first;
        // helper 移动到最后
        while (true) {
            if(helper.getNext() == first) {
                break;
            }
            helper = helper.getNext(); // helper后移
        }
        // 循环出人
        while (true) {
            for (int i = 1; i < countNum; i ++) {
                helper = helper.getNext();
            }
            // 出人
            System.out.printf("小孩的编号%d出圈\n", helper.getNext().getNo());
            helper.setNext(helper.getNext().getNext());

            if(helper.getNext() == helper) {
                System.out.printf("小孩的编号%d出圈\n", helper.getNext().getNo());
                first = null;
                break;
            }
        }
    }
}
