package adm.com.ch05;

/**
 * Created by tclresearchamerica on 11/17/16.
 * O(n+m)
 */
public class C0701 {
}

class ConnectedComponents extends GraphTraverse {
    public void traverse(GraphStructure.Graph graph, int start) {

    }

    public void connectedComponents(GraphStructure.Graph graph) {
        int c;//components number
        int i;

        //初期化相关的数据结构
        initializeSearch(graph);

        c = 0;

        for (i = 1; i <= graph.nVertexes; i++) {
            //尚未被BFS算法发现的节点是目标节点
            if (discovered[i] == false) {
                c++;
                System.out.printf("Component %d", c);
                traverse(graph, i);
                System.out.println();
            }
        }


    }

    @Override
    public void processVertexEarly(int v) {
        System.out.printf(" %d", v);
    }

    @Override
    public void processEdge(int x, int y) {

    }
}