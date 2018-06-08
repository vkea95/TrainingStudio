package leetcode.com.pickup1.medium;

import java.util.*;

/**
 * Created by tclresearchamerica on 6/19/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/minimum-height-trees/
 * ****************************************************
 * For a undirected graph with tree characteristics, we can choose any node as the root. The result graph is then a
 * rooted tree. Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs).
 * Given such a graph, write a function to find all the MHTs and return a list of their root labels.
 * Format
 * The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number n and a list of
 * undirected edges (each edge is a pair of labels).
 * You can assume that no dupNumber edges will appear in edges. Since all edges are undirected, [0, 1] is the same
 * as [1, 0] and thus will not appear together in edges.
 * Example 1:
 * Given n = 4, edges = [[1, 0], [1, 2], [1, 3]]
 * 0
 * |
 * 1
 * / \
 * 2   3
 * return [1]
 * <p>
 * Example 2:
 * <p>
 * Given n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]
 * <p>
 * 0  1  2
 * \ | /
 * 3
 * |
 * 4
 * |
 * 5
 * return [3, 4]
 * ****************************************************
 * Thoughts:
 * 1.无向图转换成Tree之后,求最小高度的问题,所以每个最小高度树所对应的root至多是1个或是2个,不会再多了
 * 2.关于输入参数的edge,可以理解为,每个edge会和2个节点相互关联,在考虑数据结构的时候,要注意到这一点,且利用这一点,第一DS是HashMap
 * 3.需要考虑是BFS or DepthFirstSearch
 * 4.要考虑一次搜寻之后,再次搜寻之时,如何恢复DS的初始状态?没有思路啊!!!放弃啦,直接看答案.
 * 看了BFS的解答,发现有个关键点被遗漏了,就是需要从维度为1的节点入手,解答问题,最后被放入结果集的节点就是我们要的答案.
 * ***此处继续参考使用BFS的用法
 * ****************************************************
 * Time: 60 mins
 * Beats: 85%
 * Bug: 3
 * KeyPoint: 1.从维度为1的节点入手哦, 2 开始循环后,按照我们的逻辑,维度为2的节点要进入队列呢
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No310_Minimum_Height_Trees {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {

        List<List<Integer>> graph = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        int[] degree = new int[n];
        List<Integer> result = new ArrayList<>();
        int len1 = edges.length;

        //bug4: forget to deal 1 node situation
        if (n == 1) {
            result.add(0);
            return result;
        }
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < len1; i++) {
            graph.get(edges[i][0]).add(edges[i][1]);
            graph.get(edges[i][1]).add(edges[i][0]);
            degree[edges[i][0]]++;
            degree[edges[i][1]]++;
        }
        for (int i = 0; i < n; i++) {
            if (degree[i] == 1) {
                queue.offer(i);
            } else if (degree[i] == 0) {
                return result;
            }
        }

        while (!queue.isEmpty()) {
            result = new ArrayList<>();
            int length = queue.size();
            for (int i = 0; i < length; i++) {
                int curr = queue.poll();
                degree[curr]--;
                //bug2: don't where to add the curr to result
                result.add(curr);
                for (int j = 0; j < graph.get(curr).size(); j++) {
                    int neighbour = graph.get(curr).get(j);
                    //bug1:don't know th judge conditions for reduce degrees
                    if (degree[neighbour] == 0) continue;
                    if (degree[neighbour] == 2) queue.offer(neighbour);
                    degree[neighbour]--;

                }
            }
        }
        return result;
    }


}
