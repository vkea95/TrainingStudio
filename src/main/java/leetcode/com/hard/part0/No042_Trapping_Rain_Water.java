package leetcode.com.hard.part0;

/**
 * Created by jason on 2016/2/21.
 * Location:
 * https://leetcode.com/problems/trapping-rain-water/
 * ***************************************************
 * Description:
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it is able to trap after raining.
 * For example,
 * Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
 * ***************************************************
 * Solution:
 * 新生成一个数组，数值来源于参数数组中相近两个元素中的最大值
 * 计算面积的时候，需要用 Math.min(max, maxHeight[i]) - height[i]
 * max = Math.max(max, height[i]),这个即是计算某点两侧高于改点的最小值，然后选取最小值进行计算面积
 */
public class No042_Trapping_Rain_Water {
    public static void main(String[] args) {
        No042_Trapping_Rain_Water obj = new No042_Trapping_Rain_Water();
        int[] arr = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        obj.trap(arr);
    }

    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int[] maxHeight = new int[height.length + 1];

        maxHeight[0] = 0;

        for (int i = 0; i < height.length; i++) {
            maxHeight[i + 1] = Math.max(maxHeight[i], height[i]);
        }

        //max右侧的最高高度
        int max = 0;
        int area = 0;
        for (int i = height.length - 1; i >= 0; i--) {
            area += Math.min(max, maxHeight[i]) > height[i] ? Math.min(max, maxHeight[i]) - height[i] : 0;
            max = Math.max(max, height[i]);
        }
        return area;

    }
}
