package leetcode.com.medium;

import java.util.Arrays;

/**
 * Created by tclresearchamerica on 5/9/16.
 * ****************************************************
 * Description:
 * Write a program to find the nth super ugly number.
 * Super ugly numbers are positive numbers whose all prime factors are in the given prime list primes of size k.
 * For example, [1, 2, 4, 7, 8, 13, 14, 16, 19, 26, 28, 32] is the sequence of the first 12 super ugly numbers
 * given primes = [2, 7, 13, 19] of size 4.
 * <p>
 * Note:
 * (1) 1 is a super ugly number for any given primes.
 * (2) The given numbers in primes are in ascending order.
 * (3) 0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000.
 * ****************************************************
 * Analysis:
 * 超级丑数是指:若某数的质因子都在给定的数组primes中,那么该数就是超级丑数,
 * 所以,根据input参数, 需要计算primes所对应的丑数集合,并取出第n个
 * 想象这样的一个算法,通过primes算出来的丑数,放入某个队列里,或是说不放入队列,只是有个计数器就够了?
 * 按照之前做题的思路,先构思核心算法,再选择合适的数据结构
 * 思路一:primes 应该是 超级丑数的序列一个子集,primes中的元素组合再构成超级丑数的序列
 * ****************************************************
 * Solution:
 * http://blog.csdn.net/zly9923218/article/details/51010348
 * 利用ugly,pointer,prime三个数组存储当前找到的所有ugly，同时存储下次可能是最小的数：
 * 这是一个三重关系,pointer是连接ugly和prime的纽带
 * Pointer里面一一对应地存着每个prime对应的当前最小的ugly数字的下标--这个最小是相对而言,
 * 每次取完之后都会,对被选中的指针+1,让其指到下一个ugly数字去
 * 初期化时:ugly[0]=1
 * 然后再计算,这个方法比较适合这种pattern,或许别的题目也会用到这种方法
 */
public class No313_Super_Ugly_Numbe {
    public static void main(String[] args) {
        No313_Super_Ugly_Numbe obj = new No313_Super_Ugly_Numbe();
        int[] primes = {2, 7, 13, 19};
        obj.nthSuperUglyNumber(12, primes);
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

        }

        return ugly[n - 1];
    }

    public int nthSuperUglyNumber_demo(int n, int[] primes) {
        int[] pointer = new int[primes.length];
        Arrays.fill(pointer, 0);

        int ugly[] = new int[n];
        ugly[0] = 1;

        for (int i = 1; i < n; i++) {
            int min = Integer.MAX_VALUE;
            int minIndex = 0;
            for (int j = 0; j < primes.length; j++) {
                if (ugly[pointer[j]] * primes[j] < min) {
                    min = ugly[pointer[j]] * primes[j];
                    minIndex = j;
                } else if (ugly[pointer[j]] * primes[j] == min) {
                    System.out.print("equal min");
                    pointer[j]++;
                }
            }
            ugly[i] = min;
            pointer[minIndex]++;
            for (int j = 0; j < n; j++) System.out.print(ugly[j] + " ");
            System.out.println("");
            System.out.println("====================================");
            for (int j = 0; j < pointer.length; j++) {
                System.out.println(primes[j] + "  " + pointer[j]);
            }
            System.out.println("--------------------------------");
        }

        return ugly[n - 1];
    }
}
