package leetcode.com.pickup1.medium;

/**
 * Created by tclresearchamerica on 7/13/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/container-with-most-water/
 * ****************************************************
 * Description:
 * Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). n vertical
 * lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together
 * with x-axis forms a container, such that the container contains the most water.
 * Note: You may not slant the container.
 * ****************************************************
 * Thoughts:
 * 1.寻找最大值和最小值,这样中间的差值就是我们要的容积啦,但是因为牵扯到线的长度,所以也并没有那么简单。
 * 思路就是要先控制住一个条件不变
 * ****************************************************
 * Time: 20mins
 * Beat: 50%
 * Bug: -
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No011_Container_With_Most_Water {
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1, answer = 0;
        while (left <= right) {
            int area = cal(left, right, height);
            answer = Math.max(answer, area);
            if (height[left] <= height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return answer;
    }

    private int cal(int left, int right, int[] height) {
        return (right - left) * Math.min(height[left], height[right]);
    }
}
