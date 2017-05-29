package leetcode.com.pickup1.hard;

import leetcode.com.util.Interval;
import leetcode.com.util.ListNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by tclresearchamerica on 7/7/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/merge-intervals/
 * ****************************************************
 * Description:
 * Given a collection of intervals, merge all overlapping intervals.
 * For example,
 * Given [1,3],[2,6],[8,10],[15,18],
 * return [1,6],[8,10],[15,18].
 * ****************************************************
 * ****************************************************
 * ****************************************************
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
public class No056_Merge_Intervals {

    public static void main(String[] args) {
        No056_Merge_Intervals obj = new No056_Merge_Intervals();
        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1, 2));
        intervals.add(new Interval(2, 2));
        intervals.add(new Interval(3, 2));
        obj.merge(intervals);
    }


    public List<Interval> merge(List<Interval> intervals) {

        //bug1: 没有判断intervals是否为空
        if (intervals == null || intervals.size() <= 1) {
            return intervals;
        }

        List<Interval> result = new ArrayList<>();
        //sort the elements of the  list
        Collections.sort(intervals, new Comparator<Interval>() {
                    @Override
                    public int compare(Interval o1, Interval o2) {
                        return o1.start - o2.start;
                    }
                }
        );

        Interval prev = intervals.get(0);
        for (int i = 1; i < intervals.size(); i++) {
            Interval curt = intervals.get(i);
            if (prev.end >= curt.start) {
                prev.end = Math.max(prev.end, curt.end);
            } else {
                result.add(prev);
                prev = curt;
            }
        }
        result.add(prev);

        return result;
    }
}
