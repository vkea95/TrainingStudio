package google.com;

/**
 * Created by tcl on 2016/3/21.
 * location:
 * http://mp.weixin.qq.com/s?__biz=MzA5MzE4MjgyMw==&mid=401408804&idx=1&sn=99356fe2b158135d6822cf8827bcc2e2&scene=21#wechat_redirect
 * *********************************************************
 * Description:
 * 给出一个从小到大排好序的整数数组nums和一个整数n，在数组中添加若干个补丁（元素）使得[1,n]的区间内的所有数都可以
 * 表示成nums中若干个数的和。返回最少需要添加的补丁个数。
 * Example 1：
 * nums = [1, 3], n = 6
 * 返回1，表示至少需要添加1个数｛2｝，才可以表示1到6之间所有数。
 * Example 2:
 * nums = [1, 5, 10], n = 20
 * 返回2，表示至少需要添加两个数｛2，4｝，才可以表示1到20之间所有数。
 * **********************************************************
 * Solution:
 * 读者不难想到暴力搜索的做法：先穷举每一个不在数组里的数p，再穷举判断p是否可以表示为数组中若干个数的和；
 * 如果不能，则把p加入数组中，把答案加一。
 * <p>
 * 然而，这种做法时间复杂度高且实际操作难度大（需要考虑穷举的顺序）。
 * 我们不妨先思考一个简单的问题，如果nums数组为空，那么最少需要多少个数字才能表示1到n之间所有数？
 * 相信大家都可以想到一个贪心算法，即按照1、2、4、8...都顺序添加，每次加入都数都比之前所有数的总和大1，
 * 直到总和大于n。本题的难点是预先给出了一些数，但这不影响我们的贪心策略：
 * 假设nums当前至多可以表示1到m之间的所有数，加入m+1；直到m大于等于n。
 */
public class No001_Min_Patches {
    public static void main(String[] args) {
        No001_Min_Patches obj = new No001_Min_Patches();
        int[] nums = new int[]{1, 5, 10};
        int n = 20;
        obj.minPatches(nums, n);
    }

    public int minPatches(int[] nums, int n) {
        long sum = 0;
        int ans = 0;
        int index = 0;
        if (nums.length > 0 && nums[0] == 1) {
            sum = 1;
            index = 1;
        }
        while (sum < n) {
            while (index < nums.length && nums[index] <= sum) {
                sum += nums[index];
                index++;
            }
            if (sum < n) {
                if (index < nums.length && nums[index] == sum + 1)
                    index++;
                else
                    ans++;
                sum = 2 * sum + 1;
                System.out.println("sum = 2 * sum + 1: " + sum);
            }
        }
        return ans;
    }
}
