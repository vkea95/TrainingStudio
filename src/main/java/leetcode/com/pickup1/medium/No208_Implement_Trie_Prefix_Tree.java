package leetcode.com.pickup1.medium;

/**
 * Created by jason on 2016/7/28.
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * Time:20 mins
 * Beat: 44%
 * Bug: 1
 * ****************************************************
 * Hindsight:
 * 早晨刚刚做完类似的题目，所以概念流程都还记得，没有问题
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No208_Implement_Trie_Prefix_Tree {

}
class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode curt = root;

        for (int i=0;i<word.length();i++){
            char c=word.charAt(i);
            if (curt.children[c-'a']==null){
                curt.children[c-'a']=new TrieNode();
            }
            //bug1:isWord的标识打错了位置，考虑下就明白了，root是不能做为任何字符存在的
            curt=curt.children[c-'a'];
            if (i==word.length()-1){
                curt.isWord=true;
            }
        }
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode curt = root;
        for (int i=0;i<word.length();i++){
            char c=word.charAt(i);
            if (curt.children[c-'a']==null){
                return false;
            }
            if (i==word.length()-1){
                return curt.children[c-'a'].isWord;
            }


            curt=curt.children[c-'a'];
        }
        return false;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {

        TrieNode curt = root;
        for (int i=0;i<prefix.length();i++){
            char c=prefix.charAt(i);
            if (curt.children[c-'a']==null){
                return false;
            }

            curt=curt.children[c-'a'];
        }
        return true;
    }
}

class TrieNode {
    // Initialize your data structure here.
    public TrieNode[] children ;
    public boolean isWord;
    public TrieNode() {
        children = new TrieNode[26];
        isWord=false;
    }
}
