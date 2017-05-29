package leetcode.com.pickup1.easy;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by tclresearchamerica on 6/23/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/valid-parentheses/
 * ****************************************************
 * Description:
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine
 * if the input string is valid.
 * The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
 * ****************************************************
 * Thoughts:
 * 1.需要避免的case是先有右括号,再有左括号,以及所有的右括号没有被干掉的情况,但是提示的结果case更简单些,比价好判断.
 * 2.不知道为什么会出现我的运行结果和 leetcode的运行结果不一致的问题
 * <p>
 * ****************************************************
 * Time: 20 mins
 * Beats: 12%
 * Bug: 1
 * ****************************************************
 * Hindsight:
 * 1.首先,比较两个字符串是否相等该用equals,而不是=
 * 2.测试用例包含{【()】},这样的合法子串,所以直接判断"()"是不合理的,除非循环替换
 * 3.还是参照了第一版的做法,使用栈来处理子串ol.........77777777777777777777777777777777777777777777777777777777777777777777
 */
public class No020_Valid_Parentheses {
    public static void main(String[] args) {
        No020_Valid_Parentheses obj = new No020_Valid_Parentheses();
        System.out.print(obj.isValid("()"));
    }

    public boolean isValid(String s) {
        if (s == null || s.length() == 0) return true;
        char[] chars = s.toCharArray();

        Map<Character,Character> map = new HashMap<>();
        map.put('(',')');
        map.put('{','}');
        map.put('[',']');

        Stack<Character> stack = new Stack<>();
        for (char c : chars) {
            if ( map.containsKey(c)) {
                stack.push(c);
            } else if (!stack.isEmpty()&& c==map.get(stack.pop())){
                continue;
            }else {
                return false;
            }
        }

        //bug1: 此时需要判断stack是否为空,而不是直接返回true
        return stack.isEmpty();
    }
}
