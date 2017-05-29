package leetcode.com.pickup1.medium;

import java.util.*;

/**
 * Created by tclresearchamerica on 8/12/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/word-ladder/
 * ****************************************************
 * Description:
 * Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation
 * sequence from beginWord to endWord, such that:
 * <p>
 * Only one letter can be changed at a time
 * Each intermediate word must exist in the word list
 * For example,
 * <p>
 * Given:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * return its length 5.
 * ****************************************************
 * Thought:
 * 1.就是用一个字符数组不断地替换目标单词,然后和wordlist中的单词比较,
 * 2.所以需要构筑一张图
 * 3.BFS
 * ****************************************************
 * Time:
 * Beat:
 * Bug:
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No127_Word_Ladder {
    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {

        int result = 0;


        Set<String> set = getNextWord(beginWord, wordList);
        result++;
        while (!set.isEmpty()) {
            if (set.contains(endWord)) break;

        }

        return result;

    }

    private Set<String> getNextWord(String beginWord, Set<String> wordList) {
        Set<String> result = new HashSet<>();
        for (char c = 'a'; c <= 'z'; c++) {
            char[] chars = beginWord.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == c) continue;
                chars[i] = c;
                String nextWord = new String(chars);
                if (wordList.contains(nextWord)) {
                    result.add(nextWord);
                    wordList.remove(nextWord);
                }
            }
        }
        return result;
    }
}
