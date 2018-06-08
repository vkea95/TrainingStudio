package leetcode.com.tag.bfs;

import edu.princeton.cs.algs4.In;

import java.util.*;

/**
 * Created by JianZhang on 9/30/17.
 * There are a total of n courses you have to take, labeled from 0 to n - 1.
 * <p>
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1,
 * which is expressed as a pair: [0,1]
 * <p>
 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
 * <p>
 * For example:
 * <p>
 * 2, [[1,0]]
 * There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.
 * <p>
 * 2, [[1,0],[0,1]]
 * There are a total of 2 courses to take. To take course 1 you should have finished course 0,
 * and to take course 0 you should also have finished course 1. So it is impossible.
 * <p>
 * Note:
 * The input prerequisites is a graph represented by a list of edges, not adjacency matrices.
 * Read more about how a graph is represented.
 * You may assume that there are no dupNumber edges in the input prerequisites.
 * click to show more hints.
 * <p>
 * Solutions
 * 1. 根据Alg4的文章说明,构建一个有向图,然后判断是否有环,也就是在任意一次DFS遍历中,只要有一个点被访问多次,就说明有环
 * 1.1 HashMap这个数据结构可以精简为size为n的一个List<>
 * Bugs:
 * 1. Line 25: java.lang.NullPointerException --> 原因是因为有的节点并不在hashMapzhong存在
 */
public class No207_Course_Schedule {

    public static void main(String[] args) {
        No207_Course_Schedule obj = new No207_Course_Schedule();
//        obj.canFinish(2, new int[][]{{1, 0}});

        obj.canFinish(3, new int[][]{{1, 0}, {0, 2},{2,1}});
    }

    private int[] edgeTo = null;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses == 0) return true;
        boolean[] marked = new boolean[numCourses];
        boolean[] onStack = new boolean[numCourses];
        edgeTo = new int[numCourses];
        HashMap<Integer, List<Integer>> graph = new HashMap<>();

        for (int i = 0; i < prerequisites.length; i++) {
            if (!graph.containsKey(prerequisites[i][1])) graph.put(prerequisites[i][1], new ArrayList<>());
            if (!graph.containsKey(prerequisites[i][0])) graph.put(prerequisites[i][0], new ArrayList<>());

            graph.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }
        for (Integer key : graph.keySet()) {
            if (!dfsHelper(graph, key, marked, onStack)) return false;
        }
        return true;
    }

    private boolean dfsHelper(HashMap<Integer, List<Integer>> graph, int v, boolean[] marked, boolean[] onStacke) {
        onStacke[v] = true;
        marked[v] = true;

        for (int i : graph.get(v)) {
            if (!marked[i]) {
                //edgeTo 记录有向边
                edgeTo[i] = v;
                if (!dfsHelper(graph, i, marked, onStacke)) return false;
            } else if (onStacke[i]) {
                //此处可以追加对cycle的数据类型声明和初期化,并通过edgeTO这个数组遍历出来这个cycle

                Stack<Integer> cyle = new Stack<>();
                for (int x = v; x != i; x = edgeTo[x])
                    cyle.push(x);
                cyle.push(i);
//                cyle.push(v);
                System.out.println("cycle start");
                while (!cyle.isEmpty()) System.out.println(cyle.pop());
                System.out.println("cycle end");
                return false;
            }
        }

        onStacke[v] = false;
        return true;
    }
}
