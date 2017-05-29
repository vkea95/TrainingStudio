package leetcode.com.hard;

/**
 * Created by tclresearchamerica on 5/28/16.
 * ************************************************
 * Location:
 * https://leetcode.com/problems/self-crossing/
 * ************************************************
 * Description:
 * You are given an array x of n positive numbers. You start at point (0,0) and moves x[0] metres to the north,
 * then x[1] metres to the west, x[2] metres to the south, x[3] metres to the east and so on. In other words,
 * after each move your direction changes counter-clockwise.
 * <p>
 * Write a one-pass algorithm with O(1) extra space to determine, if your path crosses itself, or not.
 * <p>
 * Example 1:
 * Given x = [2, 1, 1, 2],
 * ┌───┐
 * │   │
 * └───┼──>
 * │
 * <p>
 * Return true (self crossing)
 * Example 2:
 * Given x = [1, 2, 3, 4],
 * ┌──────┐
 * │      │
 * │
 * │
 * └────────────>
 * <p>
 * Return false (not self crossing)
 * Example 3:
 * Given x = [1, 1, 1, 1],
 * ┌───┐
 * │   │
 * └───┼>
 * <p>
 * Return true (self crossing)
 * ************************************************
 * Thoughts
 * 1.必要条件:
 * A.下标0,1,2,3,4,5,6,7:index% 4
 * B.Y轴坐标必须为零否则无解--->思路有误
 * -->原题的意思不是穿过原点,而是是否和path本身相交
 * Again:
 * 这个是贪食蛇的原形题
 * 如果这个path和其本身路径不想交的话,那么就可能向外的无线循环和向内的无线循环.
 * 研究了路径自我穿越的几种情况,发现有规律但是还没搞清楚.横线穿竖线,
 * ************************************************
 * Solutions:
 * 网络答案:
 * 也是按照情况做的分类,不过他们并没有考虑坐标的情况,只是单纯的比较长度,其实,这样也是合理的,添加坐标反而比较麻烦了
 * 一个比较好的方式,是先把外螺旋剔除干净,然后根据2中情况,对x[i-1]进行重新划定:x[i-1]=x[i-1]-x[i-3]
 * 2中情况分布式:i>=4 && x[i]>=x[i-2]-x[i-4] 或 i=3 && x[i]=x[i-2]
 * 在内循环确定的情形下,只要x[i]>=x[i-2],就可以认为是有交叉的情况啦
 * ************************************************
 * ************************************************
 * ************************************************
 * ************************************************
 */
public class No335_Self_Crossing {

    public static void main(String[] args) {
        No335_Self_Crossing obj = new No335_Self_Crossing();

        int[] x = new int[]{2, 1, 1, 2};
        obj.isSelfCrossing(x);
    }

    public boolean isSelfCrossing(int[] x) {
        if (x.length <= 3) {
            return false;
        }
        int i = 2;
        // keep spiraling outward
        while (i < x.length && x[i] > x[i - 2]) {
            i++;
        }
        if (i >= x.length) {
            return false;
        }
        // transition from spiraling outward to spiraling inward
        if ((i >= 4 && x[i] >= x[i - 2] - x[i - 4]) ||
                (i == 3 && x[i] == x[i - 2])) {
            x[i - 1] -= x[i - 3];
        }
        i++;
        // keep spiraling inward
        while (i < x.length) {
            if (x[i] >= x[i - 2]) {
                return true;
            }
            i++;
        }
        return false;
    }

    /**
     * Categorize the self-crossing scenarios, there are 3 of them:
     * 1. Fourth line crosses first line and works for fifth line crosses second line and so on...
     * 2. Fifth line meets first line and works for the lines after
     * 3. Sixth line crosses first line and works for the lines after
     */
    public boolean isSelfCrossing_slow(int[] x) {
        int l = x.length;
        if (l <= 3) return false;

        for (int i = 3; i < l; i++) {
            if (x[i] >= x[i - 2] && x[i - 1] <= x[i - 3]) return true;  //Fourth line crosses first line and onward
            if (i >= 4) {
                if (x[i - 1] == x[i - 3] && x[i] + x[i - 4] >= x[i - 2])
                    return true; // Fifth line meets first line and onward
            }
            if (i >= 5) {
                if (x[i - 2] - x[i - 4] >= 0 &&
                        x[i] >= x[i - 2] - x[i - 4] &&
                        x[i - 1] >= x[i - 3] - x[i - 5] &&
                        x[i - 1] <= x[i - 3])
                    return true;  // Sixth line crosses first line and onward
            }
        }
        return false;
    }
}
