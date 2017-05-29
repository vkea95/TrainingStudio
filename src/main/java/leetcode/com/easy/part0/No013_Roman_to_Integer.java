package leetcode.com.easy.part0;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jason on 2016/1/30.
 * Locations:
 * https://leetcode.com/problems/roman-to-integer/
 * ***************************************************
 * Descriptions:
 * Given a roman numeral, convert it to an integer.
 * Input is guaranteed to be within the range from 1 to 3999.
 * ***************************************************
 * Solutions:
 * 构造合适的map对象，建立字符和数值间的对应关系，需要注意的是如果当前字符对应的数值小于排在其后面的字符所对应的数值，
 * 那么就该用结果减去当前字符所对应的数值，反之则相加。当然在做这个循环处理之前，需要把最后的字符所对应的数值先放入结果
 */
public class No013_Roman_to_Integer {
    public int romanToInt(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        Map<Character, Integer> m = new HashMap<Character, Integer>();
        m.put('I', 1);
        m.put('V', 5);
        m.put('X', 10);
        m.put('L', 50);
        m.put('C', 100);
        m.put('D', 500);
        m.put('M', 1000);

        int length = s.length();
        int result = m.get(s.charAt(length - 1));
        for (int i = length - 2; i >= 0; i--) {
            if (m.get(s.charAt(i + 1)) <= m.get(s.charAt(i))) {
                result += m.get(s.charAt(i));
            } else {
                result -= m.get(s.charAt(i));
            }
        }
        return result;
    }
}
