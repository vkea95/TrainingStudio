package leetcode.com.hard.part2;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Created by tclresearchamerica on 5/4/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/find-median-from-data-stream/
 * ****************************************************
 * Description:
 * Median is the middle value in an ordered integer list. If the size of the list is even,
 * there is no middle value. So the median is the mean of the two middle value.
 * Examples:
 * [2,3,4] , the median is 3
 * [2,3], the median is (2 + 3) / 2 = 2.5
 * Design a data structure that supports the following two operations:
 * void addNum(int num) - Add a integer number from the data stream to the data structure.
 * double findMedian() - Return the median of all elements so far.
 * ****************************************************
 * Analysis:
 * 1.按照普通的方式进行计算,肯定会存在精度问题,估计这个才是这道题的难点,
 * 2.不断累加肯定会溢出,即使是double类型,也不保证没有问题.
 * 3.题意理解错了?是的.我改用数据结构,那么就该在树,栈,队列,链表等当中选一个...哎,完全跑题.
 * 4.没有真正立即median的含义:当变量值的项数N为奇数时，处于中间位置的变量值即为中位数；当N为偶数时，
 * 中位数则为处于中间位置的2个变量值的平均数。
 * ****************************************************
 * Solution:
 * http://www.programcreek.com/2015/01/leetcode-find-median-from-data-stream-java/
 * First of all, it seems that the best time complexity we can get for this problem is O(log(n)) of add() and O(1)
 * of getMedian(). This data structure seems highly likely to be a tree.
 * <p>
 * We can use heap to solve this problem. In Java, the PriorityQueue class is a priority heap. We can use two heaps to
 * store the lower half and the higher half of the data stream. The size of the two heaps differs at most 1
 * ****************************************************
 * ****************************************************
 */
public class No295_Find_Median_from_Data_Stream {
}

class MedianFinder {
    PriorityQueue<Integer> maxHeap;//lower half
    PriorityQueue<Integer> minHeap;//higher half

    public MedianFinder() {
        //bug1: don't how to initialize 倒序队列
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();

    }

    // Adds a number into the data structure.
    public void addNum(int num) {
        //add 与 offer 在这里没有区别
        maxHeap.offer(num);
        minHeap.offer(maxHeap.poll());
        if (maxHeap.size() < minHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    // Returns the median of current data stream
    public double findMedian() {
        if (maxHeap.size() == minHeap.size()) {
            //bug2:写错变量名称
            return (double) (maxHeap.peek() + minHeap.peek()) / 2;
        } else {
            return maxHeap.peek();
        }

    }
};