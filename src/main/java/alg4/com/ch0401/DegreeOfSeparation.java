package alg4.com.ch0401;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by JianZhang on 7/23/17.
 */
public class DegreeOfSeparation {

    public static void main(String[] args) {
        SymbolGraph sg = new SymbolGraph(args[0], args[1]);

        Graph G = sg.G();
        String source = args[2];
        if (!sg.contains(source)) {
            StdOut.print(source + " not here");
            return;
        }
        int s = sg.index(source);
        BreadthFirstSearch bfs = new BreadthFirstSearch(G, s);
        while (!(StdIn.isEmpty())) {
            String sink = StdIn.readLine();
            if (sg.contains(sink)) {
                int t = sg.index(sink);
                if (bfs.hasPathTo(t)) {
                    for (int v : bfs.pathTo(t)) {
                        StdOut.println("   " + sg.name(v));
                    }
                }
            } else {
                StdOut.print("not in database");
            }
        }
    }
}
