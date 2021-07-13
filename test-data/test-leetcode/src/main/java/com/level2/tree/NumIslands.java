package com.level2.tree;

/**
 * 岛屿数量
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * <p>
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * <p>
 * 此外，你可以假设该网格的四条边均被水包围。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：grid = [
 * ["1","1","1","1","0"],
 * ["1","1","0","1","0"],
 * ["1","1","0","0","0"],
 * ["0","0","0","0","0"]
 * ]
 * 输出：1
 * 示例 2：
 * <p>
 * 输入：grid = [
 * ["1","1","0","0","0"],
 * ["1","1","0","0","0"],
 * ["0","0","1","0","0"],
 * ["0","0","0","1","1"]
 * ]
 * 输出：3
 *  
 * <p>
 * 提示：
 * <p>
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] 的值为 '0' 或 '1'
 * 相关标签
 * 深度优先搜索
 * 广度优先搜索
 * 并查集
 * 数组
 * 矩阵
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-medium/xvtsnm/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class NumIslands {

    public int numIslands(char[][] grid) {
        int count = 0;
        int wide = grid.length;
        int heigh = grid[0].length;
        for (int i = 0; i < wide; i++) {
            for (int j = 0; j < heigh; j++) {
                if (grid[i][j] == '0') {
                    // 水
                    continue;
                } else if (grid[i][j] == '1') {
                    // 陆地
                    count++;
                    // 将它的上下左右陆地都变2
                    changGrid(grid, wide, heigh, i, j);
                }
            }
        }
        return count;
    }

    private void changGrid(char[][] grid, int wide, int heigh, int i, int j) {
        if (i >= 0 && j >= 0 && i < wide && j < heigh
            && grid[i][j] == '1') {
            grid[i][j] = '2';
            changGrid(grid, wide, heigh, i-1, j);
            changGrid(grid, wide, heigh, i+1, j);
            changGrid(grid, wide, heigh, i, j-1);
            changGrid(grid, wide, heigh, i, j+1);
        }
    }



    public static void main(String[] args) {
        char[][] grid = new char[][]{
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        int numIslands = new NumIslands().numIslands(grid);
        System.out.println("numIslands = " + numIslands);
    }
}
