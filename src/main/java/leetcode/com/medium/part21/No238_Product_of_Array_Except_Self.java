package leetcode.com.medium.part21;

import java.util.Arrays;

/**
 * Created by tclresearchamerica on 5/23/16.
 * ***************************************************************
 * Location:
 * https://leetcode.com/problems/product-of-array-except-self/
 * ***************************************************************
 * Description:
 * Given an array of n integers where n > 1, nums, return an array output such that output[i] is equal to the
 * product of all the elements of nums except nums[i].
 * Solve it without division and in O(n).
 * For example, given [1,2,3,4], return [24,12,8,6].
 * ***************************************************************
 * Analysis:
 * 1.那就算所有的乘积,然后再除以这个数,但这样会不会导致溢出呢?overflow
 * 2.有个思路,但是会用到除法,这样就有违背题意,放弃.重新来过...
 * 2.1 那么,开始从数据结构入手,什么样的数据结构比较好达到O(n)呢?
 * 2.2 正常的做法,会达到n*n的时间复杂度,那么比较好的化解方法,就是要提前准备下咯,或者后续有个处理,来规整答案.
 * 2.3 开始写写代码吧,看看有没有什么感觉.给answer数组初始值为1,那么它就需要和下标不等的值进行乘积处理
 * 2.4 另一个想法就是,构筑乘积的component,将一般的component建立好,需要计算的是,拿过来进行有限次乘法即可.
 * 这样的话就有2个预备数组,一个算从左向右的乘积,一个算从右向左的乘积
 * 2.5 想法成功,但是效率极低
 * 2.6 猜测需要用优化循环的方式来提高效率,就是根据现有的程序的内涵,进行优化处理
 * 那么另一想法,就是clone一个同样的数组出来,判断下,相等的话,就设成1...不好!其实需要的仅仅是计算完毕后再放置数据进去.
 * 2.7 用HashMap怎么样?他可以动态的保存数据,根据hash值存储数据,类似于数组,感觉至少要两边循环才够呢.
 * ***************************************************************
 * Solution:
 * 1.网络答案:次只存左侧
 * Beat:1%
 * ***************************************************************
 */
public class No238_Product_of_Array_Except_Self {
    public int[] productExceptSelf(int[] nums) {
         int[] result = new int[nums.length];

        result[0] = 1;

        for (int i = 1; i < result.length; i++) {
            result[i] = result[i - 1] * nums[i - 1];

        }

        for (int j = nums.length - 1; j > 0; j--) {
            result[j] *= result[0];
            result[0] *= nums[j];
        }

        return result;
    }

    public int[] productExceptSelf_slow(int[] nums) {
        int[] answers = new int[nums.length];
        int[] lefts = new int[nums.length];
        int[] rights = new int[nums.length];
        //初始化为1
        Arrays.fill(answers, 1);
        Arrays.fill(lefts, 1);
        Arrays.fill(rights, 1);

        lefts[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            lefts[i] = lefts[i - 1] * nums[i];
        }

        rights[nums.length - 1] = nums[nums.length - 1];
        for (int i = nums.length - 2; i >= 0; i--) {
            rights[i] = rights[i + 1] * nums[i];
        }
        answers[0] = rights[1];
        //bug1:forget to minus 1 from length
        answers[nums.length - 1] = lefts[nums.length - 2];
        for (int i = 1; i < nums.length - 1; i++) {
            answers[i] = lefts[i - 1] * rights[i + 1];
        }
        return answers;
    }
}
