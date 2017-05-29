package leetcode.com.pickup1.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tclresearchamerica on 7/20/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/ugly-number-ii/
 * ****************************************************
 * Description:
 * Write a program to find the n-th ugly number.
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. For example,
 * 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
 * Note that 1 is typically treated as an ugly number.
 * ****************************************************
 * Thoughts:
 * 1.根据前提条件,丑数就是质数因子只有2,3,5的,所以需要寻找更快的数学推理表达式
 * 2.或者是根据,2,3和5这几个数字,用乘法推出来一个大小顺序的序列,这样2,3,5每个数字都有一个可以乘的数字,
 * 乘完之后,就可以下移一位,
 * ****************************************************
 * Bug:1.插入ugly number的时候,没有考虑重复的number,
 * ****************************************************
 * Time:25mins
 * Beat: 16% ->22%
 * Bug: 1
 * ****************************************************
 * Hindsight:
 * 1.这次是自己推到出来的解法,基本通过了。表明这段时间的刷题是起了作用的。
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No264_Ugly_Number_II {
    public static void main(String[] args) {
        No264_Ugly_Number_II obj = new No264_Ugly_Number_II();
        obj.nthUglyNumber(7);
    }

    public int nthUglyNumber(int n) {
        List<Integer> solutions = new ArrayList<>();
        solutions.add(1);

        int idx2 = 0, idx3 = 0, idx5 = 0;
//        int ugly2 = 0, ugly3 = 0, ugly5 = 0;
        int cur = 1;
        while (solutions.size() < n) {
            while (solutions.get(idx2) * 2 <= cur) {
                idx2++;
            }
            while (solutions.get(idx3) * 3 <= cur) {
                idx3++;
            }
            while (solutions.get(idx5) * 5 <= cur) {
                idx5++;
            }
            int ugly2 = solutions.get(idx2) * 2;
            int ugly3 = solutions.get(idx3) * 3;
            int ugly5 = solutions.get(idx5) * 5;

            cur = Math.min(Math.min(ugly2, ugly3), ugly5);
            solutions.add(cur);

        }
        return solutions.get(n - 1);
    }

    public int nthUglyNumber_zj(int n) {
        List<Integer> solutions = new ArrayList<>();
        solutions.add(1);
//        solutions.add(2);
//        solutions.add(3);
//        solutions.add(5);
//        if (n <= 4) return solutions.get(n - 1);

        int idx2 = 0, idx3 = 0, idx5 = 0;
        while (solutions.size() < n) {
            int ugly2 = solutions.get(idx2) * 2;
            int ugly3 = solutions.get(idx3) * 3;
            int ugly5 = solutions.get(idx5) * 5;
            if (ugly2 < ugly3) {
                if (ugly2 < ugly5) {
                    solutions.add(ugly2);
                    idx2++;
                } else {
                    solutions.add(ugly5);
                    idx5++;
                }
            } else {
                if (ugly3 < ugly5) {
                    solutions.add(ugly3);
                    idx3++;
                } else {
                    solutions.add(ugly5);
                    idx5++;
                }
            }
            //bug1:没有考虑再次计算的值,会有重复的,会导致ugly 数列顺序不正确,所以要做删除操作
            if (solutions.get(solutions.size() - 1) <= solutions.get(solutions.size() - 2)) {
                solutions.remove(solutions.size() - 1);
            }
        }


        return solutions.get(n - 1);
    }
}
