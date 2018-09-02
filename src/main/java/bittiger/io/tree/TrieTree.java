package bittiger.io.tree;

public class TrieTree {
    TrieNode root;

    public TrieTree() {
        root = new TrieNode();
    }

    public boolean search(String word) {
        TrieNode result = match(root, word.toCharArray(), 0);
        return result != null && result.isWord;
    }

    public boolean startsWith(String prefix) {
        return match(root, prefix.toCharArray(), 0) != null;
    }

    private TrieNode match(TrieNode curtNode, char[] chars, int depth) {
        if (curtNode == null || chars.length == depth) {
            return curtNode;
        }
        return match(curtNode.children[chars[depth] - 'a'], chars, depth + 1);

    }

    public void insert(String word) {
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


}

class TrieNode {
    TrieNode[] children;
    boolean isWord;

    public TrieNode() {
        children = new TrieNode[26];
    }

}