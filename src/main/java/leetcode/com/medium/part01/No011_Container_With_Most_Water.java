package leetcode.com.medium.part01;

/**
 * Created by jason on 2016/1/17.
 * Locations:
 * https://leetcode.com/problems/container-with-most-water/
 * http://www.jiuzhang.com/solutions/container-with-most-water/
 * *************************************************************
 * Descriptions:
 * Given n non-negative integers a1, a2, ..., an,where each represents a point at coordinate (i, ai).
 * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
 * Find two lines, which together with x-axis forms a container,
 * such that the container contains the most water
 * 图形面积就是用最短的线段长度再乘以数组下标的差值即可
 * *************************************************************
 * Solutions:
 * 这个题目的难点在于，用来算面积的宽度和高度（两条线段取最短）都是变量，如果用遍历的方法计算的话，会很麻烦
 * 解法就是先从宽度着手，用宽度最大值开始计算，左右两个点不断向中间取收敛，收敛方式即抛弃最短边
 * *************************************************************
 * Bug:
 * 1.右侧收敛的时候，数组下标应该进行--操作
 * 2.数组的长度是通过属性length取得，而不是length方法
 * *************************************************************
 * Tip:
 * 1.初次看题完全不明白什么意思，其实是没有明白下标i是指数组的下标，随着ai变化的
 * 2.完全没有思路，不知道该抓住哪个不变的地方，其实换位思考，应该从极值角度着手较好
 */
public class No011_Container_With_Most_Water {
    public static void main(String[] args) {
        No011_Container_With_Most_Water no011 = new No011_Container_With_Most_Water();
        no011.maxArea(new int[]{1, 1, 2});
    }

    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int answer = 0;
        while (left <= right) {
            int area = calArea(left, right, height);
            answer = Math.max(answer, area);
            if (height[left] <= height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return answer;
    }

    private int calArea(int left, int right, int[] height) {
        return (right - left) * Math.min(height[left], height[right]);
    }
}
