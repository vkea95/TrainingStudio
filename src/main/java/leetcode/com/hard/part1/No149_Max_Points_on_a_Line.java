package leetcode.com.hard.part1;

import leetcode.com.util.Point;

/**
 * Created by tclresearchamerica on 5/18/16.
 * ***************************************************************
 * Location:
 * https://leetcode.com/problems/max-points-on-a-line/
 * ***************************************************************
 * Description:
 * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
 * ***************************************************************
 * Analysis:
 * 1.思路:满足(y1/y2=x1/x2) 的点,但是因为点很多,所以需要穷举法啊...执行效率很低,
 * 时间复杂度就更甭说了
 * 2.问题来了,什么样的数据结构会是更好的选择呢?需要调整point的顺序?
 * 3.有个想法就是,每个点都要和其余的点,做减法,然后用Y的差值/X的差值,
 * 4.网络答案:
 * 4.1两点就是一条直线,所以,只需要看3个点的情况就好,是否有3点一线,有的话,就count+1
 * 4.2处理duplicate问题:必须要小心,这是个动态变化的值.
 * ***************************************************************
 * ***************************************************************
 * ***************************************************************
 * ***************************************************************
 * ***************************************************************
 * ***************************************************************
 */
public class No149_Max_Points_on_a_Line {
    public static void main(String[] args) {
        No149_Max_Points_on_a_Line obj = new No149_Max_Points_on_a_Line();
        Point[] points = {new Point(0, 0), new Point(1, 1), new Point(0, 0)};
        obj.maxPoints(points);
    }

    public int maxPoints(Point[] points) {
        if (points == null) return 0;
        if (points.length <= 2) return points.length;

        int duplicate = 0;
        int result = 0;
        int len = points.length;
        int count = 0;

        for (int i = 0; i < len; i++) {
            Point p = points[i];
//            count = 0;
            duplicate = 0;

            for (int j = i + 1; j < len; j++) {
                Point q = points[j];
                if (q.x == p.x && q.y == p.y) {
                    //发现重复的点,之后,对最大的值进行加算
                    duplicate++;
                    // result += dupNumber+1;
                    //bug2:因为duplicate是截止目前所出现的,那么就不能把他和result加在一起
                    result = Math.max(result, duplicate + 1);

                    continue;
                }

                count = 2;
                for (int k = j + 1; k < len; k++) {
                    Point r = points[k];
                    count += isLinear(p, q, r) ? 1 : 0;
                }
                //bug1:duplicate代表的是目前的duplicate状况,所以要立即加上,而不要跳到第二个for外面,那样就会有重复的计算了
                result = Math.max(result, count + duplicate);

            }
            //bug1:duplicate有可能在其他地方已经被计算过了,就不在我们的计算范围内了呢啊
//            result += dupNumber;
        }

        return result;
    }

    public boolean isLinear(Point p, Point q, Point r) {
        return (q.y - p.y) * (r.x - p.x) == (q.x - p.x) * (r.y - p.y);
    }
}
