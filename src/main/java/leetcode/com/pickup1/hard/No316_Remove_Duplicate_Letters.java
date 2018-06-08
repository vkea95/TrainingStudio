package leetcode.com.pickup1.hard;

/**
 * Created by tclresearchamerica on 7/23/16.
 * *****************************************************************************
 * Location:
 * https://leetcode.com/problems/remove-duplicate-letters/
 * *****************************************************************************
 * Description:
 * Given a string which contains only lowercase letters, remove dupNumber letters so that every letter appear once
 * and only once. You must make sure your result is the smallest in lexicographical(adj. 辞典编纂的) order among
 * all possible results.
 * Example:
 * Given "bcabc"
 * Return "abc"
 * Given "cbacdcbc"
 * Return "acdb"
 * *****************************************************************************
 * Thoughts
 * 1.requirements:A.去重,B.尽量保持字符顺序
 * 2.问题在于如何保持字典顺序,
 * 3.逻辑可以理解为,首先要搞定,如果只有一个,那么顺序不可调整,如果有多个,那么就要尽量排在比它大的字符前面?
 * 4.因为只包含26个小写字符,所以可以将位置存入相应的数组位置中,理论上可以行,但是会导致很高的时间复杂度啊
 * 5.需要这样的数据结构,可以动态更新每个字符的所对应的位置,方便比较该字符前后字符的位置是多少,方便位置的取舍,相当于strategy
 * 6.因为是动态调整的,所以可能是动态规划问题?
 * *****************************************************************************
 * 网络答案:
 * 思路,首先统计所有字符的出现次数,然后从第一个字符开始处理,每次都要比较原来没有用过的,
 * 一旦发现重复出现,就将其次数-1,然后在已知的答案串中,寻找第一个比它大的,且计数器要表明,后面还会出现的,
 * 那么就继续啊前行指针(过程中要把跳过的元素标志设为false),
 * 即找到第一个比它小的,就把它放在被找到节点的后面、同时计数器-1,标记设为true。
 * *****************************************************************************
 * *****************************************************************************
 * *****************************************************************************
 * *****************************************************************************
 * *****************************************************************************
 * *****************************************************************************
 */
public class No316_Remove_Duplicate_Letters {
    public String removeDuplicateLetters(String s) {
        if (s == null || s.length() <= 1) return s;
        int[] count = new int[128];
        boolean[] flg = new boolean[128];
        char[] chars = s.toCharArray();
        for (char c : chars) count[c]++;
        int end = -1;

        for (char c : chars) {
            //bug1:若是已经被处理的字符--依然在有效答案字符串中
            if (flg[c]) {
                count[c]--;
                continue;
            }
            char sc;
            while (end >= 0 && (sc = chars[end]) >= c && count[sc] > 0) {
                end--;
                //point1:被跳过的字符，就要把flag设置为false啦
                flg[sc] = false;
            }

            //point2:end要先进行+1处理
            chars[++end] = c;
            flg[c] = true;
            //bug2:使用后的字符，要对计数器进行-1操作
            count[c]--;
        }

        return String.valueOf(chars).substring(0, end + 1);

    }
}
