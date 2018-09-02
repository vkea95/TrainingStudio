package leetcode.com.medium;

import leetcode.com.util.TreeNode;

import java.util.*;

/**
 * Created by tclresearchamerica on 5/8/16.
 * ****************************************************
 * Description:
 * For a undirected graph with tree characteristics, we can choose any node as the root. The result graph is then a
 * rooted tree. Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs).
 * Given such a graph, write a function to find all the MHTs and return a indexList of their root labels.
 * Format
 * The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number n and a indexList
 * of undirected edges (each edge is a pair of labels).
 * You can assume that no dupNumber edges will appear in edges. Since all edges are undirected,
 * [0, 1] is the same as [1, 0] and thus will not appear together in edges.
 * Example 1:
 * <p>
 * Given n = 4, edges = [[1, 0], [1, 2], [1, 3]]
 * <p>
 * 0
 * |
 * 1
 * / \
 * 2   3
 * return [1]
 * Example 2:
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
 * Analysis:
 * 1.看了题目之后,没有思路,分析下,有可能是数组的表现方式和原来的node方式不一样所致,思绪被stuck住了,想想后,明白该自己构造合适的数据结构,
 * 这么看来数据结构这个固件还是没有特别强的构造能力.设计起来还是有些麻烦.看着网上的思路,我们可以用hashMap的方式,每个num是key,
 * value是list--存着相邻的数字的集合.这样基本的数据结构构造完毕,然后,再想象如果要求找到MHTs,就可以认为是在用DFS构造树,
 * 我来试一试这个做法吧,就是用list构建完毕的时候,再用DFS搞一次,算下每个节点遍历的树的高度是多少,保留最少的即可.
 * 此时假设没有循环节点,如果有循环节点的话,就不是树了!!!
 * 2.另外一种做法,就是用删除叶子节点的方法,每次删掉入度为1的节点,直到所剩的节点数量小于等于2(这个可以证明的,反证法最简单)之时,
 * 就是我们寻找的答案
 * 3.BFS的算法会很快,他只是遍历了必要的节点,用queue完成了记录这一趟要操作的节点信息,所以很快,所以这个是多个数据结构结合的的结果
 * ****************************************************
 * 难点:
 * 将node放入list中的话,不能简单通过定位下标来完成查找工作
 * ****************************************************
 * 技术弱点:
 * 1.不清楚ArrayDequeue
 * http://blog.csdn.net/jiutianhe/article/details/22881039
 * 队列:添加元素offer方法
 * 栈 :添加元素push方法
 * 比较ArrayList&ArrayDeque:
 * http://www.zhihu.com/question/33030727
 * ****************************************************
 */
public class No310_Minimum_Height_Trees {
    public static void main(String[] args) {

        int[][] nodes = {{1, 0}, {1, 2}, {1, 3}};

        No310_Minimum_Height_Trees obj = new No310_Minimum_Height_Trees();
//        obj.findMinHeightTrees_faster(4, nodes);

    }

    class TreeNode {
        int degree = 0;
        public List<Integer> adjList = new ArrayList<>();

        public TreeNode() {
//            this.val = val;
        }
//        public TreeNode(int val) {
//            this.val = val;
//        }
    }


    public List<Integer> findMinHeightTrees_BFS(int n, int[][] edges) {
        List<Integer> result = new ArrayList<>();
        List<List<Integer>> graph = new ArrayList<>();
        if (n == 1) {
            result.add(0);
            return result;
        }

        int[] degree = new int[n];
        for (int i = 0; i < n; i++)
            graph.add(new ArrayList<>());


        for (int i = 0; i < edges.length; i++) {

            graph.get(edges[i][0]).add(edges[i][1]);
            graph.get(edges[i][1]).add(edges[i][0]);
            degree[edges[i][0]]++;// 维度
            degree[edges[i][1]]++;
        }

        //bug1:don't know the character of the queue
        Queue<Integer> queue = new ArrayDeque<>();//?见上

        for (int i =0;i<n;i++){
            if (degree[i]==0)
                return result;
            else if (degree[i]==1)
                queue.offer(i);
        }

        while (!queue.isEmpty()){
            result=new ArrayList<>();
            int count=queue.size();

            for (int i=0;i<count;i++){
                int curr =queue.poll();
                result.add(curr);
                degree[curr]--;
                for (int j=0;j<graph.get(curr).size();j++){
                    int next=graph.get(curr).get(j);
                    if (degree[next]==0) continue;
                    if (degree[next]==2){
                        queue.offer(next);
                    }
                    //bug2:every node should minus the degrees
                    degree[next]--;
                }
            }

        }

        return result;

    }


    public List<Integer> findMinHeightTrees_faster(int n, int[][] edges) {
        List<Integer> result = new ArrayList<>();
        if (n <= 1) {
            result.add(0);
            return result;
        } else if (n <= 2) {
            result.add(edges[0][1]);
            result.add(edges[0][0]);
            return result;
        }
        TreeNode[] array = new TreeNode[n];
        // Arrays.fill(array, new TreeNode());
//        Arrays.fill
        int[] degree = new int[n];
//        List<TreeNode> nodeList = new ArrayList<>(n);
        //bug8:循环生成object,一定程度的避免循环判断,提高效率
        for (int i = 0; i < n; i++) {
            array[i] = new TreeNode();
        }
        for (int i = 0; i < edges.length; i++) {
//            if (array[edges[i][0]] == null) {
//                array[edges[i][0]] = new TreeNode();
//            }
//            if (array[edges[i][1]] == null) {
//                array[edges[i][1]] = new TreeNode();
//            }
            array[edges[i][0]].adjList.add(edges[i][1]);
            array[edges[i][1]].adjList.add(edges[i][0]);
            array[edges[i][0]].degree++;
            array[edges[i][1]].degree++;
            degree[edges[i][0]]++;// 维度
            degree[edges[i][1]]++;
        }

        int nodeCnt = n;
        while (nodeCnt > 2) {
//            HashSet<Integer> hashset = new HashSet<>();
            for (int i = 0; i < n; i++) {
                if (array[i].degree == 1 && degree[i] == 1) {
                    if (array[i].adjList.get(0) < i)
                        degree[array[i].adjList.get(0)]--;
                    array[array[i].adjList.get(0)].degree--;
                    array[array[i].adjList.get(0)].adjList.remove(Integer.valueOf(i));
                    array[i].adjList.clear();
                    array[i].degree = -1;
                    degree[i] = -1;
                    nodeCnt--;
                } else if (degree[i] > 0 && array[i].degree != degree[i]) {
                    degree[i] = array[i].degree;
//                    if(degree[i]==1){
//                        hashset.add(i);
//                    }
//                }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (degree[i] >= 0)
                result.add(i);
        }
        return result;
    }

    public List<Integer> findMinHeightTrees_Slow(int n, int[][] edges) {

        //Bug5:Time limited:超时原因是啥呢?循环太多了,需要在有限的循环内把他解决掉
        //Bug7:性能太烂,我要把他提高到击垮80%的算法,想来还是要用比较适合节点的数据结构才可以,相邻节点,维度,等等
        HashMap<Integer, List<Integer>> numMap = new HashMap<>();

        //构造树的结构
        for (int i = 0; i < edges.length; i++) {
            if (!numMap.containsKey(edges[i][0])) {
                numMap.put(edges[i][0], new ArrayList<>());
            }
            numMap.get(edges[i][0]).add(edges[i][1]);

            if (!numMap.containsKey(edges[i][1])) {
                numMap.put(edges[i][1], new ArrayList<>());
            }
            numMap.get(edges[i][1]).add(edges[i][0]);

        }
        int nodeCnt = n;
        HashSet<Integer> nodeSets = new HashSet<>();
        while (nodeCnt > 2) {
            nodeSets.clear();
            //bug5:超时,考虑将for循环中的固定次数改为根据numMap的Entry进行循环处理
            Iterator<Map.Entry<Integer, List<Integer>>> mapIt = numMap.entrySet().iterator();

            while (mapIt.hasNext()) {
                Map.Entry<Integer, List<Integer>> pair = mapIt.next();
                List<Integer> adjList = pair.getValue();
                Integer key = pair.getKey();
                if (adjList.size() == 1 && !nodeSets.contains(key)) {
//                    numMap.remove(key);//delete leaf node
                    nodeCnt--;
                    numMap.get(adjList.get(0)).remove(Integer.valueOf(key));
                    nodeSets.add(adjList.get(0));
                    //bug6:Iterator 循环下删除节点,必须用it.remove,且在it.next之后执行
                    mapIt.remove();
                }

            }
        }

        Iterator<Map.Entry<Integer, List<Integer>>> it = numMap.entrySet().iterator();
        List<Integer> result = new ArrayList<>();
        while (it.hasNext())
            result.add(it.next().getKey());
        if (result.size() == 0)
            result.add(0);


        return result;

    }
}
