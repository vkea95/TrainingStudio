package leetcode.com.pickup1.easy;

/**
 * Created by tclresearchamerica on 7/9/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/excel-sheet-column-title/
 * ****************************************************
 * Description:
 * Given a positive integer, return its corresponding column title as appear in an Excel sheet.
 * <p>
 * For example:
 * <p>
 * 1 -> A
 * 2 -> B
 * 3 -> C
 * ...
 * 26 -> Z
 * 27 -> AA
 * 28 -> AB
 * ****************************************************
 * Thought:
 * 1.看题目感觉是26进制的问题
 * 2.小问题:数字转字符
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No168_Excel_Sheet_Column_Title {

    public static void main(String[] args) {
        No168_Excel_Sheet_Column_Title obj = new No168_Excel_Sheet_Column_Title();
        System.out.print(obj.convertToTitle(27));
    }

    public String convertToTitle(int n) {
        //bug:在处理n求余数的时候,没有弄清楚该除27还是26,因为Z和A的余数很相近呢!看过第一遍的答案后,发现该是先对n-1,然后出26就没问题了
        StringBuffer stringBuffer = new StringBuffer();
        while (n > 0) {

            int mod = (n - 1) % 26;
            stringBuffer.insert(0, (char) ('A' + mod));
            n = (n - 1) / 26;
        }
        return stringBuffer.toString();


    }
}
