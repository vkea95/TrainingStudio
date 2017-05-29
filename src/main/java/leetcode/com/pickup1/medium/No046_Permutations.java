package leetcode.com.pickup1.medium;

import leetcode.com.util.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tclresearchamerica on 6/4/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/permutations/
 * ****************************************************
 * Description:
 * Given a collection of distinct numbers, return all possible permutations.
 * For example,
 * [1,2,3] have the following permutations:
 * [
 * [1,2,3],
 * [1,3,2],
 * [2,1,3],
 * [2,3,1],
 * [3,1,2],
 * [3,2,1]
 * ]
 * ****************************************************
 * Thought:
 * 依然对这个排列的算法没有掌握...
 * 想不出解决方案,想来这个是我的一个弱点
 * 目前可以想到的是,肯定有个for循环在外面,然后将solution传到递归的函数中,
 * 在递归函数中根据Solution的size判断是否加入加入结果集
 * 因为被选定元素的前面的元素顺序也需要调整,所以递归函数中需要两层函数?
 * ****************************************************
 * 解题思路：字符交换加dfs。
 * 将第0个字符和从第0开始的每个字符进行交换，对于交换后的结果，再从第1个字符开始交换。一直到最后一个字符。
 * --> 必须要自己动态推导,当然从第一次完全循环后,再推导会更容易些吧
 * ****************************************************
 * Time: 60 mins
 * Beats: 90%
 * Bug: -
 * ****************************************************
 * ****************************************************
 */
public class No046_Permutations {

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        No046_Permutations.permute(array);
    }

    public static void permutations(int[] nums, int start, List<List<Integer>> result) {

        int n = nums.length;
        if (start == n) {
            List<Integer> solution = new ArrayList<>();
            for (int num : nums) {
                solution.add(num);
            }
            result.add(solution);
            return;
        }

        int tmp = nums[start];
        for (int i = start; i < n; i++) {
            nums[start] = nums[i];
            nums[i] = tmp;
            permutations(nums, start + 1, result);
            nums[i] = nums[start];
            nums[start] = tmp;
        }
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0)
            return result;
        permutations(nums, 0, result);

        return result;
    }
}
