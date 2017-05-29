package leetcode.com.medium.part01;

import java.util.*;

/**
 * Created by tclresearchamerica on 5/14/16.
 * Location:
 * https://leetcode.com/problems/anagrams/
 * *************************************************************************
 * Description:
 * Given an array of strings, group anagrams together.
 * <p>
 * For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * Return:
 * <p>
 * [
 * ["ate", "eat","tea"],
 * ["nat","tan"],
 * ["bat"]
 * ]
 * *************************************************************************
 * Analysis:网络答案
 * 1.将字符串化成char数组, 然后排序,肯定可以构成一个唯一的ID,然后将相同ID的字符串放入一个list和ID一一对应
 * 2.在No.1中,完成了分类,然后取数据的时候,对每个list进行Collections.sort(),然后放入结果数组中
 * *************************************************************************
 * *************************************************************************
 */
public class No049_Group_Anagrams {
    public static void main(String[] args) {

        String[] array = {"eat", "tea", "tan", "ate", "nat", "bat"};
        No049_Group_Anagrams obj = new No049_Group_Anagrams();
        obj.groupAnagrams(array);
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<String, List<String>>();


        for (String s : strs) {
            char[] c = s.toCharArray();
            Arrays.sort(c);

            //all Anagrams will be the same string if sorted  (tea,eat -->aet) , which serves as our key

            String keyStr = String.valueOf(c);
            if (!map.containsKey(keyStr)) map.put(keyStr, new ArrayList<String>());
            map.get(keyStr).add(s);
        }

        //All the key value entries are stored in an entrySet which helps us return those values that have a key corresponding to it

        for (Map.Entry<String, List<String>> entry : map.entrySet()) {

            List<String> values = entry.getValue();

            //since we want lexicographic order of the result, we sort the list
            Collections.sort(values);

            result.add(values);
        }

        return result;
    }
}
