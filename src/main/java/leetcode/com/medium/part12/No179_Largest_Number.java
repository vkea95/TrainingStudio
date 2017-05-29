package leetcode.com.medium.part12;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by jason on 2016/3/30.
 * Location:
 * https://leetcode.com/problems/largest-number/
 * ************************************************
 * Description:
 * Given a list of non negative integers, arrange them such that they form the largest number.
 * For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.
 * Note: The result may be very large, so you need to return a string instead of an integer
 * ************************************************
 * Solution:
 * 完全没有想到解法。看了答案后才明白，用Comparator接口来实现啊，但是要处理下
 * ************************************************
 * Hints:
 * 一个类实现了Comparable接口则表明这个类的对象之间是可以相互比较的，这个类对象组成的集合就可以直接使用sort方法排序。
 * Comparator可以看成一种算法的实现，将算法和数据分离，Comparator也可以在下面两种环境下使用：
 * 1、类的设计师没有考虑到比较问题而没有实现Comparable，可以通过Comparator来实现排序而不必改变对象本身
 * 2、可以使用多种排序标准，比如升序、降序等。
 * 2、可以使用多种排序标准，比如升序、降序等。
 */
public class No179_Largest_Number {

    public static void main(String[] args) {
        String a = "123";
        String b = "123";
//        b = "23";

        System.out.println(a == b);
        System.out.println(a.equals(b));
        a = new String("123");

        System.out.println(a == b);
        System.out.println(a.equals(b));


    }

    public String largestNumber(int[] nums) {
        String[] strs = new String[nums.length];
        for (int i = 0; i < strs.length; i++) {
            strs[i] = Integer.toString(nums[i]);
        }

        Arrays.sort(strs, new NumberComparator());
        StringBuilder sb = new StringBuilder();
        for (String str : strs)
            sb.append(str);
        String result = sb.toString();
        int index = 0;
        //bug1: || -> &&
        //bug3: charAt(index) != '0' -> charAt(index) == '0'
        while (index < result.length() && result.charAt(index) == '0') index++;

        //bug2:index == (result.length() - 1) -> index == result.length()
        return index == result.length() ? "0" : result.substring(index);
    }

    public class NumberComparator implements Comparator<String> {
        public int compare(String s1, String s2) {
            return (s2 + s1).compareTo(s1 + s2);
        }
    }
}