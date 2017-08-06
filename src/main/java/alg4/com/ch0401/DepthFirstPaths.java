package alg4.com.ch0401;

import java.util.Stack;

/**
 * Created by JianZhang on 7/23/17.
 */
public class DepthFirstPaths {
    private boolean[] marked;
//    private int count; useless property, maybe use it in 连通分量的题目中
    private int[] edgeTo;//the previous point in the specified path whose start point is s
    private final int s;//start point

    public DepthFirstPaths(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        dfs(G, s);
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;

        for (int w : G.adj(v)) {
            if (!marked[w]) {
                // point 1: the value of the array element is the previous point
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        //check
        if (!hasPathTo(v)) return null;
        Stack<Integer> stack = new Stack<>();
        //用的是倒序的方式,将路径检索出来,这样的用法才会符合edgeTo这个数组的设计初衷
        for (int x = v; x != s; x = edgeTo[x]) {
            stack.push(x);
        }
        //最后将开始节点放入栈中
        stack.push(s);
        return stack;
    }
}
