package leetcode.com.pickup1.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by tclresearchamerica on 10/4/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/count-of-smaller-numbers-after-self/
 * ****************************************************
 * Description:
 * You are given an integer array nums and you have to return a new counts array. The counts array has the property
 * where counts[i] is the number of smaller elements to the right of nums[i].
 * <p>
 * Example:
 * <p>
 * Given nums = [5, 2, 6, 1]
 * <p>
 * To the right of 5 there are 2 smaller elements (2 and 1).
 * To the right of 2 there is only 1 smaller element (1).
 * To the right of 6 there is 1 smaller element (1).
 * To the right of 1 there is 0 smaller element.
 * Return the array [2, 1, 1, 0].
 * ****************************************************
 * Ref:http://bookshadow.com/weblog/2015/12/06/leetcode-count-of-smaller-numbers-after-self/
 * 题目大意：
 * 给定一个整数数组nums，返回一个新的数组counts。数组counts的第i个元素counts[i]表示位于nums[i]右侧且小于nums[i]的元素个数。
 * <p>
 * 实际上就是求逆序对的个数。
 * <p>
 * 测试用例如题目描述。
 * <p>
 * <p>
 * ****************************************************
 * Ref:https://www.hrwhisper.me/leetcode-count-of-smaller-numbers-after-self/
 * ****************************************************
 * Ref:https://discuss.leetcode.com/topic/31405/9ms-short-java-bst-solution-get-answer-when-building-bst/2
 * Every node will maintain a val sum recording the total of number on it's left bottom side, dup counts the
 * duplication. For example, [3, 2, 2, 6, 1], from back to beginning,we would have:
 * <p>
 * 1(0, 1)
 * \
 * 6(3, 1)
 * /
 * 2(0, 2)
 * \
 * 3(0, 1)
 * When we try to insert a number, the total number of smaller number would be adding dup and sum of the nodes
 * where we turn right.
 * for example, if we insert 5, it should be inserted on the way down to the right of 3, the nodes where
 * we turn right is 1(0,1), 2,(0,2), 3(0,1), so the answer should be (0 + 1)+(0 + 2)+ (0 + 1) = 4
 * <p>
 * if we insert 7, the right-turning nodes are 1(0,1), 6(3,1), so answer should be (0 + 1) + (3 + 1) = 5
 * ****************************************************
 * Ref:https://discuss.leetcode.com/topic/31162/mergesort-solution/2
 * The smaller numbers on the right of a number are exactly those that jump from its right to its left
 * during a stable sort. So I do mergesort with added tracking of those right-to-left jumps.
 * ****************************************************
 * Ref:https://discuss.leetcode.com/topic/31554/11ms-java-solution-using-merge-sort-with-explanation
 * MergerSort
 * ****************************************************
 * Hindsight:
 * 1.面向对象的方式,解决问题
 * 2.mergesort的实现方法,
 * 2.1需要一个clone的数组,
 * 2.2然后跳出条件是: start>=end
 * 2.3merge操作是3个循环在里面处理
 * 3.提高效率的方法,上面的那个ref实现是在里面又new出了一个新的数组,这样,这要赋值
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No315_Count_of_Smaller_Numbers_After_Self {
    public static void main(String[] args) {
        int[] nums = new int[]{5, 2, 6, 1};
        No315_Count_of_Smaller_Numbers_After_Self obj = new No315_Count_of_Smaller_Numbers_After_Self();
        obj.countSmaller(nums);

    }

    class Pair {
        int index;
        int val;

        public Pair(int index, int val) {
            this.index = index;
            this.val = val;
        }
    }

    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        Pair[] arr = new Pair[nums.length];
        Integer[] smaller = new Integer[nums.length];
        Arrays.fill(smaller, 0);
        for (int i = 0; i < nums.length; i++) {
            arr[i] = new Pair(i, nums[i]);
        }
        mergeSort(arr, new Pair[arr.length], smaller, 0, arr.length - 1);
        res.addAll(Arrays.asList(smaller));

        return res;
    }


    private void mergeSort(Pair[] arr, Pair[] clone, Integer[] smaller, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = start + (end - start) / 2;
        mergeSort(arr, clone, smaller, start, mid);
        mergeSort(arr, clone, smaller, mid + 1, end);
        merge(arr, clone, smaller, start, mid, end);
    }

    private void merge(Pair[] arr, Pair[] clone, Integer[] smaller, int start, int mid, int end) {
        for (int i = start; i <= end; i++) {
            clone[i] = arr[i];
        }
        int i = start;
        int j = mid + 1;
        int k = start;
        int count = 0;
        while (i <= mid && j <= end) {
            if (clone[i].val <= clone[j].val) {
                //bug1: 此处只需要累计有几项元素移动就好,想加的操作放在别的地方进行
                smaller[clone[i].index] += count;
                arr[k++] = clone[i++];
            } else {
                //bug1: 此处只需要累计有几项元素移动就好,想加的操作放在别的地方进行
                count++;
                arr[k++] = clone[j++];
            }
        }
        while (i <= mid) {
            smaller[clone[i].index] += count;
            arr[k++] = clone[i++];
        }

        while (j <= end) {
            arr[k++] = clone[j++];
        }
    }

}
