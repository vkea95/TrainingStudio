package leetcode.com.pickup1.medium;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * Created by tclresearchamerica on 7/19/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/simplify-path/
 * ****************************************************
 * Description:
 * Given an absolute path for a file (Unix-style), simplify it.
 * <p>
 * For example,
 * path = "/home/", => "/home"
 * path = "/a/./b/../../c/", => "/c"
 * ****************************************************
 * Thoughts:
 * 1.这是个什么玩意儿?既然,存在这个问题,那就是说,要处理的就是.和..的问题
 * 2.所以要用"/"来切割字符串,然后压栈,根据..来出栈
 * 3.需要注意的是,只有.的情况下,该显示什么路径,可以先从简单的入手
 * ****************************************************
 * Time: 25 mins
 * Beat: 4%->96%
 * Bug:2
 * ****************************************************
 * Hindsight:
 * 1.节本原理明白,但是部分解决方案有问题,漏掉了""的判断,以及没有完成对stack的为空判断,是空的话,需要补充"/"
 * 2.网络答案速度很快,想来是只扫描一次String造成的,
 * 3.有个陌生的数据结构 Zj_Deque
 * Ref: http://blog.sina.com.cn/s/blog_7768d2210101ajj6.html
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No071_Simplify_Path {
    public String simplifyPath(String path) {
        int len = path.length();
        Deque<String> stack = new ArrayDeque<>();
        for (int i = 0; i < len; ) {
            char c = path.charAt(i);
            if (c == '/') {
                ++i;
            }  // skip the separator '/'
            else if (c == '.') {
                int j = i + 1;
                while (j < len && path.charAt(j) != '/') {
                    ++j;
                }
                if (j - i == 2 && path.charAt(i + 1) == '.' && !stack.isEmpty()) {  // go up to parent directory
                    stack.removeLast();
                } else if (j - i > 2) {
                    stack.addLast(path.substring(i, j));  // go down to child directory
                }
                i = j;
            } else {
                int j = i + 1;
                while (j < len && path.charAt(j) != '/') {
                    ++j;
                }
                stack.addLast(path.substring(i, j));  // go down to child directory
                i = j;
            }
        }
        StringBuilder ans = new StringBuilder();
        for (String dir : stack) {
            ans.append('/').append(dir);
        }
        if (ans.length() == 0) {
            return "/";
        }
        return ans.toString();
    }

    public String simplifyPath_slow(String path) {
        if (path == null || path == ".") return path;
        String[] paths = path.split("/+");
        Stack<String> stack = new Stack<>();
        for (String s : paths) {
            //bug1:需要将""同.一起进行处理,否则结果不正确
            if (".".equals(s) || "".equals(s)) {
                continue;
            } else if ("..".equals(s)) {
                if (!stack.isEmpty())
                    stack.pop();
            } else {
                stack.push(s);
            }
        }

        StringBuilder sb = new StringBuilder();
        //bug2:需要对是否有结果进行处理,如果没有就补/
        if (stack.isEmpty()) {
            sb.append("/");
        } else {
            while (!stack.isEmpty()) {
                sb.insert(0, "/" + stack.pop());
            }
        }
        // sb.append("/");
        String result = sb.toString();

        return result;
    }
}
