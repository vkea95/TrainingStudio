package leetcode.com.easy.part0;

/**
 * Created by jason on 2016/1/25.
 * Locations:
 * https://leetcode.com/problems/reverse-integer/
 * ***************************************************
 * Descriptions:
 * Reverse digits of an integer.
 * Example1: x = 123, return 321
 * Example2: x = -123, return -321
 * ****************************************************
 * Solutions:
 * 输入的数字不断除10,直到除尽
 * ****************************************************
 * Tips:
 * How to deal with overflow?
 * 1. The answer is that adding a judge to source, 变后的值除以10， 与变更前的值不一样，则证明溢出，返回零
 * 2. 正数负数除以10的余数相反，例如：111 %10 =1， -111 %10= -1
 */
public class No007_Reverse_Integer {

    public static void main(String[] args) {
        No007_Reverse_Integer no007 = new No007_Reverse_Integer();
        no007.reverse(-123);
    }

    public int reverse(int x) {
        int result = 0;
        int nextResult = 0;
        while (x != 0) {
            result = result * 10 + x % 10;
            if (result / 10 != nextResult) {
                result = 0;
                break;
            }
            x = x / 10;
            nextResult = result;
        }
        return result;
    }
}
