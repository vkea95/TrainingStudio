package leetcode.com.pickup1.medium;

/**
 * Created by tclresearchamerica on 7/29/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/set-matrix-zeroes/
 * ****************************************************
 * Description:
 * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.
 * ****************************************************
 * Thought:
 * 1.the hard part is that we need to do it in place.
 * 2.DS 确定了,要考虑合适的算法啦。recursive?
 * 3.看了网络答案,其实比较就是多饶了一层,利用数组本身的特性来处理,首先扫描0行0列,得到是否清零的决定并保存,
 * 然后再扫描其余元素的时候,将状态保持在0行0列,然后通过0行0列的状态赋值,然后再通过之前的标志,对0行0列赋值
 * 4.就是利用数组和额外的空间,仿照stack做了一次处理
 * ****************************************************
 * Time: 30 mins
 * Beat: 23%
 * Bug:1
 * ****************************************************
 * Hindsight:
 * 1.解题思路有问题,所以会有点绕圈子,
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No073_Set_Matrix_Zeroes {
    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return;
        }
        boolean row0 = false, col0 = false;
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[0][i] == 0) {
                row0 = true;
                break;
            }
        }

        for (int j = 0; j < matrix.length; j++) {
            if (matrix[j][0] == 0) {
                col0 = true;
                break;
            }
        }

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }

        //bug1:此时,不能扫描0行0列,需要从1行1列开始扫描,因为0行0列已经被赋值了
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[0][j] == 0 || matrix[i][0] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        for (int i = 0; row0 && i < matrix[0].length; i++) {
            matrix[0][i] = 0;
        }

        for (int i = 0; col0 && i < matrix.length; i++) {
            matrix[i][0]=0;
        }
    }
}
