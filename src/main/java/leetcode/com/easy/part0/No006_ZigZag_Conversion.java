package leetcode.com.easy.part0;

/**
 * Created by jason on 2016/1/24.
 * Locatiions:
 * https://leetcode.com/problems/zigzag-conversion/
 * ************************************************
 * Descriptions:
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
 * (you may want to display this pattern in a fixed font for better legibility)
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 * Write the code that will take a string and make this conversion given a number of rows:
 * string convert(string text, int nRows);
 * convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
 * *************************************************
 * Solutions:
 * zigzag的输出字符串相当于N形式的，这样在一个周期内，除了首末行外，其余各行会有2个字符
 * 所以才会再定义出一个interval这么个变量，这个变量的范围要在0和step之间，都是开区间。
 * interval的推导就是数学归纳法，一算便知道
 */
public class No006_ZigZag_Conversion {
    public String convert(String s, int numRows) {
        int length = s.length();
        if (length <= numRows || numRows == 1) return s;//特殊case处理
        char[] chars = new char[length];
        int step = 2 * (length - 1);
        int count = 0;
        for (int i = 0; i < numRows; i++) {
            int interval = step - 2 * i;
            for (int j = i; j < length; j += step) {
                chars[count] = s.charAt(j);
                count++;
                if (interval < step && interval > 0 && j + interval < length && count < length) {
                    chars[count] = s.charAt(j + interval);
                    count++;
                }
            }
        }
        return new String(chars);
    }
}
