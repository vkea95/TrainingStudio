package alg4.com.ch0101;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by JianZhang on 5/20/17.
 */
public class Ex010206 {
    public static void main(String[] args) {
        String s = StdIn.readString();
        String t = StdIn.readString();
        if (s==null||t==null||s.length()!=t.length()){
            StdOut.println("unmatched");
            return;
        }

        for (int i=0;i<s.length();i++){
            if (s.indexOf(t)==0){
                StdOut.println("matched");
                return;

            }
        }
        StdOut.println("unmatched");
    }
}
