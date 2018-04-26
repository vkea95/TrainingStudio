package leetcode.com.hard;

import leetcode.com.util.TreeNode;

import java.util.*;

/**
 * Created by JianZhang on 3/31/18.
 */
public class LC_773 {

    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int subStart = 0;
        int subEnd = 0;
        int length = s.length();
        int[][] cache = new int[length][length];
        for (int i = length - 1; i >= 0; i--) {//bug: should come from end
            cache[i][i] = 1;
            for (int j = i + 1; j < length; j++) {
                if (s.charAt(i) == s.charAt(j) && ((j - i < 2) || cache[i + 1][j - 1] == 1)) {
                    cache[i][j] = 1;
                    if ((j - i) > (subEnd - subStart)) {
                        subStart = i;
                        subEnd = j;
                    }
                }
            }
        }

        return s.substring(subStart, subEnd + 1);
    }

    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }

        int n = s.length();
        int m = p.length();

        boolean[][] dp = new boolean[m + 1][n + 1];

        dp[0][0] = true;
        for (int i = 1; i <= m; i++) {
            dp[i][0] = p.charAt(i - 1) == '*' && dp[i - 2][0];//bug
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(j - 1) == p.charAt(i - 1) || p.charAt(i - 1) == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(i - 1) == '*') {
                    if (p.charAt(i - 2) != '.' && p.charAt(i - 2) != s.charAt(j - 1)) {//
                        dp[i][j] = dp[i - 2][j];
                    } else {
                        dp[i][j] = dp[i - 2][j] || dp[i - 1][j] || dp[i][j - 1];
                    }
                }
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(34567890);
        list.add(34567890);
        System.out.println(list.get(0) == list.get(1));
        System.out.println(list.get(0).equals(list.get(1)));

        Solution s = new Solution();
        int[] temp = s.getTopk(new int[]{1, 0, 19, 7, 7, 8}, 3);
        for (int i : temp) System.out.println(i);

        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < 0; i++) {
            lists.add(new ArrayList<>());
            int random = (int) (Math.random() * 10);
            for (int j = 0; j < random; j++) {
                lists.get(i).add(i);
                System.out.print(i + " -> ");

            }
            System.out.println();
        }

        ZigzagIterator zigzagIterator = new ZigzagIterator(lists);
        System.out.println("printing");
        while (zigzagIterator.hasNext()) {
            System.out.print(zigzagIterator.next() + " --> ");
        }

    }
}


class ZigzagIterator {

    List<List<Integer>> lists;
    int[] cursors;
    int count;
    int total;
    int curtCursor;

    public ZigzagIterator(List<List<Integer>> lists) {
        this.lists = new ArrayList<>();
        this.cursors = new int[lists.size()];
        for (int i = 0; i < lists.size(); i++) {
            this.lists.add(new ArrayList<>(lists.get(i)));
            this.total += lists.get(i).size();
        }
    }

    public int next() {
        count++;
        int listSize = lists.size();
        int i = 0;
        curtCursor %= listSize;//bug out of index, I should use % for mod operation
        while (cursors[curtCursor] >= lists.get(curtCursor).size() && i < listSize) {// need to check if the cursor is correct
            curtCursor++;
            curtCursor %= listSize;
            i++;
        }
        if (i == listSize) {
            System.out.println("out of index");//should throws exception
        }
        return lists.get(curtCursor).get(cursors[curtCursor++]++);
    }

    public boolean hasNext() {
        return count < total;
    }
}


class Solution {
//必须要循环遍历每个人鱼人之间的关系，
//    we must know the relationships between one another,
// the count will be n-1 + n-2 + ... + 1==> o(n^2)
//    we'll define a 2-dimension array for counting the number of persons that he knows, and the number of person he is known by whom


    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<List<Integer>> levelResult = new ArrayList<>();
        helper(root, 0, levelResult);
        List<Integer> rightBoundary = new ArrayList<>();
        List<Integer> leftBoundary = new ArrayList<>();

        for (int i = 0; i < levelResult.size() - 1; i++) {
            if (levelResult.get(i).size() > 1) {
                rightBoundary.add(levelResult.get(i).get(1));
            }
            leftBoundary.add(levelResult.get(i).get(0));
        }
        leftBoundary.addAll(levelResult.get(levelResult.size() - 1)); //bug
        Collections.reverse(rightBoundary);
        leftBoundary.addAll(rightBoundary);
        return leftBoundary;
    }

    private void helper(TreeNode root, int level, List<List<Integer>> result) {
        if (root == null) {
            return;
        }

        if (result.size() <= level) {
            result.add(new ArrayList<>());

        }
        if (root.left == null && root.right == null) {
            result.get(level).add(root.val);
            return;
        }
        int levelSize = result.get(level).size();
        if (levelSize > 1) {
            result.get(level).remove(levelSize - 1);
        }
        result.get(level).add(root.val);

        helper(root.left, level + 1, result);
        helper(root.right, level + 1, result);
    }


    public int[] getTopk(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            public int compare(Integer i1, Integer i2) {
                return i1 - i2;
            }
        });

        for (int i = 0; i < nums.length; i++) {
            pq.offer(nums[i]);
            if (pq.size() > k) {
                pq.poll();
            }
        }

        int[] result = new int[k];

        while (k > 0) {//bug: k >=0 --> k >0
            result[--k] = pq.poll();
        }
        return result;
    }

    public int getPerfectCount(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        Arrays.sort(nums);

        List<Integer> list = new ArrayList<>();
        int[] count = new int[0];

        helper(nums, 0, list, count);
        return count[0];
    }

    public void helper(int[] nums, int start, List<Integer> list, int[] count) {
        int sum = -1;
        boolean sumFlg = false;
        if (list.size() > 1) {
            sum = count(list);
            sumFlg = true;
        }
        for (int i = start; i < nums.length; i++) {
            if (sumFlg && sum == nums[i]) {
                count[0]++;
            }
            list.add(nums[start]);

            helper(nums, i + 1, list, count);

            list.remove(list.size() - 1);
        }
    }

    public int count(List<Integer> list) {
        int result = 0;
        for (int i = 0; i <= list.size(); i++) {
            result += list.get(i);
        }
        return result;
    }
}
