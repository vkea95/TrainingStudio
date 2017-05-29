package leetcode.com.medium.part02;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jason on 2016/2/28.
 * Location:
 * https://leetcode.com/problems/gray-code/
 * *********************************************
 * Description:
 * The gray code is a binary numeral system where two successive values differ in only one bit.
 * Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code.
 * A gray code sequence must begin with 0.
 * For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:
 * 00 - 0
 * 01 - 1
 * 11 - 3
 * 10 - 2
 * Note:
 * For a given n, a gray code sequence is not uniquely defined.
 * For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.
 * For now, the judge is able to judge based on one instance of gray code sequence. Sorry about that.
 * *********************************************
 * Solution:
 * 从0和1开始入手，递归操作，每增加1位，就是将之前的元素倒序后，再加上左移n位后的数值，然后再加入结果集。
 * 这样就OK了。我也不清楚为什么是这么个算法，当然，倒序是为了保证和之前的结果集连续。
 * 
 */
public class No089_Gray_Code {
    public static void main(String[] args) {
        No089_Gray_Code no08 = new No089_Gray_Code();
        no08.grayCode(5);

    }

    public List<Integer> grayCode(int n) {
        List result = new ArrayList<>();
        if (n <= 1) {
            for (int i = 0; i <= n; i++) {
                result.add(i);
            }
            return result;
        }

        result = grayCode((n - 1));
        List<Integer> r1 = reverse(result);
        int x = 1 << (n - 1);
        for (int i = 0; i < r1.size(); i++) {
            r1.set(i, r1.get(i) + x);
        }
        result.addAll(r1);

        return result;
    }

    private List<Integer> reverse(List<Integer> r) {
        List<Integer> rev = new ArrayList<>();
        for (int i = r.size() - 1; i >= 0; i--) {
            rev.add(r.get(i));
        }
        return rev;
    }
}
