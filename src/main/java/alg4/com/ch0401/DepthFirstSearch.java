package alg4.com.ch0401;

/**
 * Created by JianZhang on 7/23/17.
 */
public class DepthFirstSearch {
    private boolean[] marked;
    private int count;

    public DepthFirstSearch(Graph G, int s) {

        marked = new boolean[G.V()];
        dfs(G, s);
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        count++;
        for (int w : G.adj(v)) {
            //此处须增加判断是否走过visited
            if (!marked[w]) dfs(G, w);
        }
    }

    public boolean isMarkded(int w) {
        return marked[w];
    }

    public int count() {
        return count;
    }
}
