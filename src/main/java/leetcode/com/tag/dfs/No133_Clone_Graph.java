package leetcode.com.tag.dfs;

import leetcode.com.util.ListNode;
import leetcode.com.util.UndirectedGraphNode;

import java.net.URLDecoder;
import java.util.*;

/**
 * Created by JianZhang on 9/28/17.
 * Clone an undirected graph. Each node in the graph contains a label and a indexList of its neighbors.
 * <p>
 * <p>
 * OJ's undirected graph serialization:
 * Nodes are labeled uniquely.
 * <p>
 * We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
 * As an example, consider the serialized graph {0,1,2#1,2#2,2}.
 * <p>
 * The graph has a total of three nodes, and therefore contains three parts as separated by #.
 * <p>
 * First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
 * Second node is labeled as 1. Connect node 1 to node 2.
 * Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
 * Visually, the graph looks like the following:
 * <p>
 * 1
 * / \
 * /   \
 * 0 --- 2
 * / \
 * \_/
 * Solutions:
 * <p>
 * Important:
 * 1. 回忆下ALG4的DFS的template,然后套用呗,但是问题在于dataStructure是不一样的,alg4用一个boolean数组标记是否访问过
 * 2. 这个无向图是含有环的
 * bugs:
 * 1. 要用一个近似于全局变量的东西,来保存clone出来的图的节点,用hashMap,用hashSet的话,无法方便的取出节点出来
 */
public class No133_Clone_Graph {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {

//        Set<Integer> labelSet = new HashSet<>();
        HashMap<Integer, UndirectedGraphNode> hashMap = new HashMap<>();
        return dfs_helper(node, hashMap);
    }

    private UndirectedGraphNode dfs_helper(UndirectedGraphNode node, HashMap<Integer, UndirectedGraphNode> hashMap) {
        if (node == null) return null;

        UndirectedGraphNode cloneNode = new UndirectedGraphNode(node.label);
        hashMap.put(node.label, cloneNode);

        for (int i = 0; i < node.neighbors.size(); i++) {
            UndirectedGraphNode neighbour = node.neighbors.get(i);
            if (hashMap.containsKey(neighbour.label)) {
                cloneNode.neighbors.add(hashMap.get(neighbour.label));
            } else {
                cloneNode.neighbors.add(dfs_helper(neighbour, hashMap));
            }

        }
        return cloneNode;
    }

    private UndirectedGraphNode bfs_helper(UndirectedGraphNode node) {
        if (node == null) return node;

        HashMap<UndirectedGraphNode, UndirectedGraphNode> hashMap = new HashMap<>();
        Queue<UndirectedGraphNode> queue = new LinkedList<>();

        UndirectedGraphNode newNode = new UndirectedGraphNode(node.label);

        hashMap.put(node, newNode);
        queue.offer(node);

        while (!queue.isEmpty()) {
            //get the current node
            UndirectedGraphNode curtNode = queue.poll();
            List<UndirectedGraphNode> neighbours = curtNode.neighbors;
            for (UndirectedGraphNode neighbour : neighbours) {
                //处理新节点的状况
                if (!hashMap.containsKey(neighbour)) {
                    UndirectedGraphNode copyNode = new UndirectedGraphNode(neighbour.label);
                    hashMap.put(neighbour, copyNode);
                    queue.offer(neighbour);
                }
                hashMap.get(curtNode).neighbors.add(hashMap.get(neighbour));

            }
        }
        return newNode;
    }

    // 看了下ALg4的BSD,发现会牵扯到最短路径,是否可达等问题,还会把相应的连接边给记录下来
//    里面的实现是非递归的方法
//    因为是BSD,所以凡是访问过的节点,就都不必再搞
//    我的这个方法用的空间比较大,通过并行处理的2个队列,实际上用hashMap应该也可以的
    private UndirectedGraphNode bfs_helper_zj(UndirectedGraphNode node) {
        if (node == null) return null;

        HashMap<Integer, UndirectedGraphNode> hashMap = new HashMap<>();

        UndirectedGraphNode cloneNode = new UndirectedGraphNode(node.label);
        //save for the future reference
        hashMap.put(cloneNode.label, cloneNode);

        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        Queue<UndirectedGraphNode> queue2 = new LinkedList<>();
        queue.offer(node);
        queue2.offer(cloneNode);
        while (!queue.isEmpty()) {
            UndirectedGraphNode root = queue.poll();
            //bug 1: 要从第二个queue中取节点,才能平行
            UndirectedGraphNode goalNode = queue2.poll();
            for (int i = 0; i < root.neighbors.size(); i++) {
                //if accessed
                if (hashMap.containsKey(root.neighbors.get(i).label)) {
                    goalNode.neighbors.add(hashMap.get(root.neighbors.get(i).label));
                } else {
                    UndirectedGraphNode newNode = new UndirectedGraphNode(root.neighbors.get(i).label);
                    hashMap.put(root.neighbors.get(i).label, newNode);
                    goalNode.neighbors.add(newNode);
                    queue.offer(root.neighbors.get(i));
                    //bug 2: 要做瓶queue的话,新节点必须入队
                    queue2.offer(newNode);
                }
            }
        }

        return cloneNode;
    }


    //    下面的解法有问题,在于DFS之后,退回到某个节点时,若再次遇到某个label,则这个label就不会被放入neighbour中,
//    但是这个节点也应该放到当前节点的neighbour中,只是不必new,只需要从寄存的节点汇总获取就好
    private UndirectedGraphNode dfs_helper(UndirectedGraphNode node, Set<Integer> labelSet) {
        UndirectedGraphNode cloneNode = new UndirectedGraphNode(node.label);
        labelSet.add(node.label);
        for (int i = 0; i < node.neighbors.size(); i++) {
//            cloneNode.neighbors.add(new UndirectedGraphNode(node.neighbors.get(i).label));
            if (!labelSet.contains(node.neighbors.get(i).label)) {
//                labelSet.add(node.neighbors.get(i).label);
                cloneNode.neighbors.add(dfs_helper(node.neighbors.get(i), labelSet));
            } else if (node.neighbors.get(i).label == node.label) {
                cloneNode.neighbors.add(cloneNode);
            }

        }
        return cloneNode;
    }

}