package alg4.com.ch0105;

import edu.princeton.cs.algs4.StdOut;

/**
 * Created by JianZhang on 5/28/17.
 */
public class Ex010503 {
    public static void main(String[] args) {
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(10);
//        uf.union(9, 0);
//        displayInfo(uf);
//        uf.union(3, 4);
//        displayInfo(uf);
//        uf.union(5, 8);
//        displayInfo(uf);
//        uf.union(7, 2);
//        displayInfo(uf);
//        uf.union(1, 2);
//        displayInfo(uf);
//        uf.union(3, 0);
//        displayInfo(uf);
//        uf.union(5, 7);
//        displayInfo(uf);
//        uf.union(4, 2);
//        displayInfo(uf);


        uf.union(0, 1);
        displayInfo(uf);
        uf.union(1, 2);
        displayInfo(uf);
        uf.union(3, 4);
        displayInfo(uf);
        uf.union(2, 4);
        displayInfo(uf);
        uf.union(4, 5);
        displayInfo(uf);
    }

    private static void displayInfo(WeightedQuickUnionUF wuf) {
        StdOut.println(wuf.toString());
    }
}


