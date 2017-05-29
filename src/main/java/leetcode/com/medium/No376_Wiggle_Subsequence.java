package leetcode.com.medium;

/**
 * Created by tclresearchamerica on 7/24/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/wiggle-subsequence/
 * ****************************************************
 * Description:
 * A sequence of numbers is called a wiggle sequence if the differences between successive numbers strictly alternate
 * between positive and negative. The first difference (if one exists) may be either positive or negative.
 * A sequence with fewer than two elements is trivially a wiggle sequence.
 * <p>
 * For example, [1,7,4,9,2,5] is a wiggle sequence because the differences (6,-3,5,-7,3) are alternately positive
 * and negative. In contrast, [1,4,7,2,5] and [1,7,4,5,5] are not wiggle sequences, the first because its first
 * two differences are positive and the second because its last difference is zero.
 * <p>
 * Given a sequence of integers, return the length of the longest subsequence that is a wiggle sequence.
 * A subsequence is obtained by deleting some number of elements (eventually, also zero) from the original sequence,
 * leaving the remaining elements in their original order.
 * <p>
 * Examples:
 * Input: [1,7,4,9,2,5]
 * Output: 6
 * The entire sequence is a wiggle sequence.
 * <p>
 * Input: [1,17,5,10,13,15,10,5,16,8]
 * Output: 7
 * There are several subsequences that achieve this length. One is [1,17,10,13,10,16,8].
 * <p>
 * Input: [1,2,3,4,5,6,7,8,9]
 * Output: 2
 * Follow up:
 * Can you do it in O(n) time?
 * ****************************************************
 * Thoughts:
 * 1.2个数字比较会出现大小,小于和相等三种情况,如果连续出现大于或小于,或是相等,都要重新计数
 * 2.这里面会有2种计数方式,一个是local,算连续的比较,另一个是max,统计最大值
 * 3.没有理解对题意,这里面需要做删除处理,如果是不是1大一小的关系的话
 * 4.它要保留大小大,或是小大小这样的关系,
 * 5.读到现在才意识到这可能是个动态规划的问题哦!首先,要找到满足这样的sequences,然后再考虑合并的问题,还要在O(n)的时间复杂度之内,
 * ****************************************************
 * 网络答案:
 * https://discuss.leetcode.com/topic/51893/two-solutions-one-is-dp-the-other-is-greedy-8-lines/2
 * ****************************************************
 * Hindsight:
 * 这个题目要好好思考下才可以哦
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No376_Wiggle_Subsequence {
    public static void main(String[] args) {
        No376_Wiggle_Subsequence obj = new No376_Wiggle_Subsequence();
        int[] nums = new int[]{1, 17, 5, 10, 13, 15, 10, 5, 16, 8};
        obj.wiggleMaxLength(nums);

    }

    public int wiggleMaxLength(int[] nums) {
        int size=nums.length, f=1, d=1;
        for(int i=1; i<size; ++i){
            if(nums[i]>nums[i-1]) f=d+1;
            else if(nums[i]<nums[i-1]) d=f+1;
        }
        return Math.min(size, Math.max(f, d));


    }
    public int wiggleMaxLength_wrong(int[] nums) {
        if (nums == null) return 0;
        //bug1:1个元素的时候,该返回1
        if (nums.length <= 1) return nums.length;
        int local = 0, max = 0;

        boolean preSign = nums[1] - nums[0] >= 0;
        boolean curtSign;
        //bug4:不用复杂处理,直接判断是不是相等就可以啦
        if (nums[1] == nums[0]) local = 1;
        else local = 2;
//        boolean curtSign = nums[0] - nums[1] >= 0;
//        local = preSign ^ curtSign ? 2 : 1;
        for (int i = 2; i < nums.length; i++) {
            curtSign = nums[i] - nums[i - 1] >= 0;
            if (curtSign ^ preSign) {
                local++;
            } else {
                max = Math.max(max, local);

                //bug3:
                if (nums[i - 1] == nums[i]) local = 1;
                else local = 2;
//                local = (nums[i - 1] - nums[i] >= 0) ^ curtSign ? 2 : 1;
            }
            //bug2:没有保存之前的元素呢
            preSign = curtSign;
        }
        return Math.max(max, local);

    }
}
