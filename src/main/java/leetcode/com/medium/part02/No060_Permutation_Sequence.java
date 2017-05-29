package leetcode.com.medium.part02;

/**
 * Created by jason on 2016/2/16.
 * Location：
 * https://leetcode.com/problems/permutation-sequence/
 * ****************************************************
 * Description：
 * The set [1,2,3,…,n] contains a total of n! unique permutations.
 * By listing and labeling all of the permutations in order,
 * We get the following sequence (ie, for n = 3):
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * Given n and k, return the kth permutation sequence.
 * Note: Given n will be between 1 and 9 inclusive.
 * ****************************************************
 * Analysis:
 * 在n!个排列中，第一位的元素总是(n-1)!一组出现的，也就说如果p = k / (n-1)!，那么排列的最开始一个元素一定是nums[p]。
 * <p>
 * 假设有n个元素，第K个permutation是
 * a1, a2, a3, .....   ..., an
 * 那么a1是哪一个数字呢？
 * 那么这里，我们把a1去掉，那么剩下的permutation为
 * a2, a3, .... .... an, 共计n-1个元素。 n-1个元素共有(n-1)!组排列，那么这里就可以知道
 * 设变量K1 = K
 * a1 = K1 / (n-1)!
 * 同理，a2的值可以推导为
 * a2 = K2 / (n-2)!
 * K2 = K1 % (n-1)!
 * .......
 * a(n-1) = K(n-1) / 1!
 * K(n-1) = K(n-2) /2!
 * an = K(n-1)
 * ****************************************************
 * Ref:https://segmentfault.com/a/1190000003766760
 * ****************************************************
 * ****************************************************
 */
public class No060_Permutation_Sequence {
    public static void main(String[] args) {
        No060_Permutation_Sequence obj = new No060_Permutation_Sequence();

        obj.getPermutation(9, 123);
    }

    public String getPermutation(int n, int k) {

        StringBuffer sb = new StringBuffer();
        boolean[] used = new boolean[n];

        //KeyPoint1:k=k-1 根据数组的下标为零,所以k-1
        k = k - 1;
        //KeyPoint2:1~n-1的阶乘
        int factor = 1;
        for (int i = 1; i < n; i++) {
            factor *= i;
        }

        for (int i = 0; i < n; i++) {
            //算应该哪个数字排在n!的结果的第i位
            int index = k / factor;
            //求余,为下个循环的处理做准备.
            k = k % factor;
            for (int j = 0; j < n; j++) {
                //寻找没有被使用过的数组元素.
                if (used[j] == false) {
                    //如果index=0,那么就使用第j位的数字哦
                    if (index == 0) {
                        used[j] = true;
                        //因为j从零开始,所以使用j+1,的位移
                        sb.append((char) ('0' + j + 1));
                        break;
                    } else {
                        //第j位不满足要求,则index--,移到下一个位置
                        index--;
                    }
                }
            }
            if (i < n - 1) {
                //由(n-1)!-->(n-2)!
                factor = factor / (n - 1 - i);
            }
        }

        return sb.toString();
    }
}
