package leetcode.com.pickup1.medium;

/**
 * Created by tclresearchamerica on 6/22/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/rotate-image/
 * ****************************************************
 * Description:
 * You are given an n x n 2D matrix representing an image.
 * Rotate the image by 90 degrees (clockwise).
 * Follow up:
 * Could you do this in-place?
 * ****************************************************
 * Thoughts:
 * 可以在图上画出变化的状况,但是不能推导出数学公式
 * 推导结果:变换的时候会牵扯到半长,行列会有交换,
 * 推导方法:3*3 矩阵,在中间格子,画横竖坐标系即可.
 * Time: 30 mins
 * Beat: 12%
 * Bug: 3
 * ****************************************************
 * Thoughts:
 * 1.若i,j的上限都是<n/2,那么n=3的数组,行列中间的那个元素不会被旋转
 * 2.若i,j的上限都是<(n+1)/2,那么n=3的数组,行列中间的那个元素就会被多旋转一次\
 * 3.只有当i,j的上限分别是n/2或(n+1)/2的时候,才会刚好完成旋转
 * 4.这个可以通过为3*3数组的矩阵图,画线来模拟,凡是会被旋转的元素都画上线,那么就比较清晰了
 * ****************************************************
 * In hindsight:
 * 1.两个要点: A.旋转90度的,下标算法 B.如何界定下标范围
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No048_Rotate_Image {
    public void rotate(int[][] matrix) {
        //长度为1的数组,不必旋转
        if (matrix == null || matrix.length <= 1) return;

        int end = matrix.length - 1;
        int n = matrix.length;
        //bug1: i,j的上限被设置成了数组的长度,结果数组的内容没有发生变换,数组被复原了
        //所以只需要旋转一半的元素即可.
        //bug2: 上限设置的有问题,导致不同情况下,行的中间元素,要么没有处理,要么旋转180度
        //所以i,j的上限要设置成不同的value,依然有点懵
        //
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < (n + 1) / 2; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[end - j][i];
                matrix[end - j][i] = matrix[end - i][end - j];
                matrix[end - i][end - j] = matrix[j][end - i];
                matrix[j][end - i] = tmp;
            }
        }

    }

}
