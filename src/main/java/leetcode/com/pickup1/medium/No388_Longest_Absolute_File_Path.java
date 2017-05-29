package leetcode.com.pickup1.medium;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * Created by tclresearchamerica on 9/16/16.
 * *************************************************************
 * Location:
 * https://leetcode.com/problems/longest-absolute-file-path/
 * *************************************************************
 * Description:
 * <p>
 * The string "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext" represents:
 * <p>
 * dir
 * subdir1
 * subdir2
 * file.ext
 * The directory dir contains an empty sub-directory subdir1 and a sub-directory subdir2 containing a file file.ext.
 * <p>
 * The string "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext" represents:
 * <p>
 * dir
 * subdir1
 * file1.ext
 * subsubdir1
 * subdir2
 * subsubdir2
 * file2.ext
 * The directory dir contains two sub-directories subdir1 and subdir2. subdir1 contains a file file1.ext and an empty
 * second-level sub-directory subsubdir1. subdir2 contains a second-level sub-directory subsubdir2 containing
 * a file file2.ext.
 * <p>
 * We are interested in finding the longest (number of characters) absolute path to a file within our file system.
 * For example, in the second example above, the longest absolute path is "dir/subdir2/subsubdir2/file2.ext",
 * and its length is 32 (not including the double quotes).
 * <p>
 * Given a string representing the file system in the above format, return the length of the longest absolute path to
 * file in the abstracted file system. If there is no file in the system, return 0.
 * <p>
 * Note:
 * The name of a file contains at least a . and an extension.
 * The name of a directory or sub-directory will not contain a ..
 * Time complexity required: O(n) where n is the size of the input string.
 * <p>
 * Notice that a/aa/aaa/file1.txt is not the longest file path, if there is another path aaaaaaaaaaaaaaaaaaaaa/sth.png.
 * <p>
 * *************************************************************
 * Hindsight:
 * https://discuss.leetcode.com/topic/55247/9-lines-4ms-java-solution/2
 * *************************************************************
 * Dequeue: double queue
 * http://blog.sina.com.cn/s/blog_7768d2210101ajj6.html
 * *************************************************************
 * Thoughts
 * 1.需要stack中存有路径的长度(含\),
 * 2.用stack的size来标志路径的level,这样就生了一个stack空间!!!!------------>赞
 * *************************************************************
 * Bugs:
 * 1.特殊字符的处理:\n, \t分别对应换行和tab建,他们在string中的长度就是1
 * 2.测试用例中给出的例子,有可能一上来会含有同级:"a\n\tb.txt\na2\n\tb2.txt"
 * 所以用栈处理的时候,不能够提前将首个元素放入栈中,应该是置入一个dummy的节点
 * *************************************************************
 * *************************************************************
 * *************************************************************
 * *************************************************************
 * *************************************************************
 * *************************************************************
 */
public class No388_Longest_Absolute_File_Path {

    public static void main(String[] args) {
        int initialCapacity = 2;
        initialCapacity |= (initialCapacity >>> 1);
        System.out.println(initialCapacity);
        initialCapacity |= (initialCapacity >>> 2);
        System.out.println(initialCapacity);
        initialCapacity |= (initialCapacity >>> 4);
        System.out.println(initialCapacity);
        String path = "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext";
        path = "a\n\tb.txt\na2\n\tb2.txt";
        path = "dir\n\tfile.txt";

        No388_Longest_Absolute_File_Path obj = new No388_Longest_Absolute_File_Path();
        obj.lengthLongestPath(path);
    }

    public int lengthLongestPath(String input) {

        if (input == null) return 0;
        Stack<Integer> stack = new Stack<>();
        int result = 0;
        stack.push(0);
        for (String s : input.split("\n")) {
            //用level来控制,因为stack预先设置了dummy的值,元素可能不含有'\t',那么就要判断下了level可能只为0,1,2
            //stack的size至少为1(值为0),那么level 0对应的stack size为2,level 1对应的stack size为3
            int level = s.contains("\t") ?  s.lastIndexOf("\t") + 1:0;
            if (s.contains(".")) {
                int length = stack.peek() + s.length() - level;
                result = Integer.max(result, length);
            } else {
                //根据level，出栈
                //因为栈中至少要保持一个空的dummy元素，所以需要level+1<size()
                while (level + 1 < stack.size()) stack.pop();
                //
                stack.push(stack.peek() + s.length() - level + 1);
            }
        }
        return result;
    }


    public int lengthLongestPath_2(String input) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0); // "dummy" length
        int maxLen = 0;
        for (String s : input.split("\n")) {
            int lev = s.lastIndexOf("\t") + 1; // number of "\t"
            while (lev + 1 < stack.size()) stack.pop(); // find parent?
            int len = stack.peek() + s.length() - lev + 1; // remove "/t", add"/"
            stack.push(len);
            // check if it is file
            if (s.contains(".")) maxLen = Math.max(maxLen, len - 1);
        }
        return maxLen;
    }

    public int lengthLongestPath_practice(String input) {
        Deque<Integer> stack = new ArrayDeque<>();
        return 1;
    }

    //failid test case: path = "a\n\tb.txt\na2\n\tb2.txt";
    public int lengthLongestPath_wrong(String input) {
        int result = 0;
        if (input == null) return result;
        String[] arrays = input.split("\n");
        Stack<Integer> stack = new Stack();

        Stack<Integer> levelStack = new Stack();
        //save the length of the path
        stack.push(arrays[0].length() + 1);
        levelStack.push(0);
        for (int i = 1; i < arrays.length; i++) {
            System.out.println(arrays[i]);
            int level = arrays[i].lastIndexOf("\t") + 1;
            //file
            if (arrays[i].contains(".")) {

                int temp = stack.peek() + arrays[i].length() - level;
                result = Integer.max(result, temp);
            } else {

                // System.out.println(level);
                while (levelStack.peek() >= level) {
                    stack.pop();
                    levelStack.pop();
                }
                stack.push(stack.peek() + arrays[i].length() + 1 - level);
                levelStack.push(level);

                System.out.println(stack.peek());
            }
        }
        return result;
    }

}
