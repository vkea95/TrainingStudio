package alg4.com.ch0403;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

/**
 * Created by JianZhang on 8/19/17.
 * 加权无向图的api
 */
public class EdgeWeightedGraph {


    private final int V;
    private int E;
    private Bag<Edge>[] adj;

    public EdgeWeightedGraph(int v) {
        this.V = v;
        this.E = 0;
        adj = (Bag<Edge>[]) new Bag[V];
        for (Bag<Edge> bag : adj) {
            bag = new Bag<>();
        }
    }

//    TODO: tobe done
    public EdgeWeightedGraph(In in) {
        this.V = 0;

    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(Edge e) {
        int v = e.either(), w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        this.E++;
    }

    public Iterable<Edge> edges(int v) {
        return adj[v];
    }

    public Iterable<Edge> edges() {
        Bag<Edge> result = new Bag<>();
        for (int i = 0; i < V; i++) {
            for (Edge e : adj[i]) {
                //TODO:这个很重要,通过if判断,成功地保证每条边只会被加入到结果集中1次,
                if (e.other(i) > i) result.add(e);
            }
        }

        return result;
    }
}
