package leetcode.com.pickup1.hard;

/**
 * Created by tclresearchamerica on 6/4/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/maximum-gap/
 * ****************************************************
 * Description:
 * Given an unsorted array, find the maximum difference between the successive elements in its sorted form.
 * Try to solve it in linear time/space.
 * Return 0 if the array contains less than 2 elements.
 * You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.
 * ****************************************************
 * 题意：给定一个未排序的数组，返回其排序后的数组中 相邻元素之差 最大的值。
 * 比如给定：[5,9,8,3,15]
 * 排序后为：[3,5,8,9,15]，相邻元素之差最大的是15-9=6，返回6。
 * 复杂度要求：时间空间均为O(n)。
 * ****************************************************
 * Thoughts:
 * 完全没有思路,不知道如何破解,直接照抄元来的解法,然后在默写
 * ****************************************************
 * Time: 45 mins
 * Beats: 80%
 * Bugs: 8
 * ****************************************************
 */
public class No164_Maximum_Gap {
    //bug5:Memory Limit Exceeded
    public int maximumGap(int[] nums) {

        //bug2: name of variable is wrong: num -> nums
        //bug7: less than 2 means < not <=
        if (nums == null || nums.length < 2) return 0;
        int n = nums.length;
        //bug9:直接将第0个元素付给min和max，减少一次赋值操作
        int max = nums[0], min = nums[0];

        //optimization1: i 从1 开始计数
        for (int i = 1; i < n; i++) {
            if (nums[i] < min) min = nums[i];
            if (nums[i] > max) max = nums[i];
        }

        int avgGap = (max - min) / (n - 1);
        //bug6:when avgGap is 0, plus 1
        //optimization2: 只有必须赋值时,才赋值,
//        avgGap = avgGap == 0 ? 1 : avgGap;
        if (avgGap==0) avgGap++;
        //bug5:Memory Limit Exceeded: not mul but divide
        int len = (max - min) / avgGap + 1;
        //bug3:names of variable are dupNumber
        int[] minBucket = new int[len];
        int[] maxBucket = new int[len];
        for (int i = 0; i < n; i++) {
            //bug6: maybe avgGap is zero
            int index = (nums[i] - min) / avgGap;
            if (nums[i] > maxBucket[index]) maxBucket[index] = nums[i];
            //bug4: use wrong index in nums array: index -> i
            //bug8:wrong name of array is used
            if (minBucket[index] == 0 || nums[i] <= minBucket[index]) minBucket[index] = nums[i];
        }

        int result = 0;
        for (int i = 0; i < len; i++) {
            //bug1: leak of )
            if (result < (minBucket[i] - min)) result = minBucket[i] - min;
            if (maxBucket[i] != 0) min = maxBucket[i];
        }

        return result;


    }
}
