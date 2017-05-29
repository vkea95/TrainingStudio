package leetcode.com.medium.part12;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by tcl on 2016/3/31.
 * Location:
 * https://leetcode.com/problems/repeated-dna-sequences/
 * *************************************************************
 * Description:
 * All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG".
 * When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.
 * Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.
 * For example,
 * Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",
 * Return:
 * ["AAAAACCCCC", "CCCCCAAAAA"].
 * *************************************************************
 * Solution:
 * 完全不理解，为什么编码可以解决这个问题。。。
 */
public class No187_Repeated_DNA_Sequences {

    public int encode(String s) {
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'A') sum *= 4;
            else if (s.charAt(i) == 'C') sum = 4 * sum + 1;
            else if (s.charAt(i) == 'G') sum = 4 * sum + 2;
            else sum = 4 * sum + 3;
        }
        return sum;
    }

    public List<String> findRepeatedDnaSequences(String s) {
        HashSet<Integer> hash = new HashSet<>();
        HashSet<String> dna = new HashSet<>();
        for (int i = 9; i < s.length(); i++) {
            String subString = s.substring(i - 9, i + 1);
            int encoded = encode(subString);
            if (hash.contains(encoded)) {
                dna.add(subString);
            } else {
                hash.add(encoded);
            }
        }

        List<String> result = new ArrayList<>();
        for (String d : dna) {
            result.add(d);
        }
        return result;
    }
}
