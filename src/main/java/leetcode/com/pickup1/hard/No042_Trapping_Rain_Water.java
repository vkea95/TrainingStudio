package leetcode.com.pickup1.hard;

/**
 * Created by tclresearchamerica on 6/21/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/trapping-rain-water/
 * ****************************************************
 * Description:
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it is able to trap after raining.
 * For example,
 * Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
 * ****************************************************
 * Thoughts:
 * 1.类似于那个高楼算天际线的问题,这个题的问题在于计算有效高度,要看附近的两侧的最高的高度是多少,要用到栈?-->直接扫描,用数组保存max即可
 * 2.想想看咋办呢
 * 3.
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No042_Trapping_Rain_Water {
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

            //寻找第i个元素两侧的最低值
            int min = Math.min(max, maxHeight[i]);
            //判断是否可以寸水,如果可以则计算
            if (min > height[i]) {
                area += min - height[i];
            }
            //更新右侧最高点的高度
            max = Math.max(max, height[i]);

        }
        return area;

    }
}
