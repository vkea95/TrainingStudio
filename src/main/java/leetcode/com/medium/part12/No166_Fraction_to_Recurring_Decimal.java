package leetcode.com.medium.part12;

import java.util.HashMap;

/**
 * Created by jason on 2016/4/2.
 * Location：
 * https://leetcode.com/problems/fraction-to-recurring-decimal/
 * ******************************************************************
 * Description：
 * 分数到循环小数
 * Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.
 * If the fractional part is repeating, enclose the repeating part in parentheses.
 * For example,
 * Given numerator = 1, denominator = 2, return "0.5".
 * Given numerator = 2, denominator = 1, return "2".
 * Given numerator = 2, denominator = 3, return "0.(6)".
 * ******************************************************************
 * Solution:
 * 能想到这道题的解法的人需要数学特别好，或者是变态。。。哈哈哈哈哈
 * 我的想法是分别处理除法运算出来的商和余数，但是不知道余数该如何处理。。。百思不得其解啊
 * 答案的处理方式，对其其放大10倍--这样保证余数中的每个数都会被再次除到，
 * 后面他用map来保存某个余数所对应的答案的长度，然后但该余数出现的时候，直接对该余数所对应的长度进行分化处理
 * 即该余数开始的位置加上括号----这个我也没想到，可以用hashMap来保存临时结果
 * 后记，在处理正负数的时候，统一当做正数处理，用异或判断给结果加负号。
 * 需要把普通整数转成long 整数，估计是因为计算出来的值长度大小都会变好所致
 */
public class No166_Fraction_to_Recurring_Decimal {
    public static void main(String[] args) {
        No166_Fraction_to_Recurring_Decimal obj = new No166_Fraction_to_Recurring_Decimal();
        obj.fractionToDecimal(-1, -2147483648);
    }

    public String fractionToDecimal(int numberator, int denominator) {

        if (numberator == 0) return "0";
        if (denominator == 0) return "";

        String result = "";

        //bug 1: is result is negative
        //Hint1:这个异或操作符用的很好，可以简化比较逻辑，以后会用的到
        if ((numberator < 0) ^ (denominator < 0)) result += "-";

        //convert int to long，
        // bug1:we must set it as long integer, and then try abs, otherwise, it couldn't tranform to abs for like the value is -2147483648
        //the Math.abs(-2147483648)=-2147483648
        //
        long num = numberator;
        long den = denominator;
        num = Math.abs(num);
        den = Math.abs(den);


        //quotient
        long res = num / den;
        result += String.valueOf(res);

        //if remainder is o, return result
        long remainder = (num % den) * 10;
        if (remainder == 0) return result;

        result += ".";

        //right-hand side of decimal point
        HashMap<Long, Integer> map = new HashMap<>();

        while (remainder != 0) {
            //if digits repeat

            if (map.containsKey(remainder)) {
                int len = map.get(remainder);
                String part1 = result.substring(0, len);
                String part2 = result.substring(len);
                result = part1 + "(" + part2 + ")";
                break;

            }
            //continue
            map.put(remainder, result.length());
            res = remainder / den;
            result += String.valueOf(res);
            remainder = (remainder % den) * 10;

        }

        return result;
    }
}
