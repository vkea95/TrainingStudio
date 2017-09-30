package leetcode.com.tag.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by JianZhang on 9/30/17.
 * Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
 * <p>
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 * <p>
 * For example,
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * After running your function, the board should be:
 * <p>
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * Solutions:
 * 1. 问题不是找到可以转变为X的0,通过观察可以知道,四个边上的0肯定不可以转变为X,那么在它的上下左右的0也不可以转为X
 * Bugs:
 * 1. 这样的三维数组,每个元素都有一个index: X*Cols+Y 范围是(>=0 && <=Rows*Cols)
 * 2. 左右上下偏移,+/-1,+/-Cols
 */
public class No130_Surrounded_Regions {
    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) return;

        //定义行数,列数
        int rows = board.length;
        int cols = board[0].length;
        for (int i = 0; i < cols; i++) {
            if (board[0][i] == 'O') helper(board, 0, i);
            if (board[rows - 1][i] == 'O') helper(board, rows - 1, i);
        }

        for (int i = 0; i < rows; i++) {
            if (board[i][0] == 'O') helper(board, i, 0);
            if (board[i][cols - 1] == 'O') helper(board, i, cols - 1);
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'O') board[i][j] = 'X';
                if (board[i][j] == 'B') board[i][j] = 'O';
            }
        }

    }

    private void helper(char[][] board, int x, int y) {

        int rows = board.length;
        //bug:计算index的时候,要用x*col才对
        int cols = board[0].length;

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(x * cols + y);//(x,y)
        while (!queue.isEmpty()) {
            //bug1:通过index就可以完成定位
            int index = queue.poll();
            if (index < 0 || index >= rows * cols) {
                continue;
            }
//            if (row < 0 & row >= rows || col < 0 || col >= board[0].length) {
//                continue;
//            }

            //bug2:必须index计算,不能用queue的顶部变量了,因为已经出列了
            int row = index / cols;
            int col = index % cols;
            if (board[row][col] != 'O') {
                continue;
            }
            board[row][col] = 'B';
            //bug3:通过index就可以完成计算偏移量
            queue.offer(index + 1);
            queue.offer(index - 1);
            queue.offer(index + cols);
            queue.offer(index - cols);
//            queue.offer((row + 1) * rows + col);
//            queue.offer((row - 1) * rows + col);
//            queue.offer((row) * rows + col + 1);
//            queue.offer((row) * rows + col - 1);

        }
    }
}
