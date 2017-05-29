package leetcode.com.pickup1.medium;

/**
 * Created by tclresearchamerica on 9/25/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/search-a-2d-matrix/
 * ****************************************************
 * Description:
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
 * <p>
 * Integers in each row are sorted from left to right.
 * The first integer of each row is greater than the last integer of the previous row.
 * For example,
 * <p>
 * Consider the following matrix:
 * <p>
 * [
 * [1,   3,  5,  7],
 * [10, 11, 16, 20],
 * [23, 30, 34, 50]
 * ]
 * Given target = 3, return true.
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No074_Search_a_2D_Matrix {
    public static void main(String[] args) {
        No074_Search_a_2D_Matrix obj = new No074_Search_a_2D_Matrix();
        obj.searchMatrix(new int[][]{{1}}, 1);
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        //boundary check
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        //顺序二分法
        int start = 0, end = matrix.length * matrix[0].length - 1;
        int mid = 0;
        int step = matrix[0].length;

        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            //count the row number
            int row = mid / step;
            int col = mid % step;
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        //bug1:2分法的最后一个步骤给漏掉啦！！！要追加对start&end的元素的值判断啦
        if(matrix[start/step][start%step]==target ) return true;
        else if(matrix[end/step][end%step]==target ) return true;
        return false;
    }
}
