package leetcode.com.hard.part2;

/**
 * Created by tclresearchamerica on 5/23/16.
 * ***************************************************************
 * Location:
 * https://leetcode.com/problems/sliding-window-maximum/
 * ***************************************************************
 * Description:
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to
 * the very right. You can only see the k numbers in the window. Each time the sliding window moves
 * right by one position.
 * <p>
 * For example,
 * Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
 * <p>
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 * Therefore, return the max sliding window as [3,3,5,5,6,7].
 * ***************************************************************
 * Analysis:
 * 1.考虑个各种数据结构,觉得还是无解,因为出现的顺序和大小的顺序是不同的2种情况.
 * ***************************************************************
 * Solution:
 * 1.实际解法并没有用到特意的数据结构.所以还好啦,以后碰到这样的题,还是要先写一写再说
 * ***************************************************************
 * ***************************************************************
 */
public class No239_Sliding_Window_Maximum {
    public int[] maxSlidingWindow(int[] nums, int k) {

        if (nums == null || nums.length <= 1) return nums;
        int maxVal = Integer.MIN_VALUE;
        int[] result = new int[nums.length - k + 1];
        //Bug1:start = 0 而不是-1
        int start = 0;
        int end = k - 1;
        int length = nums.length;
        int maxIndex = -1;
        while (end < length) {
            if (maxIndex < start) {
                //good thoughts, detailed
                maxVal = nums[start];
                maxIndex = start;
                for (int i = start; i <= end; i++) {
                    if (nums[i] > maxVal) {
                        maxVal = nums[i];
                        maxIndex = i;
                    }
                }
            } else {
                //great thought! dynamically fix the max value
                if (nums[end] > maxVal) {
                    maxVal = nums[end];
                    maxIndex = end;
                }
            }
            //goo thought
            result[start] = maxVal;
            start++;
            end++;


        }
        return result;
    }
}
