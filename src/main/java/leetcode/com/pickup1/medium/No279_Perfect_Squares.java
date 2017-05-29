package leetcode.com.pickup1.medium;

import java.util.Arrays;

/**
 * Created by tclresearchamerica on 7/8/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/perfect-squares/
 * ****************************************************
 * Description:
 * Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...)
 * which sum to n.
 * For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.
 * ****************************************************
 * Thoughts:
 * 1.要找到可以平方的数字然后一起搞,因为要找到least的对象,
 * 所以现在可以想到的有2分法,将目标值除2,再想辙?还是有别的数学逼近方法?
 * 但是涉及到开放,还真没有什么好招呢啊!再想想啊!
 * 2.依然是没有想到,看了第一遍的提示,才知道数字可以表示为x=a+b*b
 * 但是依然没有想到解决的方案呢
 * 3.看过答案后,发现可以用先将可以用1个平方数表示的元素,做上标记,
 * 再将标记,i+j*j这样的也做上标记,但是要取最小值呢,然后不断的循环处理i=0和j=0开始
 * ****************************************************
 * Time: 50 mins
 * Beat: 49%
 * Bug: -
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No279_Perfect_Squares {
    public int numSquares(int n) {
        int[] solutions = new int[n + 1];
        //bug1:初期化数字值为最大值
        Arrays.fill(solutions, Integer.MAX_VALUE);

        for (int i = 0; i * i <= n; i++) {
            solutions[i * i] = 1;
        }
        if (solutions[n] == 1) return 1;

        for (int i = 0; i <= n; i++) {
            for (int j = 0; i + j * j <= n; j++) {
                solutions[i + j * j] = Math.min(solutions[i] + 1, solutions[i + j * j]);
            }
        }

        return solutions[n];
    }
}
