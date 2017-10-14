package leetcode.com.tag.trie;

/**
 * Created by JianZhang on 10/13/17.
 * Implement a trie with insert, search, and startsWith methods.
 * Solutions:
 * 1. 要将trie想象成一张图,基本ds就是一个节点(相当去指针?)
 * 2. 遍历方式是dfs
 */
public class No208_Implement_Trie {

    private class TrieNode {
        private TrieNode[] children;
        private boolean isWord;

        public TrieNode() {
            children = new TrieNode[26];
        }
    }


    private class Trie {

        private TrieNode root;

        /**
         * Initialize your data structure here.
         */
        public Trie() {
            root = new TrieNode();
        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            if (word == null) return;
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (node.children[index] == null) {
                    node.children[index] = new TrieNode();
                }
                node = node.children[index];
            }
            node.isWord = true;
        }

        private TrieNode searchNode(TrieNode root, String word) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (node.children[index] == null) return null;
                node = node.children[index];
            }
            return node;
        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
//            return dfsSearch(root, word, 0);
            TrieNode node = searchNode(root, word);
            return node == null ? false : node.isWord;
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            TrieNode node = searchNode(root, prefix);
            return node != null;
        }

        //        DFS遍历,每次传递当前的index的开始位置和整个单词
        private boolean dfsSearch(TrieNode node, String word, int start) {

//        DFS遍历,根据length来判断是否到头了
            if (node.isWord && start == word.length()) {
                return true;
            }

            if (start >= word.length()) {
                return false;
            }
            char c = word.charAt(start);
            if (c == '.') {
                boolean bRst = false;
                for (int j = 0; j < 26; j++) {
                    if (node.children[j] != null) {
                        if (dfsSearch(node.children[j], word, start + 1)) {
                            bRst = true;
                            break;
                        }
                    }
                }
                if (bRst)
                    return true;
            } else {
                int index = c - 'a';
                if (node.children[index] != null) {
                    return dfsSearch(node.children[index], word, start + 1);
                } else {
                    return false;
                }
            }
            return false;

        }

    }

}
