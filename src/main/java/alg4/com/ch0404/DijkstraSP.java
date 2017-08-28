package alg4.com.ch0404;

import edu.princeton.cs.algs4.IndexMinPQ;

import java.util.Stack;

/**
 * Created by JianZhang on 8/20/17.
 */
public class DijkstraSP {

    private double[] distTo;
    private DirectedEdge edgeTo[];
    private IndexMinPQ<Double> pq;

    public DijkstraSP(EdgeWeightedDigraph G, int s) {
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        pq = new IndexMinPQ<>(G.V());

        for (int i = 0; i < G.V(); i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;//default definition
//        edgeTo[s] = null;//default definition
        while (!pq.isEmpty())
            relax(G, pq.delMin());

    }

    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public double distTo(int v) {
        return distTo[v];
    }

    public Iterable<DirectedEdge> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<DirectedEdge> path = new Stack<>();

        for (DirectedEdge edge = edgeTo[v]; edge != null; edge = edgeTo[edge.from()]) {
            path.push(edge);
        }

        return path;

    }

    /*
    边的松弛
     */
    private void relax(DirectedEdge e) {
        int from = e.from(), to = e.to();
        if (distTo[to] > distTo[from] + e.getWeight()) {
            distTo[to] = distTo[from] + e.getWeight();
            edgeTo[to] = e;
        }
    }

    private void relax(EdgeWeightedDigraph G, int v) {
        for (DirectedEdge e : G.adj(v)) {
            int w = e.to();
            if (distTo[w] > distTo[v] + e.getWeight()) {
                distTo[w] = distTo[v] + e.getWeight();
                edgeTo[w] = e;
                if (pq.contains(w)) pq.change(w, distTo(w));
                else pq.insert(w, distTo(w));
            }
        }
    }
}
