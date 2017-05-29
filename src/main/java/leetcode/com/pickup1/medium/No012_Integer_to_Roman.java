package leetcode.com.pickup1.medium;

/**
 * Created by tclresearchamerica on 7/19/16.
 * **************************************************************
 * Location:
 * https://leetcode.com/problems/integer-to-roman/
 * **************************************************************
 * Description:
 * Given an integer, convert it to a roman numeral.
 * Input is guaranteed to be within the range from 1 to 3999
 * **************************************************************
 * Thoughts:
 * 1.这个算法该是比较通用的,但是需要知道换算后的基本表示方法
 * 2.需要从最高位开始降序处理,进行表示
 * **************************************************************
 * **************************************************************
 * **************************************************************
 * **************************************************************
 * **************************************************************
 */
public class No012_Integer_to_Roman {


    public String intToRoman(int num) {
        if (num <= 0) return "";
        int[] nums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        int digit = 0;
        StringBuilder sb = new StringBuilder();

        while (num > 0) {
            int times = num / nums[digit];
            num -= times * nums[digit];
            for (; times > 0; times--) {
                sb.append(symbols[digit]);
            }
            digit++;
        }

        return sb.toString();
    }
}
