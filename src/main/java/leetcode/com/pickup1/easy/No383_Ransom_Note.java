package leetcode.com.pickup1.easy;

import org.omg.CORBA.INTERNAL;

/**
 * Created by tclresearchamerica on 9/30/16.
 * ****************************************************
 * Location:
 * ****************************************************
 * Description:
 *  Given  an  arbitrary  ransom  note  string  and  another  string  containing  letters from  all  the  magazines,
 * write  a  function  that  will  return  true  if  the  ransom   note  can  be  constructed  from  the  magazines ;
 *  otherwise,  it  will  return  false.
 * <p>
 * Each  letter  in  the  magazine  string  can  only  be  used  once  in  your  ransom  note.
 * <p>
 * Note:
 * You may assume that both strings contain only lowercase letters.
 * <p>
 * canConstruct("a", "b") -> false
 * canConstruct("aa", "ab") -> false
 * canConstruct("aa", "aab") -> true
 * ****************************************************
 * Thoughts:
 * 题意没有理解清楚,其实只是要考虑是否后面的字符可以构成前面的字符。
 * ****************************************************
 * Hindsight:
 * 统计字符的个数是否满足ransom note
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No383_Ransom_Note {
    public static void main(String[] args) {
        long l = 1;
        int it = 1;
        long max = Integer.MAX_VALUE;
        long startTime = System.currentTimeMillis();   //获取开始时间
        for (long i = 0; i < 1000; i++) {
            for (long j = 0; j < 1000; j++) {
                continue;
            }
        }
        long middeleTime = System.currentTimeMillis();   //获取开始时间

        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                continue;
            }
        }
        long endTime = System.currentTimeMillis();   //获取开始时间
        System.out.println("long cal running time:" + (middeleTime - startTime));
        System.out.println("int cal running time:" + (endTime - middeleTime));


    }

    public boolean canConstruct(String ransomNote, String magazine) {
        int[] count = new int[26];
        for (char c : magazine.toCharArray()) {
            count[c - 'a'] += 1;
        }
        for (char c : ransomNote.toCharArray()) {
            count[c - 'a']--;
            if (count[c - 'a'] < 0) return false;
        }
        return true;
    }
}
