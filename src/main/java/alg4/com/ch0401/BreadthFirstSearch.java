package alg4.com.ch0401;

import edu.princeton.cs.algs4.Queue;

import java.util.Stack;

/**
 * Created by JianZhang on 7/23/17.
 */
public class BreadthFirstSearch {
    private int distTo[];
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;

    public BreadthFirstSearch(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        distTo = new int[G.V()];
        initShortestPath();
        this.s = s;
        this.bfs(G, s);
    }

    private void initShortestPath() {
        for (int i : distTo) i = Integer.MAX_VALUE;
    }

    /*
        作者的方法比较好,直接用shortestPath之前的计算value,就可以搞定衍生点了,
        而我的还要用queue,还是需要对图的数据结构的属性深刻理解才行啊
     */
    private void bfs(Graph G, int s) {
        Queue<Integer> queue = new Queue<>();
//        Queue<Integer> pathLengthQueue = new Queue<>();
        marked[s] = true;
        distTo[s] = 0;
        queue.enqueue(s);
//        pathLengthQueue.enqueue(0);
        while (!queue.isEmpty()) {
            int v = queue.dequeue();
//            int pathLength = pathLengthQueue.dequeue()+1;
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    marked[w] = true;
                    distTo[w] = distTo[v] + 1; //-->key point
                    edgeTo[w] = v; //save and edge of the shortest path
                    queue.enqueue(w);
//                    pathLengthQueue.enqueue(distTo[v]);
                }
            }
        }
    }

    private void bfs_zj(Graph G, int s) {
        Queue<Integer> queue = new Queue<>();
        Queue<Integer> pathLengthQueue = new Queue<>();
        marked[s] = true;
        distTo[s] = 0;
        queue.enqueue(s);
        pathLengthQueue.enqueue(0);
        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            int pathLength = pathLengthQueue.dequeue() + 1;
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    marked[w] = true;
                    distTo[w] = pathLength;
                    edgeTo[w] = v; //save and edge of the shortest path
                    queue.enqueue(w);
                    pathLengthQueue.enqueue(distTo[v]);
                }
            }
        }
    }

    // breadth-first search from multiple sources
    private void bfs(Graph G, Iterable<Integer> sources) {
        Queue<Integer> q = new Queue<Integer>();
        for (int s : sources) {
            marked[s] = true;
            distTo[s] = 0;
            q.enqueue(s);
        }
        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    q.enqueue(w);
                }
            }
        }
    }

    public boolean hasPathTo(int v) {
        validateVertex(v);

        return marked[v];
    }

    /**
     * Returns a shortest path between the source vertex {@code s} (or sources)
     * and {@code v}, or {@code null} if no such path.
     *
     * @param v the vertex
     * @return the sequence of vertices on a shortest path, as an Iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<Integer> pathTo(int v) {
        validateVertex(v);
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        int x;
        for (x = v; distTo[x] != 0; x = edgeTo[x])
            path.push(x);
        path.push(x);
        return path;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertices(Iterable<Integer> vertices) {
        if (vertices == null) {
            throw new IllegalArgumentException("argument is null");
        }
        int V = marked.length;
        for (int v : vertices) {
            if (v < 0 || v >= V) {
                throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
            }
        }
    }


//    public Iterable<Integer> pathTo(int v) {
//        //check
//        if (!hasPathTo(v)) return null;
//        Stack<Integer> stack = new Stack<>();
//        //用的是倒序的方式,将路径检索出来,这样的用法才会符合edgeTo这个数组的设计初衷
//        for (int x = v; x != s; x = edgeTo[x]) {
//            stack.push(x);
//        }
//        //最后将开始节点放入栈中
//        stack.push(s);
//        return stack;
//    }


    /*
    return the shortest path length from start point to dest point
     */
    public int distTo(int dest) {
        validateVertex(dest);

        return distTo[dest];

    }

}
