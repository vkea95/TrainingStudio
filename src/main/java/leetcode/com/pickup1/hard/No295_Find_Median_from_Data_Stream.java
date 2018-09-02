package leetcode.com.pickup1.hard;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Created by tclresearchamerica on 6/9/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/find-median-from-data-stream/
 * ****************************************************
 * Description:
 * Median is the middle value in an ordered integer indexList. If the size of the indexList is even,
 * there is no middle value. So the median is the mean of the two middle value.
 * <p>
 * Examples:
 * [2,3,4] , the median is 3
 * <p>
 * [2,3], the median is (2 + 3) / 2 = 2.5
 * <p>
 * Design a data structure that supports the following two operations:
 * <p>
 * void addNum(int num) - Add a integer number from the data stream to the data structure.
 * double findMedian() - Return the median of all elements so far.
 * For example:
 * <p>
 * add(1)
 * add(2)
 * findMedian() -> 1.5
 * add(3)
 * findMedian() -> 2
 * ****************************************************
 * Thoughts:
 * 1.预想2个队列,一个从大到小一个从小到大,size最多相差1
 * PriorityQueue
 * Time: 30mins
 * Beats: 50%
 * Bug: 2
 * ****************************************************
 * 如果要是换成一个自己写的算法的话,可以参考II的解法,用一棵二叉树来解决这个问题,算法略难,没看懂
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No295_Find_Median_from_Data_Stream {

    public static void main(String[] args) {
        MedianFinder obj = new MedianFinder();
        obj.addNum(1);
        obj.addNum(2);
        obj.findMedian();
        obj.addNum(3);
        obj.findMedian();
    }
}


class MedianFinder {
    PriorityQueue<Integer> queue = new PriorityQueue<>();
    PriorityQueue<Integer> reverseQueue = new PriorityQueue<>(Collections.reverseOrder());


    // Adds a number into the data structure.
    public void addNum(int num) {
        queue.offer(num);
        //bug2:因为数据并不是顺序插入的,有可能连续插入2个较大的数值,这样的话,计算起来就不对了
        //opt2:判断条件略显复杂 beats:40% ->50%
        reverseQueue.offer(queue.poll());
//        while (queue.size() > 0 && reverseQueue.peek() > queue.peek()) {
//            reverseQueue.offer(queue.poll());
//            queue.offer(reverseQueue.poll());
//        }
        //opt1: 其实不必计算size间的差值,只要比它大就可以了,就去另一边的值就好了
        //Beat 从20% --> 40%
        if (reverseQueue.size() > queue.size()) {
            queue.offer(reverseQueue.poll());
        }
    }

    // Returns the median of current data stream
    public double findMedian() {
        if (queue.size() == 0) return 0.0d;

        if (reverseQueue.size() == queue.size()) {
            //bug1: 2个整数做除法,返回的数值也是整数,但这样有问题哦
            return ((double) reverseQueue.peek() + (double) queue.peek()) / 2;
        } else {
            return queue.peek();
        }
    }
};

// Your MedianFinder object will be instantiated and called as such:
// MedianFinder mf = new MedianFinder();
// mf.addNum(1);
// mf.findMedian();