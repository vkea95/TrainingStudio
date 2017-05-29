package leetcode.com.medium.part21;

import leetcode.com.util.TreeNode;

/**
 * Created by tclresearchamerica on 4/20/16.
 * *****************************************************************************
 * Location:
 * https://leetcode.com/problems/add-and-search-word-data-structure-design/
 * *****************************************************************************
 * Description:
 * Design a data structure that supports the following two operations:
 * void addWord(word)
 * bool search(word)
 * search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.
 * For example:
 * addWord("bad")
 * addWord("dad")
 * addWord("mad")
 * search("pad") -> false
 * search("bad") -> true
 * search(".ad") -> true
 * search("b..") -> true
 * *****************************************************************************
 * Thought:
 * 1.基本思想是HashMap来解决问题,但是问题在于,如何应对.的问题,1个.还好,如果是多个的话,就很难说了啊
 * 2.那么我们现在用什么数据结构比较合适呢?想!
 * 3.根据查找的特定分析,用Tree这个结构是比较合适的,那么用哪种树呢?这个树要满足能够在平行的节点当中移动才可以啊!trie树!
 * *****************************************************************************
 * Hindsight:
 * 1.26个字符就是用有限数组来解决问题
 * 2.然后并没有用next这样的指针,只是不断地轮转判断是否为null
 * 3.给每个节点增加了isWord的flag
 * *****************************************************************************
 * *****************************************************************************
 * *****************************************************************************
 * *****************************************************************************
 * *****************************************************************************
 * Solution:
 */
public class No211_WordDictionary {

}

class WordDictionary {
    TrieWordNode root = new TrieWordNode();

    // Adds a word into the data structure.
    public void addWord(String word) {
        TrieWordNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int idx = ch - 'a';
            //判断是否有下级字符
            if (node.children[idx] == null) node.children[idx] = new TrieWordNode(ch);
            node = node.children[ch - 'a'];
        }
        node.isWord = true;
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        return helper(word, 0, root);
    }

    private boolean helper(String word, int pos, TrieWordNode node) {
        if (pos == word.length()) return node.isWord;
        char ch = word.charAt(pos);
        int idx = ch - 'a';
        if (ch != '.') return node.children[idx] != null && helper(word, pos + 1, node.children[idx]);
        else {
            for (int i = 0; i < 26; i++) {
                if (node.children[i] != null && helper(word, pos + 1, node.children[i])) return true;
            }
        }
        return false;

    }
}

class TrieWordNode {
    char val;
    //bug1:因为单词长度不一致,所以需要这样的一个标志
    boolean isWord;
    TrieWordNode[] children;

    public TrieWordNode() {
        children = new TrieWordNode[26];
    }

    TrieWordNode(char c) {
        children = new TrieWordNode[26];
        val = c;
    }
}

// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");