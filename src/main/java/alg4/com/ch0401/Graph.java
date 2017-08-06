package alg4.com.ch0401;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;

/**
 * Created by JianZhang on 7/23/17.
 */
public class Graph {

    private final int V;//number of the vertexes
    private int E;//number of the edges


    private Bag<Integer>[] adj;

    public Graph(int V) {
        this.V = V;
        this.E = 0;
        adj = new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<>();
        }
    }

    public Graph(In in) {

        try {
//            this(in.readInt());//read the V & graphisize
            this.V = in.readInt();// read the E
            if (V < 0) throw new IllegalArgumentException("number of vertices in a Graph must be nonnegative");
            adj = (Bag<Integer>[]) new Bag[V];
            for (int v = 0; v < V; v++) {
                adj[v] = new Bag<Integer>();
            }
            int E = in.readInt();// read the E
            if (E < 0) throw new IllegalArgumentException("number of edges in a Graph must be nonnegative");

            for (int i = 0; i < E; i++) {
                int v = in.readInt();
                int w = in.readInt();
                validateVertex(v);
                validateVertex(w);
                addEdge(v, w);
            }
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("invalid input format in Graph constructor", e);
        }


    }

    public Graph(Graph g) {
        if (g == null) throw new RuntimeException();
        this.V = g.V();
        this.E = g.E();
        adj = new Bag[this.V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<>();
            for (int temp : g.adj(v)) {
                adj[v].add(temp);

            }
        }
    }

    public int V() {
        return V;
    }

    ;

    public int E() {
        return E;
    }

    public void addEdge(int v, int w) {
        if (v == w) throw new RuntimeException("Self circle");
        if (this.hasEdge(v, w)) throw new RuntimeException("parallel edge");

        adj[w].add(v);
        adj[v].add(w);
        E++;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];

    }

    public boolean hasEdge(int v, int w) {
        if (v >= this.V || v < 0) return false;
        for (int tmp : adj[v]) {
            if (tmp == w) return true;

        }
        return false;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (int index = 0; index < this.adj.length; index++) {
            sb.append(index);
            sb.append(":{");
            for (int tmp : this.adj(index)) {
                sb.append(" ");
                sb.append(tmp);
            }
            sb.append("} \r\n");

        }

        return "Graph{" +
                "Number of vertexes =" + V +
                ", number of edges =" + E + "\r\n" +
                sb.toString() +
                '}';

    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        Graph G = new Graph(in);

        StdOut.println(G);
    }
}
