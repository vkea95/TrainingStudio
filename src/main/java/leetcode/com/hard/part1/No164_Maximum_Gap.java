package leetcode.com.hard.part1;

import java.util.Arrays;

/**
 * Created by tclresearchamerica on 5/19/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/maximum-gap/
 * ****************************************************
 * Description:
 * Given an unsorted array, find the maximum difference between the successive elements in its sorted form.
 * Try to solve it in linear time/space.------------------------->这个要求限定了我们要用桶排序,而不是其他的排序方式
 * Return 0 if the array contains less than 2 elements.
 * You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.
 * ****************************************************
 * Analysis:
 * 1.题意:找到最大的差值,在它已经排好序号的case,
 * "find the maximum difference between the successive elements in its sorted form."
 * -->指的是在现有array中,已经排好序的,连续的2个数字间的差值?
 * 2.网络答案:
 * 2.1找出数组中最大的差值(Max-Min)
 * 2.2算出各个元素间的平均差值=(Max-Min)/(n-1)
 * 利用桶排序思想：
 * 假设有N个元素数组array,最大值为Max,最小值为Min。
 * 那么最大差值不会大于ceiling[(Maz - Min) / (N - 1)]。
 * 令bucket（桶）的大小len = ceiling[(Maz - Min) / (N - 1)]，则最多会有(Max - Min) / len + 1个桶
 * 对于数组中的任意整数K，很容易通过算式index = (K - Min) / len找出其桶的位置，然后维护每一个桶的最大值和最小值。
 * 由于同一个桶内的元素之间的差值至多为len - 1，因此最终答案不会从同一个桶中选择。
 * 对于每一个非空的桶p，找出下一个非空的桶q，则q.min - p.max可能就是备选答案。返回所有这些可能值中的最大值
 * ****************************************************
 * Beat
 * ****************************************************
 */
public class No164_Maximum_Gap {

    public static void main(String[] args) {

//        int[] nums = {200,10,30,101,90,99,67,21,9,12,651,201,2,3,231,212,2020202};

        int[] nums = {3, 6, 9, 10};
        No164_Maximum_Gap obj = new No164_Maximum_Gap();
        obj.maximumGap(nums);

    }

    public int maximumGap_slow(int[] nums) {
        Arrays.sort(nums);
        int max = 0;
        for (int i = 1; i < nums.length; i++) {
            max = Math.max(max, nums[i] - nums[i - 1]);
        }

        return max;
    }

    public int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) return 0;
        int n = nums.length;
        int min = nums[0];
        int max = nums[0];
        for (int num : nums) {
            if (min>num) min=num;
            if (max<num) max=num;
            //bug6:提高执行效率,赋值操作,比判断更消耗时间
//            min = Math.min(min, num);
//            max = Math.max(max, num);
        }
        //bug1:计算平均差值等于多少
        int gap = (max - min) / (n - 1);

        //bug2:避免gap=0的情况
        gap = gap == 0 ? 1 : gap;

        //bug3:算下最大的有可能的长度,+1是为了处理四舍五入,避免超出范围
        int len = (max - min) / gap + 1;
        //因为所有的数字都是非负数,所以初期化数值为零
        int[] tMax = new int[len];
        int[] tMin = new int[len];

        for (int i = 0; i < n; i++) {
            int index = (nums[i] - min) / gap;
            //bug4:保留同样index情况下,最大的数值和最小的数值
            if (nums[i] > tMax[index])
                tMax[index] = nums[i];
            if (tMin[index] == 0 || nums[i] < tMin[index])
                tMin[index] = nums[i];
        }

        int result = 0;

        for (int i = 0; i < len; i++) {
            //bug5:因为在求连续的数字中差值最大的,所以就用tMin[i]-min了
            if (result < tMin[i] - min) result = tMin[i] - min;
            if (tMax[i] != 0) min = tMax[i];
        }


        return result;

    }
}
