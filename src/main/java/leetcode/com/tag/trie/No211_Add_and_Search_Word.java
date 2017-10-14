package leetcode.com.tag.trie;

/**
 * Created by JianZhang on 10/13/17.
 * Design a data structure that supports the following two operations:
 * <p>
 * void addWord(word)
 * bool search(word)
 * search(word) can search a literal word or a regular expression string containing only letters a-z or ..
 * A . means it can represent any one letter.
 * <p>
 * For example:
 * <p>
 * addWord("bad")
 * addWord("dad")
 * addWord("mad")
 * search("pad") -> false
 * search("bad") -> true
 * search(".ad") -> true
 * search("b..") -> true
 */
public class No211_Add_and_Search_Word {

    class WordDictionary {
        private TrieWordNode root = new TrieWordNode();

        public void addWord(String word) {
            TrieWordNode node = root;
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (node.children[index] == null) node.children[index] = new TrieWordNode();
                node = node.children[index];

            }
            node.isWord = true;
        }

        public boolean search(String word) {
            return dfsSearch(word, 0, root);
        }

        //此处使用了递归处理
        private boolean dfsSearch(String word, int pos, TrieWordNode node) {
            if (pos == word.length()) return node.isWord;
            char c = word.charAt(pos);
            if (c == '.') {
                //通配符进行处理
                for (TrieWordNode child : node.children) {
                    if (child != null && dfsSearch(word, pos + 1, child)) return true;
                }
            } else {
                int index = c - 'a';
                return node.children[index] != null && dfsSearch(word, pos + 1, node.children[index]);
            }
            return false;
        }
    }

    private class TrieWordNode {
        private boolean isWord;
        public TrieWordNode[] children;

        public TrieWordNode() {
            children = new TrieWordNode[26];
        }
    }
}
