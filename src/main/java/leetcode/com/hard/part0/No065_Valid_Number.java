package leetcode.com.hard.part0;

/**
 * Created by tclresearchamerica on 5/17/16.
 * ***************************************************************
 * Location:
 * https://leetcode.com/problems/valid-number/
 * ***************************************************************
 * Description:
 * Validate if a given string is numeric.
 * <p>
 * Some examples:
 * "0" => true
 * " 0.1 " => true
 * "abc" => false
 * "1 a" => false
 * "2e10" => true
 * Note: It is intended for the problem statement to be ambiguous. You should gather all requirements up
 * front before implementing one.
 * ***************************************************************
 * Analysis:
 * 网络答案:有限状态机
 * 今天看到了大神的解法(https://github.com/fuwutu/LeetCode/blob/master/Valid%20Number.cpp),
 * 用有限状态机，非常简洁，不需要复杂的各种判断！
 * 先枚举一下各种合法的输入情况：
 * 1.空格+ 数字 +空格
 * 2.空格+ 点 + 数字 +空格
 * 3.空格+ 符号 + 数字 +　空格
 * 4.空格 + 符号 + 点 +　数字　＋空格
 * 5.空格 + (1, 2, 3, 4） + e +　(1, 2, 3, 4) +空格
 * 组后合法的字符可以是：
 * 1.数字
 * 2.空格
 * 有限状态机的状态转移过程：
 * 起始为0：
 * 　　当输入空格时，状态仍为0，
 * 　　输入为符号时，状态转为3，3的转换和0是一样的，除了不能再接受符号，故在0的状态的基础上，把接受符号置为-1；
 * 　　当输入为数字时，状态转为1, 状态1的转换在于无法再接受符号，可以接受空格，数字，点，指数；状态1为合法的结束状态；
 * 　　当输入为点时，状态转为2，状态2必须再接受数字，接受其他均为非法；
 * 　　当输入为指数时，非法；
 * 状态1：
 * 　　接受数字时仍转为状态1，
 * 　　接受点时，转为状态4，可以接受空格，数字，指数，状态4为合法的结束状态，
 * 　　接受指数时，转为状态5，可以接受符号，数字，不能再接受点，因为指数必须为整数，而且必须再接受数字；
 * 状态2：
 * 　　接受数字转为状态4；
 * 状态3：
 * 　　和0一样，只是不能接受符号；
 * 状态4：
 * 　　接受空格，合法接受；
 * 　　接受数字，仍为状态4；
 * 　　接受指数，转为状态5，
 * 状态5：
 * 　　接受符号，转为状态6，状态6和状态5一样，只是不能再接受符号，
 * 　　接受数字，转为状态7，状态7只能接受空格或数字；状态7为合法的结束状态；
 * 状态6：
 * 　　只能接受数字，转为状态7；
 * 状态7：
 * 　　接受空格，转为状态8，状态7为合法的结束状态；
 * 　　接受数字，仍为状态7；
 * 状态8：
 * 　　接受空格，转为状态8，状态8为合法的结束状态；
 * index	Invalid 	space	sign	digit	dot	exponent
 * 0	-1	0	3	1	2	-1
 * 1	-1	8	-1	1	4	5
 * 2	-1	-1	-1	4	-1	-1
 * 3	-1	-1	-1	1	2	-1
 * 4	-1	8	-1	4	-1	5
 * 5	-1	-1	6	7	-1	-1
 * 6	-1	-1	-1	7	-1	-1
 * 7	-1	8	-1	7	-1	-1
 * 8	-1	8	-1	-1	-1	-1
 */
public class No065_Valid_Number {
    public static void main(String[] args) {
        No065_Valid_Number obj = new No065_Valid_Number();
        obj.isNumber("s");
    }

    public boolean isNumber(String s) {
        boolean result = false;
        int[][] stateArray = {
                {-1, 0, 3, 1, 2, -1},
                {-1, 8, -1, 1, 4, 5},
                {-1, -1, -1, 4, -1, -1},
                {-1, -1, -1, 1, 2, -1},
                {-1, 8, -1, 4, -1, 5},
                {-1, -1, 6, 7, -1, -1},
                {-1, -1, -1, 7, -1, -1},
                {-1, 8, -1, 7, -1, -1},
                {-1, 8, -1, -1, -1, -1}
        };
        s=s.trim();

        int state = 0;
        for (int i = 0; i < s.length() && state != -1; i++) {
            char c = s.charAt(i);
            int inputType = 0;
            if (c == ' ') {
                inputType = 1;
            } else if (c == '+' || c == '-') {

                inputType = 2;
            } else if (c >= '0' && c <= '9') {
                inputType = 3;
            } else if (c == '.') {
                inputType = 4;

            } else if (c == 'e' || c == 'E') {
                inputType = 5;
            }

            state = stateArray[state][inputType];

        }

        //bug1: don't know the judge condition
        return state == 1 || state == 4 || state == 7 || state == 8;
    }
}
