package leetcode.com.pickup1.medium;

/**
 * Created by tclresearchamerica on 9/25/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/search-a-2d-matrix-ii/
 * ****************************************************
 * Description:
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
 * <p>
 * Integers in each row are sorted in ascending from left to right.
 * Integers in each column are sorted in ascending from top to bottom.
 * For example,
 * <p>
 * Consider the following matrix:
 * <p>
 * [
 * [1,   4,  7, 11, 15],
 * [2,   5,  8, 12, 19],
 * [3,   6,  9, 16, 22],
 * [10, 13, 14, 17, 24],
 * [18, 21, 23, 26, 30]
 * ]
 * Given target = 5, return true.
 * <p>
 * Given target = 20, return false.
 * ****************************************************
 * Thoughts:
 * 2分法?如何二分呢
 * a[i][j]
 * a[0][0] >
 * <p>
 * startRow, endRow, startCol, endCol
 * medRow,medCol
 * if(a[medRow][medCol]<target)
 * med所在行必须保留,不能扔掉的,否则会丢失部分可能的candidate
 * 其实也就是说,不能单纯地挪用2分法,根据a[medRow][medCol]抛弃值,
 * 但是这样的处理就会很麻烦,没有找到好的解法。曾经的解法要处理很多的分支结构,巨复杂
 * ****************************************************
 * Ref:
 * https://segmentfault.com/a/1190000004288673
 * 复杂度
 * 时间 O(N+M) 空间 O(1)
 * 思路
 * 虽说该方法叫贪心法不太得当，但是贪心最能描述该方法的过程。由于数组的特性，我们可以从二维数组的右上角出发，
 * 如果该数比目标大，则向左移动（左边的数字肯定更小）。如果该数比目标小，则向下移动（下边的数字肯定更大）。
 * 这样反复重复该过程就能找到目标数。如果直到左下角都没有该数，说明找不到该数。同样的，这题也可以从左下角向右上角寻找。
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No240_Search_a_2D_Matrix_II {
    public boolean searchMatrix(int[][] matrix, int target) {
        //boundaryCheck
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int row = 0, col = matrix[0].length - 1;

        while (row < matrix.length && col >= 0) {
            int curt = matrix[row][col];
            if (curt == target) {
                return true;
            } else if (curt < target) {
                row++;
            } else {
                col--;
            }
        }
        return false;
    }
}
