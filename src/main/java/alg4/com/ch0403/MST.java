package alg4.com.ch0403;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by JianZhang on 8/19/17.
 */
public class MST {
    public MST(EdgeWeightedGraph G) {

    }

    public Iterable<Edge> edges() {
        return null;
    }

    public double weight() {
        return 0.0;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);
        MST mst = new MST(G);
        for (Edge e : mst.edges()) {
            StdOut.println(e);
        }
        StdOut.println(mst.weight());
    }
}
