package google.com;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jason on 2016/3/1.
 * Description:
 * 给出一个整数数组nums，重新排列nums使得nums[0] < nums[1] > nums[2] < nums[3]...
 * Example:
 * nums = [1, 5, 1, 1, 6, 4], 一个可能的答案是[1, 4, 1, 5, 1, 6]
 * 数据保证必定有解。
 * ******************************************
 * Solution:
 * 本题有一种简单的做法，先快速排序，然后把最小的一半依次放在奇数位上，最大的一半依次放在偶数位上。
 * 算法复杂度是快速排序的复杂度O(NlogN)。仔细思考后发现快速排序不是必要的，
 * 只需要找到中位数即可。利用快速排序的思想找中位数的期望时间复杂度是O(N)。为了防止相等的数放在一起，
 * 需要注意放置的顺序。笔者采用的方法是依nums长度分两种情况：若长度为奇数，把比中位数小的依次放在0,2,4,...位置，
 * 比中位数大的依次放在length-2,length-4,...位置；若长度为偶数，把比中位数小的依次放在length-2,length-4,...位置，
 * 比中位数大的依次放在1,3,5,...位置。其余位置填充中位数。这样可以保证中位数一定与较小与较大的数相邻（题目保证一定有解）。
 */
public class No002_Wiggle_Sort {
    public static void main(String[] args) {
//test
    }
}
