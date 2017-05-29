package leetcode.com.pickup1.hard;

import sun.nio.cs.ext.MacHebrew;

/**
 * Created by tclresearchamerica on 7/12/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/median-of-two-sorted-arrays/
 * ****************************************************
 * Description:
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 * Example 1:
 * nums1 = [1, 3]
 * nums2 = [2]
 * The median is 2.0
 * Example 2:
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * The median is (2 + 3)/2 = 2.5
 * ****************************************************
 * Thought:
 * 变态二分法,首先要分奇偶数,但还是忘记具体的实现方式了,啊!!!!!!
 * 所以没有想出来,跳出循环的条件呢啊---原来是寻找第k个来解决问题哦
 * ****************************************************
 * Time: 30 mins
 * Beat: 40%
 * Bug:1
 * 整数除以整数,返回还是整数,即使想要的是double,所以要在第一轮的function定义中就定义好类型为double
 * ****************************************************
 * Hindsight:
 * 考虑好久还是没有想到答案,看来这道题的掌握度很低啊,近似于零咯
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No004_Median_of_Two_Sorted_Arrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int len = m + n;

        if (len % 2 == 1) {
            return searchKth(nums1, nums2, 0, 0, len / 2 + 1);
        } else {

            return (searchKth(nums1, nums2, 0, 0, len / 2) +
                    searchKth(nums1, nums2, 0, 0, len / 2 + 1)) / 2;
        }


    }

    private double searchKth(int[] nums1, int[] nums2, int start1, int start2, int k) {

        if (start1 >= nums1.length) return nums2[start2 + k - 1];
        if (start2 >= nums2.length) return nums1[start1 + k - 1];

        if (k == 1) return Math.min(nums1[start1], nums2[start2]);
        int val1 = start1 + k / 2 - 1 >= nums1.length ? Integer.MAX_VALUE : nums1[start1 + k / 2 - 1];
        int val2 = start2 + k / 2 - 1 >= nums2.length ? Integer.MAX_VALUE : nums2[start2 + k / 2 - 1];

        if (val1 > val2) {
            return searchKth(nums1, nums2, start1, start2 + k / 2, k - k / 2);
        } else {

            return searchKth(nums1, nums2, start1 + k / 2, start2, k - k / 2);
        }

    }
}
