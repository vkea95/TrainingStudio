package leetcode.com.medium;

/**
 * Created by tclresearchamerica on 7/9/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/valid-perfect-square/
 * ****************************************************
 * Description:
 * Given a positive integer num, write a function which returns True if num is a perfect square else False.
 * <p>
 * Note: Do not use any built-in library function such as sqrt.
 * <p>
 * Example 1:
 * <p>
 * Input: 16
 * Returns: True
 * Example 2:
 * <p>
 * Input: 14
 * Returns: False
 * ****************************************************
 * Thoughts:
 * 1.简言之就是判断数字num是否为某数的平方,能够和那个a+b*b=x有关系,不过有没有办法开方.
 * 2.显然Greedy不是最好的方法.二分法或许可以使用,但是想来也没有很好的算法表达方式
 * 3.看了网络答案,原来是由个数学公式
 * This is a math problem：
 * 1 = 1
 * 4 = 1 + 3
 * 9 = 1 + 3 + 5
 * 16 = 1 + 3 + 5 + 7
 * 25 = 1 + 3 + 5 + 7 + 9
 * 36 = 1 + 3 + 5 + 7 + 9 + 11
 * ....
 * so 1+3+...+(2n-1) = (2n-1 + 1)n/2 = nn
 * 4.ref:https://discuss.leetcode.com/topic/49342/3-4-short-lines-integer-newton-most-languages/2
 * 这个方法接近于2分法,但是有点晦涩不易懂,回去慢慢琢磨吧,
 * 看来Leetcode会把所有的内置function都出成算法题,让大家玩啊,这样也好,顺便又熟悉了语言功能
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No367_Valid_Perfect_Square {
    public boolean isPerfectSquare(int x) {
        long r = x;
        while (r * r > x)
            r = (r + x / r) / 2;//-->为什么用x/r呢? 数学原理?
        return r * r == x;

    }
}
