package leetcode.com.tag.topologicalSort;

/**
 * Created by JianZhang on 10/8/17.
 * Given an integer matrix, find the length of the longest increasing path.
 * From each cell, you can either move to four directions: left, right, up or down.
 * You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).
 * Example 1:
 * nums = [
 * [9,9,4],
 * [6,6,8],
 * [2,1,1]
 * ]
 * Return 4
 * The longest increasing path is [1, 2, 6, 9].
 * Example 2:
 * nums = [
 * [3,4,5],
 * [3,2,6],
 * [2,2,1]
 * ]
 * Return 4
 * The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 * Solutions:
 * 1. limited conditions: A. increasing B.
 * 2. graph: DFS or BFS
 * 3. ds: 邻接表?
 * Time: 1532
 */
public class No329_Longest_Increasing_Path_in_a_Matrix {

    private int[][] steps = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    //    credit: http://www.cnblogs.com/grandyang/p/5148030.html
//    那么这道题的解法要用递归和DP来解，用DP的原因是为了提高效率，避免重复运算。我们需要维护一个二维动态数组dp，
// 其中dp[i][j]表示数组中以(i,j)为起点的最长递增路径的长度，初始将dp数组都赋为0，当我们用递归调用时，遇到某个位置(x, y),
// 如果dp[x][y]不为0的话，我们直接返回dp[x][y]即可，不需要重复计算。我们需要以数组中每个位置都为起点调用递归来做，比较找出最大值。
// 在以一个位置为起点用DFS搜索时，对其四个相邻位置进行判断，如果相邻位置的值大于上一个位置，则对相邻位置继续调用递归，并更新一个最大值，
// 搜素完成后返回即可，

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;
        int result = 1;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++)
                result = Math.max(result, dfs(dp, matrix, i, j));
        }

        return result;
    }

    //
    private int dfs(int[][] dp, int[][] matrix, int i, int j) {

        if (dp[i][j] > 0) return dp[i][j];
        int len = 1;
        for (int[] step : steps) {
            int x = step[0] + i;
            int y = step[1] + j;
//            因为目标只会想增序方向前进,所以不用考虑回环问题
            if (x < 0 || x >= dp.length || y < 0 || y >= dp[0].length || matrix[i][j] >= matrix[x][y]) continue;
            len = Math.max(1 + dfs(dp, matrix, x, y), len);
        }
        dp[i][j] = len;
        return dp[i][j];
    }
}
