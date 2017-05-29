package leetcode.com.medium.part22;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tclresearchamerica on 5/1/16.
 * *********************************************
 * Location:
 * https://leetcode.com/problems/ugly-number-ii/
 * *********************************************
 * Description:
 * Write a program to find the n-th ugly number.
 * <p>
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. For example, 1, 2, 3, 4, 5, 6, 8, 9, 10,
 * 12 is the sequence of the first 10 ugly numbers.
 * <p>
 * Note that 1 is typically treated as an ugly number.
 * *********************************************
 * Analysis:
 * 1. n <= 6,则直接返回n即可
 * 2. n >  6,以6以前的数值为基础,进行判断,即以较小值去和2,3,5相乘,去和n相近的值,然后再计算
 * ------------>上面这个算法导致运算超时... 时间复杂度太高啦
 * *********************************************
 */
public class No264_Ugly_Nubmber_II {
    public static void main(String[] args) {
        No264_Ugly_Nubmber_II obj = new No264_Ugly_Nubmber_II();
        obj.nthUglyNumber(11);
    }

    public int nthUglyNumber(int n) {
        List<Integer> uglys = new ArrayList<>();
        uglys.add(1);
        int cur = 2;
        int p1 = 0, p2 = 0, p3 = 0;
        int min1, min2, min3;
        while (uglys.size() < n) {

            while (uglys.get(p1) * 2 < cur)
                p1++;
            min1 = uglys.get(p1) * 2;

            while (uglys.get(p2) * 3 < cur)
                p2++;
            min2 = uglys.get(p2) * 3;

            while (uglys.get(p3) * 5 < cur)
                p3++;
            min3 = uglys.get(p3) * 5;

            int next = Math.min(Math.min(min1, min2), min3);

            cur = next + 1;
            uglys.add(next);

        }


        return uglys.get(n - 1);
    }

    public int nthUglyNumber_time_limited(int n) {
        if (n <= 6) return n;
        int u, v, w, x, y, z;

        int rst = 2 * n;
        while (true) {
            u = rst / 2;
            v = rst / 3;
            w = rst / 5;
            x = rst / 6;
            y = rst / 10;
            z = rst / 15;
            int n1 = 0 + u + v + w - x - y - z;
            if (n1 == n) {
                int num = rst;
                while (num >= 2 && num % 2 == 0) num /= 2;
                while (num >= 3 && num % 3 == 0) num /= 3;
                while (num >= 5 && num % 5 == 0) num /= 5;

                if (num == 1)
                    break;
            }
            rst--;
        }
        return rst;

    }
}
