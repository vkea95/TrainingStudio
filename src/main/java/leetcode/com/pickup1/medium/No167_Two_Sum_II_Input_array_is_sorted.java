package leetcode.com.pickup1.medium;

/**
 * Created by tclresearchamerica on 9/26/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
 * ****************************************************
 * Description:
 * Given an array of integers that is already sorted in ascending order, find two numbers such that they add up
 * to a specific target number.
 * <p>
 * The function twoSum should return indices of the two numbers such that they add up to the target,
 * where index1 must be less than index2. Please note that your returned answers (both index1 and index2)
 * are not zero-based.
 * <p>
 * You may assume that each input would have exactly one solution.
 * <p>
 * Input: numbers={2, 7, 11, 15}, target=9
 * Output: index1=1, index2=2
 * ****************************************************
 * Thought:
 * 从数组两端不断接近,
 * ****************************************************
 * Beat: 37%
 * Time: 15 mins
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No167_Two_Sum_II_Input_array_is_sorted {
    public int[] twoSum(int[] numbers, int target) {
        int[] result = new int[2];
        int start = 0;
        int end = numbers.length - 1;
        for (int i = start; i <= end; i++) {
            int minus = target - numbers[i];
            for (int j = end; j >= i; j--) {
                if (minus == numbers[j]) {
                    result[0] = i + 1;
                    result[1] = j + 1;
                    return result;
                } else if (minus > numbers[j]) {
                    end = j;
                    break;
                }
            }
        }

        return result;
    }
}
