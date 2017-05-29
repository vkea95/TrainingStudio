package leetcode.com.medium.part21;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by tclresearchamerica on 4/28/16.
 * Location:
 * https://leetcode.com/problems/kth-largest-element-in-an-array/
 * ****************************************************************
 * Description:
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order,
 * not the kth distinct element.
 * <p>
 * For example,
 * Given [3,2,1,5,6,4] and k = 2, return 5.
 * <p>
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ array's length.
 * ****************************************************************
 * Analysis:
 * 1.使用Java内的数据结构机制完成任务:Arrays.sort() 或是PriorityQueue
 * PriorityQueue是从JDK1.5开始提供的新的数据结构接口。
 * 　　如果不提供Comparator的话，优先队列中元素默认按自然顺序排列，也就是数字默认是小的在队列头，字符串则按字典序排列。
 * 2.通过经典的排序方法实现
 */
public class No215_Kth_Largest_Element_in_an_Array {
    //Time is O(nlog(n))
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        //bug1:k --> nums.length-k
        //analsys:升序排列
        return nums[nums.length - k];
    }


    /**
     * We can use a min heap to solve this problem. The heap stores the top k elements.
     * Whenever the size is greater than k, delete the min. Time complexity is O(nlog(k)).
     * Space complexity is O(k) for storing the top k numbers.
     *
     * @param nums
     * @param k
     * @return
     */

    public int findKthLargest_II(int[] nums, int k) {
        //initialization
        PriorityQueue<Integer> queue = new PriorityQueue<>(k);
        for (int i : nums) {
            queue.offer(i);
            if (queue.size() > k) {
                queue.poll();
            }
        }
        return queue.peek();
    }

    /**
     * Average case time is O(n), worst case time is O(n^2).
     * <p>
     * This problem can also be solve by using the quickselect approach, which is similar to quicksort.
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest_III(int[] nums, int k) {
        //initialization
        if (k < 1 || nums == null) return 0;

        //bug3: k --> nums.length-k+1
        return getKth(nums.length - k + 1, nums, 0, nums.length - 1);
    }

    public int getKth(int k, int[] nums, int start, int end) {
        int pivot = nums[end];
        int left = start;
        int right = end;
        while (true) {
            while (nums[left] < pivot && left < right) {
                left++;
            }
            while (nums[right] >= pivot && right > left) {
                right--;
            }
            if (left == right) break;
            swqp(nums, left, right);

        }

        //bug1:
        swqp(nums, left, end);
        //big2:why? so judge
        if (k == left + 1) {
            return pivot;
        } else if (k < left + 1) {
            return getKth(k, nums, start, left - 1);
        } else {
            return getKth(k, nums, left + 1, end);
        }
    }

    public void swqp(int[] nums, int left, int right) {
        int tmp = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }
}
