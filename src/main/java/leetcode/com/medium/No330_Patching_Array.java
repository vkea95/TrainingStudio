package leetcode.com.medium;

import java.util.Arrays;

/**
 * Created by tclresearchamerica on 5/10/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/patching-array/
 * ****************************************************
 * Description:
 * Given a sorted positive integer array nums and an integer n, add/patch elements to the array
 * such that any number in range [1, n] inclusive can be formed by the sum of some elements in the array.
 * Return the minimum number of patches required.
 * <p>
 * Example 1:
 * nums = [1, 3], n = 6
 * Return 1.
 * <p>
 * Combinations of nums are [1], [3], [1,3], which form possible sums of: 1, 3, 4.
 * Now if we add/patch 2 to nums, the combinations are: [1], [2], [3], [1,3], [2,3], [1,2,3].
 * Possible sums are 1, 2, 3, 4, 5, 6, which now covers the range [1, 6].
 * So we only need 1 patch.
 * <p>
 * Example 2:
 * nums = [1, 5, 10], n = 20
 * Return 2.
 * The two patches can be [2, 4].
 * <p>
 * Example 3:
 * nums = [1, 2, 2], n = 5
 * Return 0.
 * ****************************************************
 * Analysis:
 * 完全没有思路,
 * ****************************************************
 * Solution:
 * http://www.bubuko.com/infodetail-1345277.html
 * 这道题给我们一个有序的正数数组nums，又给了我们一个正整数n，问我们最少需要给nums加几个数字，使其能组成[1,n]之间的所有数字，
 * 注意数组中的元素不能重复使用，否则的话只有要有1，就能组成所有的数字了。这道题我又不会了，上网看到了史蒂芬大神的解法，膜拜啊，
 * 这里就全部按他的解法来讲吧。
 * 我们定义一个变量miss，用来表示[0,n]之间最小的不能表示的值，那么初始化为1，为啥不为0呢，因为n=0没啥意义，直接返回0了。
 * 那么此时我们能表示的范围是[0, miss)，表示此时我们能表示0到miss-1的数，如果此时的num <= miss，
 * 那么我们可以把我们能表示数的范围扩大到[0, miss+num)，如果num>miss，那么此时我们需要添加一个数，
 * 为了能最大限度的增加表示数范围，我们加上miss它本身，以此类推直至遍历完整个数组，我们可以得到结果。下面我们来举个例子说明：
 * <p>
 * 给定nums = [1, 2, 4, 11, 30], n = 50，我们需要让[0, 50]之间所有的数字都能被nums中的数字之和表示出来。
 * <p>
 * 首先使用1, 2, 4可能表示出0到7之间的所有数，表示范围为[0, 8)，但我们不能表示8，因为下一个数字11太大了，
 * 所以我们要在数组里加上一个8，此时能表示的范围是[0, 16)，那么我们需要插入16吗，答案是不需要，因为我们数组有1和4，
 * 可以组成5，而下一个数字11，加一起能组成16，所以有了数组中的11，我们此时能表示的范围扩大到[0, 27)，但我们没法表示27，
 * 因为30太大了，所以此时我们给数组中加入一个27，那么现在能表示的范围是[0, 54)，应经满足要求了，我们总共添加了两个数8和27，所以返回2即可。
 * ****************************************************
 * 事后诸葛亮:
 * 思路被题目弄混乱了,其实就是如果把1~n-1,放到数轴上,向n的方向做平移,就会明白,他可以表示的范围就是1~2n-1,所以可以尝试2倍的方式进行接近
 * <p>
 * ****************************************************
 * 点评:
 * <p>
 * ****************************************************
 */
public class No330_Patching_Array {
    public static void main(String[] args) {
        No330_Patching_Array obj = new No330_Patching_Array();
//        int[] nums = {1, 3};
//        obj.minPatches(nums, 6);
        int[] nums = {1, 5, 10};
        obj.minPatches(nums, 20);
    }

    public int minPatches(int[] nums, int n) {
        int miss = 1, res = 0, i = 0;
        while (miss <= n) {
            if (i < nums.length && nums[i] <= miss) {
                miss += nums[i++];
            } else {
                miss += miss;
                ++res;
            }
        }
        return res;

    }


    public int minPatches_II(int[] nums, int n) {
        int count = 0, i = 0;
        for (long covered = 0; covered < n; ) {
            if ((i < nums.length && nums[i] > covered + 1) || i > nums.length) {
                covered += covered + 1;
                ++count;
            } else {
                covered += nums[i++];
            }
        }
        return count;
    }
}
