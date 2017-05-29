package leetcode.com.pickup1.easy;

import java.util.Arrays;

/**
 * Created by tclresearchamerica on 8/26/16.
 * ***************************************************************
 * Location:
 * https://leetcode.com/problems/longest-common-prefix/
 * ***************************************************************
 * Description:
 * Write a function to find the longest common prefix string amongst an array of strings.
 * ***************************************************************
 * Thoughts:
 * 寻找公共前缀,是否可以想象矩阵求column.看了第一版的答案,发现也不难。就是用第一个串当做prefix去搞
 * ***************************************************************
 * Time:10min
 * Beat:25%
 * Bug:-
 * ***************************************************************
 * Hindsight:
 * 1.开始没有解决问题,原因还是在于没头绪。
 * 09/22/2016
 * 1.自己吧问题解开了,有bug
 * ***************************************************************
 * ***************************************************************
 * ***************************************************************
 * ***************************************************************
 * ***************************************************************
 * ***************************************************************
 * ***************************************************************
 */
public class No014_Longest_Common_Prefix {

    public String longestCommonPrefix_ref_01(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        String pre = strs[0];
        int i = 1;
        while (i < strs.length) {
            while (strs[i].indexOf(pre) != 0)
                pre = pre.substring(0, pre.length() - 1);
            i++;
        }
        return pre;
    }

    public String longestCommonPrefix_fastest(String[] strs) {
        StringBuilder result = new StringBuilder();

        if (strs != null && strs.length > 0) {

            Arrays.sort(strs);

            char[] a = strs[0].toCharArray();
            char[] b = strs[strs.length - 1].toCharArray();

            for (int i = 0; i < a.length; i++) {
                if (b.length > i && b[i] == a[i]) {
                    result.append(b[i]);
                } else {
                    return result.toString();
                }
            }

        }
        return result.toString();
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            int j = 0;
            String target = strs[i];
            while (j < target.length() && j < prefix.length() && target.charAt(j) == prefix.charAt(j)) {
                j++;
            }

            prefix = prefix.substring(0, j);
        }

        return prefix;
    }
}
