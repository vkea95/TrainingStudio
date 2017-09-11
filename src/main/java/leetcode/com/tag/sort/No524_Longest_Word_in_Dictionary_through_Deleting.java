package leetcode.com.tag.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by JianZhang on 9/10/17.
 * Given a string and a string dictionary, find the longest string in the dictionary
 * that can be formed by deleting some characters of the given string. If there are more than one possible results,
 * return the longest word with the smallest lexicographical order.
 * If there is no possible result, return the empty string.
 * Example 1:
 * Input:
 * s = "abpcplea", d = ["ale","apple","monkey","plea"]
 * Output:
 * "apple"
 * Example 2:
 * Input:
 * s = "abpcplea", d = ["a","b","c"]
 * Output:
 * "a"
 * Note:
 * All the strings in the input will only contain lower-case letters.
 * The size of the dictionary won't exceed 1,000.
 * The length of all the strings in the input won't exceed 1,000.
 * Thoughts:
 * 1. 需要通过字典的单词排序,进行处理,即长度大的在前边,长度相等的字符串小的在前面
 * 2. 循环处理字典,发现第一个满足下面条件的就是目标单词
 * 3. 想来是要算下每个单词和目标单词的差,即通过删掉哪些字符可以让两个字符串匹配,
 *
 * Bugs:
 * 1. j++的位置,需要注意,就是要保证放到匹配之后才进行的代码中,而不是放到跳出循环的位置;
 */
public class No524_Longest_Word_in_Dictionary_through_Deleting {
    public static void main(String[] args) {
        No524_Longest_Word_in_Dictionary_through_Deleting obj = new No524_Longest_Word_in_Dictionary_through_Deleting();
        String s1 = "abpcplea";
        List<String> dict = new ArrayList<>();
        dict.add("ale");
        dict.add("apple");
        dict.add("monkey");
        dict.add("plea");
        obj.findLongestWord(s1, dict);
    }

    public String findLongestWord(String s, List<String> d) {
        String result = "";
        if (s == null||"".equals(s)) return result;

        Collections.sort(d, new DidctonaryComparator());
        char[] goalChars = s.toCharArray();
        for (String candidate : d) {
            char[] chars = candidate.toCharArray();
            int i = 0;
            int j = 0;
            while (j < chars.length) {
                while (i < goalChars.length) {
                    if (goalChars[i++] == chars[j]) {
                        j++;//bug1:--->如果j的位置外移,会导致,不正常的处理case,为j进行自加
                        break;
                    }
                }
                if (i == goalChars.length) {break;}
                // j++;//bug1:--->如果j的位置外移,会导致,不正常的处理case,为j进行自加
            }
            if (j == chars.length) {
                return candidate;
            }
        }

        return result;
    }

    private class DidctonaryComparator implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            int size1 = o1.length();
            int size2 = o2.length();
            if (size1 > size2) {
                return -1;
            } else if (size1 < size2) {
                return 1;
            } else {
                return o1.compareTo(o2);
            }
        }
    }
}
