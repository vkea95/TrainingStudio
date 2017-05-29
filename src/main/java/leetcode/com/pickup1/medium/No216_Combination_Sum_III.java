package leetcode.com.pickup1.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tclresearchamerica on 6/28/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/combination-sum-iii/
 * ****************************************************
 * Description:
 * Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can
 * be used and each combination should be a unique set of numbers.
 * Example 1:
 * Input: k = 3, n = 7
 * Output:
 * [[1,2,4]]
 * Example 2:
 * Input: k = 3, n = 9
 * Output:
 * [[1,2,6], [1,3,5], [2,3,4]]
 * ****************************************************
 * Thought
 * 1.初步想法就是Greedy,有几个就算几个呗,保证和为n就可以了,用递归实现,但这样的做法真的可行吗?
 *
 * ****************************************************
 * Time:20 mins
 * Beat: 65%
 * Bug:1
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No216_Combination_Sum_III {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> solution = new ArrayList<>();
        //bug:0->1
        helper(result, solution, 1, k, n);
        return result;
    }

    private void helper(List<List<Integer>> result, List<Integer> solution, int start, int k, int remain) {
        if (k == 0) {
            if (remain == 0) {
                result.add(new ArrayList<>(solution));
            }

            return;

        } else if (remain < 0) {
            return;
        }

        for (int i = start; i <= 9; i++) {
            solution.add(i);
            helper(result, solution, i + 1, k - 1, remain - i);
            solution.remove(solution.size() - 1);
        }
    }
}
