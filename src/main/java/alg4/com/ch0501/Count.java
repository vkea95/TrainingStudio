package alg4.com.ch0501;

import edu.princeton.cs.algs4.Alphabet;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by JianZhang on 8/27/17.
 */
public class Count {
    public static void main(String[] args) {
        Alphabet alpha = new Alphabet(args[0]);
        int R = alpha.radix();
        int[] count = new int[R];

        String s = StdIn.readAll();
        int N = s.length();
        for (int i = 0; i < N; i++) {
            if (alpha.contains(s.charAt(i)))
                count[alpha.toIndex(s.charAt(i))]++;
        }

        for (int c = 0; c < R; c++) {
            StdOut.println(alpha.toChar(c) + " " + count[c]);
        }
    }
}
