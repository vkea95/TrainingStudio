package leetcode.com.medium.part21;

/**
 * Created by tclresearchamerica on 4/13/16.
 * Location:
 * https://leetcode.com/problems/maximal-square/
 * ************************************************
 * Description:
 * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing all 1's and return its area.
 * <p>
 * For example, given the following matrix:
 * <p>
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 * Return 4.
 * *************************************************
 * Analysis:
 * This problem can be solved by dynamic programming. The changing condition is:
 * t[i][j] = min(t[i][j-1], t[i-1][j], t[i-1][j-1]) + 1. It means the square formed before this point.
 * *************************************************
 * Hints:
 * 需要结合某个算法选择合适的数据结构,根据题意,会想到动态规划,但是没有想到合适算法即想法来化解这个问题.
 */
public class No221_Maximal_Square {
    public static void main(String[] args) {
        No221_Maximal_Square obj = new No221_Maximal_Square();
//        obj
    }
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;
        int n = matrix.length;
        int m = matrix[0].length;
        //default value is zero
        int square[][] = new int[n][m];

        //initialize top row & left column
        //bug1: don't know the method of Character class
        for (int i = 0; i < n; i++)
            square[i][0] = Character.getNumericValue(matrix[i][0]);

        for (int i = 0; i < m; i++)
            square[0][i] = Character.getNumericValue(matrix[0][i]);

        //calculating inside cells
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (matrix[i][j] == '1') {
                    //bug2:该是取square矩阵中的值,而不是matrix中的值
                    int min = Math.min(square[i - 1][j], square[i - 1][j - 1]);
                    min = Math.min(min, square[i][j - 1]);
                    square[i][j] = min + 1;

                }
            }
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                max = Math.max(max, square[i][j]);
            }
        }

        return max * max;
//        int m = matrix.length;
//        int n = matrix[0].length;
//
//        int[][] t = new int[m][n];
//
//        //top row
//        for (int i = 0; i < m; i++) {
//            t[i][0] = Character.getNumericValue(matrix[i][0]);
//        }
//
//        //left column
//        for (int j = 0; j < n; j++) {
//            t[0][j] = Character.getNumericValue(matrix[0][j]);
//        }
//
//        //cells inside
//        for (int i = 1; i < m; i++) {
//            for (int j = 1; j < n; j++) {
//                if (matrix[i][j] == '1') {
//                    int min = Math.min(t[i - 1][j], t[i - 1][j - 1]);
//                    min = Math.min(min, t[i][j - 1]);
//                    t[i][j] = min + 1;
//                } else {
//                    t[i][j] = 0;
//                }
//            }
//        }
//
//        int max = 0;
//        //get maximal length
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                if (t[i][j] > max) {
//                    max = t[i][j];
//                }
//            }
//        }
//
//        return max * max;

    }
}
