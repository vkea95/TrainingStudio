package leetcode.com.hard.part2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tclresearchamerica on 7/27/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/word-search-ii/
 * ****************************************************
 * Description:
 * Given a 2D board and a list of words from the dictionary, find all words in the board.
 * <p>
 * Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those
 * horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
 * <p>
 * For example,
 * Given words = ["oath","pea","eat","rain"] and board =
 * <p>
 * [
 * ['o','a','a','n'],
 * ['e','t','a','e'],
 * ['i','h','k','r'],
 * ['i','f','l','v']
 * ]
 * Return ["eat","oath"].
 * <p>
 * ****************************************************
 * Thought:
 * 1.这个问题在于构造比价合适的数据结构,可以在有效的时间内,将单词search处理
 * 2.二叉树似乎可以,但是因为单词的匹配方式是上下左右,所以遍历方式就会有问题,不好使用啊
 * 3.并查集吗?
 * ****************************************************
 * Hindsight:
 * 1.依旧是图的问题!!!
 * ****************************************************
 * Ref: https://segmentfault.com/a/1190000005710651
 * 1.Trie + DFS
 * 复杂度
 * O(MN*4^K ) 时间 O(MN) 空间
 * K为word最大长度， M*N为board大小
 * 空间复杂度：用boolean[][]来存visited信息
 * 时间复杂度：对每个点都要作为起始点dfs，对于每个起始点，拓展一次有四个可能性(四个邻居)，要拓展k次(word最大长度为k)。
 * <p>
 * 思路
 * 要用trie，拿trie来dfs
 * 先建trie，然后在board里搜所有trie里的word
 * <p>
 * 递归函数接口:
 * <p>
 * public void hasPath(int x, int y,
 * char[][] board,
 * boolean[][] visited,
 * Trie trie,
 * StringBuilder sb,
 * List<String> result)
 * 满足的property：在进入hasPath的一刹那，1.(x,y)还没有访问；2.(x,y)是valid的而且还没有被访问过；3.此时的dfs快照是sb和visited
 * <p>
 * 注意
 * 尽管visited可以有多种实现方法，这一题用1，3都可以，用2就会超时：
 * <p>
 * boolean[ ] [ ] visited
 * HashSet<Integer> visited，用个小trick把二维坐标转化为一维
 * 二维转一维：(x,y) -> index : index = x * col + y
 * 一维转二维：index -> (x,y) : x = index / col; y = index % col;
 * 直接修改board数组，将访问过的格子改成特定字符比如 '#' 或者 '$'等
 * ****************************************************
 * Time: 120 mins
 * Beat: 40%
 * Bug: -
 * ****************************************************
 * Hindsight:
 * 题目很大很难,使用了trie树
 * ****************************************************
 * ****************************************************
 */
public class No212_Word_Search_II {
    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<String>();
        Trie trie = new Trie();
        boolean[][] visited = new boolean[board.length][board[0].length];
        //step 1: 为词典构筑Trie树
        for (String str : words)
            trie.insert(str);
        //step 1: 为词典构筑Trie树
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                //带着当前的string去explore(i,j)这个点
                hasPath(i, j, board, visited, trie, new StringBuilder(), result);
            }
        }
        return result;
    }

    //x,y是合法的，sb里存得也是合法的，(x,y)还没有explore
    public void hasPath(int x, int y, char[][] board, boolean[][] visited, Trie trie, StringBuilder sb, List<String> result) {
        //explore (x,y)
        sb.append(board[x][y]);
        //标记是否已经访问过的节点
        visited[x][y] = true;
        //does (x,y) make sense? do this only when it does
        if (trie.startsWith(sb.toString())) {
            if (trie.search(sb.toString())) {
                if (!result.contains(sb.toString()))
                    result.add(sb.toString());
            }
            //提前定义好坐标的偏移量
            int[] dirx = {0, 0, 1, -1};
            int[] diry = {1, -1, 0, 0};
            for (int i = 0; i < 4; i++) {
                int newx = x + dirx[i];
                int newy = y + diry[i];
                if (isValid(newx, newy, board) && !visited[newx][newy]) {
                    hasPath(newx, newy, board, visited, trie, sb, result);
                }
            }
        }
        //finished exploring (x,y),let's go back explore another one
        visited[x][y] = false;
        sb.deleteCharAt(sb.length() - 1);
    }

    public boolean isValid(int x, int y, char[][] board) {
        if (x >= 0 && x <= board.length - 1 && y >= 0 && y <= board[0].length - 1)
            return true;
        else
            return false;
    }
}

class Trie {
    private static final int R = 26;
    TrieNode root = new TrieNode();

    private static class TrieNode {
        private boolean isWord = false;
        private TrieNode[] children = new TrieNode[R];
    }

    public void insert(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            if (cur.children[word.charAt(i) - 'a'] == null) {
                cur.children[word.charAt(i) - 'a'] = new TrieNode();
            }
            cur = cur.children[word.charAt(i) - 'a'];
            if (i == word.length() - 1)
                cur.isWord = true;
        }
    }

    public boolean search(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            if (cur.children[word.charAt(i) - 'a'] == null)
                return false;
            else {
                if (i == word.length() - 1)
                    return cur.children[word.charAt(i) - 'a'].isWord;
                else {
                    cur = cur.children[word.charAt(i) - 'a'];
                }
            }
        }
        return false;
    }

    public boolean startsWith(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            if (cur.children[word.charAt(i) - 'a'] == null) {
                return false;
            }
            cur = cur.children[word.charAt(i) - 'a'];
        }
        return true;
    }

}