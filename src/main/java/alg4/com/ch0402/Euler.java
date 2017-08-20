package alg4.com.ch0402;

import java.util.Iterator;
import java.util.Stack;

/**
 * Created by JianZhang on 8/6/17.
 * This program is not finished,
 */
public class Euler {
    private Stack<Integer> cycle = null;  // Eulerian cycle; null if no such cylce

    public Euler(Digraph G) {
        //at least one edge
        if (G.E() == 0) return;

        for (int i = 0; i < G.V(); i++) {
//TODO:finish this program,
            if (G.indegree(i) != G.outdegree(i)) return;
        }

        // create local view of adjacency lists, to iterate one vertex at a time
        Iterator<Integer>[] adj = (Iterator<Integer>[]) new Iterator[G.V()];        //-----> point 要用iterable

        // initialize stack with any non-isolated vertex
        int s = nonIsolatedVertex(G);
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(s);

        // greedily add to putative cycle, depth-first search style
        cycle = new Stack<Integer>();
        while (!stack.isEmpty()) {
            int v = stack.pop();        //-----> 继续向外扩散寻找点
            while (adj[v].hasNext()) {
                stack.push(v);
                v = adj[v].next();       //-----> 继续向外扩散寻找点
            }
            // add vertex with no more leaving edges to cycle
            cycle.push(v);//

        }
        // check if all edges have been used
        // (in case there are two or more vertex-disjoint Eulerian cycles)
        if (cycle.size() != G.E() + 1)       //-----> 必须保证环的条件是边数+1
            cycle = null;
    }

    private int nonIsolatedVertex(Digraph G) {
        for (int v = 0; v < G.V(); v++) {
            if (G.outdegree(v) > 0)
                return v;

        }
        return -1;
    }
}
