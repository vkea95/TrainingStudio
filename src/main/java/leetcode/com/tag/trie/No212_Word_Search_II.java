package leetcode.com.tag.trie;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by JianZhang on 10/14/17.
 * Given a 2D board and a indexList of words from the dictionary, find all words in the board.
 * Each word must be constructed from letters of sequentially adjacent cell,
 * where "adjacent" cells are those horizontally or vertically neighboring.
 * The same letter cell may not be used more than once in a word.
 * For example,
 * Given words = ["oath","pea","eat","rain"] and board =
 * [
 * ['o','a','a','n'],
 * ['e','t','a','e'],
 * ['i','h','k','r'],
 * ['i','f','l','v']
 * ]
 * Return ["eat","oath"].
 * Solutions:
 * 1. 如何避免过高的复杂度呢? 如果走光每个boar的组合,则需要n*m 时间复杂度,若是按照单词来搞,也很费时
 * 2. 原来想的是如何把遍历过的board的单词记下来,防止再duplicate操作==>没思路
 * 3. 看了答案,才意识到用Trie为wordList建立结构,再结合startsWith和searchWord,来完成操作。用复杂的Ds封装,通过简单的判断完成操作
 * 4. 因为需要去重,所以采用了Set这个集合
 * Bugs:
 * 1. 因为board中的字符会被替换为#,所以需要在原来的判断条件中特殊处理下,复杂会出现异常exception
 */
public class No212_Word_Search_II {
    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        Set<String> resultSet = new HashSet<>();
        Trie trie = new Trie();
        for (String word : words) trie.insertWord(word);
        for (int i = 0; i <= board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfsSearch(board, trie, "", i, j, resultSet);
            }
        }
        for (String s : resultSet) {
            result.add(s);
        }
        return result;
    }

    private boolean dfsSearch(char[][] board, Trie trie, String subString, int row, int col, Set<String> resultSet) {
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length||board[row][col]=='#') return false;
        char c = board[row][col];
        subString += c;
        if (!trie.startsWith(subString)) return false;
        if (trie.searchWord(subString)) resultSet.add(subString);
        board[row][col] = '#';
        boolean result = dfsSearch(board, trie, subString, row + 1, col, resultSet)
                || dfsSearch(board, trie, subString, row - 1, col, resultSet)
                || dfsSearch(board, trie, subString, row, col + 1, resultSet)
                || dfsSearch(board, trie, subString, row, col - 1, resultSet);
        board[row][col] = c;
        return result;
    }


    class Trie {
        public TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void insertWord(String word) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (node.children[index] == null) node.children[index] = new TrieNode();
                node = node.children[index];
            }
            node.isWord = true;
        }

        private TrieNode dfsSearch(TrieNode root, String word) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (node.children[index] == null) return null;
                node = node.children[index];
            }
            return node;
        }

        public boolean startsWith(String word) {
            return dfsSearch(root, word) != null;
        }

        public boolean searchWord(String word) {
            TrieNode node = dfsSearch(root, word);
            return node == null ? false : node.isWord;
        }
    }

    class TrieNode {
        public boolean isWord;
        TrieNode[] children;

        public TrieNode() {
            children = new TrieNode[26];
        }
    }
}
