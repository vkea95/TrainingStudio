package leetcode.com.hard.part0;

import java.util.Stack;

/**
 * Created by jason on 2016/1/13.
 * Locations:
 * https://leetcode.com/problems/largest-rectangle-in-histogram/
 * **************************************************************
 * Descriptions:
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1,
 * find the area of largest rectangle in the histogram.
 * For example,
 * Given height = [2,1,5,6,2,3],
 * return 10.
 * ********************************************************************
 * Solutions:
 * 循环输入的数组，将数组下标入栈，若欲入栈的数组元素，比栈顶端元素小说明，栈顶端元素不能利用欲入栈元素计算面积，
 * 则栈顶元素出栈，则当前栈顶元素的下标代表最后一个比刚出栈元素小的下标，可以计算面积。
 * 若欲入栈的数组元素，比栈顶元素大，则若明当前栈顶元素计算面积时候，可以用到欲入栈元素，则欲入栈元素顺利入栈。
 * 由于数组循环完毕，栈内仍有剩余元素，所以可以再最后输入-1--这么一个比所有元素都小的数值
 */
public class No084_Largest_Rectangle_In_Histogram {

    public static void main(String[] args) {

    }

    public int largestRectangleArea(int[] height) {

        if (height == null) return 0;
        int max = 0;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i <= height.length; i++) {
            int curt = i == height.length ? -1 : height[i];
            while (!stack.isEmpty() && curt < height[stack.peek()]) {
                int h = height[stack.pop()];
                int w = stack.isEmpty() ? i : (i - stack.peek() - 1);
                max = Math.max(max, h * w);

            }
            stack.push(i);
        }

        return max;
    }
}
