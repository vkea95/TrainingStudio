package leetcode.com.tag.unionFind;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by JianZhang on 10/3/17.
 * There are N students in a class. Some of them are friends, while some are not.
 * Their friendship is transitive in nature. For example, if A is a direct friend of B, and B is a direct friend of C,
 * then A is an indirect friend of C.
 * And we defined a friend circle is a group of students who are direct or indirect friends.
 * <p>
 * Given a N*N matrix M representing the friend relationship between students in the class.
 * If M[i][j] = 1, then the ith and jth students are direct friends with each other, otherwise not.
 * And you have to output the total number of friend circles among all the students.
 * <p>
 * Example 1:
 * Input:
 * [[1,1,0],
 * [1,1,0],
 * [0,0,1]]
 * Output: 2
 * Explanation:The 0th and 1st students are direct friends, so they are in a friend circle.
 * The 2nd student himself is in a friend circle. So return 2.
 * Example 2:
 * Input:
 * [[1,1,0],
 * [1,1,1],
 * [0,1,1]]
 * Output: 1
 * Explanation:The 0th and 1st students are direct friends, the 1st and 2nd students are direct friends,
 * so the 0th and 2nd students are indirect friends. All of them are in the same friend circle, so return 1.
 * Note:
 * N is in range [1,200].
 * M[i][i] = 1 for all students.
 * If M[i][j] = 1, then M[j][i] = 1.
 * Solutions:
 * 1. 处理联通分量问题,但是需要注意的是是否个别student没有朋友
 * Analysis:
 * 1. 用时35mins
 * 2. bug:至少2个,一个是构建表的时候,内循环的初始值,另一个是result自加处理应该在邻接表循环结束后,而不是循环过程中,因为它们是一个联通分量
 * 3. zj的做法是仿照Alg4的联通分量法解决的,Union-find也可以解决,要再考虑下数据结构
 */
public class No547_Friend_Circles {
    private boolean[] marked;

    public int findCircleNum(int[][] M) {
        if (M == null) return 0;
        int result = 0;
        marked = new boolean[M.length];
        HashMap<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
        for (int i = 0; i < M.length; i++) {
            //bug1: j=0 而不是j=i+1,或j=i,这样会违背连接表的基本含义
            for (int j = 0; j < M.length; j++) {
                if (!map.containsKey(i)) map.put(i, new ArrayList<>());
                if (M[i][j] == 1) map.get(i).add(j);
            }
        }
        for (int i = 0; i < M.length; i++) {
            if (!marked[i]) {
                for (int j : map.get(i)) {
                    helper(map, j);
                    //bug2: result 应该在循环的外面
//                    result++;
                }
                result++;

            }
        }
        return result;
    }

    private void helper(HashMap<Integer, List<Integer>> map, int i) {
        marked[i] = true;
        for (int j : map.get(i)) {
            if (!marked[j])
                helper(map, j);
        }
    }

    private int count;
    private int[] parent;

    public int findCircleNum_uf(int[][] M) {
        parent = new int[M.length];
//        设置一个count计数,最开始有N个,发生一次union就count-1
        count = M.length;
        initializeData(parent);
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M.length; j++) {

                if (i != j && M[i][j] == 1) union(M, i, j);
            }
        }
        return count;
    }

    private void initializeData(int[] id) {
        for (int i = 0; i < id.length; i++) {
            id[i] = i;
        }
    }

    private void union(int[][] M, int i, int j) {
        //代表direct friend
        int iRoot = find(i);
        int jRoot = find(j);
        if (iRoot == jRoot) return;
        parent[iRoot] = jRoot;
        count--;
    }

    private int find(int i) {
        int root = i;
//        寻找i的最上面的parent
        while (root != parent[root]) {
            root = parent[root];
        }
//        将i的parent指向最高parent
//        这个是一个压缩处理,将所有的节点都扁平化了
        while (i != root) {
            int temp = parent[i];
            parent[i] = root;
            i = temp;
        }
        return i;
    }
}
