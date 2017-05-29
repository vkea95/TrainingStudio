package leetcode.com.easy.part2;

/**
 * Created by tclresearchamerica on 4/6/16.
 * Location:
 * https://leetcode.com/problems/count-primes/
 * ********************************************
 * Description:
 * Count the number of prime numbers less than a non-negative number, n.
 * ********************************************
 * Solution:
 * 1. 需要知道如何推到关于质数的问题,然而并没有看明白
 */
public class No204_Count_Primes {
    public static void main(String[] args) {
        No204_Count_Primes obj = new No204_Count_Primes();
        obj.countPrimes(19);
    }

    public int countPrimes(int n) {

        if (n <= 2) return 0;

        // init an array to track prime numbers
        boolean[] primes = new boolean[n];
        for (int i = 2; i < n; i++) primes[i] = true;
        for (int i = 2; i <= Math.sqrt(n - 1); i++) {
            // or for (int i = 2; i <= n-1; i++) {
            if (primes[i]) {
                for (int j = i + i; j < n; j += i)
                    primes[j] = false;
            }
        }

        int count = 0;
        for (boolean prime : primes)
            count += prime ? 1 : 0;

        return count;
    }
}
