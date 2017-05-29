package leetcode.com.pickup1.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by tclresearchamerica on 8/5/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/n-queens-ii/
 * ****************************************************
 * Description:
 * Follow up for N-Queens problem.
 * Now, instead outputting board configurations, return the total number of distinct solutions.
 * ****************************************************
 * Thoughts:
 * 1.完全忘记N Queens的做法,需要自己再写个几遍,再理解几次
 * 2.
 * ****************************************************
 * Hindsight:
 * 1.要记住,三种情况下1的平移方式,
 * 2.当然这道题有些限制,平移的范围不能超过整数的位数
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No052_N_Queens_II {

    private int count = 0;

    public int totalNQueens(int n) {

        char[][] board = new char[n][n];
        for (char[] c : board) {
            //bug1: 初期化整个数组
            Arrays.fill(c, '.');
        }

        dfs(board, 0, n, 0, 0, 0);

        return count;
    }


    private void dfs(char[][] board, int row, int n, int column, int diag, int antiDiag) {
        if (row == n) {
//            List<String> solution = new ArrayList<>();
//            for (char[] chars : board) {
//                solution.add(new String(chars));
//            }
//            this.result.add(solution);
            count++;
            return;
        }
        for (int i = 0; i < n; ++i) {
            //Point1:(1 << i) ->column
            boolean isColSafe = ((1 << i) & column) == 0;
            //bug1: hard to understand ...
            //此处的diag & antiDiag的方法采用的对角线考虑法 row-i & row+i分别是不同方向的对角线

            //Point 2: 1 << (n - 1 + row - i) --> diag
            //Point 3: 1 << (row + i) --> antidiag
            boolean isDiagSafe = ((1 << (n - 1 + row - i)) & diag) == 0;
            boolean isAntiDiagSafe = ((1 << (row + i)) & antiDiag) == 0;
            if (isColSafe && isDiagSafe && isAntiDiagSafe) {
                board[row][i] = 'Q';
                dfs(board, row + 1, n, (1 << i) | column, (1 << (n - 1 + row - i)) | diag, (1 << (row + i)) | antiDiag);
                board[row][i] = '.';
            }
        }
    }
}
