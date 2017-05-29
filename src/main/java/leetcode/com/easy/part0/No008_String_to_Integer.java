package leetcode.com.easy.part0;

/**
 * Created by jason on 2016/1/26.
 * Locations:
 * https://leetcode.com/problems/string-to-integer-atoi/
 * *******************************************************
 * Descriptions:
 * Implement atoi to convert a string to an integer.
 * Hint: Carefully consider all possible input cases. If you want a challenge, please do not see below and
 * ask yourself what are the possible input cases.
 * Notes: It is intended for this problem to be specified vaguely (ie, no given input specs).
 * You are responsible to gather all the input requirements up front.
 * ********************************************************
 * Solutions:
 * ********************************************************
 * Tips:
 * 容易忽略的内容：
 * 1.整数会溢出，所以需要判断，但是数值需要先存在long类型中
 * 2.符号位会乱出，这个要考虑如何应对，例如符号位过后，如果出现的字符不在0~~9范围内，则返回零
 * 3.对于非空字符串要进行trim除掉无效的字符，不能一上来就判断是否长度为零
 */
public class No008_String_to_Integer {
    public int myAtoi(String str) {
        if (str == null)
            return 0;
        str = str.trim();
        if (str.length() == 0)
            return 0;

        int result = 0;
        for (int i = str.length(); i >= 0; i--) {
            if ('-' == str.charAt(i)) {
                result *= -1;
                break;
            }
            result = result * 10 + (int) str.charAt(i);
        }
        return result;
    }
}
