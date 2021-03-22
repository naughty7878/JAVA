package com.test.linklist.double2;

/**
 * @description: 创建一个双向链表的类
 * @author: hyr
 * @time: 2020/1/19 9:48
 */
public class DoubleLinkedList {
    // 先初始化一个头节点，头节点不要动，不存放具体的数据
    private HeroNode2 head = new HeroNode2(0, "", "");

    // 返回头节点
    public HeroNode2 getHead() {
        return head;
    }

    // 显示链表
    public void list() {
        // 判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 因为头节点，不能动，因此我们需要一个辅助变量来遍历
        HeroNode2 temp = head.next;
        while (true) {
            // 判断是否到链表最后
            if (temp == null) {
                break;
            }
            // 输出节点的信息
            System.out.println(temp);

            // 将temp后移
            temp = temp.next;
        }
    }

    // 添加一个节点到双向链表的最后
    public void add(HeroNode2 heroNode) {
        // 因为头节点不能动，因此我们需要一个辅助变量temp
        HeroNode2 temp = head;
        // 遍历链表，找到最后
        while (true) {
            // 找到链表的最后
            if (temp.next == null) {
                break;
            }
            // 如果没有找到，将temp后移
            temp = temp.next;
        }
        // 当退出循环时，temp就指向了链表的最后
        // 形成一个双向链表
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    // 按照顺序添加
    public void add1(HeroNode2 heroNode) {
        // 因为头节点不能动，因此我们仍然通过一个辅助指针(变量)来帮助找到添加的位置
        // 因为单链表，因为我们找的 temp 是位于添加位置的前一个节点，否则插入不了
        HeroNode2 temp = head;
        // 标志添加的编号是否存在，默认为false
        boolean flag = false;
        while (true) {
            // 说明 temp 已经在链表的最后
            if (temp.next == null) {
                break;
            }
            // 位置找到，就在 temp 的后面插入
            if (temp.next.no > heroNode.no) {
                break;
            }
            // 说明希望添加的 heroNode 的编号已然存在
            else if (temp.next.no == heroNode.no) {
                //说明编号存在
                flag = true;
                break;
            }
            // 后移，遍历当前链表
            temp = temp.next;
        }
        // 判断 flag 的值
        // 不能添加，说明编号存在
        if (flag) {
            System.out.printf("准备插入的英雄的编号%d已经存在了，不能加入\n", heroNode.no);
        } else {
            // 插入到链表中，temp的后面
            // 这里需要判断一下是不是最后一个节点，需要不同的操作。
            if (temp.next != null) {
                heroNode.next = temp.next;
                heroNode.next.pre = heroNode;
            }
            temp.next = heroNode;
            heroNode.pre = temp;
        }
    }

    // 修改一个节点的内容，可以看到双向链表的节点内容修改和单向链表一样
    // 只是节点类型修改为HeroNode2
    public void update(HeroNode2 newHeroNode) {
        // 判断是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 找到需要修改的节点，根据no编号
        // 定义一个辅助变量
        HeroNode2 temp = head.next;
        boolean flag = false; // 表示是否找到该节点
        while (true) {
            if (temp == null) {
                break; // 已经遍历完链表
            }
            if (temp.no == newHeroNode.no) {
                // 找到
                flag = true;
                break;
            }
            temp = temp.next;
        }
        // 根据flag判断是否找到要修改的节点
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        } else {
            System.out.printf("没有找到编号为%d的节点，不能修改\n", newHeroNode.no);
        }
    }

    // 从双向链表中删除一个节点
    // 说明
    // 1、对于双向链表，我们可以直接找到要删除的这个节点
    // 2、找到后，自我删除即可
    public void del(int no) {
        // 判断当前链表是否为空
        if (head.next == null) {
            System.out.println("链表为空，无法删除");
            return;
        }
        HeroNode2 temp = head.next; // 辅助变量
        boolean flag = false; // 标志是否找到待删除节点
        while (true) {
            if (temp == null) {
                // 已经到了链表的最后
                break;
            }
            if (temp.no == no) {
                // 找到的待删除节点的前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next; // temp后移，遍历
        }
        // 判断flag
        if (flag) { // 找到
            // 可以删除
            temp.pre.next = temp.next;
            // 如果是最后一个节点，就不需要执行下面这句话，否则出现空指针
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.printf("要删除的%d节点不存在\n", no);
        }
    }
}
