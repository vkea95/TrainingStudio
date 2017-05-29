package leetcode.com.easy.part1;

/**
 * Created by jason on 2016/3/17.
 * Location:
 * https://leetcode.com/problems/valid-palindrome/
 * **************************************************
 * Description:
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
 * For example,
 * "A man, a plan, a canal: Panama" is a palindrome.
 * "race a car" is not a palindrome.
 * **************************************************
 * Solution:
 * 从两边开始向中间靠拢，但是有2点遗漏项目
 * 1.判断字符是否为合法字符：可以通过判断是否为字符或数字来处理
 * 2.若整句话都是符号这作为特殊情况要考虑哦
 */
public class No125_Valid_Palindrome {
    public boolean isPalindrome(String s) {
        if (s == null || s.length() <= 1) return true;

        int front = 0;
        int end = s.length() - 1;
        boolean result = true;

        while (front < end) {
            while (front < s.length() && !isValidChar(s.charAt(front))) {
                front++;
            }

            if (front == s.length()) {
                break;
            }
            while (end >= 0 && !isValidChar(s.charAt(end))) {
                end--;
            }
            if (Character.toLowerCase(s.charAt(front)) == Character.toLowerCase(s.charAt(end))) {
                front++;
                //bug2: forget to add[end--]
                end--;

                //bug1: forget to add else
            } else {
                result = false;
                break;
            }

        }


        return result;

    }

    boolean isValidChar(char ch) {
        return Character.isLetter(ch) || Character.isDigit(ch);
    }
}
