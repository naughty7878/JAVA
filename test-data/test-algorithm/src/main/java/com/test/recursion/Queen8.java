package com.test.recursion;

public class Queen8 {

    // 定义一个max表示多少个皇后
    int max = 8;
    // 定义数组array，保存皇后位置
    int[] array = new int[max];
    
    int total = 0;

    public static void main(String[] args) {
        Queen8 queen8 = new Queen8();
        queen8.check(0);
        System.out.println("queen8.total = " + queen8.total);
    }

    private void check(int n) {
        if (n == max) {
            print();
            return;
        }

        for (int i = 0; i < max; i++) {
            array[n] = i;
            if (judge(n)) {
                check(n + 1);
            }
        }
    }

    private boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            if (array[i] == array[n] ||
                    Math.abs(n - i) == Math.abs(array[n] - array[i]))
            {
                return false;
            }
        }
        return true;
    }

    private void print(){
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "\t");
        }
        System.out.println();
        System.out.println("------------------------------------");
        total++;
    }
}
