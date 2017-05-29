package leetcode.com.pickup1.easy;

/**
 * Created by tclresearchamerica on 6/25/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/string-to-integer-atoi/
 * ****************************************************
 * Description:
 * Implement atoi to convert a string to an integer.
 * Hint: Carefully consider all possible input cases. If you want a challenge, please do not see below and ask yourself
 * what are the possible input cases.
 * Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). You are responsible to
 * gather all the input requirements up front.
 * ****************************************************
 * Thought:
 * 1.超过整数的最大值,但是该返回什么值呢?
 * 2.char可能为非数字
 * 3.
 * ****************************************************
 * Bug:问题出在了这个地方,也就是正常的数字处理都没有问题,一旦牵扯到Integer.Max和min,问题就出来啦,
 * 需要进行额外的处理,否则这2个值会换来换去
 * ****************************************************
 * Hindsight:
 * 1.根据网络的答案,我们可以提前进行对是否溢出进行判断,即判断num是否大于Integer.MAX_VALUE/10,然后进行相关的处理,
 * 若大于,则判断进来的数字是否大于8-->因为最大的值位数至多就是8
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No008_String_to_Integer_atoi {
    public static void main(String[] args) {
        No008_String_to_Integer_atoi obj = new No008_String_to_Integer_atoi();
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.MAX_VALUE);
    }


    public int myAtoi(String str) {
        if (str == null || str.length() == 0) return 0;
        int i = 0, len = str.length();
        int num = 0, sign = 1, bound = Integer.MAX_VALUE / 10;

        // 1. Remove whitespace
        while (i < len && str.charAt(i) == ' ') i++;

        // 2. Handle signs
        if (i < len && (str.charAt(i) == '+' || str.charAt(i) == '-')) {
            sign = str.charAt(i++) == '-' ? -1 : 1;
        }

        // 3. Convert number
        while (i < len && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
            int digit = str.charAt(i++) - '0';
            boolean overlow = (num == bound && digit >= 8) || num > bound;
            if (overlow) return sign > 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            num = num * 10 + digit;
        }

        return sign * num;
    }

    public int myAtoi_slow(String str) {
        if (str == null) return 0;
        str = str.trim();
        long rst = 0;
        int flag = 1;

        if (str.length() == 0) return 0;
        int i = 0;
        if (str.charAt(i) == '+') {
            i++;
        } else if (str.charAt(i) == '-') {
            flag = -1;
            i++;
        }

        for (; i < str.length(); i++) {
            if (isValidNumber(str.charAt(i))) {
                rst = rst * 10 + str.charAt(i) - 48;
                if (rst > Integer.MAX_VALUE) {
                    if (flag > 0)
                        return Integer.MAX_VALUE;
                    else
                        return Integer.MIN_VALUE;
                }
            } else {
                break;
            }

        }
        rst *= flag;
        System.out.println(rst);
        if (rst < Integer.MIN_VALUE) return 0;
        return (int) rst;
    }


    private boolean isValidNumber(char num) {
        if (Character.isDigit(num)) return true;
        else return false;
    }


}
