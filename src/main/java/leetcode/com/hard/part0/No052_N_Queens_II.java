package leetcode.com.hard.part0;

import java.util.Arrays;

/**
 * Created by tclresearchamerica on 5/17/16.
 * ************************************************************
 * Location:
 * https://leetcode.com/problems/n-queens-ii/
 * ************************************************************
 * Description:
 * Follow up for N-Queens problem.
 * Now, instead outputting board configurations, return the total number of distinct solutions.
 * ************************************************************
 * Analysis:
 * 1.可以用No051的解决方案,但是运行速度太慢
 * 2.网络答案,用的是bit操作
 * ************************************************************
 * ************************************************************
 */
public class No052_N_Queens_II {


    public static void main(String[] args) {
        No052_N_Queens_II obj = new No052_N_Queens_II();
        obj.totalNQueens(4);
    }


    /*
    常规n-queens解法, 数答案个数.
    用column标记此行之前的哪些column已经放置了queen. 棋盘坐标(row, col)对应column的第col位(LSB --> MSB, 下同).
    用diag标记此位置之前的哪些主对角线已经放置了queen. 棋盘坐标(row, col)对应diag的第(n - 1 + row - col)位.
    用antiDiag标记此位置之前的哪些副对角线已经放置了queen. 棋盘坐标(row, col)对应antiDiag的第(row + col)位.
*/
    int count = 0;

    public int totalNQueens(int n) {
        dfs(0, n, 0, 0, 0);
        return count;
    }

    private void dfs(int row, int n, int column, int diag, int antiDiag) {
        if (row == n) {
            ++count;
            return;
        }
        for (int i = 0; i < n; ++i) {
            boolean isColSafe = ((1 << i) & column) == 0;
            //bug1: hard to understand ...
            boolean isDiagSafe = ((1 << (n - 1 + row - i)) & diag) == 0;
            boolean isAntiDiagSafe = ((1 << (row + i)) & antiDiag) == 0;
            if (isColSafe && isDiagSafe && isAntiDiagSafe) {
                dfs(row + 1, n, (1 << i) | column, (1 << (n - 1 + row - i)) | diag, (1 << (row + i)) | antiDiag);
            }
        }
    }


    public int totalNQueens_slow(int n) {
        char[][] board = new char[n][n];
        for (char[] c : board) {
            //bug1: 初期化整个数组
            Arrays.fill(c, '.');
        }

        //定义queen是否使用的数组
        boolean[] occupied = new boolean[n];
        helper(board, occupied, 0, n);
        return count;
    }

    private void helper(char[][] curr, boolean[] col_occupied, int r, int n) {
        if (r == n) {
            count++;
            return;
        }
        for (int i = 0; i < n; i++) {
            if (isValid(curr, col_occupied, r, i, n)) {
                curr[r][i] = 'Q';
                col_occupied[i] = true;
                //递归处理r+1行的数据
                helper(curr, col_occupied, r + 1, n);
                curr[r][i] = '.';
                col_occupied[i] = false;
            }

        }
    }

    private boolean isValid(char[][] curr, boolean[] col_occupied, int row, int col, int n) {
        //bug3:因为col_occupied已经对相应的列进行了check,且Row是递增的,在row上只有一个被标记了Q,所以只需check左上和右上的两条对角线
        if (col_occupied[col]) return false;
        for (int i = 1; row - i >= 0 && col - i >= 0; i++) {
            if (curr[row - i][col - i] == 'Q') return false;
        }
        for (int i = 1; row - i >= 0 && col + i < n; i++) {
            if (curr[row - i][col + i] == 'Q') return false;
        }
        return true;
    }
}
