package leetcode.com.medium.part21;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tclresearchamerica on 4/28/16.
 * ***********************************************
 * Location:
 * https://leetcode.com/problems/summary-ranges/
 * ***********************************************
 * Description:
 * Given a sorted integer array without duplicates, return the summary of its ranges.
 * <p>
 * For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].
 * ***********************************************
 * ***********************************************
 */
public class No228_Summary_Ranges {
    public static void main(String[] args) {
        No228_Summary_Ranges obj = new No228_Summary_Ranges();
        obj.summaryRanges(new int[]{-2147483648, -2147483647, 2147483647});
    }

    public List<String> summaryRanges(int[] nums) {
        List<String> rst = new ArrayList<>();

        if (nums == null || nums.length == 0) return rst;

        StringBuffer sb = new StringBuffer();
        int tmp = nums[0];
        sb.append(nums[0]);
        for (int i = 1; i < nums.length; i++) {
//            if (sb.length() == 0) sb.append(i);
            //bug2: ">1" --> "!=1"  没有考虑数字超大的可能性
            if (nums[i] - nums[i - 1] != 1) {
                if (tmp < nums[i - 1]) {
                    sb.append("->");
                    sb.append(nums[i - 1]);
                }
                rst.add(sb.toString());
                sb.delete(0, sb.length());

                tmp = nums[i];
                sb.append(tmp);
                //bug1: 没有考虑到[0,1]这样的case
            } else if (i == nums.length - 1) {
                sb.append("->");
                sb.append(nums[i]);
            }

        }
        rst.add(sb.toString());
        return rst;

    }
}
