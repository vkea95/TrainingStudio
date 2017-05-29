package leetcode.com.pickup1.medium;

/**
 * Created by tclresearchamerica on 9/16/16.
 * *************************************************************
 * Location:
 * https://leetcode.com/problems/guess-number-higher-or-lower-ii/
 * *************************************************************
 * Description:
 * We are playing the Guess Game. The game is as follows:
 * <p>
 * I pick a number from 1 to n. You have to guess which number I picked.
 * <p>
 * Every time you guess wrong, I'll tell you whether the number I picked is higher or lower.
 * <p>
 * However, when you guess a particular number x, and you guess wrong, you pay $x. You win the game when you guess
 * the number I picked.
 * <p>
 * Example:
 * <p>
 * n = 10, I pick 8.
 * <p>
 * First round:  You guess 5, I tell you that it's higher. You pay $5.
 * Second round: You guess 7, I tell you that it's higher. You pay $7.
 * Third round:  You guess 9, I tell you that it's lower. You pay $9.
 * <p>
 * Game over. 8 is the number I picked.
 * <p>
 * You end up paying $5 + $7 + $9 = $21.
 * Given a particular n ≥ 1, find out how much money you need to have to guarantee a win.
 * <p>
 * Hint:
 * <p>
 * The best strategy to play the game is to minimize the maximum loss you could possibly face. Another strategy is
 * to minimize the expected loss. Here, we are interested in the first scenario.Show More Hint
 * <p>
 * *************************************************************
 * Thought:
 * 1.木有思路
 * 2.copy 答案:
 * https://discuss.leetcode.com/topic/51353/simple-dp-solution-with-explanation
 * For each number x in range[i~j]
 * we do: result_when_pick_x = x + max{DP([i~x-1]), DP([x+1, j])}
 * --> // the max means whenever you choose a number, the feedback is always bad and therefore leads you
 * to a worse branch.
 * then we get DP([i~j]) = min{xi, ... ,xj}
 * --> // this min makes sure that you are minimizing your cost.
 * *************************************************************
 * *************************************************************
 * *************************************************************
 * *************************************************************
 * *************************************************************
 * *************************************************************
 * *************************************************************
 */
public class No375_Guess_Number_Higher_or_Lower_II {

    public static void main(String[] args) {
        No375_Guess_Number_Higher_or_Lower_II obj = new No375_Guess_Number_Higher_or_Lower_II();
        System.out.println(obj.getMoneyAmount(10));
    }

    public int getMoneyAmount(int n) {
        int[][] table = new int[n + 1][n + 1];
        return countMoney(table, 1, n);
    }

    int countMoney(int[][] t, int start, int end) {
//        System.out.println("start:" + start + " end:" + end);
        if (start >= end) return 0;
        if (t[start][end] != 0) return t[start][end];
        int res = Integer.MAX_VALUE;
        for (int x = start; x <= end; x++) {
            int tmp = x + Math.max(countMoney(t, start, x - 1), countMoney(t, x + 1, end));
            res = Math.min(res, tmp);
        }
        t[start][end] = res;
        System.out.println("start:" + start + " end:" + end + " money:" + res);
        return res;
    }
}
