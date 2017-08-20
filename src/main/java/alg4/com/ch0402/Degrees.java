package alg4.com.ch0402;

import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by JianZhang on 8/6/17.
 * problem:4.2.7
 */
public class Degrees {

    private boolean isMap = false;
    private int[] indegree;
//    private int[] outdegree;

    private Set<Integer> sources;
    private Set<Integer> sinks;
    private boolean[] marked;

    public Degrees(Digraph G) {
        indegree = new int[G.V()];
//        outdegree = new int[G.V()];
        sources = new HashSet<>();
        sinks = new HashSet<>();

        marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v])
                dfs(G, v);
        }

        initiateStatus(G);
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            //此处的入度和出度是不以 是否访问过w为先决条件的。
//            outdegree[v]++;
            indegree[w]++;
            if (!marked[w]) dfs(G, w);
        }
    }

    private void initiateStatus(Digraph G) {
        boolean temp = true;
        for (int v = 0; v < G.V(); v++) {
            if (indegree[v] == 0) sources.add(v);
//            if (outdegree[v] == 0) sinks.add(v);

            if (indegree[v] != 1) temp = false;
        }
        this.isMap = temp;
    }

    public int inDegree(int v) {
        return indegree[v];
    }

//    public int outDegree(int v) {
//        return ad
//    }

    public Iterable<Integer> sources() {
        return sources;
    }

    public Iterable<Integer> sinks() {
        return sinks;
    }

    public boolean isMap() {
        return this.isMap;
    }
}
