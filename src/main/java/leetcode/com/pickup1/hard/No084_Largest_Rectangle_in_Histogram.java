package leetcode.com.pickup1.hard;

import leetcode.com.util.ListNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.TreeSet;

/**
 * Created by tclresearchamerica on 7/9/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/largest-rectangle-in-histogram/
 * ****************************************************
 * Description:
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1,
 * find the area of largest rectangle in the histogram.
 * Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].
 * The largest rectangle is shown in the shaded area, which has area = 10 unit.
 * For example,
 * Given heights = [2,1,5,6,2,3],
 * return 10.
 * ****************************************************
 * Thoughts:
 * 0.最大的长方形,除了高还要考虑有效长度
 * 1.怎么破?动态规划?这个就要先考虑好解法,再选择合适的数据结构啦!
 * 2.根据提示的这个例子可以知道,在没有循环完毕所有height之前,你是不知道哪个才是我们想要的答案!
 * 3.没有解法,倒推!用栈吗?动态规划?
 * 4.看了答案,发现解法本质都是类似的,就是入栈出栈,关键是规则是不一样的,就是一旦遇到height小的cell,之前的就得出栈计算面积,
 * 这里面还要控制下,栈为空,和在最后元素后面补充一个负数,保证全部数据都要得到合理计算的过程.然后用i-stack.peek()-1来处理宽度,
 * 因为当前高度是较小值,所以减一
 * ****************************************************
 * Thoughts:
 * 1.可视化处理后,发现面积是根据高度和有效的长度算出来的,有效长度是根据下标计算出来的,那么每次压栈的时候,一旦入栈元素的高度小于栈顶元素,
 * 则需要计算栈顶元素的面积,即当前栈顶元素的下标减去第二个元素的下标可知,若第二个元素不存在,则直接使用-1,
 * 最后需要放个-1进去逼出最后的元素。
 * ****************************************************
 * Time: 60 mins
 * Beat: 3%
 * Bug: 2
 * ****************************************************
 * Hindsight:
 * 1.已经可以将问题可视化了,
 * ****************************************************
 */
public class No084_Largest_Rectangle_in_Histogram {


    public int largestRectangleArea(int[] heights) {


        Stack<Integer> stack = new Stack<>();
        int max = 0;
        for (int i = 0; i <= heights.length; i++) {
            int curt = i == heights.length ? -2 : heights[i];
            while (!stack.isEmpty() && curt < heights[stack.peek()]) {
                int h = heights[stack.pop()];
                //bug1:算宽度的时候,一定要以当前的要入栈的元素的下标开始算起啊!!!
                int w = stack.isEmpty() ? i : (i - stack.peek() - 1);
                max = Math.max(max, h * w);
            }
            //bug1:
            stack.push(i);
        }

        return max;
    }

    public int largestRectangleArea_wrong(int[] heights) {

        Stack<Integer> stack = new Stack<>();
        int max = Integer.MIN_VALUE;


        for (int i = 0; i < heights.length; i++) {

            //bug
            while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                int idx = stack.pop();
                if (stack.isEmpty()) {
                    max = Integer.max(max, (idx + 1) * heights[idx]);
                } else {
                    max = Integer.max(max, (idx - stack.peek()) * heights[idx]);
                }
            }
            stack.push(i);

        }

        while (!stack.isEmpty() && -1 <= heights[stack.peek()]) {
            int idx = stack.pop();
            //bug1:循环所有元素后,再计算面积的时候,需要用到数组的长度啦
            //bug2:还是不知道如何处理这个问题呢-->问题出在宽度推导不对,应该用当前的下标去减去peek()-1,而不是用出栈的那个下标
            if (stack.isEmpty()) {
                max = Integer.max(max, (heights.length) * heights[idx]);
            } else {
                max = Integer.max(max, (heights.length - 1 - stack.peek()) * heights[idx]);
            }
        }


        return max == Integer.MIN_VALUE ? 0 : max;

    }
}