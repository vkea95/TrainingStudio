package alg4.com.ch0401;

/**
 * Created by JianZhang on 7/23/17.
 */
public class Cycle {

    private boolean[] marked;
    private boolean hasCycle;

    public Cycle(Graph G) {
        marked = new boolean[G.V()];
        for (int s = 0; s < G.V(); s++) {
            if (!marked[s]) dfs(G, s, s);
        }
    }

    private void dfs(Graph G, int v, int u) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w, v);
            } else if (w != u) {//--->Key condition:已经访问过的,相邻的节点如果不是u,则有环儿===>有2个连通分量的话,就会有问题吧?
                hasCycle = true;
            }
        }
    }

    private boolean isHasCycle() {
        return this.hasCycle;
    }
}
