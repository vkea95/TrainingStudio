package leetcode.com.pickup1.easy;

/**
 * Created by tclresearchamerica on 7/8/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/merge-sorted-array/
 * ****************************************************
 * Description:
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
 * <p>
 * Note:
 * You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from
 * nums2. The number of elements initialized in nums1 and nums2 are m and n respectively.
 * ****************************************************
 * Thoughts:
 * 1.挪开不合适的元素,放入相对合适大小的元素,所以考虑倒着循环或许更好,只是nums1的最后元素并不好找,可以找到
 * 因为我们知道m和n的大小了已经
 * 所以目标位置就是从m+n-1开始的倒序,谁大就放在那里
 * 2.按照这个思路写了第一版,发现虽然通过了beat 2%,但是可以进行优化,
 * <p>
 * ****************************************************
 * Time: 20 mins
 * Beat: 45%
 * Bug: 0
 * ****************************************************
 * HindSight
 * 看了下第一遍的答案,发现做起来更简单,因为后序的末位已经确认,所以只要比较谁大,就可以完成这个任务了,将大的放入末位,然后末位减一
 * ****************************************************
 * ****************************************************
 */
public class No088_Merge_Sorted_Array {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i1 = m - 1, i2 = n - 1;

        int num1 = Integer.MIN_VALUE;
        int num2 = Integer.MIN_VALUE;
        for (int i = n + m - 1; i >= 0; i--) {
            if (i1 >= 0) {
                num1 = nums1[i1];
            } else {
                num1 = Integer.MIN_VALUE;
            }
            if (i2 >= 0) {
                num2 = nums2[i2];
            } else {
                num2 = Integer.MIN_VALUE;
            }
            if (num1 >= num2) {
                nums1[i] = num1;
                i1--;
            } else {
                nums1[i] = num2;
                i2--;
            }
        }
    }

    public void merge_2(int[] nums1, int m, int[] nums2, int n) {
        int i1 = m - 1, i2 = n - 1;

        int index = n + m - 1;
        while (i1 >= 0 && i2 >= 0) {
            if (nums1[i1] > nums2[i2]) {
                nums1[index--] = nums1[i1--];
            } else {

                nums1[index--] = nums2[i2--];
            }
        }

        //opt:此处i1即使没有循环完毕,也不必再做处理了,因为他们已经处在自己应该的位置了
        while (i2 >= 0) {
            nums1[index--] = nums2[i2--];
        }

    }
}
