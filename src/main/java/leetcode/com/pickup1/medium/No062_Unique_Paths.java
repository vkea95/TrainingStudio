package leetcode.com.pickup1.medium;

import java.util.Arrays;

/**
 * Created by tclresearchamerica on 7/9/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/unique-paths/
 * ****************************************************
 * Description:
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 * <p>
 * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right
 * corner of the grid (marked 'Finish' in the diagram below).
 * <p>
 * How many possible unique paths are there?
 * ****************************************************
 * Thoughts:
 * 1.毫无疑问的动态规划,所以要找到动态转化方程和初始条件
 * 第一行和第一列只会有一种走法,然后某格子的走法就是左面和上面各自的走法的总和
 * ****************************************************
 * Thoughts:
 * TIme: 10 mins:
 * Beat: 6%
 * Bug: 1
 * ****************************************************
 * Hindsight:
 * 网络答案是按照数学思想整的,其实这个也是正确的解法,这是没能深入理解它的含义,后面再搞好啦!
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No062_Unique_Paths {


    /*
    *
    * If you mark the south move as '1' and the east move as '0'. This problem shall be equal to :
    *Given (m+n-2) bits. you can fill in '1' for (m-1) times and '0' for (n-1) times, what is the number
    * of different numbers?
    *the result is clear that the formula shall be C(m-1)(m+n-2), where m-1 is the superscript behind C and m+n-2 is
    *  the subscript behind C.
    *To avoid overflow, I write the program in this manner.
     */
    public int uniquePaths_fast(int m, int n) {
        long result = 1;
        for (int i = 0; i < Math.min(m - 1, n - 1); i++)
            result = result * (m + n - 2 - i) / (i + 1);
        return (int) result;

    }

    public int uniquePaths(int m, int n) {

        int[][] solutions = new int[m][n];
        //bug1:fill方法只能用于一维数组,然后,其实也不用初始化为零,因为本身就是零嘛
//        Arrays.fill(solutions, 0);
        for (int i = 0; i < m; i++) solutions[i][0] = 1;
        for (int i = 0; i < n; i++) solutions[0][i] = 1;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                solutions[i][j] = solutions[i][j - 1] + solutions[i - 1][j];
            }
        }
        return solutions[m - 1][n - 1];
    }
}
