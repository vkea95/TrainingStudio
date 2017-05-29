package leetcode.com.medium.part01;

/**
 * Created by jason on 2016/2/14.
 * Locations：
 * https://leetcode.com/problems/multiply-strings/
 * ************************************************
 * Descriptions：
 * Given two numbers represented as strings, return multiplication of the numbers as a string.
 * Note: The numbers can be arbitrarily large and are non-negative.
 * ************************************************
 * Solutions：
 */
public class No043_Multiply_Strings {
    public String multiply(String num1, String num2) {
        if (num1 == null || num2 == null) {
            return num1;
        }

        int len1 = num1.length();
        int len2 = num2.length();
        int len3 = len1 + len2;
        int i, j, product, carry;

        int[] num3 = new int[len3];
        for (i = len1 - 1; i >= 0; i--) {
            carry = 0;
            for (j = len2 - 1; j >= 0; j--) {
                product = carry + num3[i + j + 1] +
                        Character.getNumericValue(num1.charAt(i)) *
                                Character.getNumericValue(num2.charAt(j));
                num3[i + j + 1] = product % 10;
                carry = product / 10;
            }
            num3[i + j + 1] = carry;
        }

        StringBuffer sb = new StringBuffer();
        i = 0;
        while (i < len3 - 1 && num3[i] == 0) {
            i++;
        }

        while (i < len3) {
            sb.append(num3[i++]);
        }
        return sb.toString();
    }
}
