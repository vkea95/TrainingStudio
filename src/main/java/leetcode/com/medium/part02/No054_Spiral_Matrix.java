package leetcode.com.medium.part02;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jason on 2016/2/15.
 * Location:
 * https://leetcode.com/problems/spiral-matrix/
 * *********************************************
 * Description:
 * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
 * For example,
 * Given the following matrix:
 * [
 * [ 1, 2, 3 ],
 * [ 4, 5, 6 ],
 * [ 7, 8, 9 ]
 * ]
 * You should return [1,2,3,6,9,8,7,4,5].
 * ************************************************
 * Solutions:
 * 根据网上答案分析:count可以表示spiral的次数，正常的完成一次spiral，会走2行2列，所以count*2<rows && count *2 <cols
 * 循环至一半的时候要判断下 是否只剩1行或1列，如果是的话，似乎就不必循环啦
 * 可根据count=0时的状况，完成各个for循环的数值带入等等
 */
public class No054_Spiral_Matrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();

        if (matrix == null || matrix.length == 0)
            return result;

        int rows = matrix.length;
        int cols = matrix[0].length;
        int count = 0;

        while (count * 2 < rows && count * 2 < cols) {
            for (int i = count; i < cols - count; i++)
                result.add(matrix[count][i]);

            for (int i = count + 1; i < rows - count; i++)
                result.add(matrix[i][cols - count - 1]);

            if (rows - 2 * count == 1 || cols - 2 * count == 1)
                break;// if only one row / col remains


            for (int i = cols - count - 2; i >= count; i--)
                result.add(matrix[rows - count - 1][i]);


            for (int i = rows - count - 2; i >= count + 1; i--)
                result.add(matrix[i][count]);

            count++;
        }
        return result;
    }

}
