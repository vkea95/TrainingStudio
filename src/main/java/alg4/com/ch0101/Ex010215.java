package alg4.com.ch0101;

import edu.princeton.cs.algs4.In;

/**
 * Created by JianZhang on 5/21/17.
 * Based on the method split of class String, implement the In.readInts
 *
 */
public class Ex010215 {
    public static int[] readInts(String name){
        In in=new In(name);
        String input =in.readAll();
        String[] words=input.split("\\s+");//replace blank, space, line break
        int[] ints = new int[words.length];
        for (int i=0;i<ints.length;i++)
            ints[i]=Integer.parseInt(words[i]);
        return ints;
    }
}
