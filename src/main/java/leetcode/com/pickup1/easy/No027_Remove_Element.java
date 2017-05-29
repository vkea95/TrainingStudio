package leetcode.com.pickup1.easy;

/**
 * Created by tclresearchamerica on 7/7/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/remove-element/
 * ****************************************************
 * Description:
 * Given an array and a value, remove all instances of that value in place and return the new length.
 * <p>
 * Do not allocate extra space for another array, you must do this in place with constant memory.
 * <p>
 * The order of elements can be changed. It doesn't matter what you leave beyond the new length.
 * <p>
 * Example:
 * Given input array nums = [3,2,2,3], val = 3
 * Your function should return length = 2, with the first two elements of nums being 2.
 * ****************************************************
 * Thought:
 * 1.用for循环控制起来比较麻烦,那么用while试试?
 * ****************************************************
 * Thought:
 * 没有特别好的想法,但是可以写出来,只是没有那么快速而已
 * ****************************************************
 * Time: 20 mins
 * Beat: 3%
 * Bug: 0
 * 其实还是第一次的写法更加便捷
 * 它的做法,就是如果发现一个是val的元素,就从末尾取一个元素存过来,末尾的下标自减1,当前元素下标不加1,如果当前元素不是val那么就下标加1,
 * 持续比较,返回的值就是末尾下标+1
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No027_Remove_Element {
    public int removeElement(int[] nums, int val) {
        int i = 0, j = nums.length;
        int count = 0;
        while (i < nums.length) {
            if (nums[i] == val) {
                count++;
                if (j == nums.length)
                    j = i;
            } else if (i > j) {
                nums[j] = nums[i];
                nums[i] = val;
                while (j < nums.length && nums[j] != val) {
                    j++;
                }
            }
            i++;
        }
        return nums.length - count;
    }
}
