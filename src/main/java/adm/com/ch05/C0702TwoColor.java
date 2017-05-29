package adm.com.ch05;

/**
 * Created by tclresearchamerica on 11/17/16.
 */

enum Color {
    WHITE, BALCK;
}

public class C0702TwoColor extends BFS {
    //设置成初期状态null
    public Color[] color = new Color[MAXV];
    public boolean bipartite = true;

    public void twoColor(GraphStructure.Graph graph) {
        int i;
        //须确保color数组的初期状态是null
        //assume the graph is bipartite
        bipartite = true;

        initializeSearch(graph);

        for (i = 1; i < graph.nVertexes; i++) {
            if (discovered[i] == false) {
                color[i] = Color.WHITE;
                traverse(graph, i);
            }
        }

    }

    @Override
    public void processEdge(int v, int y) {
        if (color[v] == color[y]) {
            bipartite = false;
            System.out.printf("Warning: not bipartite due to  (%d, %d)\n", v, y);
        }
        //颜色取反
        color[y]=complement(color[v]);
    }

    private Color complement(Color color) {
        if (color == Color.WHITE) return Color.BALCK;
        if (color == Color.BALCK) return Color.WHITE;

        return null;
    }
}
