package leetcode.com.easy.part0;

/**
 * Created by jason on 2016/2/28.
 * Location:
 * https://leetcode.com/problems/merge-sorted-array/
 * **********************************************************
 * Description:
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
 * Note:
 * You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional
 * elements from nums2. The number of elements initialized in nums1 and nums2 are m and n respectively.
 * **********************************************************
 * Solution:
 * 倒序处理2个数组，即从最后的元素开始进行比较，比较后面的n个元素空间是空的；:
 */
public class No088_Merge_Sorted_Array {
    /**
     * @param nums1: sorted integer array A which has m elements,
     *               but size of A is m+n
     * @param nums2: sorted integer array B which has n elements
     * @return: void
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int index = m + n - 1;
        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[index--] = nums1[i--];
            } else {

                nums1[index--] = nums2[j--];
            }
        }
        while (i >= 0) {
            nums1[index--] = nums1[i--];
        }
        while (j >= 0) {
            nums1[index--] = nums2[j--];
        }
    }
}
