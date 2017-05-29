package leetcode.com.easy;

import java.util.*;

/**
 * Created by tclresearchamerica on 6/28/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/intersection-of-two-arrays-ii/
 * ****************************************************
 * Description:
 * Given two arrays, write a function to compute their intersection.
 * <p>
 * Example:
 * Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].
 * <p>
 * Note:
 * Each element in the result should appear as many times as it shows in both arrays.
 * The result can be in any order.
 * Follow up:
 * What if the given array is already sorted? How would you optimize your algorithm?
 * What if nums1's size is small compared to nums2's size? Which algorithm is better?
 * What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements
 * into the memory at once?
 * ****************************************************
 * Thoughts
 * 1.要先排序才好办,但是后面的follow up问题不知如何解法
 * 2.虽然解决了问题,但是感觉,不是很简明
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No350_Intersection_of_Two_Arrays_II {
    public int[] intersect(int[] nums1, int[] nums2) {


        Map<Integer, Integer> hashMap = new HashMap<>();
        for (int i : nums1) {
            if (!hashMap.containsKey(i)) {
                hashMap.put(i, 1);
            } else {
                hashMap.put(i, hashMap.get(i) + 1);
            }
        }
        int[] solution = new int[nums1.length];
        int index = 0;
        for (int i : nums2) {
            if (hashMap.containsKey(i) && hashMap.get(i) > 0) {
                solution[index++] = i;
                hashMap.put(i, hashMap.get(i) - 1);
            }
        }
        int[] result = new int[index];

        //opt1:数组拷贝的函数要记下来o
        System.arraycopy(solution, 0, result, 0, index);
        return result;
    }
}
