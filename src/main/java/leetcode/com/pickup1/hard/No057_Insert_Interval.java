package leetcode.com.pickup1.hard;

import leetcode.com.util.Interval;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tclresearchamerica on 6/29/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/insert-interval/
 * ****************************************************
 * Description:
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
 * ****************************************************
 * Thoughts:
 * 1.输入的都是已经排好序列的,所以,我们就可以遍历,寻找是否有overlap,
 * 2.最初的想法终究还是不正确的,按照第一版的方法来处理好了
 * ****************************************************
 * Hindsight:
 * 1.所以说,hard问题关键要想到点儿上,我这次换成去删除数据的做法,因为思考不周全,所以失败了,
 * 但是也没关系.下次还是要考虑周全再写code,必须将全case推到清楚才可以啊
 * 按照旧的做法,就没什么问题了,理解也简单,只是自己没有考虑清楚而已
 * 2.最终按照,modify的思想,将代码实现了,可是他效率却是很低啊
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No057_Insert_Interval {
    public static void main(String[] args) {
        No057_Insert_Interval obj = new No057_Insert_Interval();
        List<Interval> list = new ArrayList<>();
        obj.insert_slow(list, new Interval(5, 7));
    }

    public List<Interval> insert_slow(List<Interval> intervals, Interval newInterval) {

        int insertInx = 0;
        boolean insertFlg = false;
        for (int i = 0; i < intervals.size(); i++) {
            Interval interval = intervals.get(i);
            if (interval.end < newInterval.start) {
//                result.add(interval);
                insertInx++;
            } else if (interval.start > newInterval.end) {
                intervals.add(insertInx, newInterval);
                insertFlg = true;
                break;
//                result.add(interval);

            } else {
                //bug1:remove之后,需要自动i--处理，否则就会有个项目被略过不处理
                intervals.remove(i--);
                //最复杂情况
                newInterval.start = Math.min(newInterval.start, interval.start);
                newInterval.end = Math.max(newInterval.end, interval.end);
            }
        }
        if (!insertFlg) intervals.add(newInterval);
        return intervals;
    }

    public List<Interval> insert_fail(List<Interval> intervals, Interval newInterval) {
        List<Interval> result = new ArrayList<>();
        int start = -1, end = -1;
        for (int i = 0; i < intervals.size(); i++) {
            Interval interval = intervals.get(i);
            if (interval.start <= newInterval.start && newInterval.start <= interval.end) {
                start = i;
            }
            //bug1: don't use else if, for it's not opposite
            if (interval.start <= newInterval.end && newInterval.end <= interval.end) {
                end = i;
            }
        }
        if (start == -1) {

            if (end == -1) {
                System.out.println("1");
                //bug2: 新加的元素要保证输出的顺序是升序
                if (intervals.size() > 0 && newInterval.end < intervals.get(0).start) {
                    intervals.add(0, newInterval);
                } else if (intervals.size() > 0 && newInterval.start > intervals.get(intervals.size() - 1).end) {

                    intervals.add(newInterval);
                    //bug3:没考虑全包含的情况
                } else {
                    intervals.clear();
                    intervals.add(newInterval);
                }
            } else {
                System.out.println("2");
                for (int i = 0; i < end; i++) {
                    intervals.remove(0);
                }
                intervals.get(0).start = newInterval.start;
//                intervals.get(0).end=newInterval.en
            }
        } else {
            if (end == -1) {
                System.out.println("3");
                for (int i = start + 1; i < intervals.size(); i++) {
                    intervals.remove(i);
                }
                intervals.get(start).end = newInterval.end;
            } else {
                System.out.println("4");
                if (start != end) {
                    intervals.get(start).end = intervals.get(end).end;
                    for (int i = 1; i < (end - start + 1); i++) {
                        intervals.remove(start + 1);
                    }
                }
            }
        }
        return intervals;

    }
}
