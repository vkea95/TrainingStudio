package leetcode.com.medium.part22;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by tclresearchamerica on 5/4/16.
 * *******************************************
 * Location:
 * https://leetcode.com/problems/word-pattern/
 * *******************************************
 * Description:
 * Given a pattern and a string str, find if str follows the same pattern.
 * <p>
 * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.
 * <p>
 * Examples:
 * pattern = "abba", str = "dog cat cat dog" should return true.
 * pattern = "abba", str = "dog cat cat fish" should return false.
 * pattern = "aaaa", str = "dog cat cat dog" should return false.
 * pattern = "abba", str = "dog dog dog dog" should return false.
 * *******************************************
 * bug1:
 * 向HashMap存储value的时候,没有考虑是否同样的value曾经被放入map
 * bug2:需要考虑两个比较对象的size是否一致.
 * *******************************************
 */
public class No290_Word_Pattern {

    public boolean wordPattern(String pattern, String str) {
        HashMap<Character, String> map = new HashMap<>();
        Set<String> valueSet = new HashSet<String>();
        String[] objArray = str.split(" ");
        if (objArray.length != pattern.length()) return false;
        for (int i = 0; i < objArray.length && i < pattern.length(); i++) {
            if (map.containsKey(pattern.charAt(i))) {
                String value = map.get(pattern.charAt(i));
                if (!value.equals(objArray[i]))
                    return false;
            } else {
                map.put(pattern.charAt(i), objArray[i]);
                if (valueSet.contains(objArray[i]))
                    return false;
                else
                    valueSet.add(objArray[i]);
            }

        }
        return true;

    }
}
