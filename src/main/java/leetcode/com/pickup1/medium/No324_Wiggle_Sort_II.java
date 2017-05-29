package leetcode.com.pickup1.medium;

import java.util.Arrays;

/**
 * Created by tclresearchamerica on 7/27/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/wiggle-sort-ii/
 * ****************************************************
 * Description:
 * Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
 * <p>
 * Example:
 * (1) Given nums = [1, 5, 1, 1, 6, 4], one possible answer is [1, 4, 1, 5, 1, 6].
 * (2) Given nums = [1, 3, 2, 2, 3, 1], one possible answer is [2, 3, 1, 3, 1, 2].
 * <p>
 * Note:
 * You may assume all input has valid answer.
 * <p>
 * Follow Up:
 * Can you do it in O(n) time and/or in-place with O(1) extra space?
 * ****************************************************
 * Thought:
 * 1.如果将数组排序的话,就是较小的部分放到奇数列,较大的部分放入偶数列?--->网络答案和我的相反,它是从中间和最后面开始取的开始取,
 * 2.如果是这样子的话,该如何遍历呢?要求O(n)的时间复杂度,那就先排序,再分配位置好了
 * 3.但是不知道如何inPlace!!!还是要再另开一个数组才好处理
 * ****************************************************
 * Hindsight:
 * 1.取长度一半的方法很好
 * 2.答案中在做循环处理的时候,需要带入值跟着视图进行推导才能明白需要先做减减处理
 * ****************************************************
 * 网络答案:
 * 难点是会有相等的元素，而要求相邻元素除了wiggle外，不能相等。
 * 那么就不能取排序后相邻的元素交换，而要和后面的元素交换。例如：
 * <p>
 * //1 2 3 4 5 6
 * //3 6 2 5 1 4
 * 牺牲空间的做法是，建立一个新数组temp，按照我们想要的规律放入元素，最后copy回原数组nums。
 * 简单的思路就是，假设nums里有n个数，我们循环n/2次或者n/2+1次，每次循环里为temp添加两个数（n为奇数时，最后一次循环只添加一个数）。
 * 最后用System.arraycopy(sourceArray, 0, targetArray, 0, targetArray.length).
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No324_Wiggle_Sort_II {
    public void wiggleSort(int[] nums) {

        Arrays.sort(nums);
        int[] temp = new int[nums.length];
        int s = (nums.length + 1) >> 1, t = nums.length;
        for (int i = 0; i < nums.length; i++) {
            //这是在判断奇偶数,奇数的话,放前半部分,偶数的话,放后半部分
            temp[i] = (i & 1) == 0 ? nums[--s] : nums[--t];
        }

        System.arraycopy(temp, 0, nums, 0, nums.length);
//        for (int i = 0; i < nums.length; i++)
//            nums[i] = temp[i];
    }

    public void wiggleSort_internetanswer(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length, mid = (n-1)/2, index = 0;
        int[] temp = new int[n];
        for (int i = 0; i <= mid; i++) {
            temp[index] = nums[mid-i];
            if (index+1 < n) {
                temp[index+1] = nums[n-1-i];
            }
            index += 2;
        }
        System.arraycopy(temp, 0, nums, 0, n);
    }

}
