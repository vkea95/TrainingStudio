package leetcode.com.pickup1.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tclresearchamerica on 6/23/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/different-ways-to-add-parentheses/
 * ****************************************************
 * Description:
 * Given a string of numbers and operators, return all possible results from computing all the different possible
 * ways to group numbers and operators. The valid operators are +, - and *.
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
 * ****************************************************
 * Thoughts:
 * 1.Graph? tree? permutation? Combination?
 * 2.看了之前的做法留下的笔记,发现当时也并未理解掌握哦...
 * 3.看了之前的答案,相当于说就是,根据符号拆解string,还是循环拆解,然后再将数据一个一个计算出来
 * 因为用到了hashmap所以没有什么问题
 * ****************************************************
 * Hindsight:
 * 1.不知道这类问题如何解决,所以一直无法破解
 * 07/23 add
 * 2.看了答案,才意识到这个是一个组合问题,题主用括号混淆了视听,可以根据题主提供的example推断出来。
 * 3.网络答案很巧妙,他把String串存到了Map中,因为每次都是根据计算符来进行的处理,所以经过过滤后,数值自然就浮现出来了
 * 4.这次是在网页上直接写的答案,虽然算法本身没有问题,但是,很多依然有很多编译的错误,有些事方法名字写错了(漏写关键字,拼错大小写),
 * 有些是忘了给变量加上属性的,还有些是变量类型写错了。
 * 5.基本上就认为这道题基本完结啦
 * ****************************************************
 * Time:30mins
 * Beat: 80%
 * Bug: 5
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No241_Different_Ways_to_Add_Parentheses {
    public List<Integer> diffWaysToCompute(String input) {
        if (input == null) return null;

        //bug3:map中value的类型该是List<Integer>
        Map<String, List<Integer>> map = new HashMap<>();
        return helper(input, map);
    }

    //bug1:忘记给参数加上<>的参数类型了
    private List<Integer> helper(String input, Map<String, List<Integer>> map) {
        //bug2:判断map中有key的方法是containsKey(),而不是contains();
        if (map.containsKey(input)) return map.get(input);

        List<Integer> rst = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                //bug4:substring()方法都是小写字符，不需要任何大写字符
                //bug5：没有给list加上<Integer>,后面的循环处理会出现问题
                List<Integer> left = helper(input.substring(0, i), map);//end index
                List<Integer> right = helper(input.substring(i + 1), map);
                for (int v1 : left) {
                    for (int v2 : right) {
                        if (c == '+') rst.add(v1 + v2);
                        if (c == '-') rst.add(v1 - v2);
                        if (c == '*') rst.add(v1 * v2);
                    }
                }
            }
        }
        if (rst.isEmpty()) rst.add(Integer.valueOf(input));
        return rst;
    }
}
