package leetcode.com.easy.part2;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by tclresearchamerica on 4/9/16.
 * Location:
 * https://leetcode.com/problems/contains-duplicate-ii/
 * ****************************************************
 * Given an array of integers and an integer k,
 * find out whether there are two distinct indices i and j in the array
 * such that nums[i] = nums[j] and the difference between i and j is at most k.
 * ****************************************************
 * Solution:
 * 根据网络答案,其实最好用的还是hashMap,当重复元素放生的时候算算两个index的差值是否满足要求,然后判断返回
 * ****************************************************
 * Hints:
 * 1.对比我的答案和网路答案,发现其实问题主要出现在数据结构的选择上面,如果熟知各种数据结构的变形和用途,那么在选择方案和拓宽思路上面就更加容易
 * 了,比如HashMap就可以提供存储键值对,然后开发人员可以利用键值对搞再次变化.
 * 2.TreeSet提供一些value的科学比较法:ceiling,floor和subSet等等
 * 3.对比我的方案和网络答案,发现网络答案在LeetCdde运行的时候,有时会发生超时现象
 */
public class No219_Contains_Duplicate_II {

    public static void main(String[] args) {
        No219_Contains_Duplicate_II obj = new No219_Contains_Duplicate_II();
        obj.containsNearbyDuplicate(new int[]{1, 0, 1, 1}, 1);
    }

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                int pre = map.get(nums[i]);
                if (i - pre <= k)
                    return true;
            }

            map.put(nums[i], i);
        }

        return false;
    }

    public boolean containsNearbyDuplicate2(int[] nums, int k) {

        if (nums == null) return false;
        int[] numBK = nums.clone();
        Arrays.sort(numBK);
        for (int i = 1; i < numBK.length; i++) {
            int j = 0;
            while (i < numBK.length && numBK[i] == numBK[i - 1]) {
                for (; j < nums.length; j++) {
                    if (nums[j] == numBK[i]) {
                        //bug1:没有对kk+j进行范围控制
                        for (int kk = 1; kk <= k && j + kk < nums.length; kk++) {
                            //bug2:kk->j+kk
                            if (nums[j + kk] == nums[j]) {
                                return true;
                            }
                        }
                    }
                }
                i++;
            }
        }

        return false;
    }
}
