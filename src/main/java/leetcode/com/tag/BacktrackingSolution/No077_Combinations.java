package leetcode.com.tag.BacktrackingSolution;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JianZhang on 9/12/17.
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
 * <p>
 * For example,
 * If n = 4 and k = 2, a solution is:
 * <p>
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]
 * Bugs:
 * 1. 没有剔除使用过的元素,需要使用最后一个index
 * 2.
 */
public class No077_Combinations {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> solution = new ArrayList<>();
        helper(result, solution, n, k, 1);
        return result;
    }

    private void helper(List<List<Integer>> result, List<Integer> solution, int n, int k, int start) {
        if (solution.size() == k) {
            result.add(new ArrayList(solution));
            return;
        }

        for (int i = start; i <= n; i++) {
            solution.add(i);
            helper(result, solution, n, k, i + 1);
            solution.remove(solution.size() - 1);
        }
    }
}
