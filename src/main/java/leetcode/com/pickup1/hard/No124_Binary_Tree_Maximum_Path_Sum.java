package leetcode.com.pickup1.hard;

import leetcode.com.util.TreeNode;

import javax.xml.transform.Result;
import java.util.ResourceBundle;

/**
 * Created by tclresearchamerica on 7/13/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/
 * ****************************************************
 * Description:
 * Given a binary tree, find the maximum path sum.
 * For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree
 * along the parent-child connections. The path does not need to go through the root.
 * For example:
 * Given the below binary tree,
 * 1
 * / \
 * 2   3
 * Return 6.
 * ****************************************************
 * Thought:
 * 1.依然是不会做啊!看了答案,发现思路是这个样子的,
 * A.路径通过root,
 * B.路径不通过root,
 * 2.节点上的value可能是负值
 * 所以需要这样的存储结构,然而我们的解法还是要从最小的根节点开始处理,因为当前节点下可能是不好本节点的value,
 * 但是并不排除后面的节点需要本节点,所以需要保留的是会有2个值,1个是通过本节点的值,另1个是当前节点下的maxValue
 * 这里面的single 路径,该是只和left或right节点相连,且只能大于等于0,
 * maxPath则可以是left 或 right的single path或left,right single & root的和值,然后,
 * ****************************************************
 * understand: 60 mins
 * CodingTime: 30 mins
 * Beat: 17%
 * Bug:-
 * ****************************************************
 * Hindsight:
 * 问题就还是看到了题,发现做过,但是没有想到答案哦
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No124_Binary_Tree_Maximum_Path_Sum {

    public int maxPathSum(TreeNode root) {
        ResultPath resultPath = helper(root);
        return resultPath.maxPath;
    }


    private ResultPath helper(TreeNode root) {
        if (root == null) {
            return new ResultPath(0, Integer.MIN_VALUE);
        }

        ResultPath left = helper(root.left);
        ResultPath right = helper((root.right));
        //留下过本节点的single Path的最大值
        int single = Math.max(left.singlePath, right.singlePath) + root.val;
        single = single > 0 ? single : 0;

        //maxPath 代表当前可以取到的最大值,那么就该是left和right的maxPath
        //或是过本节点的single path,因为左右节点的singlePath可以为0,所以直接将所有的single Path相加即可
        int maxPath = Math.max(left.maxPath, right.maxPath);

        maxPath = Math.max(maxPath, left.singlePath + right.singlePath + root.val);
        return new ResultPath(single, maxPath);

    }
}

class ResultPath {
    int singlePath, maxPath;

    public ResultPath(int singlePath, int maxPath) {
        this.singlePath = singlePath;
        this.maxPath = maxPath;
    }
}