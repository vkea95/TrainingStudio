package leetcode.com.pickup1.medium;

import java.util.Map;

/**
 * Created by tclresearchamerica on 8/6/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/maximum-product-of-word-lengths/
 * ****************************************************
 * Description:
 * Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the two words do not
 * share common letters. You may assume that each word will contain only lower case letters. If no such two words exist,
 * return 0.
 * <p>
 * Example 1:
 * Given ["abcw", "baz", "foo", "bar", "xtfn", "abcdef"]
 * Return 16
 * The two words can be "abcw", "xtfn".
 * <p>
 * Example 2:
 * Given ["a", "ab", "abc", "d", "cd", "bcd", "abcd"]
 * Return 4
 * The two words can be "ab", "cd".
 * <p>
 * Example 3:
 * Given ["a", "aa", "aaa", "aaaa"]
 * Return 0
 * No such pair of words.
 * ****************************************************
 * Thought:
 * 1.可以在一起进行相乘的2个单词的要求是他们的字符没有交集
 * 2.图?不该是DP
 * 3.DS:HashMap?
 * 4.参照第一版的答案:
 * 4.1也就是我只需要知道每个单词中含有哪些字符,而不管含有多少,
 * 4.2不同单词间的交集该是空集
 * 4.3int有32位可以囊括所有的26个字符
 * ****************************************************
 * Time:-
 * Beat:90%
 * Bug:-
 * ****************************************************
 * Hindsight:
 * 这道题还是将图的问题,化简到一个数字的关系啦,或者说是几个数字之间的问题
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No318_Maximum_Product_of_Word_Lengths {
    public int maxProduct(String[] words) {
        if (words == null || words.length < 2) return 0;
        int result = 0;

        int[] bits = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            for (char c : words[i].toCharArray()) {
                bits[i] |= (1 << (c - 'a'));
            }
        }

        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if ((bits[i] & bits[j]) == 0) {

                    result = Math.max(result, words[i].length() * words[j].length());
                }
            }
        }

        return result;
    }
}
