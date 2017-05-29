package leetcode.com.pickup1.medium;

/**
 * Created by tclresearchamerica on 9/28/16.
 * **************************************************************
 * Location:
 * https://leetcode.com/problems/decode-ways/
 * **************************************************************
 * Description:
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 * <p>
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * Given an encoded message containing digits, determine the total number of ways to decode it.
 * <p>
 * For example,
 * Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
 * <p>
 * The number of ways decoding "12" is 2.
 * **************************************************************
 * Thoughts:
 * DP?初期化一个长度为n的数组,第一项设置为1,然后用DP的方法,从index为1的项目开始循环,n和n-1的数字小于等于26,则加1,反之,则只是赋值
 * 但是如何处理数字零呢,看来这个里面的数字也存在非法的数字感觉,
 * **************************************************************
 * Hindsight:
 * 1.即如果是0的话,那么就是非法的字符
 * 2.
 * **************************************************************
 * **************************************************************
 * **************************************************************
 * **************************************************************
 */
public class No091_Decode_Ways {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) return 0;
        int[] dp = new int[s.length() + 1];

        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1;
        for (int i = 2; i <= s.length(); i++) {
            if (s.charAt(i - 1) != '0') {
                dp[i] = dp[i - 1];
            }
            int twoDigits = Integer.valueOf(s.substring(i - 2, i));
            dp[i] += twoDigits >= 10 && twoDigits <= 26 ? dp[i - 2] : 0;

        }
        //bug1:
        return dp[s.length()];
    }
}
