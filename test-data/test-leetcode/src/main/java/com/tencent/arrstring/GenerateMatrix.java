package com.tencent.arrstring;

import java.util.Arrays;

/**
 * 螺旋矩阵 II
 * 给你一个正整数n ，生成一个包含 1 到n2所有元素，且元素按顺时针顺序螺旋排列的n x n 正方形矩阵 matrix 。
 *
 * 
 *
 * 示例 1：
 *
 *
 * 输入：n = 3
 * 输出：[[1,2,3],[8,9,4],[7,6,5]]
 * 示例 2：
 *
 * 输入：n = 1
 * 输出：[[1]]
 * 
 *
 * 提示：
 *
 * 1 <= n <= 20
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/tencent/x5rn92/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class GenerateMatrix {

    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int minI = 0, maxI = n - 1;
        int minJ = 0, maxJ = n - 1;
        int i = 0, j = 0, start = 0;
        char direction = 'r';
        while (0 <= i && i < n && 0 <= j && j < n && matrix[i][j] == 0) {
            matrix[i][j] = ++start;
            switch (direction) {
                case 'r':
                    if (j + 1 <= maxJ) {
                        j++;
                    }else {
                        direction = 'd';
                        minI++;
                        i++;
                    }
                    break;
                case 'd':
                    if (i + 1 <= maxI) {
                        i++;
                    }else {
                        direction = 'l';
                        maxJ--;
                        j--;
                    }
                    break;
                case 'l':
                    if (j - 1 >= minJ) {
                        j--;
                    }else {
                        direction = 'u';
                        maxI--;
                        i--;
                    }
                    break;
                case 'u':
                    if (i - 1 >= minI) {
                        i--;
                    }else {
                        direction = 'r';
                        minJ++;
                        j++;
                    }
                    break;
            }
        }
        return matrix;
    }

    public static void main(String[] args) {
        int[][] matrix = new GenerateMatrix().generateMatrix(1);
        for (int i = 0; i < matrix.length; i++) {
            System.out.println("Arrays.toString(matrix[i]) = " + Arrays.toString(matrix[i]));
        }

    }
}
