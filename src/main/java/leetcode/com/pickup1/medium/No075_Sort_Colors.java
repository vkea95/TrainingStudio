package leetcode.com.pickup1.medium;

/**
 * Created by tclresearchamerica on 7/8/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/sort-colors/
 * ****************************************************
 * Description:
 * Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent,
 * with the colors in the order red, white and blue.
 * <p>
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 * <p>
 * Note:
 * You are not suppose to use the library's sort function for this problem.
 * Follow up:
 * A rather straight forward solution is a two-pass algorithm using counting sort.
 * First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's,
 * then 1's and followed by 2's.
 * <p>
 * Could you come up with an one-pass algorithm using only constant space?
 * ****************************************************
 * Thoughts:
 * 根据follow up,要一遍过的话,且是constant空间,
 * 画图想辙,
 * 这样的题目,不是围绕数据结构展开的,该是围绕某种想法或是思路,
 * 思路有那些呢?2分法?深度优先?广度优先?动态规划?或是实际积累?两侧逼近?
 * 可否假定要先找到第一个0和最后1个2,不该是第一个2,0与前面的空位交换,因为2都挪到后面去了,所以挪过来的只有1
 * 然后与后面的空位进行交互,这样0,1,2就放好了
 * Time: 20 mins
 * Beat: 78%
 * Bug:2
 * ****************************************************
 * Hindsight:
 * 这样的题目,必须通过实际的推导才能够找到解答,要假设,推导,再求证,找到隐藏的没有告知的条件.
 * 其实这样只有3种情况的题目,就是非男即女的解答方法,除此以外就是第三方啦
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No075_Sort_Colors {
    public void sortColors(int[] nums) {

        int i = 0, i0 = 0, n = nums.length, i2 = n - 1;
        while (i <= i2) {
            if (nums[i] == 0) {
                if (i == i0) {
                    i++;
                } else {
                    nums[i] = nums[i0];
                    nums[i0++] = 0;
                }
            } else if (nums[i] == 2) {
                if (i != i2) {
                    nums[i] = nums[i2];
                    nums[i2--] = 2;

                    //bug2:数组中都是2的时候,不会跳出循环
                }else{
                    break;
                }
                //bug1:数组中都是1的时候,不会跳出循环
            }else{
                i++;
            }

        }
    }
}
