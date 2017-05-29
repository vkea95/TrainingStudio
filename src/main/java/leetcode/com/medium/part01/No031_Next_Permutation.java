package leetcode.com.medium.part01;

/**
 * Created by jason on 2016/1/12.
 * Locations:
 * https://leetcode.com/problems/next-permutation/
 * ***************************************************
 * Description:
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
 * The replacement must be in-place, do not allocate extra memory.
 * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 * **************************************************************
 * Tips：
 * 基本思路：将这个数列想象成整数，最右侧为个位，最左侧为最高位，那么如果寻找下一个比当前数略大的数，就该从低位开始进行
 * 寻找和交换，如：34162 ->34216 即从低位开始寻找，找到的第一个高于右侧数字的值得位即可交换，如此例中的1,则将位的数字
 * 与之前扫描过的数字中的最小值，进行交换，由因为在寻找1的过程中，保证了被扫描的数字是升序，所以需要将被扫描的数字进行
 * 翻转，因为1是与其中最小的数字进行了交换，所以不影响交换方式
 * **************************************************************
 * 实例:
 * 123,132
 *
 * **************************************************************
 * **************************************************************
 */
public class No031_Next_Permutation {
    public static void main(String[] args) {

    }

    public void nextPermutation(int[] nums) {
        if (nums == null) {
            return;
        }
        int len = nums.length;
        for (int i = len - 2; i >= 0; i--) {
            if (nums[i + 1] > nums[i]) {
                int j;
                for (j = len - 1; j > i - 1; j--) {
                    if (nums[j] > nums[i]) {//相当于寻找next较大的排列
                        break;
                    }
                }
                swap(nums, i, j);
                reverse(nums, i + 1, len - 1);
                return;
            }

        }
        reverse(nums, 0, len - 1);
    }

    void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    void reverse(int[] nums, int begin, int end) {
        for (int i = begin, j = end; i < j; i++, j--) {
            swap(nums, i, j);
        }
    }
}
