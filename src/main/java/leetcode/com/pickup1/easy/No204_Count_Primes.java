package leetcode.com.pickup1.easy;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by tclresearchamerica on 7/13/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/count-primes/
 * ****************************************************
 * Description:
 * Count the number of prime numbers less than a non-negative number, n.
 * ****************************************************
 * Thought:
 * 质数就是只能被自己和1整除的数字,所以就只有循环啦
 * 但是看了答案,发现并不是那么回事儿,具体解法会用到数学的思想,首先,初始化一个长度为n的数组,自下标为2的数字开始,
 * 将其设为true,然后继续从下标2开始到n-1的开方,只要是prime为true的元素,就从j=i+i开始,j+=i,j<n,将被扫描的元素都给设成false
 * 最后统计,prime数组中为true的个数
 * ****************************************************
 * Time: 20 mins:
 * Beat: 45%
 * Bug:2
 * ****************************************************
 * ref:https://www.youtube.com/watch?v=eKp56OLhoQs
 * ****************************************************
 * ****************************************************
 */
public class No204_Count_Primes {
    public int countPrimes(int n) {

        //bug1: n<=2,质数个数为零!!!
        if (n <= 2) return 0;

        boolean[] primes = new boolean[n];
        Arrays.fill(primes, true);
        primes[0] = false;
        primes[1] = false;
        //bug3:i的边界为i <= Math.sqrt(n - 1)
        for (int i = 2; i <= Math.sqrt(n - 1); i++) {
            if (primes[i]) {
                for (int j = 2 * i; j < n; j += i) {
                    primes[j] = false;
                }
            }
        }
        int count = 0;
        for (boolean prime : primes)
            count += prime ? 1 : 0;
        return count;
    }
}
