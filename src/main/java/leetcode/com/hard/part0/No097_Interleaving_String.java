package leetcode.com.hard.part0;

/**
 * Created by jason on 2016/1/9.
 * Locations:
 * https://leetcode.com/problems/interleaving-string/
 * ****************************************************
 * Description:
 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
 * <p>
 * For example,
 * Given:
 * s1 = "aabcc",
 * s2 = "dbbca",
 * <p>
 * When s3 = "aadbbcbcac", return true.
 * When s3 = "aadbbbaccc", return false.
 * <p>
 * Subscribe to see which companies asked this question
 * ****************************************************
 * Solutions:
 * Dynamic programming: 1.Memorizing search; 2.Loop
 * Because I need to answer if it works out, we could use DP of memory type
 * Step 1.
 * State: f[i][j]: if the (i+j) characters of s3 are made of the i characters of s1 and the j character of s2
 * Step 2. Function:
 * <p>
 * Step 3: Initialization
 * f[i][0]= s1[0..i-1]==s3[0...i-1]&&f[i-1][0]
 * f[0][0...j-1]=s2[0..j-1]&&f[0][j-1]
 * Step 4:
 * return f[i][j]
 * ****************************************************
 * Comments:
 * Lesson 6 video of JiuZhang
 * ****************************************************
 * Tips:
 * 1.When the array is initialized, the value should be set to the first column and the first row
 * 2.The size of the array could be a little bigger, because we need to use the (i-1) and (j-1)
 * if the size of the array is length of one string, the loop condition i<s.length,
 * the (i-1) couldn't be last raw or column
 * 3.In fact, the input parameters are not connected, so we need to add some if judgement,
 * the key is that we have some loops about the length of the strings,
 * so we need to figure out a way to keep the loop safe.
 * ****************************************************
 * Problems:
 * 1. Couldn't understand the whole DP, it's hard to setup the function,initialization...
 * 2. it's hard to setup the size of array...
 */
public class No097_Interleaving_String {

    public static void main(String[] args) {
        No097_Interleaving_String no97 = new No097_Interleaving_String();
        System.out.println(no97.isInterleave("aba", "ab", "ababa"));
    }

    public boolean isInterleave(String s1, String s2, String s3) {

        if (s1 == null || s2 == null || s3 == null) return false;
        //check if the input parameters could be correct
        if ((s1.length() + s2.length()) != s3.length()) {
            return false;
        }

        // setup the status function
        boolean[][] f = new boolean[s1.length() + 1][s2.length() + 1];
        f[0][0] = true;
        int l1 = s1.length();
        int l2 = s2.length();
        int l3 = s3.length();
        // initialization, the start value is
        for (int i = 1; i <= l1; i++) {
            if (f[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1)) f[i][0] = true;
        }
        for (int j = 1; j <= l2; j++) {
            if (f[0][j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1)) f[0][j] = true;
        }
        // status change
        for (int i = 1; i <= l1; i++) {
            for (int j = 1; j <= l2; j++) {
                if ((f[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) ||
                        (f[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1))) {
                    f[i][j] = true;
                }
            }
        }
        //display the function
        for (int i = 0; i <= l1; i++) {
            for (int j = 0; j <= l2; j++) {
                System.out.print(f[i][j] ? "T " : "F ");
            }
            System.out.println();
        }
        return f[l1][l2];
    }
}
