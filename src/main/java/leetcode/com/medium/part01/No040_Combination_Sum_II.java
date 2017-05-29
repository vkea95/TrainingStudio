package leetcode.com.medium.part01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jason on 2016/2/12.
 * Locations:
 * https://leetcode.com/problems/combination-sum-ii/
 * ***************************************************
 * Descriptions:
 * Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C
 * where the candidate numbers sums to T.
 * Each number in C may only be used once in the combination.
 * Note:
 * All numbers (including target) will be positive integers.
 * Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
 * The solution set must not contain duplicate combinations.
 * For example, given candidate set 10,1,2,7,6,1,5 and target 8,
 * A solution set is:
 * [1, 7]
 * [1, 2, 5]
 * [2, 6]
 * [1, 1, 6]
 * ***************************************************************
 * Solutions:
 * 和No039类似，还是需要prev这个变量，保证本次循环中没有相同value的元素，相同value的元素都会在递归过程中被覆盖执行过
 */
public class No040_Combination_Sum_II {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

        List<List<Integer>> result = new ArrayList<>();
        if (candidates == null) {
            return result;
        }
        Arrays.sort(candidates);
        List<Integer> path = new ArrayList<>();
        helper(candidates, target, 0, path, result);
        return result;
    }

    void helper(int[] candidates, int target, int index, List<Integer> path, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(path));
            return;
        }
        int prev = -1;
        for (int i = index; i < candidates.length; i++) {
            if (candidates[i] > target) {
                break;
            }
            if(prev !=candidates[i]){
                path.add(candidates[i]);
                helper(candidates, target - candidates[i], i + 1, path, result);
                path.remove(path.size() - 1);
                prev = candidates[i];
            }
        }
    }
}
