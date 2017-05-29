package leetcode.com.hard.part0;

import leetcode.com.util.Interval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by jason on 2016/2/22.
 * Location
 * https://leetcode.com/problems/merge-intervals/
 * ***********************************************
 * Description
 * Given a collection of intervals, merge all overlapping intervals.
 * For example,
 * Given [1,3],[2,6],[8,10],[15,18],
 * return [1,6],[8,10],[15,18].
 * ************************************************
 * Solution:
 * 为自己的类写一个comapre接口，达到可以排序的目的
 * 然后，再按照自小到大的方式循环，合并有交集的元素
 */
public class No056_Merge_Intervals {
    public List<Interval> merge(List<Interval> intervals) {
        if (null == intervals || intervals.size() <= 1) {
            return intervals;
        }
        List<Interval> result = new ArrayList<>();

        Collections.sort(intervals, new IntervalComparator());
        Interval last = intervals.get(0);
        for (int i = 1; i < intervals.size(); i++) {
            Interval curt = intervals.get(i);
            if (curt.start <= last.end) {
                last.end = Math.max(last.end, curt.end);
            } else {
                result.add(last);
                last = curt;
            }
        }
        result.add(last);

        return result;
    }


    private class IntervalComparator implements Comparator<Interval> {
        public int compare(Interval a, Interval b) {
            return a.start - b.start;
        }
    }

}
