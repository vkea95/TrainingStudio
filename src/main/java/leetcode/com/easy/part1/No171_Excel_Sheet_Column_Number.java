package leetcode.com.easy.part1;

/**
 * Created by jason on 2016/3/30.
 * Location:
 * https://leetcode.com/problems/excel-sheet-column-number/
 * ************************************************************
 * Description:
 * Related to question Excel Sheet Column Title
 * Given a column title as appear in an Excel sheet, return its corresponding column number.
 * For example:
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Z -> 26
 * AA -> 27
 * AB -> 28
 * **************************************************************
 * Solution:
 */
public class No171_Excel_Sheet_Column_Number {
    public int titleToNumber(String s) {

        if (s == null || s.length() == 0)
            return 0;
        int result = 0;
        for (char c : s.toCharArray()) {
            int num = (c - 'A') + 1;
            result = result * 26 + num;
        }
        return result;
    }
}
