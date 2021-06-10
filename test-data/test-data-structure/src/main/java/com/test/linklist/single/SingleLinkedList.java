package com.test.linklist.single;

import java.util.Stack;

// 定义 SingleLinkedList 管理我们的英雄
public class SingleLinkedList {
    // 现初始化一个头节点，头节点不要动，不存放具体的数据
    private HeroNode head = new HeroNode(0,"","");

    // 一、添加节点到单向链表
    // 思路，当不考虑编号顺序时
    // 1、找到当前链表的最后节点
    // 2、将最后这个节点的 next 指向新的节点
    public void add(HeroNode heroNode){
        // 因为 head 节点不能动，因此我们需要一个辅助遍历 temp
        HeroNode temp = head;
        // 遍历链表，找到最后
        while (true){
            // 找到链表的最后
            if (temp.next == null){
                break;
            }
            // 如果没有找到最后，将temp后移
            temp = temp.next;
        }
        // 当退出while循环时，temp就指向了链表的最后
        // 将最后这个节点的next指向新的节点
        temp.next = heroNode;
    }

    // 二、按照no从小到大的顺序添加节点到单向链表
    // 第二种方式在添加英雄时，根据排名将英雄插入到指定位置
    // 如果有这个排名，则添加失败，并给出提示
    public void addByOrder(HeroNode heroNode){
        // 因为头节点不能动，因此我们仍然通过一个辅助指针(变量)来帮助找到添加的位置
        // 因为单链表，因为我们找的 temp 是位于添加位置的前一个节点，否则插入不了
        HeroNode temp = head;
        // 标志添加的编号是否存在，默认为false
        boolean flag = false;
        while (true){
            // 说明 temp 已经在链表的最后
            if (temp.next == null){
                break;
            }
            // 位置找到，就在 temp 的后面插入
            if (temp.next.no > heroNode.no) {
                break;
            }
            // 说明希望添加的 heroNode 的编号已然存在
            else if (temp.next.no == heroNode.no){
                //说明编号存在
                flag = true;
                break;
            }
            // 后移，遍历当前链表
            temp = temp.next;
        }
        // 判断 flag 的值
        // 不能添加，说明编号存在
        if (flag){
            System.out.printf("准备插入的英雄的编号 %d 已经存在了，不能加入\n", heroNode.no);
        }
        else {
            // 插入到链表中，temp的后面
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    // 三、修改节点的信息，根据no编号来修改，即no编号不能改
    // 说明
    // 1、根据 newHeroNode 的 no 来修改即可
    public void update(HeroNode newHeroNode){
        // 判断是否空
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        // 找到需要修改的节点，根据 no 编号
        // 定义一个辅助变量
        HeroNode temp = head.next;
        // 表示是否找到该节点
        boolean flag = false;
        while(true){
            // 已遍历完链表
            if (temp == null){
                break;
            }
            if (temp.no == newHeroNode.no){
                // 找到
                flag = true;
                break;
            }
            temp = temp.next;
        }
        // 根据 flag 判断是否找到要修改的节点
        if(flag){
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        }
        // 没有找到
        else {
            System.out.printf("没有找到编号%d的节点，不能修改\n", newHeroNode.no);

        }
    }

    // 四、删除节点
    // 思路
    // 1、head 不能动，因此我们需要一个 temp 辅助节点找到待删除节点的前一个节点
    // 2、说明我们在比较的时，是 temp.next.no 和需要删除的节点的 no 比较
    public void del(int no){
        HeroNode temp = head;
        boolean flag = false; //标志是否找到待删除节点的no
        while (true){
            // 已经到链表的最后
            if (temp.next == null) {
                break;
            }
            // 找到的待删除节点的前一个节点temp
            if (temp.next.no == no){
                // 找到的待删除节点的前一个节点 temp
                flag = true;
                break;
            }
            // temp后移，遍历
            temp = temp.next;
        }
        // 判断 flag
        if(flag){ // 找到
            // 可以删除
            temp.next = temp.next.next;
        }
        else {
            System.out.printf("要删除的%d节点不存在\n",no);
        }
    }

    // 五、显示链表
    public void list(){
        // 判断链表是否为空
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        // 因为头节点，不能动，因此我们需要一个辅助变量来遍历
        HeroNode temp = head.next;
        while (true){
            // 判断是否到链表最后
            if (temp == null){
                break;
            }
            // 输出节点的信息
            System.out.println(temp);
            // 将 temp 后移
            temp = temp.next;
        }
    }

    // 六、反转链表
    public  void  reverseList(){
        // 如果当前链表为空，或者只有一个节点，无需反转，直接返回
        if (head.next == null || head.next.next == null){
            return;
        }

        // 定义一个辅助的指针(变量)，帮助我们遍历原来的链表
        HeroNode cur = head.next;
        // 指向当前节点[cur]的下一个节点
        HeroNode next = null;
        HeroNode reverseHead = new HeroNode(0, "", "");
        // 遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表 reverseHead 的最前端
        while (cur != null){
            next = cur.next; // 先暂时保存当前节点的下一个节点，后面需要使用
            cur.next = reverseHead.next; // 将cur的下一个节点指向新的链表
            reverseHead.next = cur; // 将cur连接到新的链表上
            cur = next; // 让cur后移
        }
        // 将 head.next 指向 reverseHead.next，实现单链表的反转
        head.next = reverseHead.next;
    }

    // 七、求单链表中的有效节点的个数
    public  int  getLength(){
        /**
         * @description:
         * @return: 返回的就是有效节点的个数
         * @param: head 链表的头节点
         * @author: hyr
         * @time: 2020/1/13 20:11
         */
        if(head.next == null){ // 空链表
            return 0;
        }
        int length = 0;
        // 定义一个辅助的变量，这里我们没有统计头节点
        HeroNode cur = head.next;
        while (cur != null){
            length++;
            cur = cur.next;
        }
        return length;
    }

    // 八、逆序打印链表
    public void reversePrint() {
        /**
         * @description: 使用栈这个数据结构，将各个节点压入栈中，再弹出就可以实现逆序打印的效果
         * @return:
         * @param: head 单链表的头节点
         * @author: hyr
         * @time: 2020/1/13 21:21
         */
        if (head.next == null) {
            return; // 空链表，不能打印
        }
        // 创建要给一个栈，将各个节点压入栈
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
        // 将链表中的所有节点压入栈
        while (cur != null) {
            stack.push(cur);
            cur = cur.next; //cur后移，这一就可以压入下一个节点
        }
        // 将栈中的节点进行打印，pop出栈
        while (stack.size() > 0) {
            System.out.println(stack.pop()); // 栈的特点是先进后出
        }
    }

    // 九、查找单链表中的倒数第k个结点
    public HeroNode findLastIndexNode(int index){
        // 思路
        // 1、编写一个方法，接受 head 节点，同时接收一个index
        // 2、index表示是倒数第 index 个节点
        // 3、先把链表从头到尾遍历，得到链表的总的长度 getLength
        // 4、得到 size 后，我们从链表的第一个开始遍历(size-index)个，就可以得到
        // 5、如果找到了，则返回该节点，否则返回null

        // 判断如果链表为空，返回null
        if (head.next == null){
            System.out.println("没有找到");
        }

        // 第一个遍历得到链表的长度(节点个数)
        int size = getLength();

        // 第二次遍历 size-index 位置，就是我们倒数的第K个节点
        // 先做一个 index 的校验
        if(index <= 0 || index > size){
            System.out.println("要查找的位置不存在");
        }

        // 定义给辅助变量，for循环定位到倒数的index
        HeroNode cur = head.next;
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }

        return cur;
    }
}
