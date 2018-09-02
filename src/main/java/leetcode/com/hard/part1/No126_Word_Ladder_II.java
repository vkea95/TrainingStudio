package leetcode.com.hard.part1;

import java.util.*;

/**
 * Created by tclresearchamerica on 5/25/16.
 * ***************************************************************
 * Location:
 * https://leetcode.com/problems/word-ladder-ii/
 * ***************************************************************
 * Description:Given two words (beginWord and endWord), and a dictionary's word indexList, find all shortest
 * transformation sequence(s) from beginWord to endWord, such that:
 * <p>
 * Only one letter can be changed at a time
 * Each intermediate word must exist in the word indexList
 * For example,
 * <p>
 * Given:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * Return
 * [
 * ["hit","hot","dot","dog","cog"],
 * ["hit","hot","lot","log","cog"]
 * ]
 * Note:
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * ***************************************************************
 * Analysis:
 * 如何拆分这个数组呢? 或者说要找到和一个单词相似度最高的单词列表
 * ***************************************************************
 * Solution:
 * 网络答案:
 * 使用BFS先构建了一张图,
 * 然后再用DFS的递归方式构建出各个路径
 * ***************************************************************
 * 技术点:
 * 1.Set的删除方法,可以有removeAll
 * 2.使用BFS的过程中,根据set的尺寸大小调整,之间的相互关系,进而优化性能
 * ***************************************************************
 * Thoughts:
 * 1.这个题目难度超大,面试中出现的可能性很低,但是BFS,DFS的实践思想,值得借鉴
 * ***************************************************************
 * ***************************************************************
 */
public class No126_Word_Ladder_II {


    boolean isConnected = false;

    public List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {

        //we use bi-dirctional BFS to find shortest path
        Set<String> fwd = new HashSet<>();
        fwd.add(beginWord);

        Set<String> bwd = new HashSet<>();
        bwd.add(endWord);


        Map<String, List<String>> hs = new HashMap<>();
        BFS(fwd, bwd, wordList, false, hs);

        List<List<String>> result = new ArrayList<>();

        //if two parts can not be connected, then return empty indexList
        if (!isConnected) return result;

        //we need to add start node to temp indexList as there is no other node can get start node
        List<String> temp = new ArrayList<>();
        temp.add(beginWord);

        DFS(result, temp, beginWord, endWord, hs);

        return result;


    }

    //将预处理的对象，传入BFS方法。
    public void BFS(Set<String> forward, Set<String> backward, Set<String> dict, boolean swap, Map<String, List<String>> hs) {
        //boundary check
        if (forward.isEmpty() || backward.isEmpty()) {
            return;
        }

        //we always do BFS on direction with less nodes
        //here we assume forward set has less nodes, if not, we swap them
        //这个方法好，动态调整后优化处理效果
        if (forward.size() > backward.size()) {
            BFS(backward, forward, dict, !swap, hs);
            //bug2:动态调整后，需要直接返回，否则会导致重复的结果出现
            return;
        }

        //remove all forward/backward from dict to avoid dupNumber addition
        //new technology:hashmap 可以删除指定的set的数据集
        //bug1:要删除所有的元素，并不是一个：remove-->removeAll
        dict.removeAll(forward);
        dict.removeAll(backward);

        //new set contains all new nodes from forward set
        Set<String> newSet = new HashSet();

        //do BFS on every node of forward direction
        for (String str : forward) {
            //try to change each char of str
            for (int i = 0; i < str.length(); i++) {
                //try to replace current char with every chars from a to z
                char[] array = str.toCharArray();
                for (char j = 'a'; j <= 'z'; j++) {
                    array[i] = j;
                    String temp = new String(array);

                    //we skip this string if it is not in dict nor in backward
                    if (!backward.contains(temp) && !dict.contains(temp)) {
                        continue;
                    }


                    //we follow forward direction
                    String key = !swap ? str : temp;
                    String val = !swap ? temp : str;

                    if (!hs.containsKey(key))
                        hs.put(key, new ArrayList<String>());

                    //if temp string in backward set, then it will connect two parts
                    if (backward.contains(temp)) {
                        hs.get(key).add(val);
                        isConnected = true;
                    }

                    //if temp is in dict, then we can add it to newSet as new nodes in next layer
                    if (!isConnected && dict.contains(temp)) {
                        hs.get(key).add(val);
                        newSet.add(temp);
                    }
                }
            }
        }
        //to force our path to be shortest, we will not do BFS if we have found shortest paht(isConnected=true)
        if (!isConnected) {
            BFS(newSet, backward, dict, swap, hs);
        }

    }

    public void DFS(List<List<String>> result, List<String> temp, String start, String end, Map<String, List<String>> hs) {
        //we will use DepthFirstSearch, more specifically backtracking to build paths.

        //boundary case
        if (start.equals(end)) {
            result.add(new ArrayList<String>(temp));
            return;
        }

        // not each node in hs is valid node in shortest path, if we found current node does not have children node
        //then it mean it's not in shortest path
        if (!hs.containsKey(start)) {
            return;
        }

        for (String s : hs.get(start)) {
            temp.add(s);
            DFS(result, temp, s, end, hs);
            temp.remove(temp.size() - 1);
        }

    }
}
