package leetcode.com.medium.part02;

/**
 * Created by jason on 2016/3/1.
 * Location:
 * https://leetcode.com/problems/word-search/
 * ******************************************
 * Description:
 * Given a 2D board and a word, find if the word exists in the grid.
 * The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.
 * For example,
 * Given board =
 * [
 * ['A','B','C','E'],
 * ['S','F','C','S'],
 * ['A','D','E','E']
 * ]
 * word = "ABCCED", -> returns true,
 * word = "SEE", -> returns true,
 * word = "ABCB", -> returns false.
 * ******************************************
 * Solution:
 * 1.先找到第一个匹配的字符，然后调用递归方法
 * 2.在递归方法中，先将已经匹配的board数组中的元素设置成#
 * 3.然后递归调用查找该元素周边的邻居元素
 * 4.当累计查找的元素长度等于word长度时，返回
 * 5.记得给出边界判断条件和还原数组元素值
 */
public class No079_Word_Search {
    public boolean exist(char[][] board, String word) {
        boolean rst = false;

        if (word.length() == 0) {
            return true;
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    rst = find(board, i, j, word, 0);
                    if (rst)
                        return true;
                }
            }
        }

        return rst;
    }

    private boolean find(char[][] board, int row, int column, String word, int start) {
        if (start == word.length()) {
            return true;
        }

        if (row < 0 || row >= board.length || column < 0 || column >= board[0].length || board[row][column] != word.charAt(start)) {
            return false;
        }

        board[row][column] = '#';//shoulde remember to mark it

        boolean rst = find(board, row - 1, column, word, start + 1)
                || find(board, row + 1, column, word, start + 1)
                || find(board, row , column-1, word, start + 1)
                || find(board, row , column+1, word, start + 1);
        board[row][column] = word.charAt(start);
        return rst;


    }


}
