package com.test.linklist.single;

/*
    使用带 head 头的单向链表实现 -- 水浒英雄排行榜管理, 完成对英雄人物的增删改查操作。
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        // 进行测试
        // 先创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        // 创建链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();

//        // 一、不按顺序添加节点，进行测试
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero4);
//        singleLinkedList.add(hero3);
//        //查看一下链表的信息
//        singleLinkedList.list();

        // 二、按照no顺序添加节点，进行测试
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero3);
        // 查看一下链表信息
        System.out.println("按序插入后的链表信息为：");
        singleLinkedList.list();
        System.out.println();

        // 三、修改节点的信息
        singleLinkedList.update(new HeroNode(1,"宋宋","小宋"));
        // 修改后的链表信息
        System.out.println("修改后的链表信息为：");
        singleLinkedList.list();
        System.out.println();

        // 四、删除节点
        singleLinkedList.del(1);
        // 删除节点后的链表信息
        System.out.println("删除节点后的链表信息为：");
        singleLinkedList.list();
        System.out.println();

        // 五、显示链表
        // 当前链表信息
        System.out.println("当前链表信息为：");
        singleLinkedList.list();
        System.out.println();

        // 六、反转链表
        System.out.println("反转后的链表信息为：");
        singleLinkedList.reverseList();
        singleLinkedList.list();
        System.out.println();

        // 七、求链表中有效节点的个数
        System.out.println("链表中有效节点的个数为：");
        System.out.println(singleLinkedList.getLength());
        System.out.println();

        // 八、逆序打印链表
        System.out.println("逆序打印的链表信息为：");
        singleLinkedList.reversePrint();
        System.out.println();

        // 九、查找链表中的倒数第k个结点
        System.out.println("链表中的倒数第一个节点信息为：");
        System.out.println(singleLinkedList.findLastIndexNode(1));
    }
}
