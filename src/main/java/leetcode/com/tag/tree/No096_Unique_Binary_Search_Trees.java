package leetcode.com.tag.tree;

/**
 * Created by JianZhang on 9/13/17.
 * Given n, how many structurally unique BST's (binary search trees) that store values 1...n?
 * <p>
 * For example,
 * Given n = 3, there are a total of 5 unique BST's.
 * <p>
 * 1         3     3      2      1
 * \       /     /      / \      \
 * 3     2     1      1   3      2
 * /     /       \                 \
 * 2     1         2                 3
 * Thoughts:
 * 1. 结构unique:完全没有思路,现在就是搞成排列组合中的组合问题了
 * 2. 0个节点,1种结构,1个节点,1种结构, 2个节点,2种结构,2*1+1*1+1*2=5
 * c[n]=c[0]*c[n-1]+c[1]*c[n-2]...c[n-1]*c[0]
 * ==>所以需要套用两层循环,外层循环是目标,里层循环是公式累计值到目标
 */
public class No096_Unique_Binary_Search_Trees {
    public int numTrees(int n) {
        int[] nums = new int[n + 1];
        nums[0] = 1;
        nums[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            for (int j = 0; j < i; j++) {
                nums[i] += nums[j] * nums[i - j - 1];
            }
        }

        return nums[n];
    }
}
