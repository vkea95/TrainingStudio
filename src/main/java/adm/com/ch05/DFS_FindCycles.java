package adm.com.ch05;

/**
 * Created by tclresearchamerica on 11/19/16.
 */
public class DFS_FindCycles extends DFS {
    @Override
    public void processEdge(int x, int y) {
        if (parent[x] != y) {/*found back edge!*/
            System.out.printf("Cycle from %d to %d:", y, x);
            findPath(y, x, parent);
            System.out.println();
            isFinished = true;
        }
    }
}
