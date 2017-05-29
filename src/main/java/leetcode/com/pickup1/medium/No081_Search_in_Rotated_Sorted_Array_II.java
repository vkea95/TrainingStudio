package leetcode.com.pickup1.medium;

/**
 * Created by tclresearchamerica on 7/23/16.
 * *****************************************************************************
 * Location:
 * https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
 * *****************************************************************************
 * Description:
 * Follow up for "Search in Rotated Sorted Array":
 * What if duplicates are allowed?
 * <p>
 * Would this affect the run-time complexity? How and why?
 * <p>
 * Write a function to determine if a given target is in the array.
 * *****************************************************************************
 * Thoughts:
 * 1.Rotated sorted Array指的就是平移了的数组?因为有重复的元素,所以并不适用于2分法,画一张xy坐标图就能清楚地知道了,
 * 不能根据取到的中位值,判断下一步该取那一段数组。
 * 2.因为二分法被废掉了,所以需要重新组织结构,那就是需要遍历全部元素,既然遍历了,那就顺便比较,则问题解决。
 * *****************************************************************************
 * Time: 5 mins
 * Beat: 23%
 * Bug: 0
 * *****************************************************************************
 * Hindsight:
 * 1.重点在于分析不能二分法的原因?,但实际上二分法,可以解开问题,在原来二分法的基础上追加一些额外处理即可
 * 2.需要回顾下这个原题的二分法的处理方式了。
 * 3.实际上,我使用遍历的方式解决问题。这个问题的worst时间复杂度是O(n),avg时间复杂度是O(logn)?并不确定,
 * 估计是完美的情况下是O(logn)吧
 * *****************************************************************************
 * *****************************************************************************
 * *****************************************************************************
 * *****************************************************************************
 * *****************************************************************************
 */
public class No081_Search_in_Rotated_Sorted_Array_II {
    //二分法处理duplicate 元素的问题
    //做法和之前的二分法类似,不过在处理元素值相同的时候,可以用start++,来解决问题啦
    public boolean search_Binary(int[] nums, int target) {
        int start = 0, end = nums.length - 1;

        //check each num so we will check start == end
        //We always get a sorted part and a half part
        //we can check sorted part to decide where to go next
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) return true;

            //if left part is sorted
            if (nums[start] < nums[mid]) {
                if (target < nums[start] || target > nums[mid]) {
                    //target is in rotated part
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            } else if (nums[start] > nums[mid]) {
                //right part is rotated

                //target is in rotated part
                if (target < nums[mid] || target > nums[end]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                //duplicates, we know nums[mid] != target, so nums[start] != target
                //based on current information, we can only move left pointer to skip one cell
                //thus in the worest case, we would have target: 2, and array like 11111111, then
                //the running time would be O(n)
                start++;
            }
        }

        return false;
    }

    public boolean search(int[] nums, int target) {
        boolean result = false;

        for (int num : nums) {
            if (num == target) return true;
        }


        return result;

    }
}
