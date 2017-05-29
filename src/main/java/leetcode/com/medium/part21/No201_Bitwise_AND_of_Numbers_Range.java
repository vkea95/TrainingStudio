package leetcode.com.medium.part21;

/**
 * Created by jason on 2016/4/3.
 * Location：
 * https://leetcode.com/problems/bitwise-and-of-numbers-range/
 * **************************************************************
 * Description：
 * Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.
 * For example, given the range [5, 7], you should return 4.
 * ***************************************************************
 * Solution：
 * The key to solve this problem is bitwise AND consecutive numbers.
 * You can use the following example to walk through the code
 * ####8  4  2  1
 * ---------------
 * 5 | 0  1  0  1
 * 6 | 0  1  1  0
 * 7 | 0  1  1  1
 * n=n&n-1
 */
public class No201_Bitwise_AND_of_Numbers_Range {
    private int index = 0;

    public static void main(String[] args) {
        No201_Bitwise_AND_of_Numbers_Range obj = new No201_Bitwise_AND_of_Numbers_Range();
        obj.index = 0;
        obj.rangeBitwiseAnd(1, 12345678);
        System.out.println("index: " + obj.index);
        obj.index = 0;
        obj.rangeBitwiseAnd_Jiuzhang(1, 12345678);
        System.out.println("index: " + obj.index);
    }

    public int rangeBitwiseAnd(int m, int n) {
        while (n > m) {
            index++;
            //bug1:这行代码代表从n至m间的数字不断取与操作，找到最小的集合，然后再m进行与操作
            //Hints:按位于操作还是比较快的
            n = n & n - 1;
        }
        return m & n;
    }

    public int rangeBitwiseAnd_Jiuzhang(int m, int n) {
        index++;
        if (n == m) {
            return n;
        }
        if (n - m == 1) {
            return n & m;
        }
        return rangeBitwiseAnd(m / 2, n / 2) << 1;
    }
}
