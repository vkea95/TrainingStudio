package leetcode.com.tag.BinarySearchTree;

/**
 * Created by JianZhang on 10/21/17.
 * Given an integer array nums, return the number of range sums that lie in [lower, upper] inclusive.
 * Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j (i ≤ j), inclusive.
 * <p>
 * Note:
 * A naive algorithm of O(n2) is trivial. You MUST do better than that.
 * <p>
 * Example:
 * Given nums = [-2, 5, -1], lower = -2, upper = 2,
 * Return 3.
 * The three ranges are : [0, 0], [2, 2], [0, 2] and their respective sums are: -2, -1, 2.
 */
public class No327_Count_of_Range_Sum {

//    public static void main(String[] args) {
//        No327_Count_of_Range_Sum obj = new No327_Count_of_Range_Sum();
//        System.out.println("1st result is: " + obj.countRangeSum(new int[]{0}, 0, 0));
//        int[] aa = new int[]{-2, 5, -1};
//        System.out.println("2nd result is: " +obj.countRangeSum(aa, -2, 2));
////        for (int a:aa) System.out.println(a);
//    }
//
//    public int[] aux;
//
//    //    Credit:https://discuss.leetcode.com/topic/33770/short-simple-o-n-log-n
//    public int countRangeSum(int[] nums, int lower, int upper) {
//        if (nums == null || nums.length == 0) return 0;
//        int[] sum = new int[nums.length + 1];
//        aux = new int[nums.length + 1];
//        for (int i = 1; i < sum.length; i++) {
//            sum[i] = sum[i - 1] + nums[i - 1];
//        }
//
//        int rest = sort(sum, 1, sum.length - 1, lower, upper);
//        System.out.println();
//        for (int a : sum) System.out.print(a + ", ");
//
//        return rest;
//    }
//
//    private int sort(int[] obj, int lo, int hi, int lower, int upper) {
//        if (lo >= hi) return 0;
//        int res = 0;
//        int mid = lo + (hi - lo) / 2;
//        res += sort(obj, lo, mid, lower, upper);
//        res += sort(obj, mid + 1, hi, lower, upper);
//        int i = mid;
//        int j = mid;
//        for (int left = lo; left <= mid; left++) {
//            while (i <= hi && (obj[i] - obj[left]) < lower) i++;
//            while (j <= hi && (obj[j] - obj[left] <= upper)) j++;
//            res += j - i;
//        }
//        merge(obj, lo, mid, hi);
//        return res;
//    }
//
//    private void merge(int[] obj, int lo, int mid, int hi) {
//        for (int i = lo; i <= hi; i++) {
//            aux[i] = obj[i];
//        }
//        int i = lo, j = mid;
//        for (int k = lo; k <= hi; k++) {
//            if (i >= mid) {
//                obj[k] = aux[j++];
//            } else if (j > hi) {
//                obj[k] = aux[i++];
//            } else if (aux[i] <= aux[j]) {
//                obj[k] = aux[i++];
//            } else {
//                obj[k] = aux[j++];
//            }
//        }
//    }
}
