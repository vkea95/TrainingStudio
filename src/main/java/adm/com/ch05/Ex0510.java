package adm.com.ch05;

/**
 * Created by tclresearchamerica on 11/19/16.
 * 此处可做一次优化,即当某节点被处理过后,就可以直接使用其留存下的之前处理的结果,而不必继续dfs
 */
public class Ex0510 extends DFS {
    public int traversal(GraphStructure.Graph graph, int v) {
        GraphStructure.EdgeNode p = null;/*temporary pointer*/
        int y;/*successor vertex*/
//        if (isFinished) return 0;
        discovered[v] = true;

        time = time + 1;
        entryTime[v] = time;

        processVertexEarly(v);
        p = graph.edgeNodes[v];
        //calculating the reuslt
        int[] children = new int[2];
        int i = 0; /*counter*/
        int sumRst;

        while (p != null) {
            y = p.y;
            //邻接节点y没有被发现过
            if (discovered[y] == false) {
                //设置父节点信息
                parent[y] = v;
                //处理临边
                processEdge(v, y);
                //递归掉用dfs遍历整张图
                children[i++] = traversal(graph, y);
            } else if ((!processed[y]) || graph.isDirected) {
                //check exercise 5-6就会明白 即使节点y被处理过,但只要是有向图,就还是要奥继续处理的,毕竟有向图,不需要逆向处理
                //邻接节点y未被处理,或是有向图
                processEdge(v, y);

            }

//            if (isFinished) return 0;

            p = p.next;
        }

        sumRst = processVertexLate(v, children);
        time++;
        exitTime[v] = time;
        processed[v] = true;

        return sumRst;
    }

    /*
    此处只做示意说明,当v时数字时候,直接返回v,反之,则返回处理后的子元素的值
    * */
    public int processVertexLate(int v, int[] children) {
        if (v == 0) return v;

        else return children[0] + children[1];

    }

}
