package leetcode.com.pickup1.medium;

/**
 * Created by tclresearchamerica on 8/7/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/word-search/
 * ****************************************************
 * Description:
 * Given a 2D board and a word, find if the word exists in the grid.
 * <p>
 * The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally
 * or vertically neighboring. The same letter cell may not be used more than once.
 * <p>
 * For example,
 * Given board =
 * <p>
 * [
 * ['A','B','C','E'],
 * ['S','F','C','S'],
 * ['A','D','E','E']
 * ]
 * word = "ABCCED", -> returns true,
 * word = "SEE", -> returns true,
 * word = "ABCB", -> returns false.
 * ****************************************************
 * Thought:
 * 1.trie树-->建图,然后用词典中的单词去寻找
 * 2.网络答案,BSF,每次要把遍历过的字符设置成'#'-->key point,保证不会遍历错误呗
 * ****************************************************
 * Time:30 mins
 * Beat: 36%->66%
 * Bug: 2
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No079_Word_Search {
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0) return false;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (helper(board, word, 0, i, j)) return true;
            }
        }
        return false;
    }

    private boolean helper(char[][] board, String word, int pos, int i, int j) {
        //bug2:成功的条件,没有考虑数组的大小只是1的情况啊,即进来就匹配成功了,但是没法移动呢
//        if (pos == word.length()) return true;

        if (board[i][j] != word.codePointAt(pos)) return false;
        if (pos == word.length() - 1) return true;
        //key point
        char c = board[i][j];
        board[i][j] = '#';
        int[][] dxy = new int[][]{{0, 0, 1, -1}, {1, -1, 0, 0}};
        for (int k = 0; k < 4; k++) {
            //bug1:没有对新坐标的下限进行检查,坐标的变更会同时影响到坐标的上限和下限,必须都检查
            int row = i + dxy[0][k], col = j + dxy[1][k];
            if (row >= 0 && row < board.length && col < board[i].length && col >= 0) {

                if (helper(board, word, pos + 1,row, col)) {
                    return true;
                }
            }
        }
        board[i][j] = c;
        return false;

    }
}
