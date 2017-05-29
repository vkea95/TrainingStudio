package leetcode.com.medium;

/**
 * Created by tclresearchamerica on 5/10/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/power-of-three/
 * ****************************************************
 * Desription:
 * Given an integer, write a function to determine if it is a power of three.
 * <p>
 * Follow up:
 * Could you do it without using any loop / recursion?
 * ****************************************************
 */
public class No326_Power_Of_Three {
    public static void main(String[] args) {
        No326_Power_Of_Three obj = new No326_Power_Of_Three();
        System.out.print(obj.isPowerOfThree(27));
    }

    public boolean isPowerOfThree(int n) {
        while (n % 3 == 0) {
            n /= 3;
        }

        return n % 3 == 0;
    }
}
