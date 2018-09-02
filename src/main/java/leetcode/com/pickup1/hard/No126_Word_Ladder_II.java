package leetcode.com.pickup1.hard;

import java.util.*;

/**
 * Created by tclresearchamerica on 8/3/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/word-ladder-ii/
 * ****************************************************
 * Description:
 * Given two words (beginWord and endWord), and a dictionary's word indexList, find all shortest transformation
 * sequence(s) from beginWord to endWord, such that:
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
 * ****************************************************
 * Thoughts:
 * 1.recursive,不断替换,再循环,再替换的过程-->时间复杂度太高了,因为每个字母都替换一遍的话,相当于遍历了图的全部路径,
 * 会导致时间超时-->错误思想源于没有画图实现
 * 2.根据来自https://segmentfault.com/a/1190000002567124的解释,还是先要构筑一个根据BFS的达成的树
 * <p>
 * ****************************************************
 * Hindsight:
 * I
 * 1.题目依然不能够在当时给出答案,Bobo给了一个略简单的答案,即对单词建立level的概念
 * 2.09/17/16 在level的那个解法中,DFS是逆向开始遍历的,从endword开始,一旦endword和newword的level差为,
 * 则继续DFS递归调用
 * 3.退出递归的条件就是循环完毕所有的备选节点,或是找到答案。
 * 4.这个level的做法需要注意的是,在将备选答案集放入list中的时候,需要进行一次reverse,否则会出现逆序的问题
 * Collections.reverse(l)即可
 * II
 * 1.那个快速的方法,是快速构建BFS集合,然后再用DFS进行遍历完毕才可以的
 * <p>
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No126_Word_Ladder_II {


    public List<List<String>> findLadders_prac(String beginWord, String endWord, Set<String> wordList) {
        //最短路径的话,还是要考虑BFS优先的好, 其实要处理的
        //问题:
        // 1.避免单词回指到前面的单词去,所以需要从这个备选的列表中剔除这个词
        // 2.
        //module: 需要一个判断方法,判断当前单词的邻居是否在列表中,
        //顺序:1.建图,一旦发现目标单词存在,是否直接退出呢?

        Set<String> fwd = new HashSet<>();
        fwd.add(beginWord);
        Set<String> bwd = new HashSet<>();
        bwd.add(endWord);

        Map<String, List<String>> hs = new HashMap<>();
        boolean[] connected = new boolean[1];
        setupBFS(fwd, bwd, wordList, false, hs, connected);

        List<List<String>> result = new ArrayList<>();
        //如果没有联系的话,直接返回空集就好
        if (!connected[0]) return result;

        List<String> solution = new ArrayList<>();
        solution.add(beginWord);

        traverseDFS(result, solution, beginWord, endWord, hs);

        return result;
    }

    private void setupBFS(Set<String> forward,
                          Set<String> backward,
                          Set<String> dict,
                          boolean swap,
                          Map<String, List<String>> hs,
                          boolean[] connected) {
        //boundary check
        if (forward.isEmpty() || backward.isEmpty()) return;

        //opt2:adjust the order of the set
        if (forward.size() > backward.size()) {
            setupBFS(backward, forward, dict, !swap, hs, connected);
            //bug2:图形逆转后,直接返回就可以了,否则结果会错误的
            return;
        }
        //remove all forward/backward from dict to avoid dupNumber addition
        //opt1:delete set from set
        dict.removeAll(forward);
        dict.removeAll(backward);

        //setup next forward
        Set<String> newSet = new HashSet<>();

        //do BFS for every node in forward
        for (String s : forward) {
            for (int i = 0; i < s.length(); i++) {
                //try to setup new word with changing only one letter
                char[] chars = s.toCharArray();
                for (char j = 'a'; j <= 'z'; j++) {
                    chars[i] = j;
                    String temp = new String(chars);
                    //we skip this string if it is not in dict nor in backward
                    //bug1:因为forward已经被处理过了,而只有dict和backwards需要比较
                    if (!backward.contains(temp) && !dict.contains(temp)) {
                        continue;
                    }
                    //we follow the forward direction
                    //因为forward&backward可能会调换位置,所以key-value会需要变更,但无论如何,
                    String key = !swap ? s : temp;
                    String value = !swap ? temp : s;
                    if (!hs.containsKey(key)) {
                        hs.put(key, new ArrayList<>());
                    }
                    hs.get(key).add(value);
                    //if temp string is in backward set, then it will connect 2 pars;
                    if (backward.contains(temp)) connected[0] = true;
                    if (!connected[0] && dict.contains(temp)) {
                        //此处需要重新构造forward set 所有将字符串加入new set中
                        newSet.add(temp);
                    }
                }


            }
        }

        if (!connected[0]) {
            setupBFS(newSet, backward, dict, swap, hs, connected);
        }
    }


    public void traverseDFS(List<List<String>> result,
                            List<String> solution,
                            String start,
                            String end,
                            Map<String, List<String>> hs) {

        if (start.equals(end)) {
            result.add(new ArrayList<>(solution));
        }
        // not each node in hs is valid node in shortest path, if we found current node does not have children node
        //then it mean it's not in shortest path
        if (!hs.containsKey(start)) {
            return;
        }

        for (String s : hs.get(start)) {
            solution.add(s);
            traverseDFS(result, solution, s, end, hs);
            //bug1:此处删除最后一个元素应该
            solution.remove(solution.size() - 1);
        }
    }    //最短路径,所以这个适用于图的解法,DepthFirstSearch? BFS?


    boolean isConnected = false;

    public List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {

        //we use bi-dirctional BFS to find shortest path
        Set<String> fwd = new HashSet<>();
        fwd.add(beginWord);

        Set<String> bwd = new HashSet<>();
        bwd.add(endWord);

        Map<String, List<String>> hs = new HashMap<>();
        createBFS(fwd, bwd, wordList, false, hs);

        List<List<String>> result = new ArrayList<>();

        //if two parts can not be connected, then return empty indexList
        if (!isConnected) return result;

        //we need to add start node to temp indexList as there is no other node can get start node
        List<String> temp = new ArrayList<>();
        temp.add(beginWord);

        DFS(result, temp, beginWord, endWord, hs);

        return result;
    }


    //prepare for the graph
    public void createBFS(Set<String> forward, Set<String> backward, Set<String> dict, boolean swap, Map<String, List<String>> hs) {

        //boundary check
        if (forward.isEmpty() || backward.isEmpty()) return;

        //we always do BFS on direction with less nodes
        //here we assume forward set has less nodes, if not, we swap them
        //这个方法好，动态调整后优化处理效果
        if (forward.size() > backward.size()) {
            createBFS(backward, forward, dict, !swap, hs);
            return;
        }

        //remove all forward/backward from dict to avoid dupNumber addition
        //new technology:hashmap 可以删除指定的set的数据集
        //bug1:要删除所有的元素，并不是一个：remove-->removeAll
        dict.removeAll(forward);
        dict.removeAll(backward);

        //new set contains all new nodes from forward set
        Set<String> newSet = new HashSet<>();

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
                    //bug1:因为forward已经被处理过了,而只有dict和backwards需要比较
                    if (!backward.contains(temp) && !dict.contains(temp)) {
                        continue;
                    }

                    //we follow the forward direction
                    //因为forward&backward可能会调换位置,所以key-value会需要变更,但无论如何,
                    String key = !swap ? str : temp;
                    String value = !swap ? temp : str;

                    if (!hs.containsKey(key)) {
                        hs.put(key, new ArrayList<>());
                    }
                    hs.get(key).add(value);
                    //if temp string in backward set, then it will connect two parts
                    if (backward.contains(temp)) {
                        isConnected = true;
                    }

                    //if temp is in dict, then we can add it to newSet as new nodes in next layer
                    if (!isConnected && dict.contains(temp)) {
                        //!!!!此处是newSet,因为str来自于旧的forward集合,所以构造出来的temp肯定要放在newSet中再次循环使用
                        newSet.add(temp);
                    }
                }

            }

        }
        //to force our path to be shortest, we will not do BFS if we have found shortest paht(isConnected=true)
        if (!isConnected) {
            createBFS(newSet, backward, dict, swap, hs);
        }
    }


    //标准的DFS 递归调用了
    private void DFS(List<List<String>> result, List<String> solution, String start, String end, Map<String, List<String>> hs) {
        //we will use DepthFirstSearch, more specifically backtracking to build paths.
        //有可能是LeetCode的测试case 从前疏后密集
        //boundary case
        if (start.equals(end)) {
            result.add(new ArrayList<>(solution));
            return;
        }

        // not each node in hs is valid node in shortest path, if we found current node does not have children node
        //then it mean it's not in shortest path
        if (!hs.containsKey(start)) {
            return;
        }

        for (String s : hs.get(start)) {
            solution.add(s);
            //bug2:此处需要使用新的start了
            DFS(result, solution, s, end, hs);
            solution.remove(solution.size() - 1);
        }

    }


    public List<List<String>> findLadders_slow(String beginWord, String endWord, Set<String> wordList) {
        HashMap<String, Integer> distMap = new HashMap<String, Integer>();
        List<List<String>> re = new ArrayList<List<String>>();
        getDist(beginWord, endWord, wordList, distMap);
        List<String> solution = new ArrayList();
        solution.add(endWord);
        dfs(beginWord, endWord, distMap, re, solution);
        System.out.println(re.toString());
        return re;
    }

    public void dfs(String beginWord, String endWord, HashMap<String, Integer> distMap, List<List<String>> re, List<String> cur) {
        if (endWord.equals(beginWord)) {
            ArrayList<String> l = new ArrayList<>(cur);
            // l.add(endWord);

            Collections.reverse(l);
            re.add(l);


            return;
        }

        for (int i = 0; i < endWord.length(); i++) {
            char[] chars = endWord.toCharArray();
            for (char c = 'a'; c <= 'z'; c++) {
                chars[i] = c;
                String newStr = new String(chars);
                //opt:此处通过level的减法,判断两个单词是否相邻
                if (distMap.containsKey(newStr) && distMap.get(endWord) - distMap.get(newStr) == 1) {
                    cur.add(newStr);
                    dfs(beginWord, newStr, distMap, re, cur);
                    cur.remove(cur.size() - 1);
                }
            }
        }

    }


    public void getDist(String beginWord, String endWord, Set<String> wordList, HashMap<String, Integer> distMap) {
        distMap.put(beginWord, 1);
        //先进先出 FIFO
        Queue<String> q = new LinkedList<>();
        q.add(beginWord);

        while (!q.isEmpty()) {
            String cur = q.poll();

            for (int i = 0; i < cur.length(); i++) {
                char[] chars = cur.toCharArray();
                for (char j = 'a'; j <= 'z'; j++) {
                    chars[i] = j;
                    String newStr = new String(chars);
                    if (newStr.equals(endWord)) {
                        distMap.put(newStr, distMap.get(cur) + 1);
                        return;
                    }
                    if (wordList.contains(newStr) && !distMap.containsKey(newStr)) {
                        distMap.put(newStr, distMap.get(cur) + 1);
                        q.add(newStr);
                    }
                }
            }
        }
    }

}
