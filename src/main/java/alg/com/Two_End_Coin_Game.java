package alg.com;

/**
 * Created by tclresearchamerica on 7/26/16.
 * ****************************************************
 * Description:
 * 两个人拿一列硬币，A每次选一头，B每次拿大的，求A能拿到最多多少
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class Two_End_Coin_Game {
    public static void main(String[] args) {

    }
    public int takeMost(int[] coins) {
        int len = coins.length;
        int[][] dp = new int[len][len];
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                if (i == j) {
                    dp[i][j] = coins[i];
                } else if (i + 1 == j) {
                    dp[i][j] = Math.max(coins[i], coins[j]);
                }
                //这里一定要分析好，两种可能都有，然后把对方拿大的表示出来。
                else {
                    int leftOpt = coins[i];
                    int rightOpt = coins[j];
                    if (coins[i + 1] >= coins[j]) {
                        leftOpt += dp[i + 2][j];
                    } else {
                        leftOpt += dp[i + 1][j - 1];
                    }
                    if (coins[i] >= coins[j - 1]) {
                        rightOpt += dp[i + 1][j - 1];
                    } else {
                        rightOpt += dp[i][j - 2];
                    }
                    dp[i][j] = Math.max(leftOpt, rightOpt);
                }
            }
        }
        return dp[0][len - 1];
    }
}
