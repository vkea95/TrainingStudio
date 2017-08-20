package alg4.com.ch0402;

import edu.princeton.cs.algs4.Bag;

import java.util.Stack;

/**
 * Created by JianZhang on 8/5/17.
 * 有向图的处理:基本类似于无向图,但要增加一个reverse图,可以找出"指向"每个顶点的所有边
 * 此时未牵扯到遍历的问题,所以,不要edgeTo和marked这2个数组,但还是需要用adj来表示相邻的节点
 */
public class Digraph {
    private final int V;
    private int E;
    private Bag<Integer>[] adj;
    private int[] indegree;
//    private int[] outdegree;--->这个可以通过adj.size获得

    //    private boolean[] marked;
//    private int[] edgeTo;
    public Digraph(int V) {
        this.V = V;
        indegree = new int[V];
//        outdegree = new int[V];
        adj = (Bag<Integer>[]) new Bag[V];//此时,数组中的每个元素还没有被实例化呢哦,要注意!!!
        for (int v = 0; v < V; v++)
            adj[v] = new Bag<>();
    }

    public int V() {
        return this.V;
    }

    public int E() {
        return this.E;
    }

    public void addEdge(int v, int w) {
        if (v == w) throw new RuntimeException("Self circle");
        if (this.hasEdge(v, w)) throw new RuntimeException("parallel edge");
        indegree[w]++;
//        outdegree[v]++;
        adj[v].add(w);
        E++;

    }

    public Digraph reverse() {
        Digraph r = new Digraph(V);
        for (int v = 0; v < V; v++) {
            for (int w : adj[v])
                r.addEdge(w, v);

        }
        return r;
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

    public Digraph clone() {
        Digraph clone = new Digraph(V);
        for (int v = 0; v < V; v++) {
            for (int w : this.adj(v))
                clone.addEdge(v, w);
        }
        return clone;
    }

    public Digraph(Digraph G) {
        this(G.V());
        this.E = G.E();

        for (int v = 0; v < G.V(); v++) {
            this.indegree[v] = G.indegree(v);
        }
        for (int v = 0; v < G.V(); v++) {
            Stack<Integer> reverse = new Stack<>();
            for (int w : G.adj(v)) {
                reverse.push(w);
            }
            for (int w : reverse) {
                adj[v].add(w);
            }
        }

    }

    /**
     * Returns the number of directed edges incident to vertex {@code v}.
     * This is known as the <em>indegree</em> of vertex {@code v}.
     *
     * @param v the vertex
     * @return the indegree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int indegree(int v) {
        validateVertex(v);
        return indegree[v];
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }

    /**
     * Returns the number of directed edges incident from vertex {@code v}.
     * This is known as the <em>outdegree</em> of vertex {@code v}.
     *
     * @param v the vertex
     * @return the outdegree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int outdegree(int v) {
        validateVertex(v);
        return adj[v].size();
    }

}
