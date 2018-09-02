package leetcode.com.pickup1.easy;

/**
 * Created by tclresearchamerica on 9/29/16.
 * **************************************************************
 * Location:
 * https://leetcode.com/problems/plus-one/
 * **************************************************************
 * Description:
 * Given a non-negative number represented as an array of digits, plus one to the number.
 * The digits are stored such that the most significant digit is at the head of the indexList.
 * **************************************************************
 * Hindsight:
 * 1.题目的关键在于如何处理进位,如果有进位,则一直要处理到进位为零
 * **************************************************************
 * **************************************************************
 * **************************************************************
 * **************************************************************
 * **************************************************************
 * **************************************************************
 */
public class No066_Plus_One {
    public int[] plusOne(int[] digits) {
        int carry = 1;
        for (int i = digits.length - 1; i >= 0 & carry != 0; i--) {
            digits[i] += carry;
            carry = digits[i] / 10;
            digits[i] = digits[i] % 10;
        }
        if (carry == 0) return digits;
        int[] newDigits = new int[digits.length + 1];
        newDigits[0] = 1;
        //optimization: 后面的循环处理根本就不需要,因为一旦因为最低位+1,导致数字位数增大,那必然是因为所有位都被清零所致,所以不必循环
//        for (int i = 0; i < digits.length; i++) {
//            newDigits[i + 1] = digits[i];
//        }
        return newDigits;
    }
}
