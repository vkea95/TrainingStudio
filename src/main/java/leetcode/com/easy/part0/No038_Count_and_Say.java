package leetcode.com.easy.part0;

/**
 * Created by jason on 2016/2/19.
 * Location:
 * https://leetcode.com/problems/count-and-say/
 * *********************************************
 * Description:
 * The count-and-say sequence is the sequence of integers beginning as follows:
 * 1, 11, 21, 1211, 111221, ...
 * 1 is read off as "one 1" or 11.
 * 11 is read off as "two 1s" or 21.
 * 21 is read off as "one 2, then one 1" or 1211.
 * Given an integer n, generate the nth sequence.
 * ***************************************************
 * Solution:
 */
public class No038_Count_and_Say {

    public static void main(String[] args) {
        No038_Count_and_Say no038 = new No038_Count_and_Say();
        System.out.println("23 : " + no038.countAndSay(21));
    }

    public String countAndSay(int n) {
        String oldString = "1";
        while (--n > 0) {
            StringBuilder sb = new StringBuilder();
            char[] oldChars = oldString.toCharArray();

            for (int i = 0; i < oldChars.length; i++) {
                int count = 1;
                while ((i + 1) < oldChars.length && oldChars[i] == oldChars[i + 1]) {
                    count++;
                    i++;
                }
                sb.append(String.valueOf(count) + String.valueOf(oldChars[i]));
            }
            oldString = sb.toString();
            System.out.println(n + ": " + oldString);
        }
        System.out.println("lalalalala");
        return oldString;
    }
}