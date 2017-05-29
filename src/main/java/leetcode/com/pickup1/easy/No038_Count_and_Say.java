package leetcode.com.pickup1.easy;

/**
 * Created by tclresearchamerica on 8/10/16.
 * **************************************************************
 * Location:
 * https://leetcode.com/problems/count-and-say/
 * **************************************************************
 * Description:
 * The count-and-say sequence is the sequence of integers beginning as follows:
 * 1, 11, 21, 1211, 111221, ...
 * <p>
 * 1 is read off as "one 1" or 11.
 * 11 is read off as "two 1s" or 21.
 * 21 is read off as "one 2, then one 1" or 1211.
 * Given an integer n, generate the nth sequence.
 * **************************************************************
 * Explanation:
 * Careercup has a same problem, I think its description is better:
 * http://www.careercup.com/question?id=4425679
 * <p>
 * "Count and Say problem" Write a code to do following:
 * <p>
 * n String to print
 * 0 1
 * 1 1 1
 * 2 2 1
 * 3 1 2 1 1
 * ...
 * Base case: n = 0 print "1"
 * for n = 1, look at previous string and write number of times a digit is seen and the digit itself. In this case,
 * <p>
 * digit 1 is seen 1 time in a row... so print "1 1"
 * <p>
 * for n = 2, digit 1 is seen two times in a row, so print "2 1"
 * <p>
 * for n = 3, digit 2 is seen 1 time and then digit 1 is seen 1 so print "1 2 1 1"
 * <p>
 * for n = 4 you will print "1 1 1 2 2 1"
 * <p>
 * Consider the numbers as integers for simplicity. e.g. if previous string is "10 1" then the next will be "1 10 1 1"
 * and the next one will be "1 1 1 10 2 1"
 * **************************************************************
 * Time: 30 mins
 * Beat: 49%
 * Bug: -
 * **************************************************************
 * Hindsight: 之前题目理解的有问题,现在理解了,处理这个问题的时候,还是要注意,需要全部遍历,同时保证计算字符的算法争取哦
 * **************************************************************
 * **************************************************************
 * **************************************************************
 * **************************************************************
 * **************************************************************
 * **************************************************************
 * **************************************************************
 */
public class No038_Count_and_Say {
    public static void main(String[] args) {
        No038_Count_and_Say obj = new No038_Count_and_Say();
        obj.countAndSay(13);
    }

    public String countAndSay(int n) {
        String oldString = "1";
        while (--n > 0) {
            StringBuilder sb = new StringBuilder();
            char[] oldChars = oldString.toCharArray();

            for (int i = 0; i < oldChars.length; i++) {
                //bug1: count开始计算
                int count = 1;
                while ((i + 1) < oldChars.length && oldChars[i] == oldChars[i + 1]) {
                    count++;
                    i++;
                }
                sb.append(String.valueOf(count) + String.valueOf(oldChars[i]));
            }
            oldString = sb.toString();
            System.out.println(oldString);
        }
        return oldString;

    }
}
