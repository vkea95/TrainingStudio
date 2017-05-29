package alg4.com.ch0105;

import edu.princeton.cs.algs4.StdOut;

/**
 * Created by JianZhang on 5/28/17.
 */
public class Ex010512 {
    public static void main(String[] args) {
//        路径压缩算法,如果每次都是用root的话,就没有压缩的可能啦
        QuickUnionPathCompressionUF uf = new QuickUnionPathCompressionUF(10);
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
//        uf.union(3, 0);
//        displayInfo(uf);
//        uf.union(5, 7);
//        displayInfo(uf);
//        uf.union(4, 2);
//        displayInfo(uf);

    }
    private static void displayInfo(QuickUnionPathCompressionUF wuf) {
        StdOut.println(wuf.toString());
    }

}
