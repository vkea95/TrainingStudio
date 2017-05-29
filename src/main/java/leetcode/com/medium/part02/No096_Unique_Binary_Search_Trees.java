package leetcode.com.medium.part02;

/**
 * Created by jason on 2016/2/24.
 * Location:
 * https://leetcode.com/problems/unique-binary-search-trees/
 * *********************************************************
 * Description:
 * Given n, how many structurally unique BST's (binary search trees) that store values 1...n?
 * For example,
 * Given n = 3, there are a total of 5 unique BST's.
 * 1         3     3      2      1
 * \       /     /      / \      \
 * 3     2     1      1   3      2
 * /     /       \                 \
 * 2     1         2                 3
 * ***********************************************************
 * Solution:
 * The case for 3 elements example
 * Count[3] = Count[0]*Count[2]  (1 as root)
 * Count[3] += Count[1]*Count[1]  (2 as root)
 * Count[3] += Count[2]*Count[0]  (3 as root)
 * Therefore, we can get the equation: Count[i] = ∑ Count[0...k] * [ k+1....i]     0<=k<i-1
 * ***********************************************************
 * Tips:
 * 二叉查找树是满足以下条件的二叉树：
 * 1.左子树上的所有节点值均小于根节点值，2右子树上的所有节点值均不小于根节点值，3，左右子树也满足上述两个条件。
 * ***********************************************************
 * 这是一个Catalan数啊，C(n)表示n个元素可以构成的二叉搜索树个数，那
 * C(0)=1
 * C(1)=1
 * C(2)=2
 * ……
 * C(n)=C(0)*C(n-1)+C(1)*C(n-2)+C(2)*C(n-3)+...+C(n-1)*C(0)
 * ************************************************************
 *  一个 BST 可以被分解为根, 左子树, 右子树.
 * 如果根节点确定, 左/右子树的每一个组合都组成一个唯一的BST.
 * 令 h[n] = 给定 1..n 情况下的解, 则有
 * h[n] = h[0] * h[n-1]     // 1在根, 2...n 右
 *      + h[1] * h[n-2]     // 2 在根, 1 左, 3...n 右
 *      + ...
 *      + h[n-1] * h[0]     // n 在根, 1...n-1 左
 */
public class No096_Unique_Binary_Search_Trees {
    public int numTrees(int n) {
        int[] count = new int[n + 2];
        count[0] = 1;
        count[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                count[i] += count[j] * count[i - j - 1];
            }
        }
        return count[n];
    }
}
