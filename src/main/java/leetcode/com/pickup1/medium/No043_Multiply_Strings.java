package leetcode.com.pickup1.medium;

/**
 * Created by tclresearchamerica on 6/29/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/multiply-strings/
 * ****************************************************
 * Description:
 * Given two numbers represented as strings, return multiplication of the numbers as a string.
 * <p>
 * Note:
 * The numbers can be arbitrarily large and are non-negative.
 * Converting the input string to integer is NOT allowed.
 * You should NOT use internal library such as BigInteger.
 * ****************************************************
 * Thought:
 * 1.应该有个算法可以将乘法转换为加法
 * 2,看了答案后,发现采用的是模拟手计算的方法,需要处理的就是进位和乘积处理,以及该放到哪个位置,和最后如何从数组取得结果
 * ****************************************************
 * Time: 40mins
 * Beat: 20%
 * Bug:2
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No043_Multiply_Strings {
    public static void main(String[] args) {
        No043_Multiply_Strings obj = new No043_Multiply_Strings();
        obj.multiply("99", "99");
        obj.multiply("0", "0");
    }

    public String multiply(String num1, String num2) {

        int l1 = num1.length();
        int l2 = num2.length();
        int l = l1 + l2;
        int[] num = new int[l];
        int i, j, carry, product;
        for (i = l1 - 1; i >= 0; i--) {
            carry = 0;
            for (j = l2 - 1; j >= 0; j--) {
                //bug3:此处还需要 将结果集中num相应下标的元素累加上才好
                product = num[i + j + 1] + carry + Character.getNumericValue(num1.charAt(i)) *
                        Character.getNumericValue(num2.charAt(j));
                carry = product / 10;
                product %= 10;
                //bug2: i+j-1 ->当i j 都为0的时候,会发生数组越界,
                num[i + j + 1] = product;
            }
            //important1:easy to forget
            //bug1: index is incorrect
            num[i] = carry;
        }

        //important: 需要将num中前面的零,清空才好.
        StringBuffer sb = new StringBuffer();
        i = 0;
        //opt1:不必循环到最后一个元素,保留一个元素,供输出0使用即可.
        while (i < num.length - 1 && num[i] == 0) {
            i++;
        }
        while (i < num.length) {
            sb.append(num[i++]);
        }
        return sb.toString();
    }
}
