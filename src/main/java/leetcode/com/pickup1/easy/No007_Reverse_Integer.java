package leetcode.com.pickup1.easy;

/**
 * Created by tclresearchamerica on 7/20/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/reverse-integer/
 * ****************************************************
 * Description:
 * Reverse digits of an integer.
 * Example1: x = 123, return 321
 * Example2: x = -123, return -321
 * ****************************************************
 * Time: 20 mins
 * Beat:
 * Bug:2
 * ****************************************************
 * Hindsight:
 * 1.没有考虑好如何处理溢出的问题,就是一个数字被重置的时候,会导致溢出呢!!!!!
 * 2.在标准算法中,其实是不区分输入数字的正负值的,直接用标准工时就可以解开了
 * 因为每次求余数的时候,余数的值都是负值,这样后面的连续计算,就都是负值之间的运算,所以没有问题啦
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No007_Reverse_Integer {
    public static void main(String[] args) {
        No007_Reverse_Integer obj = new No007_Reverse_Integer();
        obj.reverse(-199);
    }

    public int reverse(int x) {

        int result = 0, prevResult = 0;

        while (x != 0) {
            prevResult = result;
            result = result * 10 + x % 10;
            if (result / 10 != prevResult) {
                result = 0;
                return result;
            }
            x /= 10;
        }
        return result;

        //bug1:没有考虑溢出的问题啊,这不是一个Sr.Engineer,应该做的。
//        int temp = 0;
//        int sign = 1;
//        if (x < 0) {
//            sign = -1;
//            x *= -1;
//        }
//        while (x != 0) {
//            int mod = x % 10;
//            temp = temp * 10 + mod;
//            x /= 10;
//        }
//        return sign * temp;
    }
}
