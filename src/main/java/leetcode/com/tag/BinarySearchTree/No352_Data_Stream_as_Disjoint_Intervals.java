package leetcode.com.tag.BinarySearchTree;

import leetcode.com.util.Interval;

import java.util.*;

/**
 * Created by JianZhang on 10/22/17.
 * Given a data stream input of non-negative integers a1, a2, ..., an, ...,
 * summarize the numbers seen so far as a indexList of disjoint intervals.
 * <p>
 * For example, suppose the integers from the data stream are 1, 3, 7, 2, 6, ..., then the summary will be:
 * <p>
 * [1, 1]
 * [1, 1], [3, 3]
 * [1, 1], [3, 3], [7, 7]
 * [1, 3], [7, 7]
 * [1, 3], [6, 7]
 * Follow up:
 * What if there are lots of merges and the number of disjoint intervals are small compared to the data stream's size?
 * Solutions:
 * 1. 用的DS是TreeSet,然后implement compare方法,进而完善出来floor,higher方法,为我们所用
 */
public class No352_Data_Stream_as_Disjoint_Intervals {
    //    Credit: http://bookshadow.com/weblog/2016/05/31/leetcode-data-stream-as-disjoint-intervals/
    class SummaryRanges {

        private TreeSet<Interval> intervalSet;

        /**
         * Initialize your data structure here.
         */
        public SummaryRanges() {
            intervalSet = new TreeSet<>(new Comparator<Interval>() {
                @Override
                public int compare(Interval o1, Interval o2) {
                    return o1.start - o2.start;
                }
            });

        }

        public void addNum(int val) {
            Interval curInterval = new Interval(val, val);
            Interval floorInterval = intervalSet.floor(curInterval);//找到略小于当前interval的那个点
            if (floorInterval != null) {
                //bug1:此处就该返回了,因为已经囊括了这个val
                if (curInterval.start <= floorInterval.end) {
                    return;
                } else if (curInterval.start == floorInterval.end + 1) {
                    //updateHelper element
//                    bug2:直接就是去floor的start,它必然小
                    curInterval.start = floorInterval.start;
                    //remove it
                    intervalSet.remove(floorInterval);
                }
            }
            Interval higherInterval = intervalSet.higher(curInterval);//higher等价于ceiling
            if (higherInterval != null) {
//                    bug3:此处不必比较大小了,必然higher的start大于curtInerval,所以直接用start和curt的end比较
                if (higherInterval.start == curInterval.end + 1) {
                    curInterval.end = higherInterval.end;
                    //remove it
                    intervalSet.remove(higherInterval);
                }
            }
            intervalSet.add(curInterval);
        }

        public List<Interval> getIntervals() {
            return Arrays.asList(intervalSet.toArray(new Interval[0]));
        }
    }
}
