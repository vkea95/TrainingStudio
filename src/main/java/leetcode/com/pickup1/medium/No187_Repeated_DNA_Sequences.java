package leetcode.com.pickup1.medium;

import leetcode.com.util.ListNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by tclresearchamerica on 7/9/16.
 * ****************************************************
 * Location:
 * ****************************************************
 * Description:
 * All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When
 * studying DNA, it is sometimes useful to identify repeated sequences within the DNA.
 * Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.
 * Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",
 * Return:
 * ["AAAAACCCCC", "CCCCCAAAAA"].
 * ****************************************************
 * Thoughts:
 * 1.重复出现的这10个字符,有可能含有另外的重复出现的10个字符中的部分
 * 2.可以用String的indexOf来完成部分寻找,然后将数据压入list中
 * 3.果然TLE,才是要解决的主要问题呢!所以该如何解决的呢?moving window?
 * 4.结果是用2个HashSet解决问题,不要在每次都到字符串中寻找.第一遍的的解答中,用到了编码,想到于10一个四进制数字的code,所以保证了编码的唯一性.
 * ****************************************************
 * Time: 40 mins
 * Beat: 96%
 * Bug: 3
 * ****************************************************
 * Hindsight:
 * 1.从网上搞得另一个答案,其实解答方法类似,依然考虑了4进制的编码方式,每个字母自小到大,表示1,2,3和4
 * 每次读入一个字符就移动2个bit,这样需要20个bit来完成一次读入的编码.
 * 然后通过向HashSet进行添加字符的方法返回值来判断是否添加成功
 * 2.然我们来做一次改良,每次循环都是要从v的低位开始,其实没有必要,我们只要将它的高位和00进行与操作,然后就可以循环搞了,不必再从头来过
 * 3.将答案放在hashSet中进行判断,再取结果的方式没准更快
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No187_Repeated_DNA_Sequences {


    public List<String> findRepeatedDnaSequences_fastest(String s) {
        Set<Integer> words = new HashSet<>();
        Set<Integer> doubleWords = new HashSet<>();

        List<String> rv = new ArrayList<>();
        char[] map = new char[26];
        //map['A' - 'A'] = 0;
        map['C' - 'A'] = 1;
        map['G' - 'A'] = 2;
        map['T' - 'A'] = 3;

        int v = 0;
        int mod = 3;
        //初次的时候,只是在v中保留9个位数,然后留在后续处理

        for (int j = 0; j < s.length() && j < 9; j++) {
            v <<= 2;

            mod <<= 2;
            //bug1:没有给mod补位
            mod |= 3;
            v |= map[s.charAt(j) - 'A'];
        }
        //bug2:mod没有回退,导致结果不正确
        mod >>= 2;
        for (int i = 9; i < s.length(); i++) {
            v &= mod;
            v <<= 2;
            v |= map[s.charAt(i) - 'A'];


            //opt1:这个用2次add的方式导致,不必保存i的值,只要将value存入结果集中即可.
            //这个方法好,用!配合2个HashSet就可以解决重复插入的问题啦
            if (!words.add(v) && doubleWords.add(v)) {
                // bug3:subString的开始和终了位置取值有问题,目前该从i-9,然后一直取到i+1
                rv.add(s.substring(i - 9, i + 1));
            }
        }


        return rv;
    }


    public List<String> findRepeatedDnaSequences_faster(String s) {
        Set<Integer> words = new HashSet<>();
        Set<Integer> doubleWords = new HashSet<>();
        List<String> rv = new ArrayList<>();
        char[] map = new char[26];
        //map['A' - 'A'] = 0;
        map['C' - 'A'] = 1;
        map['G' - 'A'] = 2;
        map['T' - 'A'] = 3;

        for (int i = 0; i < s.length() - 9; i++) {
            int v = 0;
            for (int j = i; j < i + 10; j++) {
                v <<= 2;
                v |= map[s.charAt(j) - 'A'];
            }
            if (!words.add(v) && doubleWords.add(v)) {
                rv.add(s.substring(i, i + 10));
            }
        }
        return rv;
    }

    public List<String> findRepeatedDnaSequences_TLE(String s) {
        List<String> result = new ArrayList<>();
        HashSet<String> set = new HashSet<>();
        HashSet<String> dna = new HashSet<>();

        for (int i = 0; i <= s.length() - 10; i++) {
            //bug:将寻址位置的start改为i+1
            if (set.contains(s.substring(i, i + 10))) {
                dna.add(s.substring(i, i + 10));
            } else {
                set.add(s.substring(i, i + 10));
            }

        }
        for (String ss : dna) {
            result.add(ss);
        }

        return result;
    }
}
