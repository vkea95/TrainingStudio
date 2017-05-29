package leetcode.com.pickup1.easy;

/**
 * Created by tclresearchamerica on 9/19/16.
 * ***************************************************************
 * Location:
 * https://leetcode.com/problems/rotate-function/
 * ***************************************************************
 * Description:
 * Given an array of integers A and let n to be its length.
 * Assume Bk to be an array obtained by rotating the array A k positions clock-wise, we define a "rotation
 * function" F on A as follow:
 * F(k) = 0 * Bk[0] + 1 * Bk[1] + ... + (n-1) * Bk[n-1].
 * Calculate the maximum value of F(0), F(1), ..., F(n-1).
 * Note:
 * n is guaranteed to be less than 105.
 * Example:
 * A = [4, 3, 2, 6]
 * F(0) = (0 * 4) + (1 * 3) + (2 * 2) + (3 * 6) = 0 + 3 + 4 + 18 = 25
 * F(1) = (0 * 6) + (1 * 4) + (2 * 3) + (3 * 2) = 0 + 4 + 6 + 6 = 16
 * F(2) = (0 * 2) + (1 * 6) + (2 * 4) + (3 * 3) = 0 + 6 + 8 + 9 = 23
 * F(3) = (0 * 3) + (1 * 2) + (2 * 6) + (3 * 4) = 0 + 2 + 12 + 12 = 26
 * So the maximum value of F(0), F(1), F(2), F(3) is F(3) = 26.
 * ***************************************************************
 * ref:
 * https://discuss.leetcode.com/topic/58616/java-solution-o-n-with-non-mathametical-explaination/2
 * Consider we have 5 coins A,B,C,D,E
 * <p>
 * According to the problem statement
 * F(0) = (0A) + (1B) + (2C) + (3D) + (4E)
 * F(1) = (4A) + (0B) + (1C) + (2D) + (3E)
 * F(2) = (3A) + (4B) + (0C) + (1D) + (2*E)
 * <p>
 * This problem at a glance seem like a difficult problem. I am not very strong in mathematics, so this is how
 * I visualize this problem
 * <p>
 * We can construct F(1) from F(0) by two step:
 * Step 1. taking away one count of each coin from F(0), this is done by subtracting "sum" from "iteration" in
 * the code below
 * after step 1 F(0) = (-1A) + (0B) + (1C) + (2D) + (3*E)
 * <p>
 * Step 2. Add n times the element which didn't contributed in F(0), which is A. This is done by adding "A[j-1]len"
 * in the code below.
 * after step 2 F(0) = (4A) + (0B) + (1C) + (2D) + (3E)
 * <p>
 * At this point F(0) can be considered as F(1) and F(2) to F(4) can be constructed by repeating the above steps.
 * ***************************************************************
 * Hindsight:
 * 1.按照原始的推理做法,会导致TLE,看了答案才明白,需要进行式子的推到和化简呢
 * ***************************************************************
 * ***************************************************************
 * ***************************************************************
 * ***************************************************************
 * ***************************************************************
 */
public class No396_Rotate_Function {
    public int maxRotateFunction(int[] A) {
        if (A == null || A.length == 0) return 0;

        int sum = 0, iteration = 0, n = A.length;
        for (int i = 0; i < n; i++) {
            sum += A[i];
            iteration += (A[i] * i);
        }
        //tip:先取个基本值,
        int max =iteration;
        //bug1:下标从1开始计算
        for (int i = 1; i < n; i++) {
            iteration = iteration - sum + A[i - 1] * n;
            max=iteration>max?iteration:max;
        }

        return max;
    }
}
