package adm.com.ch08;

import org.omg.CORBA.PUBLIC_MEMBER;

/**
 * Created by tclresearchamerica on 11/13/16.
 * ADM Pg 280
 */

enum Operations {
    MATCH(0), INSERT(1), DELETE(2);
    private int value;

    Operations(int value) {
        this.value = value;
    }
}

public class StringMatching_Greedy {

    public final int MATCH = 0;
    public final int INSERT = 1;
    public final int DELETE = 2;

    /*

    */
    public int string_compare(String s, String t, int i, int j) {
        int k; /*counter*/
        int[] operation = new int[3]; /*cost of three options:*/
        int lowest_cot; /*lowest cost*/

        if (i == 0) return (j * indel(' '));
        if (j == 0) return (i * indel(' '));
        operation[MATCH] = string_compare(s, t, i - 1, j - 1) + match(s.charAt(i), t.charAt(j));
        operation[INSERT] = string_compare(s, t, i, j - 1) + indel(t.charAt(j));
        operation[DELETE] = string_compare(s, t, i - 1, j) + indel(s.charAt(i));

        lowest_cot = operation[MATCH];
        for (k = INSERT; k <= DELETE; k++) {
            if (operation[k] < lowest_cot) lowest_cot = operation[k];
        }

        return lowest_cot;
    }

    /*

    */
    public int match(char c1, char c2) {
        return 0;
    }

    /*

     */
    public int indel(char c) {
        return 1;
    }
}
