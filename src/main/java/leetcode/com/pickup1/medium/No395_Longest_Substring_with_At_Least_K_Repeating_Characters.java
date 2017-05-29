package leetcode.com.pickup1.medium;

/**
 * Created by tclresearchamerica on 9/28/16.
 * **************************************************************
 * Location:
 * **************************************************************
 * Description:
 * Find the length of the longest substring T of a given string (consists of lowercase letters only) such that
 * every character in T appears no less than k times.
 * Example 1:
 * Input:
 * s = "aaabb", k = 3
 * Output:
 * 3
 * The longest substring is "aaa", as 'a' is repeated 3 times.
 * Example 2:
 * Input:
 * s = "ababbc", k = 2
 * Output:
 * 5
 * The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.
 * Subscribe to see which companies asked this question
 * **************************************************************
 * ref:http://www.cnblogs.com/grandyang/p/5852352.html
 * **************************************************************
 * Thought:
 * 1.扫描字符串, 将出现次数小于k的字符找出来,即将字符串切分,然后再递归求解子串,问题在于数据结构没有想清楚呢
 * <p>
 * 有个解法和我的思路类似:
 * http://www.cnblogs.com/dongling/p/5843874.html
 * **************************************************************
 * **************************************************************
 * **************************************************************
 * **************************************************************
 * **************************************************************
 * **************************************************************
 * **************************************************************
 * **************************************************************
 * **************************************************************
 */
public class No395_Longest_Substring_with_At_Least_K_Repeating_Characters {

    public int longestSubstring_wrong(String s, int k) {
        if (s == null || s.length() == 0) return 0;
        int res = 0, i = 0, n = s.length();
        while (i + k < n) {
            int[] m = new int[26];
            int mast = 0;
            int max_idx = i;
            for (int j = i; j < n; j++) {
                int t = s.charAt(i) - 'a';
                m[t]++;
                if (m[t] < k) mast |= (1 << t);
                else mast &= (~(1 << t));
                if (mast == 0) {
                    res = Integer.max(res, j - i + 1);
                    max_idx = j;
                }
            }
            i = max_idx + 1;
        }
        return res;
    }

    public int longestSubstring(String s, int k) {
        //boundary check
        if (s == null || s.length() == 0) return 0;
        if (k <= 1) {
            return s.length();
        }

        int[] repeat = new int['z' + 1];
        for (int i = 0; i < s.length(); i++) {
            repeat[s.charAt(i)]++;
        }

        StringBuilder regex = new StringBuilder("");
        boolean firstSplit = true;

        for (int i = 'a'; i <= 'z'; i++) {
            if (repeat[i] > 0 && repeat[i] < k) {
                if (firstSplit) {
                    regex.append((char) i);
                    firstSplit = false;
                } else {
                    regex.append("|" + (char) i);
                }
            }
        }
        if (regex.length() > 0) {
            //Tip:此处用regex进行切分字符串
            //说明有分隔符
            String[] strs = s.split(regex.toString());
            int max = 0;
            int tmpAns = 0;
            for (String str : strs) {
                if (max > str.length()) continue;
                tmpAns = longestSubstring(str, k);
                if (tmpAns > max) {
                    max = tmpAns;
                }
            }
            return max;
        } else {
            //没有分隔符,说明s中的每一个字符出现的次数都大于等于k
            return s.length();
        }
    }
}
