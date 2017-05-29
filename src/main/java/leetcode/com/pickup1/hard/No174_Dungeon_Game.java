package leetcode.com.pickup1.hard;

/**
 * Created by tclresearchamerica on 7/24/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/dungeon-game/
 * ****************************************************
 * Description:
 * The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon.
 * The dungeon consists of M x N rooms laid out in a 2D grid. Our valiant knight (K) was initially positioned in
 * the top-left room and must fight his way through the dungeon to rescue the princess.
 * <p>
 * The knight has an initial health point represented by a positive integer. If at any point his health point drops to 0
 * or below, he dies immediately.
 * <p>
 * Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms;
 * other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).
 * <p>
 * In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in
 * each step.
 * <p>
 * <p>
 * Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.
 * <p>
 * For example, given the dungeon below, the initial health of the knight must be at least 7 if he follows the optimal
 * path RIGHT-> RIGHT -> DOWN -> DOWN.
 * <p>
 * -2 (K)	-3	3
 * -5	-10	1
 * 10	30	-5 (P)
 * <p>
 * Notes:
 * <p>
 * The knight's health has no upper bound.
 * Any room can contain threats or power-ups, even the first room the knight enters and the bottom-right room where
 * the princess is imprisoned.
 * ****************************************************
 * Thoughts:
 * 1.毫无疑问,动态规划
 * 2.第一次解的时候,出了问题,正向推导,会导致找不到更好的判断条件,所以需要逆向推导回去
 * ****************************************************
 * Time: 90 mins
 * Beat:15%
 * Bug:4
 * ****************************************************
 * Hindsight:
 * 1.首先是方向错误了,从头捋顺这个问题的时候,思路不对
 * 2.从尾部入手的时候,忘记补血+1这个处理啦,
 * 3.没考虑好在可以补血的时候,可以调整dp的值-->当时就发现当血是正数的时候,没有利用起来,后来才意识到可以调整dp的数值
 * 4.一定要记好,骑士必须至少有一滴血,否则就会失败.然后这一滴血是在第一次的时候补充的,在每次移动的时候,都不能弄丢,必须保证随时带至少1滴血
 * 5.
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No174_Dungeon_Game {
    public static void main(String[] args) {
        No174_Dungeon_Game obj = new No174_Dungeon_Game();
        obj.calculateMinimumHP(new int[][]{{0, 5}, {-2, 3}});
    }

    public int calculateMinimumHP(int[][] dungeon) {
        int n = dungeon.length, m = dungeon[0].length;
        //1个是minest health, 另一个是累计血量
        int[][] dp = new int[n][m];
        if (dungeon[n - 1][m - 1] > 0) {
            dp[n - 1][m - 1] = 1;
        } else {
            dp[n - 1][m - 1] = Math.abs(dungeon[n - 1][m - 1]) + 1;
        }
        int right = 0, down = 0;
        for (int i = n - 2; i >= 0; i--) {

            int self = dungeon[i][m - 1];
            if (self >= 0) {
                //bug3:没有考虑健康的时候,补血的情况
                if (self >= dp[i + 1][m - 1]) {

                    dp[i][m - 1] = 1;
                } else {

                    dp[i][m - 1] = dp[i + 1][m - 1] - self;
                }
            } else {
                dp[i][m - 1] = dp[i + 1][m - 1] + Math.abs(self);
            }
        }

        for (int j = m - 2; j >= 0; j--) {

            int self = dungeon[n - 1][j];
            //bug1:如果都是零的话,就会有问题,所以等于0需要单独处理
            if (self >= 0) {
                //bug3:没有考虑健康的时候,补血的情况
                if (self >= dp[n - 1][j + 1]) {

                    dp[n - 1][j] = 1;
                } else {

                    dp[n - 1][j] = dp[n - 1][j + 1] - self;
                }

            } else {
                //bug1:因为在初始化的时候,就已经做过+1处理了,所以不必再加一处理啦
                dp[n - 1][j] = dp[n - 1][j + 1] + Math.abs(self);
            }
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = m - 2; j >= 0; j--) {

                right = dp[i][j + 1];
                down = dp[i + 1][j];
                dp[i][j] = Math.min(down, right);
                int self = dungeon[i][j];

                if (self < 0) {
                    dp[i][j] = dp[i][j] + Math.abs(dungeon[i][j]);
                } else {
                    if (self >= dp[i][j]) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] -= self;
                    }
                }


            }
        }

        return dp[0][0];

    }

    public int calculateMinimumHP_wrong(int[][] dungeon) {
        int n = dungeon.length, m = dungeon[0].length;
        //1个是minest health, 另一个是累计血量
        int[][][] dp = new int[n][m][2];
        if (dungeon[0][0] > 0) {
            dp[0][0][0] = 0;
            dp[0][0][1] = dungeon[0][0];
        } else {
            dp[0][0][0] = -1 * dungeon[0][0] + 1;
            dp[0][0][1] = 1;
        }

        for (int i = 1; i < n; i++) {
            if (dungeon[0][i] > 0) {

                dp[0][i][0] = dp[0][i - 1][0];
                dp[0][i][1] = dp[0][i - 1][1] + dungeon[0][i];
            } else {
                int temp = dp[0][i - 1][1] + dungeon[0][i];
                if (temp > 0) {
                    dp[0][i][0] = dp[0][i - 1][0];
                    dp[0][i][1] = temp;
                } else {
                    dp[0][i][0] = dp[0][i - 1][0] + Math.abs(temp) + 1;
                    dp[0][i][1] = 1;
                }
            }
        }

        for (int i = 1; i < m; i++) {
            if (dungeon[1][0] > 0) {

                dp[i][0][0] = dp[i - 1][0][0];
                dp[i][0][1] = dp[i - 1][0][1] + dungeon[i][0];
            } else {
                int temp = dp[i - 1][0][1] + dungeon[i][0];
                if (temp > 0) {
                    dp[i][0][0] = dp[i - 1][0][0];
                    dp[i][0][1] = temp;
                } else {
                    dp[i][0][0] = dp[i - 1][0][0] + Math.abs(temp) + 1;
                    dp[i][0][1] = 1;
                }
            }
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (dungeon[i][j] > 0) {
                    if (dp[i - 1][j][0] < dp[i][j - 1][0]) {
                        dp[i][j][0] = dp[i - 1][j][0];
                        dp[i][j][1] = dp[i - 1][j][1] + dungeon[i][j];
                    } else {
                        dp[i][j][0] = dp[i][j - 1][0];
                        dp[i][j][1] = dp[i][j - 1][1] + dungeon[i][j];
                    }
                }
            }
        }
        return 0;
    }
}
