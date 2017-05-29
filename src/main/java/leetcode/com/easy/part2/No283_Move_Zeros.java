package leetcode.com.easy.part2;

/**
 * Created by tclresearchamerica on 5/3/16.
 * *******************************************
 * Location:
 * https://leetcode.com/problems/move-zeroes/
 * *******************************************
 * Description:
 * Given an array nums, write a function to move all 0's to the end of it while maintaining
 * the relative order of the non-zero elements.
 * <p>
 * For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].
 * <p>
 * Note:
 * You must do this in-place without making a copy of the array.
 * Minimize the total number of operations.
 * *******************************************
 * bug1:
 * 考虑不全,没有考虑有多个0的情况
 * 看了下网络的答案,发现需要的是2个指针,一个指向零,一个指向非零,然后交换即可,似乎是我没有想透彻这个问题,所以没有思路,虽然题目简单,但是...
 * bug2:
 * 时间复杂度出现了问题,不能在较短的时间内完成运算,所以今后设计算法的时候,至少要考虑循环次数是否可以缩减...
 * *******************************************
 * *******************************************
 */
public class No283_Move_Zeros {
    public void moveZeroes(int[] nums) {
        int j = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                j = Math.max(j, i);
                while (j < nums.length) {
                    if (nums[j] != 0) {
                        nums[i] = nums[j];
                        nums[j] = 0;
                        break;
                    }
                    j++;
                }

            }
        }
    }
}
