package leetcode.com.medium.part01;

/**
 * Created by jason on 2016/1/30.
 * Locations:
 * https://leetcode.com/problems/integer-to-roman/
 * *************************************************
 * Descriptions:
 * Given an integer, convert it to a roman numeral.
 * Input is guaranteed to be within the range from 1 to 3999.
 * *************************************************
 * Solutions:
 * 整数的另一种表现形式，那么定义好两个数组，保证他们之间的一一对应关系，
 * 通过整除的方法，计算他们之间的关系
 * 关键是明白roman numeral的换算公式，然后抽象出相应的算法。
 * 其实这个程度属于easy+，不能算正统的medium程度
 * *************************************************
 * Tips:
 * what is the roman numeral
 */
public class No012_Integer_to_Roman {
    public String intToRoman(int num) {
        if (num <= 0) {
            return "";
        }
        int[] nums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder res = new StringBuilder();
        int digit = 0;
        while (num > 0) {
            int times = num / nums[digit];
            num -= nums[digit] * times;
            for (; times > 0; times--) {
                res.append(symbols[digit]);
            }
            digit++;
        }
        return res.toString();
    }
}
