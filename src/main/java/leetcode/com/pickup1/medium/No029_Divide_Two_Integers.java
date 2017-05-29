package leetcode.com.pickup1.medium;

/**
 * Created by tclresearchamerica on 9/27/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/divide-two-integers/
 * ****************************************************
 * Description:
 * Divide two integers without using multiplication, division and mod operator.
 * ****************************************************
 * Thoughts:
 * 毫无疑问要用到位操作符,将除法用别的方法进行替换啦
 * ****************************************************
 * ref:https://segmentfault.com/a/1190000003789802
 * ****************************************************
 * Hindsight:
 * 1.boundary check
 * 2.需要将整数转化为更大取值范围的long
 * 3.符号位提前确定
 * 4.除法的位计算法要知晓呢
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No029_Divide_Two_Integers {
    public int divide(int dividend, int divisor) {
        //boundary check
        if (dividend == 0) return 0;
        if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;
        if (divisor == 0) return dividend >= 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;

        boolean isNegative = (dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0);

        long a = Math.abs((long) dividend);
        long b = Math.abs((long) divisor);
        int result = 0;
        while (a >= b) {
            int shift = 0;
            //until a is less than b,
            while (a >= (b << shift)) {
                shift++;
            }
            a -= b << (shift - 1);
            result += 1 << (shift - 1);

        }
        return isNegative ? -result : result;
    }
}
