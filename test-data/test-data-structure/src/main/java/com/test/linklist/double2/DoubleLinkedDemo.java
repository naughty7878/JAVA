package com.test.linklist.double2;

/**
 * @description: 使用双向链表完成对水浒英雄榜的增删改查
 * @author: hyr
 * @time: 2020/1/19 18:25
 */
public class DoubleLinkedDemo {
    public static void main(String[] args) {
        // 先创建节点
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");

        // 创建一个双向链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();

//        // 1、按照no順序向双向链表中增添数据
//        doubleLinkedList.add1(hero1);
//        doubleLinkedList.add1(hero2);
//        doubleLinkedList.add1(hero3);
//        doubleLinkedList.add1(hero4);
//        // 查看一下双向链表中的数据
//        System.out.println("增添完数据后的双向链表为：");
//        doubleLinkedList.list();
//        System.out.println();

        // 2、按照no順序向双向链表中增添数据
        doubleLinkedList.add1(hero1);
        doubleLinkedList.add1(hero3);
        doubleLinkedList.add1(hero2);
        doubleLinkedList.add1(hero4);
        // 查看一下双向链表中的数据
        System.out.println("增添完数据后的双向链表为：");
        doubleLinkedList.list();
        System.out.println();

        // 3、修改
        HeroNode2 newHeroNode = new HeroNode2(4,"鲁智深", "花和尚");
        doubleLinkedList.update(newHeroNode);
        System.out.println("修改后的双向链表为：");
        doubleLinkedList.list();
        System.out.println();

        // 4、删除
        doubleLinkedList.del(3);
        System.out.println("删除后的链表情况为：");
        doubleLinkedList.list();
    }
}