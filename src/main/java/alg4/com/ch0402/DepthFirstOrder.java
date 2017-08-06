package alg4.com.ch0402;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import leetcode.com.util.Interval;

import java.util.Stack;

/**
 * Created by JianZhang on 8/6/17.
 * 有向图中集于深度优先搜索的顶点排序问题: 前序(队列),后序(队列),逆后序(栈)
 * 这个序列是何顶点相对于操作dfs方法的时间点
 * <p>
 * 这在高级的有向图处理算法中非常有用
 */
public class DepthFirstOrder {
    private Queue<Integer> preOrderQueue;
    private Queue<Integer> postOrderQueue;
    private Stack<Integer> reversePostOrderStack;
    private boolean[] marked;

    public DepthFirstOrder(Digraph G) {
        postOrderQueue = new Queue<>();
        preOrderQueue = new Queue<>();
        reversePostOrderStack = new Stack<>();
        marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) dfs(G, v);
        }

    }

    public void dfs(Digraph G, int v) {
        preOrderQueue.enqueue(v);

        for (int w : G.adj(v)) {
            if (!marked[w]) dfs(G, w);
        }

        postOrderQueue.enqueue(v);
        reversePostOrderStack.push(v);

    }

    public Iterable<Integer> preOrder() {
        return preOrderQueue;
    }

    public Iterable<Integer> postOrder() {
        return postOrderQueue;
    }

    public Iterable<Integer> reversePostOrder() {
        return reversePostOrderStack;
    }
}
