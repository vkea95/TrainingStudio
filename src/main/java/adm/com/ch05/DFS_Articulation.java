package adm.com.ch05;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

/**
 * Created by tclresearchamerica on 11/19/16.
 */
public class DFS_Articulation extends DFS {

    public final int BACK = 0;
    public final int TREE = 0;
    public int[] reachableAncestor = new int[MAXV + 1];/*earliest reachable ancestor of v*/
    public int[] treeOutDegree = new int[MAXV + 1]; /*DFS tree outdegree of v*/

    @Override
    public void processVertexEarly(int v) {
        reachableAncestor[v] = v;
    }


    public void processVertexLate(int v) {
        boolean isRoot; /* is the vertex the root of the DFS tree? */
        int time_v; /* earliest reachable time for v */
        int time_parent; /* earliest reachable time for parent[v] */

        if (parent[v] < 1) { /*test if v is the root */
            if (treeOutDegree[v] > 1) {
                System.out.printf("root articulation vertex: %d \n", v);
                return;
            }

        }

        isRoot = (parent[parent[v]] < 1); /* test if the parent[v] is the root*/
        if ((reachableAncestor[v] == parent[v]) && (!isRoot)) {
            System.out.printf("parent articulation vertex: %d \n", parent[v]);
        }

        if (reachableAncestor[v] == v) {
            System.out.printf("bridge articulation vertex: %d \n", parent[v]);

            if (treeOutDegree[v] > 0)/*test if v is not a leaf*/
                System.out.printf("bredge articulation vertex: %d \n", v);
        }

        time_v = entryTime[reachableAncestor[v]];
        time_parent = entryTime[reachableAncestor[parent[v]]];
        if (time_v < time_parent)
            reachableAncestor[parent[v]] = reachableAncestor[v];
    }

    @Override
    public void processEdge(int x, int y) {
        int edgeClass;/*edge class*/
        edgeClass = edgeClassification(x, y);

        if (edgeClass == TREE) {
            treeOutDegree[x]++;
        }
        if ((edgeClass == BACK) && (parent[x] != y)) {
            //这个代表是回退边,且是环!!!
            //此时,将y设为x的祖先
            if (entryTime[y] < entryTime[reachableAncestor[x]]) {
                reachableAncestor[x] = y;
            }
        }
    }

    //区分边的种类,TREE? BACK? or 其他
    public int edgeClassification(int x, int y) {

        return TREE;
    }

}
