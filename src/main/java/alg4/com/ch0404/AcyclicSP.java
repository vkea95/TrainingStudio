package alg4.com.ch0404;

import alg4.com.ch0402.TopLogical;

import java.util.Stack;

/**
 * Created by JianZhang on 8/20/17.
 */
public class AcyclicSP {
    private DirectedEdge[] edgeTo;
    private double[] distTo;

    public AcyclicSP(EdgeWeightedDigraph G, int s) {
        edgeTo = new DirectedEdge[G.V()];
        distTo = new double[G.V()];

        for (int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;

        distTo[s] = 0.0;
//TODO: finish sources below with toplogical tech
//        TopLogical top = new TopLogical(G);
//        for (int v:top.order()){
//          relax(G,v);
//        }


    }


    private void relax(EdgeWeightedDigraph G, int v) {
        for (DirectedEdge e : G.adj(v)) {
            int w = e.to();
            if (distTo[w] > distTo[v] + e.getWeight()) {
                distTo[w] = distTo[v] + e.getWeight();
                edgeTo[w] = e;
                //TODO:此处不需要使用pq!!!
//                if (pq.contains(w)) pq.change(w, distTo(w));
//                else pq.insert(w, distTo(w));
            }
        }
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


}
