package leetcode.com.pickup1.medium;

/**
 * Created by tclresearchamerica on 9/9/16.
 */
public class No268_Missing_Number {
    public int missingNumber(int[] nums) {
        //根据不同的值放入相同的下标
        for (int i = 0; i < nums.length; i++) {
            int index = nums[i];
            while (index >= 0 && index < nums.length && nums[index] >= 0) {
                int temp = nums[index];
                nums[index] = -1;
                index = temp;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= 0) return i;
        }
        return nums.length;
    }
}
