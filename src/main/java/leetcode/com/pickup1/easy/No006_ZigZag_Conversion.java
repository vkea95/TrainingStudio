package leetcode.com.pickup1.easy;

/**
 * Created by tclresearchamerica on 7/21/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/zigzag-conversion/
 * ****************************************************
 * Description:
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want
 * to display this pattern in a fixed font for better legibility)
 * <p>
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 * Write the code that will take a string and make this conversion given a number of rows:
 * <p>
 * string convert(string text, int nRows);
 * convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
 * ****************************************************
 * Thoughts:
 * 其实这个里面的问题,A.要先以numRows为单位进行组合字符串,
 * B.就是interval该如何使用,算出同时出现的有哪些,推理如下:
 * 下标都是从0开始,那么就是0~2n-3
 * 1->2n-3:->2n-3-1=2n-2-2
 * 2->2n-4->2n-4-2=2n-2-4
 * 3->2n-5->2n-5-3=2n-2-6
 * 2n-2=step
 * 2,4,6=>2*i
 * ****************************************************
 * Time: 30 min
 * Beat:99%
 * Bug:2
 * ****************************************************
 * Hindsight:
 * 在归纳整理interval的时候,要体现出数学归纳法的思想来,要知道如何拆分这个数学表达式
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No006_ZigZag_Conversion {
    public String convert(String s, int numRows) {

        //2n-2
        if (s == null || s.length() <= 2 || numRows < 2) return s;
        int step = 2 * numRows - 2;
        char[] orient = s.toCharArray();
        char[] result = new char[s.length()];
        int count = 0;
        int interval = 0;
        //bug1：该以行为单位进行循环，这个才是正确的
        for (int i = 0; i < numRows; i++) {
            interval = step - 2 * i;
            for (int j = i; j < orient.length; j += step) {
                result[count++] = orient[j];
                if (i > 0 && i < numRows - 1) {
                    //bug2:interval 的算法出错了，不该用这个方法进行计算的，会导致取之前的元素
                    int index = j + interval;
                    if (index < orient.length) {
                        result[count++] = orient[index];
                    }
                }
            }

        }
        return new String(result);
    }
}
