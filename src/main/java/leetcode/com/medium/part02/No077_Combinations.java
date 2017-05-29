package leetcode.com.medium.part02;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jason on 2016/2/24.
 * Location:
 * https://leetcode.com/problems/combinations/
 * *********************************************
 * Description:
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
 * For example,
 * If n = 4 and k = 2, a solution is:
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]
 * **********************************************
 * Solution:
 * 肯定是递归解决方案。
 * 一般的递归方法都需要一个helper方法，然后，将result，solution和各种条件作为参数传入，在方法内部对result和solution进行
 * 删减，当然递归时候发生变化的条件也要作为参数传入，在方法的入口处，判断solution是否满足条件，满足即返回。
 */
public class No077_Combinations {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> solution = new ArrayList<>();
        helper(result, solution, n, k, 1);

        return result;
    }

    private void helper(List<List<Integer>> result,
                        List<Integer> solution,
                        int n,
                        int k,
                        int start) {
        if (solution.size() == k) {
            result.add(new ArrayList(solution));
            return;
        }
        for (int i = start; i <= n; i++) {
            solution.add(i);
            //the new start should be after the next number after i
            helper(result, solution, n, k, i + 1);
            solution.remove(solution.size() - 1);
        }

    }

}
