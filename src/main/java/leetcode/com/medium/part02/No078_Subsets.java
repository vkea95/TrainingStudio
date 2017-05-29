package leetcode.com.medium.part02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by jason on 2016/3/1.
 * Location:
 * https://leetcode.com/problems/subsets/
 * *****************************************
 * Description:
 * Given a set of distinct integers, nums, return all possible subsets.
 * Note:
 * Elements in a subset must be in non-descending order.
 * The solution set must not contain duplicate subsets.
 * For example,
 * If nums = [1,2,3], a solution is:
 * [
 * [3],
 * [1],
 * [2],
 * [1,2,3],
 * [1,3],
 * [2,3],
 * [1,2],
 * []
 * ]
 * **********************************************
 * Solution:
 * 1.Recursive: 首先排序，保证结果子集中的元素顺序正确，然后通过增加元素法来recursive
 * 2.Non-recursive
 * // 1 << n is 2^n
 * // each subset equals to an binary integer between 0 .. 2^n - 1
 * // 0 -> 000 -> []
 * // 1 -> 001 -> [1]
 * // 2 -> 010 -> [2]
 * // ..
 * // 7 -> 111 -> [1,2,3]
 */
public class No078_Subsets {
    public List<List<Integer>> subsets_NonRecurrsive(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < (1 << n); i++) {
            List<Integer> subset = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                // check whether the jth digit in i's binary representation is 1
                if ((i & (1 << j)) != 0) {
                    subset.add(nums[j]);
                }
            }
            result.add(subset);
        }


        return result;
    }


    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        List<Integer> solution = new ArrayList<>();
        Arrays.sort(nums);
        helper(result, solution, nums, 0);
        return result;
    }

    public void helper(List<List<Integer>> result, List<Integer> solution, int[] nums, int start) {
        result.add(new ArrayList<>(solution));

        for (int i = start; i < nums.length; i++) {
            solution.add(nums[i]);
            helper(result, solution, nums, i + 1);
            solution.remove(solution.size() - 1);
        }

    }
}
