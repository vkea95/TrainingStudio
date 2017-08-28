package alg4.com.ch0404;

import alg4.com.ch0402.Digraph;
import alg4.com.ch0403.Edge;
import edu.princeton.cs.algs4.Bag;

/**
 * Created by JianZhang on 8/19/17.
 */
public class EdgeWeightedDigraph  {

    private final int V;
    private int E;
    private Bag<DirectedEdge> adj[];

    public EdgeWeightedDigraph(int v) {
        this.V = v;
        this.E = 0;

        adj = (Bag<DirectedEdge>[]) new Bag[V];
        for (int i = 0; i < v; i++) {
            adj[i] = new Bag<>();
        }


    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }


    public void addEdge(DirectedEdge edge) {
        int from = edge.from();
        adj[from].add(edge);
        E++;
    }


    public Iterable<DirectedEdge> adj(int v) {
        return adj[v];
    }

    public Iterable<DirectedEdge> adj() {
        Bag<DirectedEdge> bag = new Bag<>();
        for (int i = 0; i < V; i++) {
            for (DirectedEdge edge : adj[i])
                bag.add(edge);
        }
        return bag;
    }

}
