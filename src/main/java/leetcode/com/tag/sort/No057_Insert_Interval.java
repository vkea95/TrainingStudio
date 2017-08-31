package leetcode.com.tag.sort;

import alg4.com.ch0401.SymbolGraph;
import edu.princeton.cs.algs4.In;
import leetcode.com.util.Interval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by JianZhang on 8/30/17.
 * Descriptions:
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 * <p>
 * You may assume that the intervals were initially sorted according to their start times.
 * <p>
 * Example 1:
 * Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
 * <p>
 * Example 2:
 * Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].
 * <p>
 * This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
 * start:0945
 * thoughts:
 * 1. sort the origin list
 * 2. O(n)循环数组,查看交集
 * bugs:
 * 1. 没有给结果排序
 * 2. 没有考虑[[1,5]] + [0,3] 这种case
 * Solution:
 * 1. 先对数字对同输入项是否有交集,进行check
 * 2. 分析出, 如果有交集的时候,需要对newInterval进行更新,更新的方式就是start取最小,end取最大
 * 3. 关于插入位置,默认为零,找到最后一个end比newInterval的start小的位置就好了
 */
public class No057_Insert_Interval {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("111");
        list.add("2");
        for (String s : list.subList(0, 10)) {

            System.out.print(s);
        }
    }


    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> result = new ArrayList<>();
        if (intervals == null) {
            result.add(newInterval);
            return result;
        }
        int insertPost = 0;
        for (Interval current : intervals) {
            if (current.end < newInterval.start) {
                insertPost++;
                result.add(current);
            } else if (current.start > newInterval.end) {
                insertPost++;
                result.add(current);
            } else {
                //交集区间处理
                newInterval.start = current.start < newInterval.start ? current.start : newInterval.start;
                newInterval.end = current.end < newInterval.end ? newInterval.end : current.end;
            }
        }
        result.add(insertPost, newInterval);
        return result;

    }

}

