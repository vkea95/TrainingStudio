package leetcode.com.hard.part2;

/**
 * Created by tclresearchamerica on 5/3/16.
 * *******************************************
 * Location:
 * https://leetcode.com/problems/find-the-duplicate-number/
 * *******************************************
 * Description:
 * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that
 * at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.
 * Note:
 * You must not modify the array (assume the array is read only).
 * You must use only constant, O(1) extra space.
 * Your runtime complexity should be less than O(n2).
 * There is only one duplicate number in the array, but it could be repeated more than once.
 * *******************************************
 * Analysis:
 * 1.这个重复的数字能会出现多次,那么有的数字就不会出现.
 * 2.空间复杂度为O(1),那么就只需要几个变量即可
 * 3.时间复杂度不能超过o(n2),即不可以2重循环.
 * 猜想:
 * 1.是否可以用value做标志,判断为第一次的设置,则设置,否则,它就是目标
 * 2.不能用stack,queue等,那么整数的bit是否可以呢,
 * 3.也可以用累计求和的方法,但是这样有大量的计算,同时还要考虑溢出的情况...
 * 4.根据提示的时间复杂度,用两重循环,然后一个正向一个逆向,若值相等,但下标不等,则直接返回value ---> Accepted  Successfully
 * *******************************************
 * Conclusion:
 * 这样的题目就是要求开发者换位思考,需要能够从各个维度进行假设和推理
 * *******************************************
 */
public class No287_Find_the_Duplicate_Number {
    public int findDuplicate(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = nums.length - 1; j >= 0; j--) {
                if (nums[i] == nums[j] && i != j) {
                    return nums[i];
                }
            }
        }
        return -1;
    }

}
