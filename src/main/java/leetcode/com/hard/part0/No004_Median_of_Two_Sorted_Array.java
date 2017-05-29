package leetcode.com.hard.part0;

/**
 * Created by tclresearchamerica on 6/28/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/median-of-two-sorted-arrays/
 * ****************************************************
 * Description:
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * <p>
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 * <p>
 * Example 1:
 * nums1 = [1, 3]
 * nums2 = [2]
 * <p>
 * The median is 2.0
 * Example 2:
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * <p>
 * The median is (2 + 3)/2 = 2.5
 * ****************************************************
 * Thoughts:
 * 1.所以需要排序依次如果奇数个的话,那么就是中间的那个,如果是偶数个那就是中间2个的平均值
 * 2.提示O(log (m+n)) 意味着树形结构,2分法,
 * 3.或者是先要插入排序,然后再去中间值呗
 * ****************************************************
 * References:
 * https://leetcode.com/discuss/15790/share-my-o-log-min-m-n-solution-with-explanation
 * https://leetcode.com/discuss/9265/share-my-simple-o-log-m-n-solution-for-your-reference
 * 中文解释
 * http://blog.csdn.net/yutianzuijin/article/details/11499917/
 * 首先假设数组A和B的元素个数都大于k/2，我们比较A[k/2-1]和B[k/2-1]两个元素，这两个元素分别表示A的第k/2小的元素和B的第k/2小的元素。
 * 这两个元素比较共有三种情况：>、<和=。如果A[k/2-1]<B[k/2-1]，这表示A[0]到A[k/2-1]的元素都在A和B合并之后的前k小的元素中。换句话说，
 * A[k/2-1]不可能大于两数组合并之后的第k小值，所以我们可以将其抛弃。
 * 证明也很简单，可以采用反证法。假设A[k/2-1]大于合并之后的第k小值，我们不妨假定其为第（k+1）小值。由于A[k/2-1]小于B[k/2-1]，
 * 所以B[k/2-1]至少是第（k+2）小值。但实际上，在A中至多存在k/2-1个元素小于A[k/2-1]，B中也至多存在k/2-1个元素小于A[k/2-1]，
 * 所以小于A[k/2-1]的元素个数至多有k/2+ k/2-2，小于k，这与A[k/2-1]是第（k+1）的数矛盾。
 * 当A[k/2-1]>B[k/2-1]时存在类似的结论。
 * 当A[k/2-1]=B[k/2-1]时，我们已经找到了第k小的数，也即这个相等的元素，我们将其记为m。由于在A和B中分别有k/2-1个元素小于m，
 * 所以m即是第k小的数。(这里可能有人会有疑问，如果k为奇数，则m不是中位数。这里是进行了理想化考虑，在实际代码中略有不同，是先求k/2，
 * 然后利用k-k/2获得另一个数。)
 * 通过上面的分析，我们即可以采用递归的方式实现寻找第k小的数。此外我们还需要考虑几个边界条件：
 * 如果A或者B为空，则直接返回B[k-1]或者A[k-1]；
 * 如果k为1，我们只需要返回A[0]和B[0]中的较小值；
 * 如果A[k/2-1]=B[k/2-1]，返回其中一个；
 * ****************************************************
 * Time: 40 mins
 * Beat: 40%
 * Bug: 1
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No004_Median_of_Two_Sorted_Array {
    public static void main(String[] args) {
        No004_Median_of_Two_Sorted_Array obj = new No004_Median_of_Two_Sorted_Array();
        obj.findMedianSortedArrays(new int[]{1, 2, 3}, new int[]{5, 6, 7});
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length = nums1.length + nums2.length;
        if (length % 2 == 0) {
//            System.out.println(findKth(nums1, nums2, 0, 0, length / 2));
//            System.out.println(findKth(nums1, nums2, 0, 0, length / 2 + 1));
            return (findKth(nums1, nums2, 0, 0, length / 2) + findKth(nums1, nums2, 0, 0, length / 2 + 1)) / 2;
        } else {
            return findKth(nums1, nums2, 0, 0, length / 2 + 1);
        }
    }


    public double findKth(int[] nums1, int[] nums2, int s1, int s2, int k) {
//
//        System.out.println(s1 + " " + s2 + " " + k);

        if (s1 >= nums1.length) return nums2[s2 + k - 1];

        if (s2 >= nums2.length) return nums1[s1 + k - 1];

        if (k == 1) return Math.min(nums1[s1], nums2[s2]);

        int key1 = (s1 + k / 2 - 1) >= nums1.length ? Integer.MAX_VALUE : nums1[s1 + k / 2 - 1];
        int key2 = (s2 + k / 2 - 1) >= nums2.length ? Integer.MAX_VALUE : nums2[s2 + k / 2 - 1];

        if (key1 > key2) {

            //bug1:新的下标是s+k/2, 然后 k -> k-k/2
            return findKth(nums1, nums2, s1, s2 + k / 2, k - k / 2);

        } else {
            //bug1:新的下标是s+k/2
            return findKth(nums1, nums2, s1 + k / 2, s2, k - k / 2);
        }


    }
}
