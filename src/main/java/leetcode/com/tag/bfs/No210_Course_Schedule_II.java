package leetcode.com.tag.bfs;

import java.util.*;

/**
 * Created by JianZhang on 9/30/17.
 * There are a total of n courses you have to take, labeled from 0 to n - 1.
 * <p>
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1,
 * which is expressed as a pair: [0,1]
 * <p>
 * Given the total number of courses and a indexList of prerequisite pairs,
 * return the ordering of courses you should take to finish all courses.
 * <p>
 * There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses,
 * return an empty array.
 * <p>
 * For example:
 * <p>
 * 2, [[1,0]]
 * There are a total of 2 courses to take. To take course 1 you should have finished course 0.
 * So the correct course order is [0,1]
 * <p>
 * 4, [[1,0],[2,0],[3,1],[3,2]]
 * There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2.
 * Both courses 1 and 2 should be taken after you finished course 0. So one correct course order is [0,1,2,3].
 * Another correct ordering is[0,2,1,3].
 * Solutions
 * 1. 参照No.207,搞出这个DFS的edgeTo,然后再用
 * 1.1 要解决的几个问题
 * A. 判断是否有环,有环则返回空
 * B. 逆后序的DS是栈,
 */
public class No210_Course_Schedule_II {

    public static void main(String[] args) {

        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        for (int i : stack) {
            System.out.println(i);
        }

        Iterable<Integer> itb =stack;
        for(int i: itb){
            System.out.println(i);

        }
//        No210_Course_Schedule_II obj = new No210_Course_Schedule_II();
//        obj.findOrder(2, new int[][]{{1, 0}});


//        obj.canFinish(3, new int[][]{{1, 0}, {0, 2},{2,1}});
    }

    private Stack<Integer> reversePost;
    private boolean isCycle;


    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses == 0) return null;
        reversePost = new Stack<>();

        int[] result = new int[numCourses];
        boolean[] marked = new boolean[numCourses];
        boolean[] onStack = new boolean[numCourses];
        HashMap<Integer, List<Integer>> graph = new HashMap<>();

        for (int i = 0; i < prerequisites.length; i++) {
            if (!graph.containsKey(prerequisites[i][1])) graph.put(prerequisites[i][1], new ArrayList<>());
            if (!graph.containsKey(prerequisites[i][0])) graph.put(prerequisites[i][0], new ArrayList<>());

            graph.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }
        for (Integer key : graph.keySet()) {
            if (!marked[key])

                dfsHelper(graph, key, marked, onStack);
        }
        if (isCycle) return new int[0];
        int i = 0;
        marked = new boolean[numCourses];
        while (!reversePost.isEmpty()) {
            if (!marked[reversePost.peek()]) {
                result[i++] = reversePost.peek();
                marked[reversePost.peek()] = true;
            }
            reversePost.pop();
        }
//        不要使用for Iterator去循环这个stack
//        for (int obj : reversePost) {
//            if (!marked[obj]) {
//                result[i++] = obj;
//                marked[obj] = true;
//            }
//        }
//        Iterator it = reversePost.returnList();
//        marked = new boolean[numCourses];
//        while (!reversePost.isEmpty()) {
//            if (marked[result.])
//                result[i] = reversePost.pop();
//        }

        for (int j = 0; j < numCourses; j++) {
            if (!marked[j]) {
                marked[j] = true;
                result[i++] = j;
            }
        }
        return result;
    }

    private void dfsHelper(HashMap<Integer, List<Integer>> graph, int v, boolean[] marked, boolean[] onStacke) {
        onStacke[v] = true;
        marked[v] = true;

        for (int i : graph.get(v)) {
            if (!marked[i]) {
                dfsHelper(graph, i, marked, onStacke);
            } else if (onStacke[i]) {
                //此处可以追加对cycle的数据类型声明和初期化,并通过edgeTO这个数组遍历出来这个cycle
                isCycle = true;
            }
        }

        //逆后序排列
        reversePost.push(v);
        onStacke[v] = false;
    }
}
