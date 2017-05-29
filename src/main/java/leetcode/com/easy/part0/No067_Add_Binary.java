package leetcode.com.easy.part0;

/**
 * Created by jason on 2016/3/9.
 * Location:
 * https://leetcode.com/problems/add-binary/
 * ********************************************
 * Description:
 * Given two binary strings, return their sum (also a binary string).
 * <p>
 * For example,
 * a = "11"
 * b = "1"
 * Return "100".
 * <p>
 * Subscribe to see which companies asked this question
 */
public class No067_Add_Binary {

    public static void main(String[] args) {
        No067_Add_Binary n067 = new No067_Add_Binary();
        n067.addBinary("1010", "1011");
    }

    public String addBinary(String a, String b) {
        if (a.length() < b.length()) {
            String tmp = a;
            a = b;
            b = tmp;
        }
        int pb = b.length() - 1;
        int pa = a.length() - 1;
        int carry = 0;
        String rst = "";
        while (pb >= 0) {
            int sum = (int) (a.charAt(pa) - '0') + (int) (b.charAt(pb) - '0') + carry;
            rst = String.valueOf(sum % 2) + rst;
            carry = sum / 2;
            pa--;
            pb--;

        }
        while (pa >= 0) {
            int sum = (int) (a.charAt(pa) - '0') + carry;
            rst = String.valueOf(sum % 2) + rst;
            carry = sum / 2;
            pa--;
            pb--;

        }
        if (carry == 1)
            rst = "1" + rst;
        return rst;
    }
}
