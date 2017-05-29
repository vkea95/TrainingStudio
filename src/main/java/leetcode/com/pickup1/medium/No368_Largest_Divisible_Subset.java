package leetcode.com.pickup1.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by tclresearchamerica on 9/13/16.
 * Location:
 * https://leetcode.com/problems/largest-divisible-subset/
 * ****************************************************
 * Description:
 * Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of elements
 * in this subset satisfies: Si % Sj = 0 or Sj % Si = 0.
 * If there are multiple solutions, return any subset is fine.
 * Example 1:
 * nums: [1,2,3]
 * Result: [1,2] (of course, [1,3] will also be ok)
 * Example 2:
 * nums: [1,2,4,8]
 * Result: [1,2,4,8]
 * ****************************************************
 * Hindsight:
 * 关于我的答案:
 * 1.对题目的认识不足,以为只要集合中的某个元素可以被整除,则全部集合都可以
 * 2.认为某个元素属于特定的集合,实际情况是某个元素可以属于多个集合
 * 3.零是不该出现的元素,否则整个集合都是解集
 * 关于正确的解:
 * 1.排序可以是工作有序化,至少在求余数的时候,只要用后面的数字%前面的数字就好了
 * 2.需要2个数组,分别记录对应元素的可以整除的元素个数和对应的最近的可以整除的元素
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No368_Largest_Divisible_Subset {
    public static void main(String[] args) {
        int[] nums = new int[]{3, 6, 12, 24, 96, 192, 576};
        No368_Largest_Divisible_Subset obj = new No368_Largest_Divisible_Subset();
        obj.largestDivisibleSubset(nums);
    }

    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0) return result;
        int n = nums.length;
        int[] dp = new int[n];
        int[] parent = new int[n];
        int max = 0;
        int max_index = -1;
        Arrays.fill(dp, 1);
        Arrays.fill(parent, -1);
        Arrays.sort(nums);
        for (int i = 1; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] % nums[j] == 0 && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                    parent[i] = j;
                }
            }
            if (dp[i] > max) {
                max = dp[i];
                max_index = i;
            }
        }
        return getResult(nums, parent, max_index);
    }

    private List<Integer> getResult(int[] nums, int[] parent, int max_index) {
        List<Integer> result = new ArrayList();
        if (max_index == -1) {
            result.add(nums[0]);
        } else {
            while (max_index != -1) {
                result.add(nums[max_index]);
                max_index = parent[max_index];
            }
        }
        return result;
    }

    /*
    这个算法错误的原因,在于没有意识到同样的一个元素可以分属于不同的subset,
    可是在我的解法中,认定某个元素只能属于特定的subset
     */
    public List<Integer> largestDivisibleSubset_wrong(int[] nums) {
        if (nums == null || nums.length == 0) return new ArrayList<>();
        List<List<Integer>> solutions = new ArrayList<>();
        for (int num : nums) {
            boolean addFlg = true;

            for (List<Integer> solution : solutions) {
                boolean okFLg = true;
                for (Integer element : solution) {
                    if ((num != 0 && element % num == 0) || element != 0 && num % element == 0) {
                        continue;
                    }
                    okFLg = false;
                    break;
                }
                if (okFLg) {
                    solution.add(num);
                    addFlg = false;
                } else {
                }

                // if((num!=0&&solution.get(0)%num==0)||solution.get(0)!=0&&num%solution.get(0)==0){
                //     //bug1:只计算 其中的一个元素并不能保证所有的余数都满足要求啊
                //     solution.add(num);
                //     addFlg= false;
                //     break;
                // }
            }
            if (addFlg) {
                List<Integer> solution = new ArrayList<>();
                solution.add(num);
                solutions.add(solution);
            }
        }

        List<Integer> result = solutions.get(0);
        for (List<Integer> solution : solutions) {
            if (solution.size() > result.size()) result = solution;
        }
        return result;
    }
}
