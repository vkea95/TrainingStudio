package leetcode.com.medium.part02;

/**
 * Created by jason on 2016/2/16.
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
 * *************************************************
 * Solution:
 * 利用Spiral Matrix I的方法，进行一次赋值操作即可
 * 九章算法的答案，其实殊途同归，它是通过外围赋值操作达到初期话的目的
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
}
