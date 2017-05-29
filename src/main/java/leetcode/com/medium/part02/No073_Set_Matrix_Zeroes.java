package leetcode.com.medium.part02;

/**
 * Created by jason on 2016/2/26.
 * Location：
 * https://leetcode.com/problems/set-matrix-zeroes/
 * **************************************************
 * Description:
 * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.
 * Follow up:
 * Did you use extra space?
 * A straight forward solution using O(mn) space is probably a bad idea.
 * A simple improvement uses O(m + n) space, but still not the best solution.
 * Could you devise a constant space solution?
 * **************************************************
 * Solution:
 * 1.首先标识第0行和第0列是否有元素为零，
 * 2.从第1行和第1列开始循环，发现有元素为零则将对应的第0行第0列元素标为零
 * 3.从第1行和第1列开始循环，发现【0】【j】或【i】【0】元素为零则改元素为零
 * 4.根据No.1的结果判断是否需对第0行和第0列数据进行处理
 */
public class No073_Set_Matrix_Zeroes {
    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;

        boolean empty_row0 = false;
        boolean empty_col0 = false;
        for (int i = 0; i < cols; i++) {
            if (matrix[0][i] == 0) {
                empty_row0 = true;
                break;
            }
        }

        for (int i = 0; i < rows; i++) {
            if (matrix[i][0] == 0) {
                empty_col0 = true;
                break;
            }
        }

        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }

        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (matrix[0][j] == 0 || matrix[i][0] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        if (empty_row0) {
            for (int i = 0; i < cols; i++) {
                matrix[0][i] = 0;
            }
        }

        if (empty_col0) {
            for (int i = 0; i < rows; i++) {
                matrix[i][0] = 0;
            }
        }
    }

}
