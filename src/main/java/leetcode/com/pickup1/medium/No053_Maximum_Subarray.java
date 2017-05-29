package leetcode.com.pickup1.medium;

/**
 * Created by tclresearchamerica on 7/18/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/maximum-subarray/
 * ****************************************************
 * Description:
 * Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
 * <p>
 * For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
 * the contiguous subarray [4,−1,2,1] has the largest sum = 6
 * ****************************************************
 * Thoughts:
 * 1.只要数是正的,就要保留下来,
 * 2.根据题意,进行可视化,发现过程中需要保留2个中间变量,一个是目前为止的最大值,另一个是到当前元素的连续累计和,
 * 需要注意的是,若连续累计和小于零,则直接设成零。
 * 每次都要用连续累计和同当前最大值中,取个较大的更新到当前最大值中。
 * ****************************************************
 * Time: 15mins
 * Beat: 86%
 * Bug:1
 * ****************************************************
 * Hindsight:
 * 1.回去看第一遍的解答,发现用到了DP 中的前缀和,即sum[i]=A[0]+..+A[i], sum[j]-sum[i]=A[i+1]...+A[j]
 * 这个概念或是思想,还是要牢记的,
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No053_Maximum_Subarray {
    public int maxSubArray(int[] nums) {
        int sum = 0;
        int candidate = Integer.MIN_VALUE;
        for (int n : nums) {
            int temp =sum+n;
            if (temp>candidate) candidate=temp;
            if (temp>=0) sum=temp;
            //bug:若比零小,则归零
            else sum=0;
        }
        return candidate;
    }
}
