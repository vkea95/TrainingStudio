package leetcode.com.easy.part2;

/**
 * Created by jason on 2016/4/4.
 * Location：
 * https://leetcode.com/problems/happy-number/
 * ***********************************************
 * Description：
 * Write an algorithm to determine if a number is "happy".
 * A happy number is a number defined by the following process: Starting with any positive integer,
 * replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1
 * (where it will stay), or it loops endlessly in a cycle which does not include 1.
 * Those numbers for which this process ends in 1 are happy numbers.
 * Example: 19 is a happy number
 * 12 + 92 = 82
 * 82 + 22 = 68
 * 62 + 82 = 100
 * 12 + 02 + 02 = 1
 * ***********************************************
 * Solution:
 * 平方求和的结果若在0~10中间，那么答案就已经揭晓了，因为他已经不能再被分下去了
 */

public class No202_Happy_Number {
    public static void main(String[] args) {
        No202_Happy_Number obj = new No202_Happy_Number();
        obj.isHappy(1111111);
    }

    public boolean isHappy(int n) {
        boolean result = false;
        int sum;
        do {
            sum = 0;
            while (n != 0) {
                sum += (n % 10) * (n % 10);
                n = n / 10;
            }
            if (sum < 10) {
                if (sum == 1) result = true;
                break;
            }
            n = sum;
        } while (n != 0);
        return result;

    }
}
