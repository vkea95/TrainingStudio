package leetcode.com.pickup1.medium;

/**
 * Created by tclresearchamerica on 6/24/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/search-for-a-range/
 * ****************************************************
 * Description:
 * Given a sorted array of integers, find the starting and ending position of a given target value.
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * If the target is not found in the array, return [-1, -1].
 * For example,
 * Given [5, 7, 7, 8, 8, 10] and target value 8,
 * return [3, 4].
 * ****************************************************
 * Thoughts:
 * O(log n) 提示就是 2分法
 * ****************************************************
 * Time: 25 mins
 * Beat: 8%
 * Bug: 2-->第二个bug比较容易再次出现
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No034_Search_for_a_Rang {
    public int[] searchRange(int[] nums, int target) {
        //opt:提前初始化,error结果,代码简明
        int[] rst = new int[]{-1, -1};
        int start = 0, end = nums.length - 1;
        int mid = (start + end) / 2;
        while (start + 1 < end) {
            if (nums[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
            mid = (start + end) / 2;
        }
        if (nums[start] == target) {
            rst[0] = start;
            //bug1:不能start++,否则会有越界的风险
//            start++;
        } else if (nums[end] == target) {
            rst[0] = end;
            //opt3:避免再次从下标0,开始扫描数组.
            start = end;
            //bug3:
//            start=end+1;

        } else {
            return rst;
        }
        //opt2:end=nums.length-1提到括号外面来,代码简洁
        end = nums.length - 1;
        while (start + 1 < end) {
            if (nums[mid] <= target) {
                start = mid;
            } else {
                end = mid;
            }
            mid = (start + end) / 2;
        }

        //bug2:先判断end再判断start,保证结果的顺序性正确
        if (nums[end] == target) {
            rst[1] = end;
        } else if (nums[start] == target) {
            rst[1] = start;
        }


        return rst;


    }
}
