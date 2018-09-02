package leetcode.com.medium.part02;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jason on 2016/2/24.
 * Location:
 * https://leetcode.com/problems/simplify-path/
 * *********************************************
 * Description:
 * Given an absolute path for a file (Unix-style), simplify it.
 * For example,
 * path = "/home/", => "/home"
 * path = "/a/./b/../../c/", => "/c"
 * click to show corner cases.
 * Corner Cases:
 * Did you consider the case where path = "/../"?
 * In this case, you should return "/".
 * Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
 * In this case, you should ignore redundant slashes and return "/home/foo".
 * ***********************************************
 * Solution:
 * 1.将字符根据“/” 打散成 indexList， 那么遇到“..”就返回上级目录 即删掉之前最后的那个路径
 * 但是如何处理"/../"呢？ 没有看明白哦
 */
public class No071_Simplify_Path {
    public String simplifyPath(String path) {
        String result = "/";
        String[] stubs = path.split("/+");//use the regular epression
        List<String> paths = new ArrayList<>();

        for (String s : stubs) {
            if (s.equals("..")) {
                if (paths.size() > 0) {
                    paths.remove(paths.size() - 1);
                }
            } else if (!s.equals(".") && !s.equals("")) {
                paths.add(s);
            }
        }

        for (String s : paths) {
            result += s + "/";
        }
        if (result.length() > 1) {
            result = result.substring(0, result.length() - 1);
        }

        return result;

    }
}
