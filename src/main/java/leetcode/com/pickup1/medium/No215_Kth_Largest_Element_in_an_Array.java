package leetcode.com.pickup1.medium;

/**
 * Created by tclresearchamerica on 7/18/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/kth-largest-element-in-an-array/
 * ****************************************************
 * Description:
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order,
 * not the kth distinct element.
 * <p>
 * For example,
 * Given [3,2,1,5,6,4] and k = 2, return 5.
 * ****************************************************
 * Thoughts:
 * 1.首先要排序,然后取第k个就好了,为啥要medium?难道有更好的解法?
 * 2.根据正常的答案来看,可以用priority queue时间复杂度是O(NlogK),Arrays的sort方法 复杂度是O(N logN)
 * 3.或者是快速排序-->这个还是要掌握下比较好
 * 时间 Avg O(N) Worst O(N^2) 空间 O(1)
 * 思路
 * 跟快速排序一个思路。先取一个枢纽值，将数组中小于枢纽值的放在左边，大于枢纽值的放在右边，具体方法是用左右两个指针，如果他们小于枢纽值则
 * 将他们换到对面，一轮过后记得将枢纽值赋回分界点。如果这个分界点是k，说明分界点的数就是第k个数。否则，如果分界点大于k，
 * 则在左半边做同样的搜索。如果分界点小于k，则在右半边做同样的搜索。
 * 快排的几个要素:
 * 1.要有交换的function
 * 2.
 * 注意
 * helper函数的k是k-1，因为我们下标从0开始的，我们要比较k和下标，来确定是否左半部分有k个数字。
 * 互换左右时，也要先判断left <= right
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No215_Kth_Largest_Element_in_an_Array {
    public int findKthLargest(int[] nums, int k) {
        return helper(nums, k - 1, 0, nums.length - 1);
    }
    /*
 * 注意
 * helper函数的k是k-1，因为我们下标从0开始的，我们要比较k和下标，来确定是否左半部分有k个数字。
 * 互换左右时，也要先判断left <= right
     */

    private int helper(int[] num, int k, int left, int right) {
        //拿到哨兵值
        int pivot = num[(left + right) / 2];
        int orgL = left, orgR = right;

        while (left <= right) {
            //find 1st number which is  the from left to right
            while (num[left] > pivot) {
                left++;
            }
            //find 1st number which is  the from right to left
            while (num[right] < pivot) {
                right--;
            }
            if (left <= right) {
                swap(num, left, right);
                left++;
                right--;
            }

        }
        // 最后退出的情况应该是右指针在左指针左边一格
        // 这时如果右指针还大于等于k，说明kth在左半边
        if (orgL < right && k <= right) return helper(num, k, orgL, right);
        // 这时如果左指针还小于等于k，说明kth在右半边
        if (left < orgR && k >= left) return helper(num, k, left, orgR);
        return num[k];
    }

    private void swap(int[] num, int idx1, int idx2) {
        int temp = num[idx1];
        num[idx1] = num[idx2];
        num[idx2] = temp;
    }

    //下面是快速排序的一个例程
    static void quicksort(int n[], int left, int right) {
        int dp;
        if (left < right) {
            dp = partition(n, left, right);
            quicksort(n, left, dp - 1);
            quicksort(n, dp + 1, right);
        }
    }

    static int partition(int n[], int left, int right) {
        int pivot = n[left];
        while (left < right) {
            while (left < right && n[right] >= pivot)
                right--;
            if (left < right)
                n[left++] = n[right];
            while (left < right && n[left] <= pivot)
                left++;
            if (left < right)
                n[right--] = n[left];
        }
        n[left] = pivot;
        return left;
    }

}
