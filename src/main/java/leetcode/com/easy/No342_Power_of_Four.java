package leetcode.com.easy;

/**
 * Created by tclresearchamerica on 5/13/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/power-of-four/
 * ****************************************************
 * Description:
 * Given an integer (signed 32 bits), write a function to check whether it is a power of 4.
 * <p>
 * Example:
 * Given num = 16, return true. Given num = 5, return false.
 * <p>
 * Follow up: Could you solve it without loops/recursion?
 * ****************************************************
 * Analysis:
 * 1.整除4,求是否余数为零,但需要循环处理
 * 2.网络答案就是,将该数的进制表达方式,转成String,再进行处理即可,原因如下
 * 0:0
 * 1  : 1000,0000,0
 * 4  : 0010,0000,0
 * 16 : 0000,1000,0
 * 64 : 0000,0000,1
 * 1的前面有2的偶数(2n)倍个零
 * ****************************************************
 * 技术弱点:
 * 1.Integer的静态方法可将整数转为二进制的表达形式----这个就是关键所在!!!
 * 2.String的Replace方法执行完毕,结果需保存,并非直接对String对象进行修改
 * ****************************************************
 */
public class No342_Power_of_Four {
    public static void main(String[] args) {
        No342_Power_of_Four obj = new No342_Power_of_Four();
        obj.isPowerOfFour(16);
    }

    public boolean isPowerOfFour(int num) {
        if (num <= 0) return false;
        String binaryNum = Integer.toBinaryString(num);
        binaryNum = binaryNum.replaceAll("00", "");
        return binaryNum.equals("1");
    }
}
