package leetcode.com.medium;

/**
 * Created by tclresearchamerica on 5/5/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/range-sum-query-2d-immutable/
 * ****************************************************
 * Description:
 * Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left
 * corner (row1, col1) and lower right corner (row2, col2).
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
 * Analysis:
 * 这道题的目的就是通过有限的几次计算,来替换多次的计算,目测属于初中1,2年级的数学水准
 * 即sum数组的初始化,是累加该cell的所有左上位置的cell的值,
 * 这样再计算的时候,只需要cell(row2,col2)-cell(row2,col1)-cell(row1,col2)+cell(row1,col1)
 * Hint:
 * 1.也许会有sum的溢出,这个需要预先提出来,那么可以通过为sum数组换数据类型来达到目的int->double
 * 2.这里没有去判断matrix的null,以及length是否为0等条件,直接对sum进行了实例化
 * <p>
 * ****************************************************
 * Bug Analysis:
 * 题目的解法已经被破了,就是在细节上出现了问题:
 * sum初始化:首行的部分元素的sum没有累加自己,
 * sum初始化:丢掉了累加符号
 * 算sum的时候,没有搞清楚边界值
 *
 * 对策:
 * 想来还是题目解法没有破解到位,即想明白了,但是转化的过程中,是在没有完美解法的前提下进行的,所以必然会有纰漏.
 * 对于像这样的题目,还是可以按照图形再推演破解的.自己整个适当的例子,就会推演清楚,结合代码,就会比较容易发现不管
 * 需要在实际编码过程中,进行实践
 */
public class No304_Range_Sum_Query_2D_Immutable {
    public static void main(String[] args) {
        int[][] matrix = {{3, 0, 1, 4, 2}, {5, 6, 3, 2, 1}, {1, 2, 0, 1, 5}, {4, 1, 0, 1, 7}, {1, 0, 3, 0, 5}};

        NumMatrix numMatrix = new NumMatrix(matrix);
        System.out.println(numMatrix.sumRegion(2, 1, 4, 3));
    }
}

class NumMatrix {

    private int[][] sum;

    public NumMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            sum = new int[0][0];
        } else {
            sum = new int[matrix.length][matrix[0].length];
            initSum(matrix);
        }

    }

    private void initSum(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                //bug1:首行的i>0的元素也要计算累加值的!!!!
                sum[i][j] = matrix[i][j];
                if (i == 0 && j > 0) {
                    sum[i][j] += sum[i][j - 1];
                } else if (i > 0 && j == 0) {
                    sum[i][j] += sum[i - 1][j];
                } else if (i > 0 && j > 0) {
                    //bug2:忘记将sum[i][j]自己进行累积了
                    sum[i][j] += sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1];
                }
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if (sum.length == 0)
            return 0;
        //bug3:需要对这个矩形以外的数据进行减法处理,而不是对矩形边缘的数据进行处理
        //还是要画个图才能清楚明了的阐明这个问题
        int result = sum[row2][col2];

        if (col1 > 0 && row1 > 0) {
            result += sum[row1 - 1][col1 - 1] - sum[row1 - 1][col2] - sum[row2][col1 - 1];
        } else if (row1 > 0) {
            result -= sum[row1 - 1][col2];
        } else if (col1 > 0) {
            result -= sum[row2][col1 - 1];
        }

        return result;
    }
}


// Your NumMatrix object will be instantiated and called as such:
// NumMatrix numMatrix = new NumMatrix(matrix);
// numMatrix.sumRegion(0, 1, 2, 3);
// numMatrix.sumRegion(1, 2, 3, 4);