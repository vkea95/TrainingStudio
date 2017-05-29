package leetcode.com.pickup1.easy;

/**
 * Created by tclresearchamerica on 7/20/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/majority-element/
 * ****************************************************
 * Description:
 * Given an array of size n, find the majority element. The majority element is the element that appears
 * more than ⌊ n/2 ⌋ times.
 * <p>
 * You may assume that the array is non-empty and the majority element always exist in the array.
 * ****************************************************
 * Thought:
 * 1.Majority Element就是出现次数超过n/2次,
 * 有一种做法,就是累计计算出现的次数,当然是在hashMap中,
 * 2.另一种方法,就是不停地比赛删除,最后剩下的就是答案啦,也就是说,我根本不care其余的数字,只是在意这个目标值
 * 3.参考了第一版的答案,才发现做法比较smart
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No169_Majority_Element {
    public int majorityElement(int[] nums) {
        int[] result = new int[2];
        for (int num : nums) {
            if (result[0] == num) {
                result[1]++;
            } else if (result[1] == 0) {
                result[0] = num;
                result[1]++;
            } else {
                result[1]--;
            }
//            result -=num==result[0]
        }
        return result[0];
    }
}
