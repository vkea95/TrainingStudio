package leetcode.com.tag.sort;

import leetcode.com.util.Interval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by JianZhang on 8/29/17.
 * Given a collection of intervals, merge all overlapping intervals.
 * <p>
 * For example,
 * Given [1,3],[2,6],[8,10],[15,18],
 * return [1,6],[8,10],[15,18].
 * Level: medium
 * start:1044
 * Points:
 * 1. Comparator是Collections的一个考点: 制作卡片,比较Comparator和Compare
 * //bugs
 * 1. 比较条件该是previous.start<current.end
 * 2. 不要忽略end条件,否则会导致区间值不正确
 * 3. 循环结束后,要插入一次结果否则,答案不正确
 */
public class No056_Merge_Intervals {
    private class IntervalComparator implements Comparator<Interval> {

        @Override
        public int compare(Interval o1, Interval o2) {
            if (o1.start < o2.start) return -1;
            if (o1.start == o2.start) return 0;
            else return 1;
        }
    }

    public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.size() <= 1) return intervals;
//        intervals.
        List<Interval> result = new ArrayList<>();
        Collections.sort(intervals,
                new IntervalComparator());

        int validStart = intervals.get(0).start;
        int validEnd = intervals.get(0).end;
        for (int i = 1; i < intervals.size(); i++) {
            Interval currentInterval = intervals.get(i);
            if (currentInterval.start <= validEnd) {
                validEnd = currentInterval.end;
                validEnd = validEnd > currentInterval.end ? validEnd : currentInterval.end;
            } else {
                result.add(new Interval(validStart, validEnd));
                validStart = currentInterval.start;
                validEnd = currentInterval.end;
            }
        }
        result.add(new Interval(validStart, validEnd));

        return result;
    }

}
