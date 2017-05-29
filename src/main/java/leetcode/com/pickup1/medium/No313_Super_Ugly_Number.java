package leetcode.com.pickup1.medium;

import java.util.Arrays;

/**
 * Created by tclresearchamerica on 8/14/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/super-ugly-number/
 * ****************************************************
 * Description:
 * Write a program to find the nth super ugly number.
 * <p>
 * Super ugly numbers are positive numbers whose all prime factors are in the given prime list primes of size k.
 * For example, [1, 2, 4, 7, 8, 13, 14, 16, 19, 26, 28, 32] is the sequence of the first 12 super ugly numbers given
 * primes = [2, 7, 13, 19] of size 4.
 * <p>
 * Note:
 * (1) 1 is a super ugly number for any given primes.
 * (2) The given numbers in primes are in ascending order.
 * (3) 0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000.
 * ****************************************************
 * Thought:
 * 1.不知道超级丑数是什么意思!
 * 超级丑数是指:若某数的质因子都在给定的数组primes中,那么该数就是超级丑数,
 * 所以,根据input参数, 需要计算primes所对应的丑数集合,并取出第n个
 * ****************************************************
 * Time: -
 * Beat: 98
 * Bug: -
 * ****************************************************
 * Hindsight:
 * copy第一版的答案,完全没有概念呢!
 * 1.有个大小和primes数组一样的pointer数组,存着每个prime对应ugly的下标,
 * 每次prime都和自己对应的ugly数字相乘(因为初始下标是0,所以都是1),取最小的放到ugly数组中,并对下标+1处理,还要保证对重复的丑数的下标也要+1
 * 2.这个思路有点绕,可是有很多和下标变化的题,用的都是类似的思想吧
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No313_Super_Ugly_Number {

    public static void main(String[] args) {
        No313_Super_Ugly_Number obj = new No313_Super_Ugly_Number();
        obj.nthSuperUglyNumber(12, new int[]{2, 7, 13, 19});
    }

    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] pointer = new int[primes.length];
        Arrays.fill(pointer, 0);

        int ugly[] = new int[n];
        ugly[0] = 1;

        for (int i = 1; i < n; i++) {
            int min = Integer.MAX_VALUE;
            int minIndex = 0;
            for (int j = 0; j < primes.length; j++) {
                int value = ugly[pointer[j]] * primes[j];
                if (value < min) {
                    min = value;
                    minIndex = j;
                } else if (value == min) {
                    pointer[j]++;
                }
            }
            ugly[i] = min;
            pointer[minIndex]++;
            for (int uglyNum : ugly) System.out.print(uglyNum + " ");
            System.out.println();
            for (int uglyNum : pointer) System.out.print(uglyNum + " ");
            System.out.println();

        }

        return ugly[n - 1];
    }
}

