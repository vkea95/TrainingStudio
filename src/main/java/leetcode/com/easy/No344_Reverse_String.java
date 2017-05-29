package leetcode.com.easy;

import java.util.*;

/**
 * Created by tclresearchamerica on 5/13/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/reverse-string/
 * ****************************************************
 * Description:
 * https://leetcode.com/problems/reverse-string/
 * ****************************************************
 * Analysis:
 * 1.原始想法:倒循环的方式,完成String内容的倒序.
 * 2.Java的类库中博大精深,想想有哪个可以方便提供倒序,我的想法使用collection相关的类完成,
 * ****************************************************
 * 技术弱点:
 * 1.倒序的话:Collections.reverse(),并且StringBuilder也会有这个方法,需要认真记下来
 * 2.String.toCharArray()的结果可以被用到Arrays.asList()的参数中,但是返回的结果类型和预想的不一致
 * 2.Arrays.parallelSort概述
 * <p>
 * 这个方法使用了一个临界值，还有一些容量小于这个临界值的数组，这个临界值和数组的容量都是计算来用于并行计算的，如下代码所示：
 * <p>
 * private static final int getSplitThreshold(int n) {
 * int p = ForkJoinPool.getCommonPoolParallelism();
 * int t = (p > 1) ? (1 + n / (p << 3)) : n;
 * return t < MIN_ARRAY_SORT_GRAN ? MIN_ARRAY_SORT_GRAN : t;
 * }
 * 一旦数组决定了使用并行排序，那他马上会被分为几个部分，然后通知Fork/Join任务对各个部分进行排序，
 * 跟着通知另外的Fork/Join任务对排序后的数组进行合并。Java8使用如下的方法进行实现：
 * 1、将数组分成4个子数组。
 * 2、对前面两个子数组进行排序然后合并。
 * 3、对后面的两个进行排序然后合并。
 * 上面着几个步骤会重复递归，每个子数组都要求容量小于上面计算出来的临界值。
 * ****************************************************
 */
public class No344_Reverse_String {
    public static void main(String[] args) {
        No344_Reverse_String obj = new No344_Reverse_String();
        String s = "hellowaaa  123";
         Collections.sort(Arrays.asList(s.toCharArray()), Collections.reverseOrder());


    }
    public String reverseString(String s) {
        StringBuilder sb = new StringBuilder(s);
        return sb.reverse().toString();

    }
}
