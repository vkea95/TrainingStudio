package leetcode.com.medium.part01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jason on 2016/2/11.
 * Locations:
 * https://leetcode.com/problems/combination-sum/
 * **********************************************
 * Descriptions:
 * Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C
 * where the candidate numbers sums to T.
 * The same repeated number may be chosen from C unlimited number of times.
 * Note:
 * All numbers (including target) will be positive integers.
 * Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
 * The solution set must not contain dupNumber combinations.
 * For example, given candidate set 2,3,6,7 and target 7,
 * A solution set is:
 * [7]
 * [2, 2, 3]
 * **********************************************************
 * Solutions:
 * 关键点：1.数组升序 2.元素可重复 3 结果集不能含有重复的组合
 * 思路：1.先对candidates进行排序，
 * 2.只在小于target的范围进行寻找
 * 3.用递归进行处理，需要一个helper方法
 */
public class No039_Combination_Sum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (candidates == null) {
            return result;
        }

        List<Integer> path = new ArrayList<>();
        Arrays.sort(candidates);
        helper(candidates, target, 0, path, result);
        return result;
    }

    void helper(int[] candidates, int target, int index, List<Integer> path, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(path));
        }
        int prev = -1;
        for (int i = index; i < candidates.length; i++) {
            if (candidates[i] > target) {
                break;
            }
            if (prev != -1 && prev == candidates[i]) {
                continue;
            }
            path.add(candidates[i]);
            helper(candidates, target - candidates[i], i, path, result);
            path.remove(path.size() - 1);

            prev = candidates[i];
        }
    }
}
