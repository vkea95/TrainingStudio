package adm.com.ch05;

/**
 * Created by tclresearchamerica on 11/18/16.
 */
public class DFS extends GraphTraverse {
    public void traverse(GraphStructure.Graph graph, int v) {
        GraphStructure.EdgeNode p = null;/*temporary pointer*/
        int y;/*successor vertex*/
        if (isFinished) return;
        discovered[v] = true;

        time = time + 1;
        entryTime[v] = time;

        processVertexEarly(v);
        p = graph.edgeNodes[v];

        while (p != null) {
            y = p.y;
            //邻接节点y没有被发现过
            if (discovered[y] == false) {
                //设置父节点信息
                parent[y] = v;
                //处理临边
                processEdge(v, y);
                //递归掉用dfs遍历整张图
                traverse(graph, y);
            } else if ((!processed[y]) || graph.isDirected) {
                //check exercise 5-6就会明白 即使节点y被处理过,但只要是有向图,就还是要奥继续处理的,毕竟有向图,不需要逆向处理
                //邻接节点y未被处理,或是有向图
                processEdge(v, y);

            }

            if (isFinished) return;

            p = p.next;
        }

        processVertexLate(v);
        time++;
        exitTime[v] = time;
        processed[v] = true;
    }

}
