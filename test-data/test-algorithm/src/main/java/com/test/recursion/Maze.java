package com.test.recursion;

public class Maze {

    public static void main(String[] args) {

        // 创建一个迷宫 Maze
        int[][] map = new int[8][7];

        // 使用1表示墙
        // 上下左右都是1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        for (int j = 0; j < 7; j++) {
            map[0][j] = 1;
            map[7][j] = 1;
        }

        // 设置挡板
        map[3][1] = 1;
        map[3][2] = 1;
//        map[1][2] = 1;
        map[2][2] = 1;

        // 输出地图
        System.out.println("地图情况");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + "\t");
            }
            System.out.println();
        }


        setWay2(map, 1, 1);

        System.out.println("走过地图");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + "\t");
            }
            System.out.println();
        }
    }


    // 使用递归算法走出迷宫
    // 1、map表示地图
    // 2、i，j 表示从地图的那个位置触发（1，1）
    // 3、如果小球能到map[6][5] 位置，则找到出路
    // 4、约定1表示墙，2表示通路可以走，3表示已走过，但走不通
    // 5、策略：下-》右-》上-》左
    public static boolean setWay(int[][]map, int i, int j) {
        if (map[6][5] == 2) {
            return true;
        } else {
            if (map[i][j] == 0) {
                map[i][j] = 2;
                // 向下
                if(setWay(map, i + 1, j)) {
                    return true;
                }
                // 向右
                else if (setWay(map, i, j + 1)) {
                    return true;
                }
                // 向上
                else if (setWay(map, i - 1, j)) {
                    return true;
                }
                // 向左
                else if (setWay(map, i, j - 1)) {
                    return true;
                }
                // 走不通
                else {
                    map[i][j] = 3;
                    return false;
                }
            }
            // 可能是 1、2、3
            return false;
        }
    }

    // 策略：上-》右-》下-》左
    public static boolean setWay2(int[][]map, int i, int j) {
        if (map[6][5] == 2) {
            return true;
        } else {
            if (map[i][j] == 0) {
                map[i][j] = 2;
                // 向上
                if (setWay2(map, i - 1, j)) {
                    return true;
                }
                // 向右
                else if (setWay2(map, i, j + 1)) {
                    return true;
                }
                // 向下
                else if(setWay2(map, i + 1, j)) {
                    return true;
                }
                // 向左
                else if (setWay2(map, i, j - 1)) {
                    return true;
                }
                // 走不通
                else {
                    map[i][j] = 3;
                    return false;
                }
            }
            // 可能是 1、2、3
            return false;
        }
    }
}
