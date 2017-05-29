package leetcode.com.pickup1.medium;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by tclresearchamerica on 8/11/16.
 * **************************************************************
 * Location:
 * https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
 * **************************************************************
 * Description:
 * Given a n x n matrix where each of the rows and columns are sorted in ascending order,
 * find the kth smallest element in the matrix.
 * <p>
 * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
 * <p>
 * Example:
 * <p>
 * matrix = [
 * [ 1,  5,  9],
 * [10, 11, 13],
 * [12, 13, 15]
 * ],
 * k = 8,
 * <p>
 * return 13.
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ n2.
 * **************************************************************
 * Thought:
 * 1.每行的数据都是拍好的顺序
 * 2.寻找第k小个元素,而不是第k个不同的元素
 * 3.解法可以选用priority queue,设置到第k个
 * 4.问题1:如何科学地遍历数组?
 * 5.重要信息:列也是升序排列的
 * **************************************************************
 * Hindsight:
 * 1.用到了priority Queue
 * 2.计算遍历了多少次k,
 * 3.每次向队列插入元素的时候,都是取最小的那个元素,然后用该元素的右侧和下侧的元素向队里里面插入
 * 4.这样保证每次都是插入当前的较小的2个值
 * 5.亮点是Priority Queue的声明方式,用到了内部方法? 可以调用外面的数组,同时这个priotiy的内部类型是数组--->脑洞开的很大
 * 6.这样再加上2个元素之间的比较,导致位置信息被正确地保留下来
 * **************************************************************
 * **************************************************************
 * **************************************************************
 * **************************************************************
 * **************************************************************
 * **************************************************************
 */
public class No378_Kth_Smallest_Element_in_a_Sorted_Matrix {

    public int kthSmallest(int[][] matrix, int k) {
        int c = 0;


        PriorityQueue<int[]> queue = new PriorityQueue<>(k, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return matrix[o1[0]][o1[1]] - matrix[o2[0]][o2[1]];
            }
        });
        //initialization
        queue.offer(new int[]{0, 0});
        while (true) {
            int[] pair = queue.poll();
            if (++c == k) {
                return matrix[pair[0]][pair[1]];
            }
            if (pair[0] == 0 && pair[1] + 1 < matrix[0].length) {
                queue.offer(new int[]{0, pair[1] + 1});
            }
            if (pair[0] + 1 < matrix.length) {
                queue.offer(new int[]{pair[0] + 1, pair[1]});
            }
        }
    }
}
