package leetcode.com.tag.dfs;

import leetcode.com.util.UndirectedGraphNode;

import java.util.HashMap;
import java.util.Set;

/**
 * Created by JianZhang on 9/28/17.
 * Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.
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
        if (node == null) return null;

//        Set<Integer> labelSet = new HashSet<>();
        HashMap<Integer, UndirectedGraphNode> hashMap = new HashMap<>();
        return dfs_helper(node, hashMap);
    }

    private UndirectedGraphNode dfs_helper(UndirectedGraphNode node, HashMap<Integer, UndirectedGraphNode> hashMap) {
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

    private UndirectedGraphNode bfs_helper(UndirectedGraphNode node, HashMap<Integer, UndirectedGraphNode> hashMap) {
        return null;
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