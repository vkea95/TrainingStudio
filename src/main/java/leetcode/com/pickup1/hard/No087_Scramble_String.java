package leetcode.com.pickup1.hard;

import java.util.Arrays;

/**
 * Created by tclresearchamerica on 6/11/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/scramble-string/
 * ****************************************************
 * Description:
 * Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.
 * <p>
 * Below is one possible representation of s1 = "great":
 * <p>
 * great
 * /    \
 * gr    eat
 * / \    /  \
 * g   r  e   at
 * / \
 * a   t
 * To scramble the string, we may choose any non-leaf node and swap its two children.
 * <p>
 * For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".
 * <p>
 * rgeat
 * /    \
 * rg    eat
 * / \    /  \
 * r   g  e   at
 * / \
 * a   t
 * We say that "rgeat" is a scrambled string of "great".
 * <p>
 * Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".
 * <p>
 * rgtae
 * /    \
 * rg    tae
 * / \    /  \
 * r   g  ta  e
 * / \
 * t   a
 * We say that "rgtae" is a scrambled string of "great".
 * <p>
 * Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.
 * ****************************************************
 * Thoughts:
 * 1.既然是二叉树,就该用到二叉树的特性了
 * 2.先试着写构造二叉树的代码,意识到既然构造,那么就已经开始进行遍历了嘛,所以可在构造的过程中进行,判断
 * 3.还需要对奇数的字符情况进行单独确认呢
 * 4.突然在想将字符串转换成字符的数组,在index的基础上进行二分就好了吧?
 * 5.不匹配的话,有2种情况,一种是本身交换后不匹配,另一种是和右侧交换后不匹配
 * ****************************************************
 * Time: 75 mins
 * Beats: 51%
 * Bug: -
 * ****************************************************
 * 网络答案:
 * 1.判断长度是否匹配
 * 2.判断内容是否匹配
 * 3.匹配排序后的字符串是否相等
 * 4.拆分字符串,然后再分别比较
 * ****************************************************
 */
public class No087_Scramble_String {

    public static void main(String[] args) {
        No087_Scramble_String obj = new No087_Scramble_String();
        System.out.println(obj.p.length);
        obj.isScramble("rgeat", "great");
    }

    int[] p = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89,
            97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197,
            199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317,
            331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449,
            457, 461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 541, 547, 557, 563, 569, 571, 577, 587, 593,
            599, 601, 607, 613, 617, 619, 631, 641, 643, 647, 653, 659, 661, 673, 677, 683, 691, 701, 709, 719, 727,
            733, 739, 743, 751, 757, 761, 769, 773, 787, 797, 809, 811, 821, 823, 827, 829, 839, 853, 857, 859, 863,
            877, 881, 883, 887, 907, 911, 919, 929, 937, 941, 947, 953, 967, 971, 977, 983, 991, 997};

    //https://leetcode.com/discuss/88867/2ms-java-recursive-solution-beat-100%25
    //通过质数乘积的方式来确定前i个字符是否相乘,因为数组中存的是质数,所以不必担心出现1*1*4=1*2*2的情况,
    //也就避免了连续累计字符进行比较的问题,太赞啦
    //这样也有个问题,就是计算的量很大,字符多了后会有溢出的问题吧,因为是单词所以还好吧
    public boolean isScramble(String s1, String s2) {
        int l1 = s1.length(), l2 = s2.length();
        if (l1 != l2) return false;
        if (l1 <= 1) return s1.equals(s2);
        if (s1.equals(s2)) return true;
        long a = 1, b = 1, c = 1;
        for (int i = 0; i < l1; i++) {
            if (i > 0 && a == b
                    && isScramble(s1.substring(0, i), s2.substring(l2 - i))
                    && isScramble(s1.substring(i), s2.substring(0, l2 - i)))
                return true;
            if (i > 0 && a == c
                    && isScramble(s1.substring(0, i), s2.substring(0, i))
                    && isScramble(s1.substring(i), s2.substring(i)))
                return true;
            a *= p[s1.charAt(i) - 'A'];
            b *= p[s2.charAt(l2 - 1 - i) - 'A'];
            c *= p[s2.charAt(i) - 'A'];
        }
        return false;
    }


    public boolean isScramble_slow(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }

        if (s1.length() == 0 || s1.equals(s2)) {
            return true;
        }
        if (!isValid(s1, s2)) {
            return false;
        }

        int len = s1.length();
        for (int i = 1; i < len; i++) {
            String s11 = s1.substring(0, i);
            String s12 = s1.substring(i, len);
            String s21 = s2.substring(0, i);
            String s22 = s2.substring(i, len);
            String s23 = s2.substring(0, len - i);
            String s24 = s2.substring(len - i, len);
            if (isScramble_slow(s11, s21) && isScramble_slow(s12, s22)) return true;
            if (isScramble_slow(s11, s24) && isScramble_slow(s12, s23)) return true;
        }
        return false;

    }

    private boolean isValid(String s1, String s2) {
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        Arrays.sort(c1);
        Arrays.sort(c2);

        return (new String(c1)).equals(new String(c2));
    }

    private boolean divideConquer_failed(char[] chars1, char[] chars2, int start, int end) {
        int count = (end - start) / 2;
        String leftS1 = String.valueOf(chars1, start, count);
        String leftS2 = String.valueOf(chars2, start, count);

        String rightS1 = String.valueOf(chars1, start + count + 1, end - count + 1);
        String rightS2 = String.valueOf(chars2, start + count + 1, end - count + 1);
        if (count == 1) {
            if ((leftS1 == leftS2 && rightS1 == rightS2) || (rightS1 == leftS2 && leftS1 == rightS2)) {
                return true;
            } else {
                return false;
            }
        }
        boolean leftFlg = false;
        if (leftS1 != leftS2) {
            leftFlg = divideConquer_failed(chars1, chars2, start, start + count);
        }
        boolean rightFlg = false;
        if (rightS1 != rightS2) {
            rightFlg = divideConquer_failed(chars1, chars2, start, start + count);
        }
        if (leftFlg && rightFlg) {
            return true;
        } else {
            leftFlg = false;
            rightFlg = false;
            if (leftS1 == rightS2 && leftS2 == rightS1) {
                return true;
            } else {

            }
        }
        return false;
    }


    private StringBT makeTree(String value1, String value2) {
        if (value1 == null) return null;

//        if (value1.length() == 1) return makeTree(value1);

        boolean leftFlg = false;
        boolean rightFlg = true;
        if (value1.substring(0, value1.length() / 2) == value2.substring(0, value1.length() / 2)) {
            leftFlg = true;
        } else {
//            leftFlg
        }


//        StringBT left = makeTree(value1.substring(0, value1.length() / 2));
//        StringBT right = makeTree(value1.substring(value1.length() / 2);
//        StringBT root = makeTree(value1);
//        root.left = left;
//        root.right = right;
//        return root;
        return null;
    }
}

class StringBT {
    public StringBT left;
    public StringBT right;
    public String val;

    public StringBT(String val) {
        this.val = val;
    }

}