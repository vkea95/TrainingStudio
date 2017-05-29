package leetcode.com.pickup1.medium;

import java.util.List;

/**
 * Created by tclresearchamerica on 8/7/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/triangle/
 * ****************************************************
 * Description:
 * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on
 * the row below.
 * For example, given the following triangle
 * [
 * [2],
 * [3,4],
 * [6,5,7],
 * [4,1,8,3]
 * ]
 * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
 * ****************************************************
 * Thought:
 * 1.每层间的数字的关系是解题的关键,
 * 2.解题的思路该是DP
 * 3.
 * ****************************************************
 * Ref:https://segmentfault.com/a/1190000003502873
 * 复杂度
 * 时间 O(NM) 空间 O(1)
 * 思路
 * 这题我们可以从上往下依次计算每个节点的最短路径，也可以自下而上。自下而上要简单一些，因为我们只用在两个下方元素中选一个较小的，
 * 就能得到确定解。如果将上一层的累加和存在一个一维数组里，则可以只用O(n)空间，但实际上我们可以直接in place在输入list中修改值，
 * 就可以不用额外空间了。
 * ****************************************************
 * Hindsight:
 * 1.完全没有想法,
 * 2.正确的做法是可以从底部开始计算,即倒数第二行开始,选择和底部相加的较小值
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
public class No120_Triangle {
    public int minimumTotal(List<List<Integer>> triangle) {
        for (int i = triangle.size() - 2; i >= 0; i--) {
            List<Integer> listI1 = triangle.get(i);
            List<Integer> listI2 = triangle.get(i+1);
            for (int j = 0; j < listI1.size(); j++) {
                listI1.set(j, listI1.get(j) + Math.min(listI2.get(j), listI2.get(j + 1)));
            }
        }
        return triangle.get(0).get(0);
    }
}
