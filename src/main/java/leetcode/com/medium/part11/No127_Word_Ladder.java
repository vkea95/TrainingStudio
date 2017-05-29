package leetcode.com.medium.part11;

import java.util.*;

/**
 * Created by jason on 2016/3/19.
 * Location:
 * https://leetcode.com/problems/word-ladder/
 * ***********************************************
 * Description:
 * Given two words (beginWord and endWord), and a dictionary's word list,
 * find the length of shortest transformation sequence from beginWord to endWord, such that:
 * Only one letter can be changed at a time
 * Each intermediate word must exist in the word list
 * For example,
 * Given:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * return its length 5.
 * Note:
 * Return 0 if there is no such transformation sequence.
 * All words have the same length.
 * *************************************************
 * Solution:
 * g过程略显复杂，原理：将start字符放入队列和hashSet（去重）。记录当前状态（即队列长度），
 * 然后，对当前状态下的字符串的每个字符进行全变换，存到一个临时list中，试图将list中的word放入hashSet和队列中，
 * 前提条件是，hashset中不存在，或者是endword，然后记录队列状态（length），再做一遍同样的处理，
 * 因为hashSet有去重功能，所以保证最先完成匹配的一定是length最短的。。。
 * *************************************************
 * Tips：
 * 1.char的变量也可以进行for循环，并++处理
 * 2.char【】 可转换成String，通过new String(char)
 * *************************************************
 * Beat:6%
 * *************************************************
 * *************************************************
 */
public class No127_Word_Ladder {

    //replace character of a string at given index to a given character
    //return a new string
    private String replace(String s, int index, char c) {
        char[] chars = s.toCharArray();
        chars[index] = c;
        return new String(chars);
    }

    //get connections with given word
    //for example, given word = 'hot', dict={'hot', 'hit', 'hog'}
    //it will return ['hit', 'hot'
    private List<String> getNextWords(String word, Set<String> dict) {
        List<String> nextWords = new ArrayList<>();
        for (char c = 'a'; c <= 'z'; c++) {
            for (int i = 0; i < word.length(); i++) {
                if (c == word.charAt(i)) {
                    continue;
                }
                String nextWord = replace(word, i, c);
                if (dict.contains(nextWord)) {
                    nextWords.add(nextWord);
                }
            }
        }

        return nextWords;
    }


    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
        int result = 0;

        if (wordList == null || wordList.size() == 0) return result;

        wordList.add(beginWord);
        wordList.add(endWord);

        HashSet<String> hash = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        queue.offer(beginWord);
        hash.add(beginWord);

        result = 1;
        while (!queue.isEmpty()) {

            result++;

            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                for (String nextWord : getNextWords(word, wordList)) {
                    if (hash.contains(nextWord)) continue;
                    if (nextWord.equals(endWord)) {
                        return result;
                    }
                    hash.add(nextWord);
                    queue.offer(nextWord);
                }

            }

        }

        return 0;
    }
}
