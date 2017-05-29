package leetcode.com.pickup1.medium;

import java.util.*;

/**
 * Created by tclresearchamerica on 9/25/16.
 */
public class No126_Word_Ladder_I {

    public static void main(String[] args) {
        No126_Word_Ladder_I obj = new No126_Word_Ladder_I();
        Set<String> list = new HashSet<>();
        list.add("hot");
        list.add("dog");
        list.add("dot");
        obj.ladderLength("hot", "dog", list);
    }


    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
        List<List<String>> results = new ArrayList<>();
        int result = Integer.MAX_VALUE;
        Map<String, Integer> map = new HashMap();
        buildLevel(beginWord, endWord, wordList, map);
        List<String> solution = new ArrayList();
        solution.add(endWord);
        traverseDFS(beginWord, endWord, results, solution, map);
        for (List<String> solu : results) {
            result = result < solu.size() ? result : solu.size();
        }

        return result == Integer.MAX_VALUE ? 0 : result;

    }

    private void traverseDFS(String beginWord, String endWord, List<List<String>> results, List<String> solution, Map<String, Integer> map) {
        if (beginWord.equals(endWord)) {
            List<String> solu = new ArrayList<>(solution);
            Collections.reverse(solu);
            results.add(solu);
            return;
        }
        //bug1:要从endword逆序处理哦
        for (int i = 0; i < endWord.length(); i++) {
            char[] chars = endWord.toCharArray();
            for (char c = 'a'; c <= 'z'; c++) {
                chars[i] = c;
                String newStr = new String(chars);
                if (map.containsKey(newStr) && map.get(newStr) == map.get(endWord) - 1) {
                    solution.add(newStr);
                    traverseDFS(beginWord, newStr, results, solution, map);
                    solution.remove(solution.size() - 1);
                }
            }
        }
    }

    private void buildLevel(String beginWord, String endWord, Set<String> wordList, Map<String, Integer> map) {
        //bug1:不知道如何利用map达到循环所有元素的目的，现在知道了，再搞个数据结构queue
        Queue<String> queue = new LinkedList<>();
        //bug2:应该从endWord开始入手处理
        queue.add(beginWord);

        map.put(beginWord, 1);
        while (!queue.isEmpty()) {
            String temp = queue.poll();
            for (int i = 0; i < temp.length(); i++) {
                char[] chars = temp.toCharArray();
                for (char j = 'a'; j <= 'z'; j++) {
                    if (j == chars[i]) continue;
                    chars[i] = j;
                    String nextStr = new String(chars);
                    //bug4:如果map中含有该单词则,不可以进行put操作
                    if (wordList.contains(nextStr)) {
                        if (!map.containsKey(nextStr)) {
                            map.put(nextStr, map.get(temp) + 1);

                            queue.add(nextStr);
                        }
                        wordList.remove(nextStr);
                    }
                    if (nextStr.equals(endWord)) {
                        return;
                    }
                    //bug3:如果集合中不含有改词，则直接返回，而不要将其加入queue中

                }

            }

        }
    }

    public int ladderLength_fast(String beginWord, String endWord, Set<String> wordList) {
        List<List<String>> results = new ArrayList<>();
        int result = Integer.MAX_VALUE;
        Map<String, Set<String>> map = new HashMap();
        Set<String> forward = new HashSet();
        Set<String> backward = new HashSet();
        boolean[] isConnected = new boolean[1];

        forward.add(beginWord);
        backward.add(endWord);

        buildBFS(forward, backward, wordList, false, isConnected, map);
        DFS(results, new ArrayList<>(), beginWord, endWord, map);

        for (List<String> solution : results) {
            result = result < solution.size() ? result : solution.size();
        }

        return result;

    }

    //bug: DFS开始的条件不是集合了，而是单词
    private void DFS(List<List<String>> results, List<String> solution, String beginWord, String endWord, Map<String, Set<String>> map) {
        if (beginWord.equals(endWord)) {
            solution.add(endWord);
            results.add(new ArrayList(solution));
            solution.remove(solution.size() - 1);
            return;
        }
        if (map.containsKey(beginWord)) {
            solution.add(beginWord);
            for (String str : map.get(beginWord)) {
                DFS(results, solution, str, endWord, map);
            }
            solution.remove(solution.size() - 1);
        }
    }

    private void buildBFS(Set<String> forward, Set<String> backward, Set<String> wordList, boolean swap, boolean[] isConnected, Map<String, Set<String>> map) {
        //boundary check
        if (forward.isEmpty() || backward.isEmpty()) return;
        if (forward.size() > backward.size()) {
            buildBFS(backward, forward, wordList, !swap, isConnected, map);
            return;
        }
        //clear the left string
        wordList.removeAll(forward);
        wordList.removeAll(backward);

        Set<String> newSet = new HashSet<>();
        for (String str : forward) {
            for (int i = 0; i < str.length(); i++) {
                char[] chars = str.toCharArray();
                for (char j = 'a'; j <= 'z'; j++) {
                    if (j == chars[i]) continue;
                    chars[i] = j;
                    String temp = new String(chars);
                    if (!wordList.contains(temp) && !backward.contains(temp)) continue;
                    if (backward.contains(temp)) {
                        isConnected[0] = true;
                    }
                    //bug1:即使发现了目标string,也要继续循环下去。
                    //一旦连接成功，则不必再增加新的元素至newSet
//                    if (isConnected[0]) {
//                        continue;
//                    }
                    //bug2:map中存对应关系的时候,应该考虑swap,否则 swap就没有存在的意义了

                    String key = swap ? temp : str;
                    String value = swap ? str : temp;
                    //bug3:无论什么 时候,只要发现有匹配的str,就要把它放入map中
                    if (!map.containsKey(key)) map.put(key, new HashSet<String>());
                    map.get(key).add(value);
//                    bug4:只有当数据没有connect的时候,才需要进行这个加入到newSet的操作
                    if (!isConnected[0] && wordList.contains(temp)) {
                        newSet.add(temp);
                    }
//                    if (wordList.contains(temp)) {
//                        newSet.add(temp);
//                        if (!map.containsKey(str)) map.put(str, new HashSet());
//                        map.get(str).add(temp);
//                    }

                }

            }
        }
        if (isConnected[0]) {
            return;
        }
        buildBFS(newSet, backward, wordList, swap, isConnected, map);
    }
}
