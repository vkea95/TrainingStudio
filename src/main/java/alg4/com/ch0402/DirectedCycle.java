package alg4.com.ch0402;

import alg4.com.ch0401.Cycle;

import java.util.Stack;

/**
 * Created by JianZhang on 8/5/17.
 * 寻找有向环
 */
public class DirectedCycle {
    private boolean[] marked;
    private int[] edgeTo;

    private Stack<Integer> cycle;// all the points in the cycle, start-point & end-point are same
    boolean[] onStack;

    public DirectedCycle(Digraph g) {
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];

        onStack = new boolean[g.V()];
        for (int v = 0; v < g.V(); v++) {//次

            if (!marked[v])
                dfs(g, v);

        }
    }

    private void dfs(Digraph g, int v) {
        marked[v] = true;
        onStack[v] = true;

        for (int w : g.adj(v)) {
            //首要条件:要判断是否存在cycle  !!!!---> keyPoint 1
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(g, w);
            } else if (onStack[w]) {
                //有cycle!!!
                cycle = new Stack<>();
                //构建stack,注意for里面的条件,是从x=v,直到x!=w   !!!!---> keyPoint 2
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }


        }


        onStack[v] = false;//在离开dfs的时候,需要剔除stack标志!!!!  !!!---> keyPoint 3
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }
}
