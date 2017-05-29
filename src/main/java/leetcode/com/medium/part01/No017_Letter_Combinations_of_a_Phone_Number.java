package leetcode.com.medium.part01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jason on 2016/1/30.
 * Locations:
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 * ********************************************************************
 * Descriptions:
 * Given a digit string, return all possible letter combinations that the number could represent.
 * A mapping of digit to letters (just like on the telephone buttons) is given below.
 * Input:Digit string "23"
 * ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * ********************************************************************
 * Solutions:
 * 首先定义好数字与字母的字符对应关系，因为每个数字字符会至少对应一个字母
 * 所以跟
 */
public class No017_Letter_Combinations_of_a_Phone_Number {

    public static void main(String[] args) {
        No017_Letter_Combinations_of_a_Phone_Number no017 = new No017_Letter_Combinations_of_a_Phone_Number();
        no017.letterCombinations("12325");

    }

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<String>();

        if (digits == null || digits.equals("")) {
            return result;
        }

        Map<Character, char[]> map = new HashMap<Character, char[]>();
        map.put('0', new char[]{' '});
        map.put('1', new char[]{' '});
        map.put('2', new char[]{'a', 'b', 'c'});
        map.put('3', new char[]{'d', 'e', 'f'});
        map.put('4', new char[]{'g', 'h', 'i'});
        map.put('5', new char[]{'j', 'k', 'l'});
        map.put('6', new char[]{'m', 'n', 'o'});
        map.put('7', new char[]{'p', 'q', 'r', 's'});
        map.put('8', new char[]{'t', 'u', 'v'});
        map.put('9', new char[]{'w', 'x', 'y', 'z'});

        StringBuilder sb = new StringBuilder();
        helper(map, digits, sb, result);

        return result;
    }

    private void helper(Map<Character, char[]> map, String digits,
                        StringBuilder sb, List<String> result) {
        if (sb.length() == digits.length()) {
            result.add(sb.toString().replaceAll(" ",""));
            return;
        }

        for (char c : map.get(digits.charAt(sb.length()))) {
            sb.append(c);
            helper(map, digits, sb, result);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
