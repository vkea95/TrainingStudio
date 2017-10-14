package leetcode.com.tag.trie;

/**
 * Created by JianZhang on 10/14/17.
 * Given a 2D board and a word, find if the word exists in the grid.
 * <p>
 * The word can be constructed from letters of sequentially adjacent cell,
 * where "adjacent" cells are those horizontally or vertically neighboring.
 * The same letter cell may not be used more than once.
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
 * Solutions:
 * 1. 曾经考虑过用一个mark数组标记是否访问过数组中的元素,后来发现可以用#号来修改,然后再用string中的字符进行复位
 * 2. 在数组周围便利的时候,要进行边界check和是否访问的check
 */
public class No079_Word_Search {
    public boolean exist(char[][] board, String word) {
        if (word == null) return true;
        if (board == null || board.length == 0 || board[0].length == 0) return false;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfsSearch(board, word, i, j, 0)) return true;
            }
        }
        return false;
    }

    private boolean dfsSearch(char[][] board, String word, int row, int col, int position) {
        boolean chkResult = false;
        //check if the string is ok with position
        if (position == word.length()) return true;

        // boarder check
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || word.charAt(position) != board[row][col])
            return false;

        // use the special flag instead of markedArray
        board[row][col] = '#';
        if (dfsSearch(board, word, row + 1, col, position + 1)
                || dfsSearch(board, word, row - 1, col, position + 1)
                || dfsSearch(board, word, row, col + 1, position + 1)
                || dfsSearch(board, word, row, col - 1, position + 1))
            chkResult = true;
        else chkResult = false;

        // recover the board array
        board[row][col] = word.charAt(position);

        return chkResult;
    }
}
