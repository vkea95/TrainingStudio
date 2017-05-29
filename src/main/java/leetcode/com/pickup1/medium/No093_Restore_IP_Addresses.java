package leetcode.com.pickup1.medium;

import java.util.*;

/**
 * Created by tclresearchamerica on 6/23/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/restore-ip-addresses/
 * ****************************************************
 * Description:
 * Given a string containing only digits, restore it by returning all possible valid IP address combinations.
 * For example:
 * Given "25525511135",
 * return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
 * ****************************************************
 * Thoughts:
 * 1.第一反应因为要寻求所有的解,所以可以考虑用递归的方式解决问题
 * ****************************************************
 * Time: 35 mins
 * Beat: 10%
 * Bug: 2
 * ****************************************************
 * 因为当时没有想到递归的写法,所以第二次换成resursive试试看
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
public class No093_Restore_IP_Addresses {
    public static void main(String[] args) {
        No093_Restore_IP_Addresses obj = new No093_Restore_IP_Addresses();
        obj.restoreIpAddresses("0000");
    }

    public List<String> restoreIpAddresses(String s) {
        List<String> solution = new ArrayList<>();
        List<String> result = new ArrayList<>();
        //bug3:异常状态的处理
        if (s == null || s.length() < 4 || s.length() > 12) return result;

        restore_DFS(s, solution, result, 0);

        return result;
    }

    //Problem1: 其实solution和result都是List<String>类型,导致到时想法乱了,还是写下来,就清楚了
    private void restore_DFS(String s, List<String> solution, List<String> list, int index) {

        if (solution.size() == 4) {
            if (index == s.length()) {
                StringBuffer sb = new StringBuffer();
                for (String item : solution) {
                    sb.append(item);
                    sb.append(".");
                }
                list.add(sb.substring(0, sb.length() - 1));
            }
        }

        //bug2:i是endIndex,它是上限+1,不是上限
        for (int i = index + 1; i <= index + 3 && i <= s.length(); i++) {
            if (isValid(s.substring(index, i))) {
                solution.add(s.substring(index, i));
                restore_DFS(s, solution, list, i);
                solution.remove(solution.size() - 1);
            }

        }
    }


    public List<String> restoreIpAddresses_bySelft(String s) {
        List<String> result = new ArrayList<>();
        Set<String> set = new HashSet<>();

        for (int i = 0; i <= s.length() - 3 && i < 3; i++) {
            for (int j = i + 1; isValid(s.substring(0, j)) && j < i + 4 && j < s.length() - 2; j++) {
                for (int k = j + 1; isValid(s.substring(j, k)) && k < j + 4 && k < s.length() - 1; k++) {
                    for (int l = 1; l <= 3 && k + l < s.length(); l++) {
                        //bug1:不加长度的判断，会导致超大的整数出现，发生溢出
                        if (s.length() - k - l > 3) continue;
                        if (isValid(s.substring(k, k + l)) && isValid(s.substring(k + l))) {
                            String solution = Integer.parseInt(s.substring(0, j)) + "."
                                    + Integer.parseInt(s.substring(j, k)) + "."
                                    + Integer.parseInt(s.substring(k, k + l)) + "."
                                    + Integer.parseInt(s.substring(k + l));
                            set.add(solution);
                        }
                    }
                }
            }

        }
        //bug3:需要处理重复的元素,所以要将set转换为Array,再转为list
        String[] arr = new String[set.size()];
        set.toArray(arr);
        return Arrays.asList(arr);

    }

    private boolean isValid(String input) {
        //bug2:需要特殊处理连续2个零或者3个零的情况
        if (input.length() > 1 && input.charAt(0) == '0') return false;

        int n = Integer.parseInt(input);


        if (n > 255 || n < 0) {
            return false;
        } else {
            return true;
        }

    }
}
