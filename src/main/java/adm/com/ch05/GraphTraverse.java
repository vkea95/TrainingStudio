package adm.com.ch05;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by tclresearchamerica on 11/16/16.
 * status of vertex: undiscovered, discovered & processed
 * parent of vertex u:
 * Two Points to remember when using GraphTraverse to find a shortest path from x to y;
 * 1. iff GraphTraverse was performed with x as the root of the search
 * 2. iff the graph is unweighted.
 */
public abstract class GraphTraverse {
    public int MAXV = 1000;
    public boolean[] processed = new boolean[MAXV + 1];
    public boolean[] discovered = new boolean[MAXV + 1];

    //重要,parent数组提供了find 路径(须知道起点和终点)的最佳方法
    //因为节点可有多个子节点,但至多只有一个父节点,所以可以通过子节点回溯到父节点
    public int[] parent = new int[MAXV + 1];
    public int totalEdge = 0;

    /*DepthFirstSearch variables*/
    public boolean isFinished;
    public int time = 0;
    //似乎是表示某节点被处理的顺序
    public int[] entryTime = new int[MAXV];
    //似乎是表示某节点被处理的顺序
    public int[] exitTime = new int[MAXV];

    public abstract void traverse(GraphStructure.Graph graph, int start);

    public void initializeSearch(GraphStructure.Graph graph) {
        int i;
        for (i = 1; i < graph.nVertexes; i++) {
            processed[i] = discovered[i] = false;
            parent[i] = -1;//父节点为空
        }
    }

    public void findPath(int start, int end, int parents[]) {
        //boundary check
        if (start == end || end == -1) {
            System.out.printf("\n%d", start);
        } else {
            findPath(start, parents[end], parents);
            System.out.printf(" %d", end);
        }
    }


    public void processVertexLate(int v) {

    }

    public void processVertexEarly(int v) {
        System.out.println("processed vertex " + v);
    }

    public void processEdge(int v, int y) {
        System.out.printf("processed edge (%d,%d)\n", v, y);

        //当然,也可以进行相关操作,例如统计边的个数
        this.totalEdge++;
    }

}
