package leetcode.com.tag.sort;

/**
 * Created by JianZhang on 9/7/17.
 * Given an unsorted array, find the maximum difference between the successive elements in its sorted form.
 * <p>
 * Try to solve it in linear time/space.
 * <p>
 * Return 0 if the array contains less than 2 elements.
 * <p>
 * You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.
 * Thoughts:
 * 1. sort in quick sort & calculate the differences between the successiv elements in its sorted from
 * <p>
 * label: bucket sort
 * Solutions:
 * 这个题的目的是，把Min-Max之间等分为N个桶。这样N个数最好的情况是分别落在N个桶里各1个。如果是这样，相当于已经排序好了，
 * 从左到右扫一遍。如果不是这种情况，一定存在一个桶是空的，那么Max Gap一定在两个桶之间而不是桶里面，
 * 所以可以用每个桶内的max和min来代表这个桶，然后计算相邻桶之间的gap
 */
public class No164_Maximum_Gap {
    public int maximumGap(int[] nums) {
        // write your code here
        if (nums.length < 2) return 0;
        int minNum = -1, maxNum = -1, n = nums.length;
        for (int i = 0; i < n; ++i) {
            minNum = min(nums[i], minNum);
            maxNum = max(nums[i], maxNum);
        }
        if (maxNum == minNum) return 0;
        double average = (maxNum - minNum) * 1.0 / (n - 1);//计算的时候，要乘以1.0
        if (average == 0) ++average;
        int[] localMin = new int[n];
        int[] localMax = new int[n];
        for (int i = 0; i < n; ++i) {
            localMin[i] = -1;
            localMax[i] = -1;
        }
        for (int i = 0; i < n; ++i) {//第n个元素貌似没用
            int t = (int) ((nums[i] - minNum) / average);
            localMin[t] = min(localMin[t], nums[i]);
            localMax[t] = max(localMax[t], nums[i]);//根据local的min和max方法,可知:一个桶有一个数的时候,它既是max也是min
        }
        int ans = (int) average, left = 0, right = 1;
        while (left < n - 1) {//left外延是N-1
            while (right < n && localMin[right] == -1) ++right;//只是循环到n-1
            if (right >= n) break;
            ans = max(ans, localMin[right] - localMax[left]);
            left = right;
            ++right;
        }
        return ans;
    }

    private int min(int a, int b) {
        if (a == -1) return b;
        else if (b == -1) return a;
        else if (a < b) return a;
        else return b;
    }

    private int max(int a, int b) {
        if (a == -1) return b;
        else if (b == -1) return a;
        else if (a > b) return a;
        else return b;
    }
}
