package leetcode.com.pickup1.easy;

/**
 * Created by tclresearchamerica on 7/5/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/happy-number/
 * ****************************************************
 * Description:
 * Write an algorithm to determine if a number is "happy".
 * A happy number is a number defined by the following process: Starting with any positive integer, replace the number
 * by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay),
 * or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in
 * 1 are happy numbers.
 * Example: 19 is a happy number
 * 12 + 92 = 82
 * 82 + 22 = 68
 * 62 + 82 = 100
 * 12 + 02 + 02 = 1
 * ****************************************************
 * Thought:
 * 1.感觉Leetcode的oj有问题,
 * 2.也许是题目的意思理解错了," or it loops endlessly in a cycle which does not include 1"
 * 所以要判断A.是否出现循环,B.是否和值是1,若A则false,若B则true
 * 3.所以这道题的解法类似于寻找单向链表中的环儿
 * ****************************************************
 * Time:25 mins
 * Beat: 80%
 * Bug:1
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No202_Happy_Number {

    public static void main(String[] args) {
        No202_Happy_Number obj = new No202_Happy_Number();
        System.out.println(obj.isHappy(1111111));
    }

    public boolean isHappy_wrong(int n) {

        int sum = 0;
        while (sum > 9 || sum == 0) {
            sum = 0;
            while (n > 0) {
                sum += (n % 10) * (n % 10);
                n /= 10;
            }
            n = sum;

        }

        return sum == 1;

    }


    public boolean isHappy(int n) {
        int x = n;
        int y = n;
        while (x > 1) {
            x = cal(x);
            System.out.println("x:" + x);
            if (x == 1) return true;
            y = cal(cal(y));
            System.out.println("y:" + y);
            if (y == 1) return true;

            if (x == y) return false;
        }
        return true;
    }

    public int cal(int n) {
        int x = n;
        int s = 0;
        while (x > 0) {
            s = s + (x % 10) * (x % 10);
            x = x / 10;
        }
        return s;
    }
}
