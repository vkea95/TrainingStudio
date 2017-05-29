package leetcode.com.pickup1.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tclresearchamerica on 8/26/16.
 * ***************************************************************
 * Location:
 * https://leetcode.com/problems/summary-ranges/
 * ***************************************************************
 * Description:
 * Given a sorted integer array without duplicates, return the summary of its ranges.
 * For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].
 * ***************************************************************
 * Time: 20mins
 * Beat: 3%
 * Bug:0
 * ***************************************************************
 * Thoughts:
 * 题目比较简单,做法上想采用图的解法,实际并没有用到,同第一版的代码相比,时间复杂度差不多。但是代码看上去清爽多了
 * ***************************************************************
 * ***************************************************************
 * ***************************************************************
 * ***************************************************************
 * ***************************************************************
 * ***************************************************************
 */
public class No228_Summary_Ranges {
    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        if (nums == null || nums.length == 0) return result;
        int curt = nums[0];
        int next = curt + 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == next) {
                next++;
            } else {
                if (curt == next - 1) {
                    result.add(String.valueOf(curt));
                } else {
                    result.add(String.valueOf(curt) + "->" + String.valueOf(next - 1));
                }
                curt = nums[i];
                next = curt + 1;
            }
        }

        if (curt == next - 1) {
            result.add(String.valueOf(curt));
        } else {
            result.add(String.valueOf(curt) + "->" + String.valueOf(next - 1));
        }
        return result;
    }
}
