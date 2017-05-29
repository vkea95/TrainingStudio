package leetcode.com.pickup1.easy;

/**
 * Created by tclresearchamerica on 7/20/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/minimum-path-sum/
 * ****************************************************
 * Description:
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes
 * the sum of all numbers along its path.
 * ****************************************************
 * Time: 10 min
 * Beat: 52%
 * Bug: 1
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No064_Minimum_Path_Sum {
    public int minPathSum(int[][] grid) {

        if (grid == null || grid.length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        int[][] solutions = new int[m][n];
        solutions[0][0] = grid[0][0];
        //bug1:行列的范围弄反了
        for (int i = 1; i < n; i++) {
            solutions[0][i] = solutions[0][i - 1] + grid[0][i];
        }
        for (int i = 1; i < m; i++) {
            solutions[i][0] = solutions[i - 1][0] + grid[i][0];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                solutions[i][j] = grid[i][j] + Math.min(solutions[i - 1][j], solutions[i][j - 1]);
            }
        }

        return solutions[m - 1][n - 1];
    }
}
