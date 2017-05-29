package leetcode.com.pickup1.medium;

import java.util.*;

/**
 * Created by tclresearchamerica on 7/15/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/largest-number/
 * ****************************************************
 * Description:
 * Given a list of non negative integers, arrange them such that they form the largest number.
 * For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.
 * Note: The result may be very large, so you need to return a string instead of an integer.
 * ****************************************************
 * Thoughts:
 * 1.组合出来的数字最大,那么就需要一个能满足这个条件的顺序数组啦。所以我们要对它按照一定规则进行排序。
 * 首数字大的胜出,相同首数字的话,继续比较,在相同情况下,后面数字大于前面数字的胜出,否则,短(小)的胜出
 * 2.但是排序出问题了,就是Arrays只支持Comparable的比较方式,但这个只能比较一个,不是比较2个,
 * 如果用collections的sort方法,又不知道如何顺畅的转换int ->Integer,毕竟Arrays.asList并不工作
 * ****************************************************
 * Time: 30 min
 * Beat: 23
 * Bug: 1
 * ****************************************************
 * Hindsight:
 * 1.需要好的思想呀,感觉拆解很困难的时候,就该想想是不是有捷径了,比如,交换比较就可以
 * 2.弱点是忘记了comparator和comparable的区别
 * Comparable & Comparator 都是用来实现集合中元素的比较、排序的，只是 Comparable 是在集合内部定义的方法实现的排序，Comparator
 * 是在集合外部实现的排序，所以，如想实现排序，就需要在集合外定义 Comparator 接口的方法或在集合内实现 Comparable 接口的方法。
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No179_Largest_Number {
    public String largestNumber(int[] nums) {

        if (nums == null || nums.length == 0) return "";
        List<String> myList = new ArrayList<>();
        for (int num : nums) {
            myList.add(String.valueOf(num));
        }

        Collections.sort(myList, new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                return (o1 + o2).compareTo(o2 + o1);
            }
        });
        StringBuffer sb = new StringBuffer();
        for (int i = myList.size() - 1; i >= 0; i--)
            sb.append(myList.get(i));

        //bug1:00 应该转换为0
//        return sb.toString();
        return sb.charAt(0) == '0' ? "0" : sb.toString();

    }

}