package leetcode.com.medium.part01;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jason on 2016/1/10.
 * Location:
 * https://leetcode.com/problems/permutations/
 * *****************************************************
 * Description:
 * Given a collection of distinct numbers, return all possible permutations.
 * For example,
 * [1,2,3] have the following permutations:
 * [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
 * *****************************************************
 * Solutions:
 * Recursion: 1 indexList to store the distinct number, if the indexList contains all the number, add then put the values of
 * the indexList into result
 * Non_recursion:
 * *****************************************************
 * Beats: 22%
 * *****************************************************
 * *****************************************************
 * *****************************************************
 */
public class No046_Permutations_I_Recursion {
    public static void main(String[] args) {
        No046_Permutations_I_Recursion no046 = new No046_Permutations_I_Recursion();
        no046.permute(new int[]{1});
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> elements = new ArrayList<>();

        if (nums == null || nums.length == 0) return result;

        helper(nums, result, elements);
        return result;
    }

    public void helper(int[] nums, List<List<Integer>> result, List<Integer> list) {
        if (list.size() == nums.length) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            //the element is already in the indexList
            if (list.contains(nums[i])) {
                continue;
            }
            //add the different number into the array
            list.add(nums[i]);
            //recursive
            helper(nums, result, list);
            //delete the prefix
            list.remove(list.size() - 1);
        }
    }

}
