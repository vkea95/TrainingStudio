package leetcode.com.pickup1.medium;

import leetcode.com.util.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tclresearchamerica on 6/12/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 * ****************************************************
 * Description:
 * Given a digit string, return all possible letter combinations that the number could represent.
 * A mapping of digit to letters (just like on the telephone buttons) is given below.
 * Input:Digit string "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * ****************************************************
 * Thoughts:
 * 数字代表着不同的字符集,用递归来完成呗,有个字符串的数组,数字的下标对应不同的字符串,来进行组合匹配
 * ****************************************************
 * Time: 20 mins
 * Beats: 47%
 * Bug: 1
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No017_Letter_Combinations_of_a_Phone_Number {
    public static void main(String[] args) {
        No017_Letter_Combinations_of_a_Phone_Number obj = new No017_Letter_Combinations_of_a_Phone_Number();
        obj.letterCombinations("2");
    }
    public List<String> letterCombinations(String digits) {

        //bug1: 数组下标越界,因为少了个元素
        String[] letterArray = new String[]{" ", "", "abc", "def", "ghi","jkl", "mno", "pqrs", "tuv", "wxyz"};
        StringBuffer solution = new StringBuffer();
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) return result;
        char[] digitArray = digits.toCharArray();
        buildUpCombinations(result, solution, letterArray, digitArray, 0);

        return result;

    }

    private void buildUpCombinations(List<String> result, StringBuffer solution, String[] letterArray,
                                     char[] digitArray, int index) {
        if (index == digitArray.length) {
            result.add(solution.toString());
            return;
        }
        int tmpIndex = digitArray[index] - '0';
        if (tmpIndex == 1) {
            buildUpCombinations(result, solution, letterArray, digitArray, index + 1);

        } else {

            for (char c : letterArray[tmpIndex].toCharArray()) {
                solution.append(c);
                buildUpCombinations(result, solution, letterArray, digitArray, index + 1);
                solution.deleteCharAt(solution.length() - 1);
            }

        }
    }
}
