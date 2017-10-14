package leetcode.com.tag.trie;

/**
 * Created by JianZhang on 10/13/17.
 * Implement a trie with insert, search, and startsWith methods.
 * Solutions:1
 */
public class No208_Implement_Trie_wrong {

    private class Trie {

        private Trie[] chars;
        private boolean wordFlg;

        /**
         * Initialize your data structure here.
         */
        public Trie() {
            chars = new Trie[26];
        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            if (word == null) return;
            int index = word.charAt(0) - 'a';
            if (word.length() == 0) {
                this.wordFlg = true;
            } else {
                if (chars[index] == null) {
                    chars[index] = new Trie();
                }
                chars[index].insert(word.substring(1));
            }
        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            if (word == null) return false;
            int index = word.charAt(0);
            if (chars[index] == null) return false;
            else if (word.length() == 0) {
                return this.wordFlg;
            } else return chars[index].search(word.substring(1));
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            if (prefix == null) return false;
            int index = prefix.charAt(0);
            if (chars[index] == null) return false;
            else return chars[index].search(prefix.substring(1));
        }
    }

}
