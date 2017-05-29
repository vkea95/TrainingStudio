package leetcode.com.pickup1.medium;

import java.util.Stack;

/**
 * Created by tclresearchamerica on 8/14/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/add-and-search-word-data-structure-design/
 * ****************************************************
 * Description:
 * Design a data structure that supports the following two operations:
 * <p>
 * void addWord(word)
 * bool search(word)
 * search(word) can search a literal word or a regular expression string containing only letters a-z or .. A .
 * means it can represent any one letter.
 * For example:
 * <p>
 * addWord("bad")
 * addWord("dad")
 * addWord("mad")
 * search("pad") -> false
 * search("bad") -> true
 * search(".ad") -> true
 * search("b..") -> true
 * <p>
 * ****************************************************
 * Thought:
 * 1.感觉这个是trie tree的节奏啊
 * 2.没有处理.的这个情况
 * ****************************************************
 * Time:30 mins
 * Beat: 39%
 * Bug: 3
 * ****************************************************
 * Hindsight:
 * 1.在处理单词的结尾字符该属于哪一层上的时候,出现了问题。root的这一层不会出现isWord为true的,tai该在下一层出现
 * 2.应该画出树形结构图来,就能够证明它是正确的了,问题就是出现在这里,当然.的处理但是也有考虑,增加了.所以就要考虑用递归来实现search功能
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No211_Add_and_Search_Word {
    public static void main(String[] args) {
        WordDictionary obj = new WordDictionary();

        obj.addWord("ab");
        System.out.println(obj.search("a."));
//        obj.addWord("a");
//        obj.addWord("a");
//        System.out.println(obj.search("."));
//        System.out.println(obj.search("a"));
//        System.out.println(obj.search("aa"));
//        System.out.println(obj.search("a"));
//        System.out.println(obj.search(".a"));
//        System.out.println(obj.search("a."));
    }
}


class WordDictionary {
    TrieDicNode root;

    WordDictionary() {
        root = new TrieDicNode();
    }

    // Adds a word into the data structure.
    public void addWord(String word) {
        TrieDicNode temp = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (temp.children[index] == null) {
                temp.children[index] = new TrieDicNode();
            }
            //bug2:temp的赋值操作该是对所有情况而言
            temp = temp.children[index];
        }
        temp.isWord = true;
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        return helper(root, word, 0);
    }

    private boolean helper(TrieDicNode temp, String word, int pos) {
        if (temp == null) return false;
        //bug3:pos的判断条件和isWord的配合使用出了问题,isWord该赋值到哪里
        if (pos==word.length()) return temp.isWord;
        if ('.' == word.charAt(pos)) {
            for (TrieDicNode node : temp.children) {
                if (helper(node, word, pos + 1)) {
                    return true;
                }
            }
            return false;
        } else {
            int index = word.charAt(pos) - 'a';
            return helper(temp.children[index], word, pos + 1);

        }
    }
}

class TrieDicNode {
    TrieDicNode[] children;
    boolean isWord;

    TrieDicNode() {
        children = new TrieDicNode[26];
    }
}