package leetcode.com.tag.unionFind;

import java.util.HashMap;

/**
 * Created by JianZhang on 10/3/17.
 * In this problem, a tree is an undirected graph that is connected and has no cycles.
 * <p>
 * The given input is a graph that started as a tree with N nodes (with distinct values 1, 2, ..., N),
 * with one additional edge added. The added edge has two different vertices chosen from 1 to N,
 * and was not an edge that already existed.
 * <p>
 * The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] with u < v,
 * that represents an undirected edge connecting nodes u and v.
 * <p>
 * Return an edge that can be removed so that the resulting graph is a tree of N nodes.
 * If there are multiple answers, return the answer that occurs last in the given 2D-array.
 * The answer edge [u, v] should be in the same format, with u < v.
 * <p>
 * Example 1:
 * Input: [[1,2], [1,3], [2,3]]
 * Output: [2,3]
 * Explanation: The given undirected graph will be like this:
 * 1
 * / \
 * 2 - 3
 * Example 2:
 * Input: [[1,2], [2,3], [3,4], [1,4], [1,5]]
 * Output: [1,4]
 * Explanation: The given undirected graph will be like this:
 * 5 - 1 - 2
 * |   |
 * 4 - 3
 * Note:
 * The size of the input 2D-array will be between 3 and 1000.
 * Every integer represented in the 2D-array will be between 1 and N, where N is the size of the input array.
 * Solutions:
 * 1.每次新加一个边之前,都用union-find确认下,如果他们都有想同的parent,则肯定会形成环,那它就是答案。
 */
public class No684_Redundant_Connection {
    public int[] findRedundantConnection(int[][] edges) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int[] pair : edges) {
            if (!hashMap.containsKey(pair[0])) hashMap.put(pair[0], pair[0]);
            if (!hashMap.containsKey(pair[1])) hashMap.put(pair[1], pair[1]);
            if (find(hashMap, pair[0]) == find(hashMap, pair[1])) {
                return pair;
            } else {
                union(hashMap, pair[0], pair[1]);
            }
        }
        return null;
    }


    private void union(HashMap<Integer, Integer> hashMap, int i, int j) {
        int iRoot = find(hashMap, i);
        int jRoot = find(hashMap, j);
        if (iRoot == jRoot) return;
//        bug1: key应该是iRoot,而非i
        hashMap.put(iRoot, jRoot);
    }

    private int find(HashMap<Integer, Integer> hashMap, int i) {
        int root = i;
        //find the most parent node
        while (root != hashMap.get(root)) {
            root = hashMap.get(root);
        }

        while (root != i) {
            int temp = hashMap.get(i);
            hashMap.put(i, root);
            i = temp;
        }
        return i;
    }

    private int[] parent;

    public int[] findRedundantConnection_fixedarray(int[][] edges) {
        parent = new int[1000];
        initArray(parent);
        for (int[] pair : edges) {
            if (find(pair[0]) == find(pair[1])) return pair;
            else {
                union(pair[0], pair[1]);
            }
        }
        return null;
    }

    private void initArray(int[] array) {
        for (int i = 0; i < array.length; i++) array[i] = i;
    }

    private void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) return;
        parent[pRoot] = qRoot;

    }

    private int find(int p) {
        int root = p;
        while (root != parent[root]) root = parent[root];

        while (root != p) {
            int temp = parent[p];
            parent[p] = root;
            p = temp;

        }
        return root;
    }
}
