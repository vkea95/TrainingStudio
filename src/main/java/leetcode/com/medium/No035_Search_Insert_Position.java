package leetcode.com.medium;

/**
 * Created by tclresearchamerica on 7/18/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/search-insert-position/
 * ****************************************************
 * Description:
 * Given a sorted array and a target value, return the index if the target is found. If not, return the index where
 * it would be if it were inserted in order.
 * <p>
 * You may assume no duplicates in the array.
 * <p>
 * Here are few examples.
 * [1,3,5,6], 5 → 2
 * [1,3,5,6], 2 → 1
 * [1,3,5,6], 7 → 4
 * [1,3,5,6], 0 → 0
 * ****************************************************
 * Thoughts:
 * 1.2分法,快速定位,
 * 2.如果找到的和他不等的就进行处理
 * ****************************************************
 * Time: 15 mins
 * Beat: 17%
 * Bug: 1
 * ****************************************************
 * Hindsight:
 * 1.虽然2分法的写法没有问题,但是在处理start,end所对应的元素的大小上面,还是要仔细考虑比较的。
 * 2.这个一定要弄清楚,否则代码就白写了,题目不难,但是也不能犯这样的错误,所以一定要加强推理验证
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No035_Search_Insert_Position {
    public int searchInsert(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        int i = 0;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] < target) {
                start = mid;
            } else if (nums[mid] > target) {
                end = mid;
            } else {
                start = mid;
                break;
            }
        }


        //bug1:在判读返回值得时候,除了考虑start之外,还要考虑end
        if (nums[start]>=target){
            return start;
        }else if(nums[end]>=target){
            return end;
        }else {
            return end+1;
        }
    }
}
