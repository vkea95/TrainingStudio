package leetcode.com.tag.sort;

/**
 * Created by JianZhang on 8/30/17.
 * Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent,
 * with the colors in the order red, white and blue.
 * <p>
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 * <p>
 * Note:
 * You are not suppose to use the library's sort function for this problem.
 * thought:
 * 1.三向切分法?-->可以解决问题,但计算时间却不是最快的,但是它有更广阔的使用空间,可以解决一系列的问题
 * 1.1 原地切分,不需要辅助数组,
 * 1.2 时间复杂度=?
 * 1.3 关键是array, lo, hi,v, lt, gt ,i ==>v=array[lo], i=lo+1,==>array[i] vs v -->lt,gt,i的增减变化
 * 1.4 曾经提交的方法中,速度比现在的快,但是没有这个整洁。。。
 * 1.5 它是快速排序的一种改进，使快排在有大量重复元素的数据，同样能保持高效。
 * 三向切分快速排序的特性就是遇到和比较值相同时,不进行数据交换, 这样对于有大量重复数据的排序时,三向切分快速排序算法就会优于
 * 普通快速排序算法,但由于它整体判断代码比普通快速排序多一点,所以对于常见的大量非重复数据,它并不能比普通快速排序多大多的优势
 */
public class No075_Sort_Colors {
    public void sortColors(int[] nums) {
        if (nums == null) return;
        sort(nums, 0, nums.length - 1);
    }

    private void sort(int[] nums, int lo, int hi) {
        if (hi <= lo) return;
        int lt = lo, gt = hi;
        int i = lo + 1;
        int v = nums[lo];
        while (i <= gt) {
            if (nums[i] < v) {
                exch(nums, lt++, i++);
            } else if (nums[i] > v) {
                exch(nums, gt--, i);
            } else {
                i++;
            }
        }
        sort(nums, lo, lt - 1);
        sort(nums, gt + 1, hi);
    }

    private void exch(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }

    public void sortColors_2(int[] nums) {
        if (nums == null) return;
        int[] count = new int[]{0, 0, 0};
        for (int num : nums) {
            if (num == 0) count[0]++;
            else if ((num == 1)) count[1]++;
            else count[2]++;
        }
        count[1] += count[0];
        count[2] += count[1];
        int i = 0;
        while (i < count[0]) {
            nums[i++] = 0;
        }
        while (i < count[1]) {
            nums[i++] = 1;
        }
        while (i < count[2]) {
            nums[i++] = 2;
        }

    }

}
