package alg4.com.ch0101;

/**
 * Created by JianZhang on 12/9/16.
 * Write a code fragment that puts the binary representation of a positive integer N
 * into a String s.
 * Solution: Java has a built-in method Integer.toBinaryString(N) for this job,
 * but the point of the exercise is to see how such a method might be implemented.
 * Here is a particularly concise solution:
 * String s = "";
 * for (int n = N; n > 0; n /= 2)
 * s = (n % 2) + s;
 * <p>
 * Reference:http://blog.csdn.net/garybrother/article/details/5991918
 * 负数转二进制
 * 以负数-5为例：
 * <p>
 * 1.先将-5的绝对值转换成二进制，即为0000 0101；
 * <p>
 * 2.然后求该二进制的反码，即为 1111 1010；
 * <p>
 * 3.最后将反码加1，即为：1111 1011
 * <p>
 * Ref:操作符
 * java中有三种移位运算符
 * <<：左移运算符，num << 1,相当于num乘以2
 * >>：右移运算符，num >> 1,相当于num除以2
 * >>>：无符号右移，忽略符号位，空位都以0补齐
 */
public class P_09 {

    public static String toBinaryString(int n) {
        StringBuilder sb = new StringBuilder();
        for (; n > 0; n /= 2) {
            sb.insert(0, n % 2);
        }


        return sb.toString();
    }

    /**
     * Returns the number of zero bits following the lowest-order ("rightmost")
     * one-bit in the two's complement binary representation of the specified
     * {@code int} value.  Returns 32 if the specified value has no
     * one-bits in its two's complement representation, in other words if it is
     * equal to zero.
     *
     * @param i the value whose number of trailing zeros is to be computed
     * @return the number of zero bits following the lowest-order ("rightmost")
     * one-bit in the two's complement binary representation of the
     * specified {@code int} value, or 32 if the value is equal
     * to zero.
     * @since 1.5
     */
    public static int numberOfTrailingZeros(int i) {
        // HD, Figure 5-14
        int y;
        if (i == 0) return 32;
        int n = 31;
        y = i << 16;
        if (y != 0) {
            //右移16位后,如果数字为零,则证明至少有16位零,所以不进行减法操作
            n = n - 16;
            i = y;
        }
        y = i << 8;
        if (y != 0) {
            n = n - 8;
            i = y;
        }
        y = i << 4;
        if (y != 0) {
            n = n - 4;
            i = y;
        }
        y = i << 2;
        if (y != 0) {
            n = n - 2;
            i = y;
        }
        //下面这个操作是为什么呢?--------------->
        return n - ((i << 1) >>> 31);
    }

    /*
    copy from Integer.numberOfLeadingZeros
    二分法求整数的二进制的前面有多少零的问题
     */

    public static int countZero(int i) {
        if (i == 0)
            return 32;
        int n = 1;
        if (i >>> 16 == 0) {
            n += 16;
            i <<= 16;
        }
        if (i >>> 24 == 0) {
            n += 8;
            i <<= 8;
        }
        if (i >>> 28 == 0) {
            n += 4;
            i <<= 4;
        }
        if (i >>> 30 == 0) {
            n += 2;
            i <<= 2;
        }
        //下面这个操作是为什么呢?没有找到特比做这个操作的理由--------------->
        n -= i >>> 31;
        return n;
    }
    private static int global_count=0;
    public static void main(String[] args) {
        global_count=0;
        System.out.println(mystery(2, 10));
        System.out.println("global_count: " +global_count);
        global_count=0;
        System.out.println(new_mystery(2, 10));
        System.out.println("global_count: " +global_count);
        System.out.println(exR1(6));
        countZero(9);
        int i = 10;
//        System.out.println(i >>> 8);
//        System.out.println(i << 8);


        System.out.println(Integer.toBinaryString(-1));
        Integer.numberOfLeadingZeros(9);
        System.out.println("number 9 of leading zeros: " + Integer.numberOfLeadingZeros(9));
        System.out.println("number -9 of leading zeros: " + Integer.numberOfLeadingZeros(-9));
        System.out.println(Integer.toBinaryString(59824));
        System.out.println(toBinaryString(9));
    }


    public static String exR1(int n) {
        if (n <= 0) return "";
        return exR1(n - 3) + n + exR1(n - 2) + n;
    }

    /*
    二分法计算a*b
     */
    public static int mystery(int a, int b) {
        global_count++;
        if (b == 0) return 0;
        if (b % 2 == 0) return mystery(a + a, b / 2);
        return mystery(a + a, b / 2) + a;
    }

    /*
    二分法计算a^b幂
     */
    public static int new_mystery(int a, int b) {
        global_count++;
        if (b == 0) return 1;
        if (b % 2 == 0) return new_mystery(a * a, b / 2);
        return new_mystery(a * a, b / 2) * a;
    }
}
