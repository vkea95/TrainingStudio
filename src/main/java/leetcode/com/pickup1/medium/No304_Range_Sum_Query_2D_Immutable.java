package leetcode.com.pickup1.medium;

/**
 * Created by tclresearchamerica on 9/26/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/range-sum-query-2d-immutable/
 * ****************************************************
 * Description:
 * Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner
 * (row1, col1) and lower right corner (row2, col2).
 * <p>
 * Range Sum Query 2D
 * The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3),
 * which contains sum = 8.
 * <p>
 * Example:
 * Given matrix = [
 * [3, 0, 1, 4, 2],
 * [5, 6, 3, 2, 1],
 * [1, 2, 0, 1, 5],
 * [4, 1, 0, 1, 7],
 * [1, 0, 3, 0, 5]
 * ]
 * <p>
 * sumRegion(2, 1, 4, 3) -> 8
 * sumRegion(1, 1, 2, 2) -> 11
 * sumRegion(1, 2, 2, 4) -> 12
 * Note:
 * You may assume that the matrix does not change.
 * There are many calls to sumRegion function.
 * You may assume that row1 ≤ row2 and col1 ≤ col2.
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
public class No304_Range_Sum_Query_2D_Immutable {
}

class NumMatrix {
    int[][] sumMatrix;

    public NumMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return;
        setupSumMatrix(matrix);
    }

    private void setupSumMatrix(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        sumMatrix = new int[row][col];
        //bug1:没有进行累加，还是需要累加的
        sumMatrix[0][0] = matrix[0][0];
        for (int j = 1; j < col; j++) sumMatrix[0][j] = matrix[0][j] + sumMatrix[0][j - 1];
        for (int j = 1; j < row; j++) sumMatrix[j][0] = matrix[j][0] + sumMatrix[j - 1][0];

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                sumMatrix[i][j] = matrix[i][j] + sumMatrix[i - 1][j] + sumMatrix[i][j - 1] - sumMatrix[i - 1][j - 1];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int temp = sumMatrix[row2][col2];
        if (row1 > 0) temp -= sumMatrix[row1 - 1][col2];
        if (col1 > 0) temp -= sumMatrix[row2][col1 - 1];
        if (row1 > 0 && col1 > 0) temp += sumMatrix[row1 - 1][col1 - 1];
        return temp;
    }
}


// Your NumMatrix object will be instantiated and called as such:
// NumMatrix numMatrix = new NumMatrix(matrix);
// numMatrix.sumRegion(0, 1, 2, 3);
// numMatrix.sumRegion(1, 2, 3, 4);