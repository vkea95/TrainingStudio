package leetcode.com.medium;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Created by tclresearchamerica on 5/9/16.
 * Location:
 * https://leetcode.com/problems/maximum-product-of-word-lengths/
 * ****************************************************
 * Description:
 * Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the two words do not
 * share common letters. You may assume that each word will contain only lower case letters.
 * If no such two words exist, return 0.
 * Example 1:
 * Given ["abcw", "baz", "foo", "bar", "xtfn", "abcdef"]
 * Return 16
 * The two words can be "abcw", "xtfn".
 * Example 2:
 * Given ["a", "ab", "abc", "d", "cd", "bcd", "abcd"]
 * Return 4
 * The two words can be "ab", "cd".
 * Example 3:
 * Given ["a", "aa", "aaa", "aaaa"]
 * Return 0
 * No such pair of words.
 * ****************************************************
 * Analysis:
 * 我的想法是,int可以有32位,然后26个字母每个对应一位,每个串对应一个整数,两整数进行&&操作,等于零的话,就可以算长度的乘机了.
 * 然后用个priority队列(Reverse)放相乘之后的值.取第一个就好---->其实,通过一个Math.max就可以完成任务啦.
 * ****************************************************
 * 技术弱点:
 * 1.位操作:并 \,与 &
 * 2.位操作符优先级高于比较运算符
 * 3.PriorityQueue的排序:Collections.reverseOrder()
 */
public class No318_Maximum_Product_of_Word_Lengths {
    public int maxProduct(String[] words) {
        if (words == null || words.length == 0)
            return 0;
        int[] bits = new int[words.length];
//initialize Integers
        for (int i = 0; i < words.length; i++) {
            char[] chars = words[i].toCharArray();
            for (char c : chars) {
                bits[i] |= 1 << (c - 'a');
            }
        }

        //optimization:因为只取最大的值,所以不必使用queue,直接用比较即可.
//        PriorityQueue<Integer> queue = new PriorityQueue(Collections.reverseOrder());
//        queue.offer(0);
        //result:经此优化后,性能由beat19%-->89.9%
        int max = 0;
        for (int i = 0; i < bits.length; i++) {
            for (int j = i + 1; j < bits.length; j++) {
                if ((bits[i] & bits[j]) == 0) {
                    max = Math.max(max, words[i].length() * words[j].length());
                }
            }
        }
        return max;

    }
}
