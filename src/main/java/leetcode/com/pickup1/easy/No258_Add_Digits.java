package leetcode.com.pickup1.easy;

/**
 * Created by tclresearchamerica on 8/7/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/add-digits/
 * ****************************************************
 * Description:
 * Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.
 * For example:
 * Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.
 * Follow up:
 * Could you do it without any loop/recursion in O(1) runtime?
 * ****************************************************
 * Thought:
 * 1.without loop/recursion
 * 2.O(1) runtime?
 * 3.看了第一遍的那个答案:
 * 公式推导如下:
 * 假设5位整数mum各位是abcde,则:
 * mum=a*10000 + b*1000 + c*100+d*10+e
 * =(a+b+c+d+e)+(a*9999+b*999+c*99+d*9)
 * 上面的后半部分可被9整除,因此mum模除9的结果和上面前半部分的结果一样
 * 对前半部分反复执行同类操作,最后的结果就是一个1-9的数字加上一串数字,最左边的数字是1-9之间的,右边数字永远都可以被9整除.
 * -->因为(x+y)%z=(x%z+y%z)%z
 * -->因为x%z%z=x%z
 * <p>
 * --> (a+b+c+d+e)%9=mum%9
 * Ref:https://segmentfault.com/q/1010000004585578
 * ****************************************************
 * ****************************************************
 */
public class No258_Add_Digits {
    public int addDigits(int num) {
        return num == 0 ? 0 : (num % 9 == 0 ? 9 : num % 9);
    }
}
