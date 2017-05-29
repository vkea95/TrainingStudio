package leetcode.com.medium.part02;

/**
 * Created by jason on 2016/3/10.
 * Location:
 * https://leetcode.com/problems/decode-ways/
 * ***********************************************
 * Description:
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * Given an encoded message containing digits, determine the total number of ways to decode it.
 * For example,
 * Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
 * The number of ways decoding "12" is 2.
 * ************************************************
 * Solution:
 * 算法简单，思路略显晦涩。如果当前字符有效（1~9），那么编码方式并没有变化，等于前一个字符的编码方式的数量，
 * 若该字符和前一个字符可以组合成合法字符，那么
 */
public class No091_Decode_Ways {
    public static void main(String[] args) {
        No091_Decode_Ways tmp =new No091_Decode_Ways();
//        System.out.println(tmp.numDecodings("1234"));
        System.out.println("new game:" +tmp.numDecodings("01"));
    }


    public int numDecodings(String s) {
        if (s == null || s.length() == 0)
            return 0;
        int[] nums = new int[s.length() + 1];
        nums[0] = 1;
        nums[1] = s.charAt(0) == '0' ? 0 : 1;

        for (int i = 2; i <= s.length(); i++) {
            if (s.charAt(i - 1) != '0') {
                nums[i] = nums[i - 1];
            }

            int twoDigits = (s.charAt(i - 2) - '0') * 10 + s.charAt(i - 1) - '0';
            if (twoDigits >= 10 && twoDigits <= 26) {
                nums[i] += nums[i - 2];
            }
        }

        for (int i: nums)
            System.out.println(i);
        return nums[s.length()];
    }
}
