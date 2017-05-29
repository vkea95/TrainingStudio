package leetcode.com.medium.part21;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tclresearchamerica on 5/20/16.
 * ***************************************************************
 * Location:
 * https://leetcode.com/problems/different-ways-to-add-parentheses/
 * ***************************************************************
 * Description:
 * Given a string of numbers and operators, return all possible results from computing all the different
 * possible ways to group numbers and operators. The valid operators are +, - and *.
 * Example 1
 * Input: "2-1-1".
 * ((2-1)-1) = 0
 * (2-(1-1)) = 2
 * Output: [0, 2]
 * Example 2
 * Input: "2*3-4*5"
 * (2*(3-(4*5))) = -34
 * ((2*3)-(4*5)) = -14
 * ((2*(3-4))*5) = -10
 * (2*((3-4)*5)) = -10
 * (((2*3)-4)*5) = 10
 * Output: [-34, -14, -10, -10, 10]
 * ***************************************************************
 * Analysis:
 * 1.木有思路啊.切入点在于如何去模拟括号的作用,划分这个东东
 * 2.看了例子会发现: 3个数的话,就是2种划分方式, 4个数的话会有5种划分方式
 * 3.类似于先从n个数中,选去2个在一起的数,然后放回去,再取2个,计算后放回去,知道算尽,这就是一种划分方法.
 * 4.这个问题比较暴露就是 运用排列组合算法的问题上, 会有点吃力呢...
 * ***************************************************************
 * Solution:
 * 1.Dp的那个方法看着不是很清楚,有点不理解.
 * 2.网上有个类似的方法,用到了hashMap,也可以避免重复计算,提高效率---->这个答案,要理解后背下来
 * ***************************************************************
 * ***************************************************************
 * ***************************************************************
 * ***************************************************************
 */
public class No241_Different_Ways_to_Add_Parentheses {
    Map<String, List<Integer>> map = new HashMap<>();

    public static void main(String[] args) {
        No241_Different_Ways_to_Add_Parentheses obj = new No241_Different_Ways_to_Add_Parentheses();
        obj.diffWaysToCompute("1+2*3-5");
    }


    public List<Integer> diffWaysToCompute(String input) {
        if (map.containsKey(input))
            return map.get(input);

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                List<Integer> leftList = diffWaysToCompute(input.substring(0, i));
                List<Integer> rightList = diffWaysToCompute(input.substring(i + 1));
                for (int v1 : leftList) {
                    for (int v2 : rightList) {
                        if (c == '+')
                            res.add(v1 + v2);
                        else if (c == '-')
                            res.add(v1 - v2);
                        else
                            res.add(v1 * v2);
                    }
                }
            }

        }

        //如果输入的是一个数字的话
        if (res.isEmpty())
            res.add(Integer.parseInt((input)));
        map.put(input, res);
        return res;
    }


    public List<Integer> diffWaysToCompute_DP(String input) {
        if (input == null || input.length() == 0) return new ArrayList<Integer>();

        //prepare for the numbers & operators
        List<Integer> numbers = new ArrayList<>();
        List<Character> operators = new ArrayList<>();

        //parse the input string
        parseInput(input, numbers, operators);

        // now divide and conquer
        List<Integer>[][] dp = new List[input.length() / 2 + 1][input.length() / 2 + 1];

        return diffWaysToCompute(numbers, operators, 0, numbers.size() - 1, dp);
    }

    private List<Integer> diffWaysToCompute(List<Integer> numbers, List<Character> operators, int start,
                                            int end, List<Integer>[][] dp) {
        if (dp[start][end] != null) {
            return dp[start][end];
        } else {
            List<Integer> values = new ArrayList<>();
            if (start == end) {
                values.add(numbers.get(start));
            } else {
                for (int i = start; i < end; i++) {//index for operator
                    List<Integer> left = diffWaysToCompute(numbers, operators, start, i, dp);
                    List<Integer> right = diffWaysToCompute(numbers, operators, i + 1, end, dp);
                    char operator = operators.get(i);
                    for (int a : left) {
                        for (int b : right) {
                            int value = compute(a, b, operator);
                            values.add(value);
                        }
                    }

                }
            }
            dp[start][end] = values;
            return values;
        }
    }

    private int compute(int a, int b, char operator) {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            default://for '*'
                return a * b;
        }
    }

    private void parseInput(String input, List<Integer> numbers, List<Character> operators) {
        int num = 0;
        for (char c : input.toCharArray()) {
            if (c >= '0' && c <= '9') {
                num = num * 10 + (c - '0');
            } else {
                numbers.add(num);
                operators.add(c);
                num = 0;
            }
        }
        //add the last number
        numbers.add(num);
    }
}
