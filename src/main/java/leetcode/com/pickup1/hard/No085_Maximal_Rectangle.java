package leetcode.com.pickup1.hard;

import java.util.Arrays;

/**
 * Created by tclresearchamerica on 8/28/16.
 * ***************************************************************
 * Location:
 * https://leetcode.com/problems/maximal-rectangle/
 * ***************************************************************
 * Description:
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
 * For example, given the following matrix:
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 * Return 6.
 * ***************************************************************
 * Thoughts:
 * 1.求矩形的最大面积,感觉需要用图来解决问题啊?BFS?
 * 2.矩形的要件。某个点?没有好的想法诶?需要什么样的数据结构呢?
 * 3.输入矩阵,最小的面积是0(没有1),如果有1的话,至少是1。如果有1连在一起的话,至少是2.
 * 4.没有想到好的数据结构,或是好的算法解析方法。再想想看啦
 * 5.换个想法。按照一定顺序进行遍历。左上到右下。那么被check的点,就不用回头去遍历后面的点了。
 * 点的遍历方式,有3种,横向,纵向和横纵结合。但横纵结合这个不好搞啊。随着点的范围的扩大。算起来就很麻烦呢
 * ***************************************************************
 * Web solution:
 * https://segmentfault.com/a/1190000005779466
 * 这题思路很重要，一定要理清previous row和current row的参数之间的关系，那么就事半功倍了。
 * left[]表示从左往右到i，出现连续‘1’的string的第一个座标，right[]表示从右往左到i, 出现连续‘1’的string的最后一个座标，
 * height[]表示从上到下的高度。那么用(left[j] - right[j] + 1)(横长) * height[j]就是可能的最大的矩形了。
 * ***************************************************************
 * Time: 40min
 * Beat: 88%
 * Bug: -
 * ***************************************************************
 * Hindsight:
 * 1.这道题的解法,还是要好好考虑下啊,同时还有另一种解法--通用于另一个题目。详见:
 * https://segmentfault.com/a/1190000002679200
 * ***************************************************************
 * ***************************************************************
 * ***************************************************************
 * ***************************************************************
 * ***************************************************************
 * ***************************************************************
 */
public class No085_Maximal_Rectangle {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int max = 0;
        int m = matrix.length, n = matrix[0].length;
        int[] left = new int[n];
        int[] right = new int[n];
        int[] height = new int[n];
        Arrays.fill(left, 0);
        Arrays.fill(height, 0);
        Arrays.fill(right, n - 1);//要放n-1作为初始值

        for (int i = 0; i < m; i++) {
            int curLeft = 0, curRight = n - 1;
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') height[j]++;
                else height[j] = 0;
            }
            //1 1 0 1
            //1 1 0 1
            //1 1 1 1
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    //见上述例子，left[j]保证了前面的数组是正方形且没有0的最小矩形，
                    //All the 3 variables left, right, and height can be determined by the information
                    // from previous row, and also information from the current row.
                    left[j] = Math.max(left[j], curLeft);
                } else {
                    left[j] = 0;
                    curLeft = j + 1;
                }
            }
            for (int j = n - 1; j >= 0; j--) {
                if (matrix[i][j] == '1') {
                    right[j] = Math.min(right[j], curRight);
                } else {
                    right[j] = n - 1;
                    curRight = j - 1;
                }
            }
            for (int j = 0; j < n; j++) {
                max = Math.max(max, (right[j] - left[j] + 1) * height[j]);
            }
        }
        return max;
    }
}
