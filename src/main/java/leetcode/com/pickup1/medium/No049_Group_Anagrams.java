package leetcode.com.pickup1.medium;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by tclresearchamerica on 6/26/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/anagrams/
 * ****************************************************
 * Description:
 * Given an array of strings, group anagrams together.
 * For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * Return:
 * <p>
 * [
 * ["ate", "eat","tea"],
 * ["nat","tan"],
 * ["bat"]
 * ]
 * Note: All inputs will be in lower-case.
 * ****************************************************
 * Thought:
 * 1.可以通过指数相乘的方式,的到唯一值,然后放进去,就可以了
 * 2.旧的算法就是排序而已
 * ****************************************************
 * Time: 20 mins
 * Beat: 80%
 * Bug:0
 * ****************************************************
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
public class No049_Group_Anagrams {
    public static void main(String[] args) {
        No049_Group_Anagrams obj = new No049_Group_Anagrams();
        List<List<String>> result = obj.groupAnagrams(new String[]{"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaafoobar"});
        System.out.print(result);
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        int[] primeArr = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71,
                73, 79, 83, 89, 97, 101};
        //103, 107, 109, 113, 127, 131};
        //137, 139, 149, 151, 157, 163, 167, 173,
        //179, 181, 191, 193, 197

        List<List<String>> result = new ArrayList<>();
        HashMap<Integer, List<String>> hashMap = new HashMap<>();
        for (String s : strs) {
            int index = 1;
            for (char c : s.toCharArray()) {
                index *= primeArr[c - 'a'] % Integer.MAX_VALUE;
            }
            if (!hashMap.containsKey(index)) {

                hashMap.put(index, new ArrayList<>());

            }
            hashMap.get(index).add(s);
        }

        for (List<String> list : hashMap.values()) {
            result.add(list);
        }
        return result;
    }

    public List<List<String>> groupAnagrams_euro_prime(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        HashMap<Integer, List<String>> hashMap = new HashMap<>();
        for (String s : strs) {
            int index = 1;
            for (char c : s.toCharArray()) {
                int n = c - 'a' + 1;
                //opt:Euro Prime,不必提前定义质数数组
                index *= (n * n + n + 41) % Integer.MAX_VALUE;
            }
            if (!hashMap.containsKey(index)) {

                hashMap.put(index, new ArrayList<>());

            }
            hashMap.get(index).add(s);
        }

        //opt:用addAll 避免循环
        result.addAll(hashMap.values());
        return result;
    }
}
