package leetcode.com.pickup1.easy;

import java.util.*;

/**
 * Created by tclresearchamerica on 6/23/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/intersection-of-two-arrays/
 * ****************************************************
 * Description:
 * Given two arrays, write a function to compute their intersection.
 * Example:
 * Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].
 * Note:
 * Each element in the result must be unique.
 * The result can be in any order.
 * ****************************************************
 * Thought:
 * 用hashMap可以解决这个问题.但问题是否有更好的解决方案
 * ****************************************************
 * Time: 10 mins
 * Beats: 56%
 * Bug: 0
 * Opt: hashMap ->HashSet
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No349_Intersection_of_Two_Arrays {
    public int[] intersection(int[] nums1, int[] nums2) {
        //优化后,都用hashset
//        HashMap<Integer, Integer> hashMap = new HashMap<>();

        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set = new HashSet<>();
        for (int num : nums1) {
            if (!set1.contains(num)) set1.add(num);
        }
        for (int num : nums2) {
            if (set1.contains(num)) set.add(num);
        }

        int[] rst = new int[set.size()];
        int i = 0;
        //set 支持内嵌的iterator
//        Iterator<Integer> it =set.iterator();
//        while (it.hasNext())
//            rst[i++]=it.next();
        for (int num : set) rst[i++] = num;

        return rst;


    }
}
