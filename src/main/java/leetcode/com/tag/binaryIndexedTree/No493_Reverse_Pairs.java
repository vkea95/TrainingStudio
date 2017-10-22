package leetcode.com.tag.binaryIndexedTree;

import java.util.*;

/**
 * Created by JianZhang on 10/16/17.
 * Given an array nums, we call (i, j) an important reverse pair if i < j and nums[i] > 2*nums[j].
 * <p>
 * You need to return the number of important reverse pairs in the given array.
 * <p>
 * Example1:
 * <p>
 * Input: [1,3,2,3,1]
 * Output: 2
 * Example2:
 * <p>
 * Input: [2,4,3,5,1]
 * Output: 3
 * Reference:
 * 1. https://discuss.leetcode.com/topic/79227/general-principles-behind-problems-similar-to-reverse-pairs/2
 * 2. http://www.cnblogs.com/grandyang/p/6657956.html
 * DP
 * http://www.hawstein.com/posts/dp-novice-to-advanced.html
 */
public class No493_Reverse_Pairs {

    public static void main(String[] args) {
        No493_Reverse_Pairs obj = new No493_Reverse_Pairs();
        int[] nums = new int[]{1, 3, 2, 3, 1};
        nums = new int[]{-5, -5};
        obj.reversePairs_2(nums);
    }

    private static int[] aux;

    public int reversePairs(int[] nums) {
        aux = new int[nums.length];
        return 0;
    }

    //Credit: http://www.jiuzhang.com/solutions/reverse-pairs/
    public long reversePairs_2(int[] A) {
        return mergeSort(A, 0, A.length - 1);
    }

    private int mergeSort(int[] A, int start, int end) {
        if (start >= end) {
            return 0;
        }

        int mid = (start + end) / 2;
        int sum = 0;
        sum += mergeSort(A, start, mid);
        sum += mergeSort(A, mid + 1, end);
        sum += merge(A, start, mid, end);
        return sum;
    }

    private int merge(int[] A, int start, int mid, int end) {
        int[] temp = new int[A.length];
        int leftIndex = start;
        int rightIndex = mid + 1;
        int index = start;
        int sum = 0;

        while (leftIndex <= mid && rightIndex <= end) {
            if (A[leftIndex] <= A[rightIndex]) {
                temp[index++] = A[leftIndex++];
            } else {
                int tempInt = leftIndex;
                while (tempInt <= mid) {
                    if (A[tempInt] > 2 * A[rightIndex]) {
                        sum += mid - tempInt + 1;
                        break;
                    }
                    tempInt++;
                }
                temp[index++] = A[rightIndex++];
//                sum += mid - leftIndex + 1;
            }
        }
        while (leftIndex <= mid) {
            temp[index++] = A[leftIndex++];
        }
        while (rightIndex <= end) {
            temp[index++] = A[rightIndex++];
        }

        for (int i = start; i <= end; i++) {
            A[i] = temp[i];
        }

        return sum;
    }

//    TODO:to implement without error
//    Credit:http://www.cnblogs.com/grandyang/p/6657956.html
//    fun4LeetCode大神归纳的第二种方法叫做分割重现关系(Partition Recurrence Relation)，
// 用式子表示是T(i, j) = T(i, m) + T(m+1, j) + C。这里的C就是处理合并两个部分的子问题，
// 那么用文字来描述就是“已知翻转对的两个数字分别在子数组nums[i, m]和nums[m+1, j]之中，
// 求满足要求的翻转对的个数”，这里翻转对的两个条件中的顺序条件已经满足，就只需要找到满足大小关系的的数对即可。
// 这里两个数字都是不确定的，如果用暴力搜索肯定会被OJ唾弃。但是如果两个子数组是有序的，
// 那么我们可以用双指针的方法在线性时间内就可以统计出符合题意的翻转对的个数。要想办法产生有序的子数组，
// 那么这就和MergeSort的核心思想完美匹配了。我们知道混合排序就是不断的将数组对半拆分成子数组，拆到最小的数组后开始排序，
// 然后一层一层的返回，最后原数组也是有序的了。这里我们在混合排序的递归函数中，对于有序的两个子数组进行统计翻转对的个数，
// 然后再逐层返回，这就完美的实现了上述的分割重现关系的思想。整个的写法非常的简洁，实在是太叼了。博主的直觉表明，
// fun4LeetCode大神肯定是国人，不要问我为什么，因为这么强的肯定是中国人，哈～
//
//    private int mergeSort(int[] nums, int left, int right) {
//        if (left >= right) return 0;
//        int mid = left + (right - left) / 2;
//        int res = mergeSort(nums, left, mid) + mergeSort(nums, mid + 1, right);
//        for (int i = left, j = mid + 1; i <= mid; ++i) {
//            while (j <= right && nums[i] / 2.0 > nums[j]) ++j;
//            res += j - (mid + 1);
//        }
//        sort(nums, left, right);
//        return res;
//    }
//
//    private void sort(int[] nums, int left, int right) {
//        int mid = left + (right - left) / 2;
//
//    }
//
//    private void merge(int[] nums, int left, int right, int mid) {
//        int i = left, j = mid + 1;
//        for (int k = left; k <= right; k++) aux[k] = nums[k];
//        for (int k = left; k <= right; k++) {
//            if (i > mid) aux[k] = nums[j++];
//            else if (j > right) aux[k] = nums[i++];
//            else if (nums[j] > nums[i]) aux[k] = nums[i++];
//            else aux[k] = nums[j++];
//
//        }
//    }
}
