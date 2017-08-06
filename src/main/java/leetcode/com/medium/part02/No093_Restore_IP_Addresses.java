package leetcode.com.medium.part02;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jason on 2016/3/10.
 * Location:
 * https://leetcode.com/problems/restore-ip-addresses/
 * ******************************************************
 * Description:
 * Given a string containing only digits, restore it by returning all possible valid IP address combinations.
 * For example:
 * Given "25525511135",
 * return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
 * ******************************************************
 * Solution:
 * http://www.cnblogs.com/yuzhangcmu/p/4106686.html
 * DepthFirstSearch 解决问题，递归经典模板
 */
public class No093_Restore_IP_Addresses {
    public List<String> restoreIpAddresses(String s) {
        List<String> rst = new ArrayList<>();

        DFS(s, 0, new ArrayList<>(), rst);
        return rst;
    }

    public void DFS(String s, int index, List<String> solution, List<String> result) {
        int len = s.length();
        if (solution.size() == 4) {
            if (len == index) {
                StringBuilder sb = new StringBuilder();
                //bug4: confusing with the sb ->string
                for (String element : solution) {
                    sb.append(element);
                    sb.append(".");
                }
                //bug5: need to remember the deleteCharAt...
                sb.deleteCharAt(sb.length() - 1);
                result.add(sb.toString());
            }
            return;
        }

        //bug1: forget the loop conditions:  i < index + 3 && i < len
        for (int i = index; i < index + 3 && i < len; i++) {
            if (s.charAt(index) == '0' && i != index) {
                break;
            }
            //bug3: forget to check the valid
            if (!isValid(s.substring(index, i + 1))) {
                continue;
            }
            //bug2: forget the recursive template
            //bug6: String.substring(index,endIndex) endIndex=i+1
            solution.add(s.substring(index, i + 1));
            DFS(s, i + 1, solution, result);
            solution.remove(solution.size() - 1);
        }
    }

    public boolean isValid(String sub) {
        int num = Integer.parseInt(sub);
        return num >= 0 && num <= 255;
    }
}
