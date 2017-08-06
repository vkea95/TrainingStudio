package alg4.com.ch0401;

/**
 * Created by JianZhang on 7/23/17.
 */
public class TwoColor {

    private boolean[] marked;
    private boolean[] color;
    private boolean isTwoColorable = true;

    public TwoColor(Graph G) {
        marked = new boolean[G.V()];
        color = new boolean[G.V()];
        for (int s = 0; s < G.V(); s++) {
            if (!marked[s])
                dfs(G, s);
        }
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                marked[w] = true;
                color[w] = !color[v];
                dfs(G, w);
            } else if (color[w] == color[v]) isTwoColorable = false;
        }
    }

    public boolean isTwoColorable() {
        return isTwoColorable;
    }

}
