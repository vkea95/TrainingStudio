package google.com;

/**
 * Created by jason on 2016/2/26.
 * Location:
 * Jiuzhang Wechat
 * ******************************************************
 * Description:
 * 给出一个字符序列，问该序列是否是一颗合法的二叉树的先序遍历？
 * 找到一种不需要构造二叉树的方法
 * For example："9,3,4,#,#1,#,#,2,#,6,#,#"
 * ******************************************************
 * Solution:
 * ******************************************************
 * Thoughts:
 * 通过观察图中二叉，可以发现：一颗合法的二叉树去掉某个叶子节点后仍是合法的二叉树。给出的字符序列中，叶子节点有明显特点，
 * 即叶子节点之后一定今个2个空节点#。通过不断把number,#,#的子串压缩成空节点（替换为#），如果最后字符序列可以缩短到
 * 只有一个#，那它就是我们要找的合法先序遍历了。
 */
public class No001_Is_Valid_Serialization {

    public boolean isValidSerialization(String preOrder) {
        String s = preOrder;
        boolean flag = true;
        while (s.length() > 1) {
            int index = s.indexOf(",#,#");
            if (index < 0) {
                flag = false;
                break;
            }
            int start = index;
            //寻找叶子节点的数字
            while (start > 0 && s.charAt(start - 1) != ',') {
                start--;
            }
            if (s.charAt(start) == '#') {
                flag = false;
                break;
            }
            s = s.substring(0, start) + s.substring(index + 3);//index+3 是为了保留一个#
        }
        if (s.equals("#") && flag) {
            return true;
        } else {
            return false;
        }


    }
}
