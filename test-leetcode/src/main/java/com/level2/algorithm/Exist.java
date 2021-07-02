package com.level2.algorithm;

import java.util.HashSet;

/**
 * 单词搜索
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
 * <p>
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * 输出：true
 * 示例 2：
 * <p>
 * <p>
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
 * 输出：true
 * 示例 3：
 * <p>
 * <p>
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
 * 输出：false
 *  
 * <p>
 * 提示：
 * <p>
 * m == board.length
 * n = board[i].length
 * 1 <= m, n <= 6
 * 1 <= word.length <= 15
 * board 和 word 仅由大小写英文字母组成
 *  
 * <p>
 * 进阶：你可以使用搜索剪枝的技术来优化解决方案，使其在 board 更大的情况下可以更快解决问题？
 * <p>
 * 相关标签
 * 数组
 * 回溯
 * 矩阵
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-medium/xvkwe2/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class Exist {

    public boolean exist(char[][] board, String word) {
        int h = board.length, w = board[0].length;
        boolean[][] visited = new boolean[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                boolean flag = check(board, visited, i, j, word, 0);
                if (flag) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean check(char[][] board, boolean[][] visited, int i, int j, String s, int k) {
        if (board[i][j] != s.charAt(k)) {
            return false;
        } else if (k == s.length() - 1) {
            return true;
        }
        visited[i][j] = true;
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        boolean result = false;
        for (int[] dir : directions) {
            int newi = i + dir[0], newj = j + dir[1];
            if (newi >= 0 && newi < board.length && newj >= 0 && newj < board[0].length) {
                if (!visited[newi][newj]) {
                    boolean flag = check(board, visited, newi, newj, s, k + 1);
                    if (flag) {
                        result = true;
                        break;
                    }
                }
            }
        }
        visited[i][j] = false;
        return result;
    }


    public boolean exist2(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                HashSet<String> strings = new HashSet<>();
                String key = i + "&" + j;
                strings.add(key);
                boolean exist = exist2(board, i, j, 0, word, strings);
                if (exist == true) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean exist2(char[][] board, int i, int j, int index, String word, HashSet<String> strings) {
        char ch = word.charAt(index);
        if (board[i][j] == ch) {
            // 已结束
            if (index == word.length() - 1) {
                return true;
            }
            // 未结束，获取下一个可走路径
            // 左 右 上 下
            String left = i + "&" + (j - 1);
            if (j - 1 > -1 && !strings.contains(left)) {
                HashSet<String> newStrings = new HashSet<String>();
                newStrings.addAll(strings);
                newStrings.add(left);
                boolean exist = exist2(board, i, j-1, index+1, word, newStrings);
                if (exist == true) {
                    return true;
                }
            }
            String right = i + "&" + (j + 1);
            if (j + 1 < board[0].length && !strings.contains(right)) {
                HashSet<String> newStrings = new HashSet<String>();
                newStrings.addAll(strings);
                newStrings.add(right);
                boolean exist = exist2(board, i, j + 1, index+1, word, newStrings);
                if (exist == true) {
                    return true;
                }
            }
            String up = (i - 1) + "&" + j;
            if ((i - 1) > -1 && !strings.contains(up)) {
                HashSet<String> newStrings = new HashSet<String>();
                newStrings.addAll(strings);
                newStrings.add(up);
                boolean exist = exist2(board, i - 1, j, index+1, word, newStrings);
                if (exist == true) {
                    return true;
                }
            }
            String down = (i + 1) + "&" + j;
            if ((i + 1) < board.length && !strings.contains(down)) {
                HashSet<String> newStrings = new HashSet<String>();
                newStrings.addAll(strings);
                newStrings.add(down);
                boolean exist = exist2(board, i + 1, j, index+1, word, newStrings);
                if (exist == true) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String word = "ABCCED";

        boolean exist = new Exist().exist(board, word);
        System.out.println("exist = " + exist);
    }
}
