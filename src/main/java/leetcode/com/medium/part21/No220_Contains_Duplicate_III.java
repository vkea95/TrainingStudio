package leetcode.com.medium.part21;

import java.util.*;

/**
 * Created by tclresearchamerica on 4/12/16.
 * Location:
 * https://leetcode.com/problems/contains-duplicate-iii/
 * ******************************************************
 * Description:
 * Given an array of integers, find out whether there are two distinct indices i and j in the array such that
 * the difference between nums[i] and nums[j] is at most t and the difference between i and j is at most k.
 * ******************************************************
 * Solution:
 * http://www.programcreek.com/2014/06/leetcode-contains-duplicate-iii-java/
 * The floor(x) method returns the greatest value that is less than x.
 * The ceiling(x) methods returns the least value that is greater than x. The following is an example.
 * The time complexity is O(nlog(k)).
 * ********************************************************
 * Hints:
 * 根据网络答案,会用到新的数据结构TreeSet,中的floor和ceiling方法,当然还有subset方法,每个方法有不同的用途.
 * 求上限,下限和子集的
 * 根据测试验证结果,证明TreeSet是不提供存储重复元素的
 */
public class No220_Contains_Duplicate_III {


    public static void main(String[] args) {
        TreeSet hashSet = new TreeSet();

        hashSet.add("a"); //向集合中添加一个字符串

        hashSet.add("a"); //向集合中添加一个字符串
        hashSet.add("e");
        hashSet.add("b");
        hashSet.add("d");
        hashSet.add("c");


        Iterator it = hashSet.iterator();
        while(it.hasNext()){
            System.out.println(it.next()+",");
        }
    }

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {

        if (k < 0 || t < 0) return false;

        TreeSet<Integer> treeSet = new TreeSet<>();

        for (int i=0; i < nums.length; i++) {
            if ((treeSet.floor(nums[i]) != null && nums[i] <= treeSet.floor(nums[i]) + t) ||
                    (treeSet.ceiling(nums[i]) != null && nums[i] >= treeSet.ceiling(nums[i]) - t
                    )) {
                return true;
            }

            treeSet.add(nums[i]);
            if (i >= k) treeSet.remove(nums[i - k]);

        }
        return false;

    }
}
