package com.test.stack;

public class Calculator {
    public static void main(String[] args) {
        String expression = "7+2*6-2"; //目前方法字符串中不要有空格
        //创建两个栈，数栈和符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        //辅助变量
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = 0; //也可以用char
        int res = 0;
        char ch = ' '; //保存每次扫描得到的char
        //使用while循环扫描expression
        while(true) {
            //依次得到expression的每一个字符
            ch = expression.substring(index, index + 1).charAt(0);
            //判断ch
            if(operStack.isOper(ch)) { //如果是运算符
                if(!operStack.isEmpty()) {
                    //不为空
                    if(operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        //把运算结果入数栈
                        numStack.push(res);
                        //把当前的符号入符号栈
                        operStack.push(ch);
                    } else {
                        operStack.push(ch);
                    }
                } else {
                    //为空，直接进栈
                    operStack.push(ch);
                }
            } else { //如果是数，则直接入数栈
                numStack.push(ch - 48); //因此ch是字符型，查Ascii码看为啥要-48
            }
            //让index + 1，并判断是否到最后
            index++;
            if(index >= expression.length()) {
                break;
            }
        }
        //当表达式扫描完毕，就顺序的从数栈和符号栈中pop出相应的数和符号，并运算
        while(true) {
            //如果符号栈为空，则计算到最后的结果，数栈中只有一个数值
            if(operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            numStack.push(res);
        }
        int res2 =numStack.pop();
        System.out.printf("表达式:%s=%d",expression , res2);
    }
}

//先创建一个栈
class ArrayStack2{
    private int maxSize; //栈的大小
    private int[] stack; //数组，数据放入该数组中
    private int top = -1; //栈顶，初始化为-1，表示没有数据

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize]; //在构造器中初始化数组，不然无法存储数据
    }

    //判断栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //判断栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈 -push
    public void push(int value) {
        //先判断栈是否满
        if(isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈-pop,将栈顶的数据返回
    //根据数组类型，选择返回类型，不一定非是int
    public int pop() {
        //先判断是否为空
        if(isEmpty()) {
            //抛出异常（代表终止了，不需要return）
            //因为这个函数有返回值，如果使用return处理不好处理
            throw new RuntimeException("栈空，没有数据");
        }
        //首先取得栈顶的值
        int temp = stack[top]; //此处的类型根据数组类型选择，同返回类型一至
        top--;
        return temp;
    }

    //显示栈的情况【遍历栈】
    public void list() {
        if(isEmpty()) {
            System.out.println("栈空，没有数据");
            return;
        }
        //遍历时候要从栈顶开始
        for(int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i , stack[i]);
        }
    }

    //返回运算符的优先级，优先级是程序员来确定，优先级使用数字表示，数字越大，则优先级越高
    public int priority(int oper) {
        if(oper == '*' || oper == '/') { //char和int 可以直接比较
            return 1;
        } else if(oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1; //假设目前的表达式只有+-*/
        }
    }

    //判断是不是一个运算符
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //计算方法
    public int cal(int num1, int num2, int oper) {
        int res = 0; //存放计算的结果
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1; //把后弹出的数作为减数
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }

    //返回当前栈顶的值，不是真正出栈
    public int peek() {
        return stack[top];
    }
}