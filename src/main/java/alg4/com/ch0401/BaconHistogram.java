package alg4.com.ch0401;

import edu.princeton.cs.algs4.StdOut;

/**
 * Created by JianZhang on 7/30/17.
 */
public class BaconHistogram {
    public static void main(String[] args) {
        String filename = args[0];
        String delimiter = args[1];
        String source = args[2];
        SymbolGraph sg = new SymbolGraph(filename, delimiter);
        Graph G = sg.G();//get the graph
        if (!sg.contains(source)) {
            StdOut.println(source + " not in database.");
            return;
        }

        //run breadth-first search from s
        int s = sg.index(source);
        BreadthFirstSearch bfs = new BreadthFirstSearch(G, s);

        // compute histogram of Kevin Bacon numbers - 100 for infinity
        int MAX_BACON = 100;

        int[] hist = new int[MAX_BACON + 1];
        for (int v = 0; v < G.V(); v++) {
            int bacon = Math.min(MAX_BACON, bfs.distTo(v));
            hist[bacon]++;

            // to print actors and movies with large bacon numbers
            if (bacon / 2 >= 7 && bacon < MAX_BACON) {
                StdOut.printf("%d %s\n", bacon / 2, sg.name(v));
            }

        }

        // print out histogram - even indices are actors
        for (int i = 0; i < MAX_BACON; i += 2) {
            if (hist[i] == 0) break;
            StdOut.printf("%3d %8d\n", i/2, hist[i]);
        }
        StdOut.printf("Inf %8d\n", hist[MAX_BACON]);



    }

}
