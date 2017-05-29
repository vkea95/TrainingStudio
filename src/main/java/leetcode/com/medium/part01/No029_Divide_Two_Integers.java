package leetcode.com.medium.part01;

/**
 * Created by jason on 2016/2/2.
 * Locations:
 * https://leetcode.com/problems/divide-two-integers/
 * ******************************************************
 * Descriptions:
 * Divide two integers without using multiplication, division and mod operator.
 * If it is overflow, return MAX_INT.
 * ******************************************************
 * Solutions:
 * 除数不断尽心位的左移操作，直到大于被除数，然后对除数用移动的次数减一尽心移动并与a相减，然后将1左移相同次数得到的结果
 * 与之前的到的结果相加
 * ******************************************************
 * Tips：
 * 位操作的流程要熟悉已经都要忘记了，
 */
public class No029_Divide_Two_Integers {
    public static void main(String[] args) {
        No029_Divide_Two_Integers No029 = new No029_Divide_Two_Integers();
        int a = 18;
        System.out.println("a=18: a<<2: " + (a << 2));
        System.out.println("89 /13:" + No029.divide(77, 13));
    }

    public int divide(int dividend, int divisor) {
        if (divisor == 0) {
            return dividend >= 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }

        if (dividend == 0) {
            return 0;
        }
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        boolean isNegative = (dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0);

        long a = Math.abs((long) dividend);
        long b = Math.abs((long) divisor);

        int result = 0;
        while (a >= b) {
            int shift = 0;
            while (a >= (b << shift)) {
                shift++;
            }
            a -= b << (shift - 1);
            result += 1 << (shift - 1);
        }
        return isNegative ? -result : result;
    }
}
