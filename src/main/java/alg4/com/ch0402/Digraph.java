package alg4.com.ch0402;

import edu.princeton.cs.algs4.Bag;

/**
 * Created by JianZhang on 8/5/17.
 * 有向图的处理:基本类似于无向图,但要增加一个reverse图,可以找出"指向"每个顶点的所有边
 * 此时未牵扯到遍历的问题,所以,不要edgeTo和marked这2个数组,但还是需要用adj来表示相邻的节点
 */
public class Digraph {
    private final int V;
    private int E;
    private Bag<Integer>[] adj;

    //    private boolean[] marked;
//    private int[] edgeTo;
    public Digraph(int V) {
        this.V = V;
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
}
