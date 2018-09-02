package adm.com.ch05;

import adm.com.ch08.EditDistance;

/**
 * Created by tclresearchamerica on 11/16/16.
 */

public class GraphStructure {
    public int MAXV = 1000;/*maxium number of vertexes*/


    public void readGrapy(boolean isDirected) {
        int i;  /* counter */
        int m;/* number of edges */
        int x, y;/* vertices in edge (x,y) */

        Graph graph = buildGrapy(isDirected);
        //read number of vertices from console
        //注意:此时就该完成了对graph.nVertexes的设置操作啦!!!!
        graph.nVertexes = 0;//??????


        //read (x,y) edge pair


    }

    public void insertEdge(Graph graph, int x, int y, boolean isDirected) {

        //生成一个端点,weight为0,终点为y!!!!!!!!!!重要!!!!
        EdgeNode edgeNode = new EdgeNode(y, 0);
        edgeNode.next = graph.edgeNodes[x];
        //此时是按照链表的方式进行插入操作呢,新的节点会排在之前的节点的前面!!!!!!!!!!重要!!!!
        graph.edgeNodes[x] = edgeNode;/*insert at head of indexList !!!!*/

        graph.degrees[x]++;/**/

        if (isDirected) {
            //如果有是有向图的话,就到此为止,不必为终点再构造边了.
            // 但是对边的数量要进行一次+1操作,无向图只在对后一个节点进行处理的时候,进行+1操作
            graph.nEdges++;
        } else {
            //如果是无向图的话,还要从另一个端点构造边,当然最后还要对总结点数进行+1操作
            insertEdge(graph, y, x, true);
        }

    }

    public void printGraph(Graph graph) {
        int i;
        for (i = 1; i <= graph.nVertexes; i++) {
            System.out.print(i);
            EdgeNode edgeNode = graph.edgeNodes[i];
            while (edgeNode != null) {
                System.out.print(" " + edgeNode.y);
                edgeNode = edgeNode.next;
            }
            System.out.println();
        }
    }


    public Graph buildGrapy(boolean isDirected) {
        return new Graph(isDirected);

    }

    public class EdgeNode {
        int y;/* adjacency info 邻接点*/
        int weight;/* edge weight, if any */
        EdgeNode next;/* next edge in indexList */

        public EdgeNode(int y, int weight) {
            this.y = y;
            this.weight = weight;
        }
    }

    class Graph {

        EdgeNode[] edgeNodes = new EdgeNode[MAXV + 1];/* adjacency info */
        int[] degrees = new int[MAXV + 1];/* outdegree of each vertex */
        int nVertexes;/* number of vertices in graph */
        int nEdges;/* number of edges in graph */
        boolean isDirected;/* is the graph directed? */

        public Graph(boolean isDirected) {
            this.isDirected = isDirected;
            this.nVertexes = 0;
            this.nEdges = 0;
            for (int degree : degrees) {
                degree = 0;
            }
            for (EdgeNode edge : edgeNodes) {
                edge = null;
            }
        }
    }

}

