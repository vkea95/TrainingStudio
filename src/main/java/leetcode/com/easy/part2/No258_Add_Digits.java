package leetcode.com.easy.part2;

/**
 * Created by tclresearchamerica on 4/30/16.
 * *********************************************
 * Location:
 * https://leetcode.com/problems/add-digits/
 * *********************************************
 * Description:
 * Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.
 * <p>
 * For example:
 * <p>
 * Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it
 * *********************************************
 * Follow up
 * Could you do it without any loop/recursion in O(1) runtime?
 * *********************************************
 * Analysis:
 * 居然不可以使用循环和递归,那么这个会牵扯到某个数学公式吧?
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
 *计算的结果是他不能是0,如果这个数字大于零的话,所以先用num-1 去摸9得到的值是0~8,然后再加1即可
 * ************************************
 * 网络答案是:num-9*((num-1)/9)
 * 111- 9 *(( 111 - 1 )/ 9)
 * =111-9 * (110/9)
 * =111-9 * 12
 * =111-108
 * =3
 * *********************************************
 */
public class No258_Add_Digits {
    public int addDigits(int num) {

//        return num - 9 * ((num - 1) / 9);
        return (num - 1) % 9 + 1;
    }
}
