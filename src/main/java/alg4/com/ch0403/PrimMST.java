package alg4.com.ch0403;

import edu.princeton.cs.algs4.IndexMinPQ;

/**
 * Created by JianZhang on 8/19/17.
 */
public class PrimMST {
    private Edge[] edgeTo;//距离MST最近的边
    private double[] distTo;//distTo[w]=edgeTo[w].weight()
    private boolean[] marked;
    private IndexMinPQ<Double> pq;//有效的横切边

    public PrimMST(EdgeWeightedGraph G) {
        edgeTo = new Edge[G.V()];
        marked = new boolean[G.V()];
        distTo = new double[G.V()];
        for (int i = 0; i < distTo.length; i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }

//        开始访问图,通过自行插入队列的方式完成
        distTo[0] = 0.0;
        pq.insert(0, 0.0);
        while (!pq.isEmpty())
            visit(G, pq.delMin());//将最近的顶点加入MST中

    }

    private void visit(EdgeWeightedGraph G, int v) {
        marked[v] = true;
        for (Edge e : G.edges(v)) {
            int w = e.other(v);
            if (marked[w]) continue;

            if (e.weight() < distTo[w]) {
                edgeTo[w] = e;
                distTo[w] = e.weight();
                if (pq.contains(w)) pq.changeKey(w, e.weight());
                else pq.insert(w, e.weight());
            }
        }
    }
}
