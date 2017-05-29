package leetcode.com.pickup1.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tclresearchamerica on 9/15/16.
 * *************************************************************
 * *************************************************************
 * Description:
 * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
 * The algorithm should run in linear time and in O(1) space
 * *************************************************************
 * Thought:
 * 1.超过1/3,
 * 2.思路基本确定,超过1/3的数字最多只能有2个,所有用4个变量分别来统计对应的数字和出现次数
 * 3.最后,还要用全部循环一遍的方式来确定是否超过1/3
 * *************************************************************
 * Hindsight:
 * ref:
 * https://segmentfault.com/a/1190000004905350
 * https://segmentfault.com/a/1190000003740925
 * 1.统计1/3这个问题,要先认清会有几个答案
 * 2.在算法过程中,如果没有匹配上的时候,都要备选元素都要减一
 * 3.最后,还需要一个循环比较计算是否真超过1/3的过程,防止将非备选答案放入结果集
 * *************************************************************
 * *************************************************************
 * *************************************************************
 * *************************************************************
 * *************************************************************
 * *************************************************************
 */
public class No229_Majority_Element_II {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0) return result;
        int n1 = 0, n2 = 0;
        int c1 = 0, c2 = 0;
        n1 = nums[0];
        for (int num : nums) {
            // 如果和某个候选数相等，将其计数器加1
            if (n1 == num) {
                c1++;
            } else if (n2 == num) {
                c2++;
                // 如果都不相等，而且计数器都不为0，则计数器都减1
            } else if (c1 != 0 && c2 != 0) {
                c1--;
                c2--;
            } else {
                if (c1 == 0) {
                    n1 = num;
                    c1 = 1;
                } else {
                    n2 = num;
                    c2 = 1;
                }
            }

        }
        c1 = 0;
        c2 = 0;
        for (int num : nums) {
            if (n1 == num) c1++;
            else if (n2 == num) c2++;
        }
        if (c1 > nums.length / 3) result.add(n1);
        if (c2 > nums.length / 3) result.add(n2);
        return result;
    }
}
