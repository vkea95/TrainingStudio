package leetcode.com.tag.sort;

import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by JianZhang on 9/7/17.
 * Given a indexList of non negative integers, arrange them such that they form the largest number.
 * <p>
 * For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.
 * <p>
 * Note: The result may be very large, so you need to return a string instead of an integer.
 * thought:
 * 1. 不能够纯粹使用sort解决问题,同样size大小的数字,越大越好。首位越大越好,首位相同比较次位,
 * 2. 考虑给sort的comparator换个内核,用按位比较来排序,若是某位相同,但是长度不同,若某数的后位大于前卫,则它大,反之则它小
 * 3
 * bugs:
 * 1. 处理第二个子串的时候要和第一个字符串不一样
 * 2. 两个字符串大小不一致的时候,第i个字符要和第0个字符比较,
 * 3. 两个字符串大小不一致的时候,第i个字符要和第0个字符比较,且不用"="
 * 4. 处理"00"的case,要额外处理下
 * 5. xiaobo 说的对,应该用2个字符串连接的方式比较大小,就简单易行了,但是要小心别溢出!!!!
 * 6. 更简单的就是直接用字符串的比较方式进行比较
 */
public class No179_Largest_Number {

    public String largestNumber(int[] nums) {
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = Integer.toString(nums[i]);
        }
        Arrays.sort(strs, new NumbersComparator());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strs.length; i++) {
            sb.append(strs[i]);
        }
        return Integer.valueOf(strs[0]) == 0 ? "0" : sb.toString();
    }

    class NumbersComparator implements Comparator<String> {
        @Override
        public int compare(String s1, String s2) {
            return (s2 + s1).compareTo(s1 + s2);
        }
    }


    public String largestNumber_zj(int[] nums) {
        if (nums == null) return "0";
        Integer[] numInt = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) numInt[i] = nums[i];

        Collections.sort(Arrays.asList(numInt), new BitComparator());
        if (numInt[numInt.length - 1] == 0) return "0";

        StringBuilder sb = new StringBuilder();
        for (int i = numInt.length - 1; i >= 0; i--) {
            sb.append(numInt[i]);
        }
        return sb.toString();
    }

    class BitComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            String s1 = Integer.toString(o1) + Integer.toString(o2);
            String s2 = Integer.toString(o2) + Integer.toString(o1);
            return (s1).compareTo(s2);
//            char[] chars1 = s1.toCharArray();
//            char[] chars2 = s2.toCharArray();
//
//            for (int i = 0; i < chars1.length; i++) {
//                if (chars1[i] < chars2[i]) {
//                    return -1;
//                }else if (chars1[i] > chars2[i]) {
//                    return 1;
//                }
//            }
//            char[] chars1 = Integer.toString(o1).toCharArray();
//            char[] chars2 = Integer.toString(o2).toCharArray();
//            int i = 0;
//            for (; i < chars1.length && i < chars2.length; i++) {
//                if (chars1[i] > chars2[i]) return -1;
//                else if (chars1[i] < chars2[i]) return 1;
//            }
//            int j=i;
//            for (;i<chars1.length;i++){
//                if (chars1[i] > chars1[i-j]) return -1;
//            }
//            for (;i<chars1.length;i++){
//                if (chars2[i] > chars1[i-j]) return 1;
//            }
//            if (i < chars1.length) {
//                if (chars1[i] > chars1[0]) return -1;
//                else return 1;
//            } else if (i < chars2.length) {
//                if (chars2[i] > chars2[0]) return 1;//bug1: 处理第二个子串的时候要和第一个字符串不一样
//                else return -1;
//            }

//            return 0;
        }
    }
}
