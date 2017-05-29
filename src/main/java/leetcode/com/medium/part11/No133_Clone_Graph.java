package leetcode.com.medium.part11;

import leetcode.com.util.UndirectedGraphNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by tclresearchamerica on 7/28/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/clone-graph/
 * ****************************************************
 * Description:
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
 * ****************************************************
 * Thought:
 * 没有想法
 * ****************************************************
 * Time: 30mins
 * Beat: 23%
 * Bug:-
 * ****************************************************
 * Hindsight:
 * 1.明白要用hashMap,
 * 2.HashMap要怎样才能解决全部的问题呢?没有想通啊
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No133_Clone_Graph {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        //check the null
        if (node == null) return null;

        List<UndirectedGraphNode> nodes = new ArrayList<>();
        HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();

        //clone nodes
        nodes.add(node);
        map.put(node, new UndirectedGraphNode(node.label));

        int start = 0;
        while (start < nodes.size()) {
            UndirectedGraphNode head = nodes.get(start++);
//            Stream.of(head.neighbors).forEach(s ->{
//                if (!map.containsKey(s)) {
//                    map.put(s, new UndirectedGraphNode(s.label));
//                    nodes.add(s);
//                }
//            });
            for (UndirectedGraphNode neighbour : head.neighbors) {
                if (!map.containsKey(neighbour)) {
                    map.put(neighbour, new UndirectedGraphNode(neighbour.label));
                    nodes.add(neighbour);
                }
            }
        }

        //clone neighbours
        for (UndirectedGraphNode oldNode : nodes) {
            UndirectedGraphNode newNode = map.get(oldNode);
            // Stream.of(oldNode.neighbors).forEach(s -> newNode.neighbors.add(map.get(s)));
            for (UndirectedGraphNode neighbour : oldNode.neighbors) {
                newNode.neighbors.add(map.get(neighbour));
            }
        }

        //return result
        return map.get(node);

    }
}
