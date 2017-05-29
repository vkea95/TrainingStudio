package leetcode.com.hard.part0;

import leetcode.com.util.Interval;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jason on 2016/2/23.
 * Location：
 * https://leetcode.com/problems/insert-interval/
 * **********************************************
 * Description:
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 * You may assume that the intervals were initially sorted according to their start times.
 * Example 1:
 * Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
 * Example 2:
 * Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].
 * This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
 * *************************************************
 * Solution:
 */
public class No057_Insert_Interval {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        if (intervals == null || newInterval == null) {
            return intervals;
        }
        List<Interval> result = new ArrayList<>();

        int insertPos = 0;

        for (Interval interval : intervals) {
            if (interval.end < newInterval.start) {
                result.add(interval);
                insertPos++;
            } else if (interval.start > newInterval.end) {
                result.add(interval);
            } else {
                newInterval.start = Math.min(interval.start, newInterval.start);
                newInterval.end = Math.max(interval.end, newInterval.end);
            }
        }
        result.add(insertPos, newInterval);
        return result;
    }


}
