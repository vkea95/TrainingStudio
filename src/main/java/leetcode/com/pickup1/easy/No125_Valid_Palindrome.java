package leetcode.com.pickup1.easy;

/**
 * Created by tclresearchamerica on 7/10/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/valid-palindrome/
 * ****************************************************
 * Description:
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
 * <p>
 * For example,
 * "A man, a plan, a canal: Panama" is a palindrome.
 * "race a car" is not a palindrome.
 * <p>
 * Note:
 * Have you consider that the string might be empty? This is a good question to ask during an interview.
 * <p>
 * For the purpose of this problem, we define empty string as valid palindrome.
 * ****************************************************
 * Tims: 15 mins
 * Beat: 59%
 * Bug:2
 * ****************************************************
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
public class No125_Valid_Palindrome {

    public static void main(String[] args) {
        No125_Valid_Palindrome obj = new No125_Valid_Palindrome();
        obj.isPalindrome("ab");
    }

    public boolean isPalindrome(String s) {
        if (s == null || s.length() <= 1) return true;
        int i = 0, j = s.length() - 1;
        char[] chars = s.toCharArray();

        while (i < j) {
            //bug1:数字和字母是对象哦,大写要转小写呢
            if (Character.isAlphabetic(chars[i]) || Character.isDigit(chars[i])) {
                if (Character.isAlphabetic(chars[j])|| Character.isDigit(chars[j])) {
                    if (Character.toLowerCase(chars[i]) == Character.toLowerCase(chars[j])) {
                        i++;
                        j--;
                    } else {
                        return false;
                    }


                } else {
                    j--;
                }
            } else {
                i++;
            }
        }
        return true;
    }
}
