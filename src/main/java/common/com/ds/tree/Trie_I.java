package common.com.ds.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tclresearchamerica on 4/22/16.
 */
public class Trie_I {
    private TrieNode root;

    public Trie_I(){
        root=new TrieNode();
    }

    //insert a word into the trie
    public void insert(String word) {
        HashMap<Character, TrieNode> children = root.children;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            TrieNode node;
            if (children.containsKey(c)) {
                node = children.get(c);
            } else {
                node = new TrieNode(c);
                children.put(c, node);
            }
            children = node.children;

            //set leaf node
            if (i == word.length() - 1) {
                //question: how about "to" & "too" : 区分是否为单词,也就是这条路径上的多个节点都可设置成true
                //标识这个单词被输入过,否则就看不到这些信息了
                node.isLeaf = true;
            }
        }
    }

    public TrieNode searchNode(String word) {
        Map<Character, TrieNode> children = root.children;
        TrieNode node = null;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (children.containsKey(c)) {
                node = children.get(c);
                children = node.children;
            } else {
                return null;
            }
        }
        return node;

    }

    //Return if the word in the trie
    public boolean search(String word) {
        TrieNode node = searchNode(word);
        if (node != null && node.isLeaf) {
            return true;
        } else {
            return false;
        }
    }

    //return if there is any word in the trie
    //that start with the given prefix
    public boolean startsWith(String prefix) {
        TrieNode node = searchNode(prefix);
        if (node != null) {
            return true;
        } else {
            return false;
        }

    }

    class TrieNode {
        char c;
        //使用HashMap表明每个节点的孩子是多个,这也是正常的理解
        HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
        boolean isLeaf;

        public TrieNode() {
        }


        public TrieNode(char c) {
            this.c = c;
        }
    }
}
