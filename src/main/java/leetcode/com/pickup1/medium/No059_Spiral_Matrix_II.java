package leetcode.com.pickup1.medium;

/**
 * Created by tclresearchamerica on 9/20/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/spiral-matrix-ii/
 * http://www.jiuzhang.com/solutions/spiral-matrix-ii/
 * ************************************************
 * Description:
 * Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
 * For example,
 * Given n = 3,
 * You should return the following matrix:
 * [
 * [ 1, 2, 3 ],
 * [ 8, 9, 4 ],
 * [ 7, 6, 5 ]
 * ]
 * ****************************************************
 * Hindsight:
 * 1.头一天没有思路,次日,按照想法把题目解了,并没有比第一版解法简洁明了
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No059_Spiral_Matrix_II {

    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];

        int count = 0;
        int number = 1;
        while (n > 2 * count) {
            for (int i = count; i < n - count; i++) {
                result[count][i] = number++;
//                number++;
            }
            for (int i = count + 1; i < n - count; i++) {
                result[i][n - count - 1] = number++;
            }
            if (n - count == 1)
                break;

            for (int i = n - count - 2; i >= count; i--) {
                result[n - count - 1][i] = number++;
            }

            for (int i = n - count - 2; i > count; i--) {
                result[i][count] = number++;
            }
            count++;
        }


        return result;
    }

    public int[][] generateMatrix_zj(int n) {
        int[][] rst = new int[n][n];
        int mostLeft = 0, mostRight = n - 1;
        int mostUp = 0, mostDown = n - 1;
        int count = 1;
        // int i =mostUp;

        while (mostLeft <= mostRight) {

            for (int j = mostLeft; j <= mostRight; j++) {
                rst[mostUp][j] = count++;
            }
            mostUp++;
            if (mostUp <= mostDown) {
                for (int i = mostUp; i <= mostDown; i++) {
                    rst[i][mostRight] = count++;
                }
            }

            mostRight--;
            if (mostLeft <= mostRight) {
                for (int j = mostRight; j >= mostLeft; j--) {
                    rst[mostDown][j] = count++;
                }
            }

            mostDown--;
            if (mostUp <= mostDown) {
                for (int i = mostDown; i >= mostUp; i--) {
                    rst[i][mostLeft] = count++;
                }
            }

            mostLeft++;
        }
        return rst;
    }
}
