package leetcode.com.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by tclresearchamerica on 7/18/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/count-of-smaller-numbers-after-self/
 * ****************************************************
 * Description:
 * You are given an integer array nums and you have to return a new counts array. The counts array has the property
 * where counts[i] is the number of smaller elements to the right of nums[i].
 * Example:
 * Given nums = [5, 2, 6, 1]
 * To the right of 5 there are 2 smaller elements (2 and 1).
 * To the right of 2 there is only 1 smaller element (1).
 * To the right of 6 there is 1 smaller element (1).
 * To the right of 1 there is 0 smaller element.
 * Return the array [2, 1, 1, 0].
 * ****************************************************
 * Thoughts:
 * 1.感觉这个又和DP相关,还是先分析再说吧,
 * 2.是不是可以这样玩,右侧第一个肯定是0
 * 第一版的解法,是有问题的,不能只考虑最近的小value,还要考虑较大的value,这个问题就略显复杂啦
 * Ref:
 * https://discuss.leetcode.com/topic/31162/mergesort-solution
 * https://discuss.leetcode.com/topic/31173/my-simple-ac-java-binary-search-code/2
 * https://discuss.leetcode.com/topic/31422/easiest-java-solution/2
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No315_Count_of_Smaller_Numbers_After_Self {
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> lstRst = new ArrayList<>();
        if (nums == null || nums.length == 0) return lstRst;
        int n = nums.length;

        Integer[] result = new Integer[n];

        //Bug1:Integer的数组不初始化,就会抛出null pointer的异常
        Arrays.fill(result, 0);
        //Bug2:首先,n最大是n-1,所以要从n-1开始
//        result[n - 2] = 0;
        for (int i = n - 2; i >= 0; i--) {
            int j = i + 1;
            while (j < n) {
                if (nums[i] > nums[j]) {
                    result[i] = result[j] + 1;
                    break;
                }
                j++;
            }
        }
        return Arrays.asList(result);
    }
}
