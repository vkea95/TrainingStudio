package leetcode.com.easy.part1;

/**
 * Created by jason on 2016/3/30.
 * Location:
 * https://leetcode.com/problems/excel-sheet-column-title/
 * ********************************************************
 * Description:
 * Given a positive integer, return its corresponding column title as appear in an Excel sheet.
 * For example:
 * 1 -> A
 * 2 -> B
 * 3 -> C
 * ...
 * 26 -> Z
 * 27 -> AA
 * 28 -> AB
 * *********************************************************
 * Solution:
 * 1.没有想到用递归
 * 2.没有想到用求余后的值加上‘A'即可求得自己想要的字符
 * 3.没有想到用（n-1)%26
 * 4.忘记强制类型转换如何用括号了
 */
public class No168_Excel_Sheet_Column_Title {
    public String convertToTitle(int n) {
        return n == 0 ? "" : convertToTitle((n - 1) / 26) + (char) ((n - 1) % 26 + 'A');
    }
}
