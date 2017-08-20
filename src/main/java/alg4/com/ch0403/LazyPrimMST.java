package alg4.com.ch0403;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;

/**
 * Created by JianZhang on 8/19/17.
 */
public class LazyPrimMST {
    private boolean[] marked;//MST的顶点
    private Queue<Edge> mst;//MST的边
    private MinPQ<Edge> pq;//横切边(含失效边)

    public LazyPrimMST(EdgeWeightedGraph G) {
        marked = new boolean[G.V()];
        mst = new Queue<>();
        pq = new MinPQ<>();
        //先访问第0个节点

        visit(G, 0);
        while (!pq.isEmpty()) {
            Edge e = pq.delMin();
            int v = e.either(), w = e.other(v);
            if (marked[v] && marked[w]) continue;//跳过无效边
            mst.enqueue(e);//更新mst
            if (!marked[v]) visit(G, v);
            if (!marked[w]) visit(G, w);
        }
    }

    private void visit(EdgeWeightedGraph G, int v) {
        //更新该结点的状态
        marked[v] = true;
        for (Edge e : G.edges(v)) {
            //将未访问的边,放入MinPQ队列中
            if (!marked[e.other(v)]) pq.insert(e);
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }
}
