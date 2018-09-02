package leetcode.com.pickup1.medium;

import leetcode.com.util.UndirectedGraphNode;

import java.util.*;

/**
 * Created by tclresearchamerica on 7/28/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/clone-graph/
 * ****************************************************
 * Description:
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
 * ****************************************************
 * Hindsight:
 * 1.用map&list在第一次遍历的时候,通过2个数据结构,构筑好新,旧节点的对应关系
 * 2.再次遍历的时候,通过新,旧节点的对应关系,再凭借旧节点neighbour,完成一个环路,搭建新的无向图
 * 3.感觉在处理图的问题上,没有足够的感觉
 * ****************************************************
 * Time: 40 mins
 * Beat: 23%
 * Bug: -
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No133_Clone_Graph {

    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {

        if (node == null) return node;

        List<UndirectedGraphNode> list = new ArrayList<>();
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();

        map.put(node, new UndirectedGraphNode(node.label));
        list.add(node);

        int start = 0;
        while (start < list.size()) {
            UndirectedGraphNode curt = list.get(start);

            for (UndirectedGraphNode neighbour : curt.neighbors) {
                if (!map.containsKey(neighbour)) {
                    map.put(neighbour, new UndirectedGraphNode(neighbour.label));
                    list.add(neighbour);
                }
            }
            start++;
        }

        for (UndirectedGraphNode oldNode:list){
            UndirectedGraphNode newNode = map.get(oldNode);
            for (UndirectedGraphNode oldNeighbour:oldNode.neighbors){
                newNode.neighbors.add(map.get(oldNeighbour));
            }

        }
        return map.get(node);
    }

    public UndirectedGraphNode cloneGraph_slow_concise(UndirectedGraphNode node) {
        if (node == null) return null;
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();

        UndirectedGraphNode root = new UndirectedGraphNode(node.label);
        map.put(node, root);
        queue.offer(node);
        while (!queue.isEmpty()) {
            UndirectedGraphNode curt = queue.poll();

            for (UndirectedGraphNode neighbour : curt.neighbors) {
                if (!map.containsKey(neighbour)) {
                    //first time setup
                    map.put(neighbour, new UndirectedGraphNode(neighbour.label));
                    queue.offer(neighbour);
                }
                map.get(curt).neighbors.add(map.get(neighbour));
            }
        }
        return root;
    }
}
