package alg4.com.ch0402;

/**
 * Created by JianZhang on 8/6/17.
 * 计算强连通分量的Kosaraju算法
 * 证明和理解很困难, 1.取原图的反向图的逆序,2.按照逆序进行dfs遍历,dfs里面按照邻接点进行遍历
 */
public class KosarajuSCC {
    private boolean[] marked;
    private int count;
    private int[] id;

    public KosarajuSCC(Digraph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];

        DepthFirstOrder depthFirstOrder = new DepthFirstOrder(G.reverse()); // step 1: top logical
        for (int v : depthFirstOrder.reversePostOrder()) {// step 2: get reversePostOrder
            if (!marked[v]) dfs(G, v);
            count++;
        }
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : G.adj(v)) {
            if (!marked[w]) dfs(G, w);
        }
    }

    public boolean isStrongConnected(int w, int v) {
        return id[w] == id[v];
    }

    public int id(int v) {
        return id[v];
    }

    public int count() {
        return count;
    }
}
