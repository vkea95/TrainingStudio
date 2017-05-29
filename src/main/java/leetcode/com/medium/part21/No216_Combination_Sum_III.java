package leetcode.com.medium.part21;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tclresearchamerica on 5/19/16.
 * ***************************************************************
 * Location:
 * https://leetcode.com/problems/combination-sum-iii/
 * ***************************************************************
 * Description:
 * Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9
 * can be used and each combination should be a unique set of numbers.
 * Ensure that numbers within the set are sorted in ascending order.
 * Example 1:
 * Input: k = 3, n = 7
 * Output:
 * [[1,2,4]]
 * Example 2:
 * Input: k = 3, n = 9
 * Output:
 * [[1,2,6], [1,3,5], [2,3,4]]
 * ***************************************************************
 * Analysis:
 * 一个数等于3个数的和,首先就是穷举法,但是这个时间复杂度是n^3,里面充斥了重复计算
 * 那么该如何优化呢?
 * 首先,可以判断结果是不是超出了目标值,超出的话,就不要循环了
 * 其次,前2个数相加后,肯定要大于目标值减去这个和值,
 * 再次,就是第一个数不能大于目标值的1/3
 * 1.这就是在考验算法了,在论的算法有:DP
 * ***************************************************************
 * ***************************************************************
 */
public class No216_Combination_Sum_III {
    public static void main(String[] args) {
        No216_Combination_Sum_III obj = new No216_Combination_Sum_III();
        int k = 2;
        int n = 6;
        obj.combinationSum3(2, 6);
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> rst = new ArrayList<>();

        List<Integer> solution = new ArrayList<>();
        helper(rst, solution, k, n, 1, n);

        return rst;
    }

    public void helper(List<List<Integer>> rst, List<Integer> solution, int k, int n, int start, int remain) {
        if (k == 0 || start > n) {
            if (remain == 0) {
                rst.add(new ArrayList<>(solution));
            }
            return;
        }

        int avg = remain / k;
        for (int i = start; i <= avg && i <= 9; i++) {
            if (remain >= i) {
                solution.add(new Integer(i));
                helper(rst, solution, k - 1, n, i + 1, remain - i);
                //bug1:对
                solution.remove(new Integer(i));

            } else {
                break;
            }
        }

    }

}
