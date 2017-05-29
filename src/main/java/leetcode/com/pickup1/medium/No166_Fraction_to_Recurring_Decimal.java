package leetcode.com.pickup1.medium;

import java.util.HashMap;

/**
 * Created by tclresearchamerica on 6/13/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/fraction-to-recurring-decimal/
 * ****************************************************
 * Description:
 * Given two integers representing the num and den of a fraction, return the fraction in string format.
 * If the fractional part is repeating, enclose the repeating part in parentheses.
 * For example,
 * Given num = 1, den = 2, return "0.5".
 * Given num = 2, den = 1, return "2".
 * Given num = 2, den = 3, return "0.(6)".
 * ****************************************************
 * Thoughts:
 * 1.数学问题,必然用到数学知识和CS的结合,这里面的问题就是无限循环小数的判断和处理方法,
 * 单纯的进行除法计算肯定是不可以的,那么是否可以用乘法来进行转换和代替呢?
 * 可以想到的有余数,除法后的
 * 2.问题归结到了,这次比第一次强一些,想到了将余数扩大10倍的做法,但是没有想到用hashmap来保存结果
 * 3.还有就是对结果的正负数的处理,统一成整数然后,结果后面加上符号即可.
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No166_Fraction_to_Recurring_Decimal {
    public static void main(String[] args) {
        System.out.println(2 % 3);

        No166_Fraction_to_Recurring_Decimal obj = new No166_Fraction_to_Recurring_Decimal();
//        obj.fractionToDecimal(1, 5);
        obj.fractionToDecimal(-1, -2147483648);

        obj.fractionToDecimal(1, 6);
    }

    public String fractionToDecimal(int numerator, int denominator) {
        //bug8:可以提前判断两个值的出发是否为零
        if (numerator == 0) return "0";
        if (denominator == 0) return "";

        //bug1:
        boolean flag = (numerator < 0) ^ (denominator < 0);
        //bug2:没取绝对值
        //bug3:integer 取绝对值,再付给long型变量,会保留符号位,所以该先赋值,再取绝对值
        long num = Math.abs(numerator);
        long den = Math.abs(denominator);
        num = Math.abs(num);
        den = Math.abs(den);


        long n = num / den;
        //bug9:还是直接进行求余运算的好,避免计算溢出
        long remainder = (num % den) * 10;
        StringBuffer sb = new StringBuffer();
//        if (flag) {
//            sb.append(String.valueOf(-1 * n));
//        } else {
//            sb.append(String.valueOf(n));
//        }
//            //bug7:0的-1 还是0 所以计算的方法并不好用
        sb.append(flag ? "-" : "");
        sb.append(n);
        if (remainder != 0) {
            sb.append('.');
        }
        HashMap<Long, Integer> hashMap = new HashMap<>();
        // hashMap.put(remainder, sb.length());
        while (remainder != 0) {

            if (hashMap.containsKey(remainder)) {
                // sb.deleteCharAt(sb.length() - 1);

                int len = hashMap.get(remainder);
//                String rst = sb.toString();
                //bug10:stringbffer insert method
                sb.insert(len,'(');
                sb.append(')');
//                sb.in
//                rst = rst.substring(0, len) + "(" + rst.substring(len) + ")";

                // sb.append('(');
                //bug4:结果要保存的不是余数而是结果
                break;
                // break;
            } else {
                //bug5:需要n,而不是remainder
                // hashMap.put(remainder, n);
            }

            //bug5:存下来循环小数第一次出现的位置，
            hashMap.put(remainder, sb.length());

//            num = ;
            n = remainder / den;
            //bug3:该将计算结果放入计算后的字符串中
            sb.append(n);
            //bug9:此处的remainder如果除不尽的话,会一直保持增长10->100->1000
            remainder = (remainder % den) * 10;
        }


        return sb.toString();
    }
}
