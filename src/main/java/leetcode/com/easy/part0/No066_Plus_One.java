package leetcode.com.easy.part0;

/**
 * Created by jason on 2016/2/19.
 * Location:
 * https://leetcode.com/problems/plus-one/
 * **********************************************
 * Description:
 * Given a non-negative number represented as an array of digits, plus one to the number.
 * The digits are stored such that the most significant digit is at the head of the indexList.
 * **********************************************
 * Solutions:
 * 按位进行加减法，注意进位的处理以及数组元素加1的可能性
 */
public class No066_Plus_One {
    public int[] plusOne(int[] digits) {
        int carry = 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i] += carry;
            carry = digits[i] / 10;
            digits[i] %= 10;
        }
        if (carry == 0) return digits;

        int[] result = new int[digits.length + 1];
        result[0] = 1;
        for (int i = 1; i < result.length; i++) {
            result[i] = digits[i - 1];
        }
        return result;
    }
}

