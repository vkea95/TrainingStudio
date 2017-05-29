package common.com.ds.tree;

/**
 * Created by tclresearchamerica on 4/22/16.
 */
public class Trie_II {
    TrieNode root;

    public Trie_II() {
        //初始化的时候,就已经设置好自己的trie树了
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int index = c - 'a';
            if (node.arr[index] == null) {
                TrieNode tmp = new TrieNode();
                node.arr[index] = tmp;
                node = tmp;
            } else {
                node = node.arr[index];
            }
        }
        node.isEnd = true;
    }

    public TrieNode searchNode(String s) {
        TrieNode p = root;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int index = c - 'a';
            if (p.arr[index] != null) {
                p = p.arr[index];
            } else {
                return null;
            }
        }

        if (p == root)
            return null;

        return p;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode p = searchNode(word);
        if (p != null && p.isEnd) {
            return true;
        } else {
            return false;
        }

    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode p = searchNode(prefix);
        if (p == null) {
            return false;
        } else {
            return true;
        }
    }

    class TrieNode {
        TrieNode[] arr;
        boolean isEnd;

        TrieNode() {
            //26个字母都准备好空间
            arr = new TrieNode[26];
        }
    }
}

