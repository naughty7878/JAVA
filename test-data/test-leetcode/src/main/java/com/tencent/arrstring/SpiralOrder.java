package com.tencent.arrstring;

/**
 * @author h__d
 * @description TODO
 * @date 2021/10/15
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 螺旋矩阵
 * 给你一个 m 行 n 列的矩阵matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 *
 * 
 *
 * 示例 1：
 *
 *
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 * 示例 2：
 *
 *
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 * 
 *
 * 提示：
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 10
 * -100 <= matrix[i][j] <= 100
 * 相关标签
 * 数组
 * 矩阵
 * 模拟
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/tencent/x5elr5/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class SpiralOrder {
    public List<Integer> spiralOrder(int[][] matrix) {
        ArrayList<Integer> list = new ArrayList<>();
        // 边界
        int up = -1, down = matrix.length, left = -1, right = matrix[0].length;
        int i = 0, j = 0;
        char direction = 'r';
        while (up < i && i < down && left < j && j < right) {
            list.add(matrix[i][j]);
            switch (direction) {
                case 'r' :
                    if (j + 1 != right) {
                        j ++;
                    }else {
                        direction = 'd';
                        up ++;
                        i ++;
                    }
                    break;
                case 'd' :
                    if (i + 1 != down) {
                        i ++;
                    }else {
                        direction = 'l';
                        right --;
                        j --;
                    }
                    break;
                case 'l' :
                    if (j - 1 != left) {
                        j --;
                    }else {
                        direction = 'u';
                        down --;
                        i --;
                    }
                    break;
                case 'u' :
                    if (i - 1 != up) {
                        i --;
                    }else {
                        direction = 'r';
                        left ++;
                        j ++;
                    }
                    break;
            }
        }
        return list;
    }

    public static void main(String[] args) {
//        int[][] matrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
//        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        int[][] matrix = {
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12},
                {13,14,15,16}};
        List<Integer> spiralOrder = new SpiralOrder().spiralOrder(matrix);
        System.out.println("spiralOrder = " + spiralOrder);
    }
}
