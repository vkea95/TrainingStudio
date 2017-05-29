package adm.com.ch05;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by tclresearchamerica on 11/19/16.
 */
public class BFS extends GraphTraverse {

    public void traverse(GraphStructure.Graph graph, int start) {
        Queue<Integer> queue = new LinkedList<>();/* queue of vertices to visit */

        int v;/* current vertex */
        int y;/* successor vertex */
        GraphStructure.EdgeNode p = null; /* temporary pointer */
        this.totalEdge = 0;

        queue.offer(start);//
        discovered[start] = true;

        while (!queue.isEmpty()) {
            v = queue.poll();
            //before dealing
            processVertexEarly(v);
            //将v标志为已经处理
            processed[v] = true;
            //取得该节点的第一个元素的指针
            p = graph.edgeNodes[v];

            while (p != null) {
                //取得邻接节点
                y = p.y;
                //check exercise 5-6就会明白 即使节点y被处理过,但只要是有向图,就还是要奥继续处理的,毕竟有向图,不需要逆向处理
                if (processed[y] == false || graph.isDirected) {
                    processEdge(v, y);
                }

                if (discovered[y] == false) {
                    queue.offer(y);
                    //对发现的节点进行相关设置
                    discovered[y] = true;
                    //设置该节点的父节点
                    parent[y] = v;

                }
            }
            //循环处理完相邻节点后,对当前节点进行回归处理
            processVertexLate(v);

        }

    }
}
