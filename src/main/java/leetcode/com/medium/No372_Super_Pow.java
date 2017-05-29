package leetcode.com.medium;

import java.util.*;

/**
 * Created by tclresearchamerica on 8/5/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/super-pow/
 * ****************************************************
 * Description:
 * Your task is to calculate ab mod 1337 where a is a positive integer and b is an extremely large positive
 * integer given in the form of an array.
 * <p>
 * Example1:
 * <p>
 * a = 2
 * b = [3]
 * <p>
 * Result: 8
 * Example2:
 * <p>
 * a = 2
 * b = [1,0]
 * <p>
 * Result: 1024
 * ****************************************************
 * 完全不懂
 * ****************************************************
 * Reference:
 * https://discuss.leetcode.com/topic/50458/1-liners-other-with-explanations/3
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No372_Super_Pow {
    public int superPow(int a, int[] b) {
        int res = 1;
        int p = a;
        for (int i = b.length - 1; i >= 0; i--) {
            res = res * pow(p, b[i], 1337) % 1337;
            p = pow(p, 10, 1337);
        }
        return res;
    }

    public int pow(int a, int b, int c) {
        long res = 1;
        long p = a;
        while (b > 0) {
            if ((b & 1) == 1) {
                res = (res * p) % c;
            }
            p = (p * p) % c;
            b >>= 1;
        }
        return (int) (res % c);
    }


}



