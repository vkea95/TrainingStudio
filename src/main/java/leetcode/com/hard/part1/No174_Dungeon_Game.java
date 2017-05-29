package leetcode.com.hard.part1;

/**
 * Created by tclresearchamerica on 5/20/16.
 * ***************************************************************
 * Location:
 * https://leetcode.com/problems/dungeon-game/
 * ***************************************************************
 * Description:
 * The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon.
 * The dungeon consists of M x N rooms laid out in a 2D grid. Our valiant knight (K) was initially positioned
 * in the top-left room and must fight his way through the dungeon to rescue the princess.
 * The knight has an initial healths point represented by a positive integer. If at any point his healths point
 * drops to 0 or below, he dies immediately.
 * Some of the rooms are guarded by demons, so the knight loses healths (negative integers) upon entering these rooms;
 * other rooms are either empty (0's) or contain magic orbs that increase the knight's healths (positive integers).
 * In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in
 * each step.
 * Write a function to determine the knight's minimum initial healths so that he is able to rescue the princess.
 * For example, given the dungeon below, the initial healths of the knight must be at least 7 if he follows
 * the optimal path RIGHT-> RIGHT -> DOWN -> DOWN.
 * -2 (K)	-3	3
 * -5	-10	1
 * 10	30	-5 (P)
 * Notes:
 * The knight's healths has no upper bound.
 * Any room can contain threats or power-ups, even the first room the knight enters and the bottom-right room
 * where the princess is imprisoned.
 * ***************************************************************
 * Analysis:
 * 1. healths,走到这个点(x,y)的最佳路径,就是Max[(x-1,y),(x,y-1)]
 * 2.这里有个大bug,就是如果进某格子之前,是负值和正值,处理的方式是不一致的
 * 3.选择分支的时候,不可以只考虑attack,还要考虑health ------>这个问题并没有想明白,所以failed了,有时间再搞啦
 * ***************************************************************
 * Solution:
 * 网络答案:https://leetcode.com/discuss/103820/1ms-beat-100%25-java-dp-memorization-solution
 * 1.这道题是逆推而成,即从princess开始着手思考,如果这个格子的是正值,那么king的health的最小值就是1,否则,就是1-dungeon[m][n]
 * 2.换成是矩阵中的任意格子,他们的最小值,来源于它的下面或是右侧的格子要求的最小值
 * ***************************************************************
 * Beats:90%
 * ***************************************************************
 */
public class No174_Dungeon_Game {

    public static void main(String[] args) {
        No174_Dungeon_Game obj = new No174_Dungeon_Game();
//        int[][] dungeon = {{-3}, {-7}};
//        int[][] dungeon = {{0, 5}, {-2, -3}};
//        int[][] dungeon = {{1, -2, 3}, {2, -2, -2}};
//        int[][] dungeon = {{0, 0}};
//        int[][] dungeon = {{1, -3, 3}, {0, -2, 0}, {-3, -3, -3}};
//        int[][] dungeon = {{-3, 5}};
//        int[][] dungeon = {{-200}};
        int[][] dungeon = {{0, 0, 0}, {1, 1, -1}};
        obj.calculateMinimumHP(dungeon);
    }

    public int myDfs(int[][] dungeon, int[][] blood, int x, int y) {

        if (blood[x][y] != 0) {
            //避免重复的计算,
            return blood[x][y];
        }

        int min = Integer.MAX_VALUE;


        if (x == dungeon.length - 1 && y == dungeon[0].length - 1) {
            if (dungeon[x][y] <= 0) {
                min = 1 - dungeon[x][y];
            } else {
                min = 1;
            }
            //bug4:最后一个格子的处理比较特殊,它是直接决定min的值,所以可以直接返回,否则经过判断后的值会有问题.
            blood[x][y] = min;
            return min;
        } else {

            if (x < dungeon.length - 1) {
                min = Math.min(myDfs(dungeon, blood, x + 1, y), min);
            }

            if (y < dungeon[0].length - 1) {
                min = Math.min(myDfs(dungeon, blood, x, y + 1), min);
            }
            //bug1:取得临界的格子的所要求的最小值后,就可以给本格子设值了
            //判断标准:如果dungeon大于min(至少是1),那么本格子要求也是1
            //否则,就用min-dungeon[x][y]


            //bug3:要存min的值,否则外面取到的数据有问题.
            //bug4:判断条件要用>=,否则,就会出现1-1=0的情况啦啦啦啦
            min = dungeon[x][y] >= min ? 1 : min - dungeon[x][y];
            blood[x][y] = min;
            return min;
        }

    }

    public int calculateMinimumHP(int[][] dungeon) {
        if (dungeon == null || dungeon.length == 0 || dungeon[0].length == 0) {//[],[[]]
            return 0;
        }
        int[][] flag = new int[dungeon.length][dungeon[0].length];
        int min = myDfs(dungeon, flag, 0, 0);
        return min;
    }


    private int dfs(int[][] dungeon, int[][] flag, int x, int y) {
        if (flag[x][y] != 0) {
            return flag[x][y];
        }
        if (x == dungeon.length - 1 && y == dungeon[0].length - 1) {//The down-right corner
            flag[x][y] = dungeon[x][y] < 0 ? -dungeon[x][y] + 1 : 1; //The minimum is 1
            return flag[x][y];
        }
        int min = Integer.MAX_VALUE;
        //go down
        if (x < dungeon.length - 1) {
            int down = dfs(dungeon, flag, x + 1, y);
            min = min < down ? min : down;
        }
        //go right
        if (y < dungeon[0].length - 1) {
            int right = dfs(dungeon, flag, x, y + 1);
            min = min < right ? min : right;
        }
        if (dungeon[x][y] >= min) {//If min is 6, dungeon[x][y] if 10, then min should be updated to 1
            min = 1;
        } else {//If min is 6, dungeon[x][y] is 3 or -3, then min should be updated to 3 or 9
            min = min - dungeon[x][y];
        }
        flag[x][y] = min;
        return min;
    }


    public int calculateMinimumHP_failed(int[][] dungeon) {

        int m = dungeon.length;
        int n = dungeon[0].length;
        int[][] attacks = new int[m][n];
        int[][] healths = new int[m][n];

        //bug1:对只有一个元素的情况进行处理

        if (m == 1 & n == 1) {
            if (dungeon[0][0] > 0)
                return 1;
            else
                return -1 * dungeon[0][0] + 1;
        }

        if (dungeon[0][0] > 0) {
            healths[0][0] = dungeon[0][0];
        } else {
            attacks[0][0] = dungeon[0][0];
        }

        for (int i = 1; i < m; i++) {
            healths[i][0] = healths[i - 1][0] + dungeon[i][0];
            if (healths[i][0] < 0) {
                attacks[i][0] = attacks[i - 1][0] + healths[i][0];
                healths[i][0] = 0;
            } else {
                attacks[i][0] = attacks[i - 1][0];
            }

        }
        for (int i = 1; i < n; i++) {
//            healths[0][i] = ;
// bug3:healths[][] -->dungeon[][]
            healths[0][i] = healths[0][i - 1] + dungeon[0][i];
            if (healths[0][i] < 0) {
                attacks[0][i] = attacks[0][i - 1] + healths[0][i];
                healths[0][i] = 0;
            } else {
                attacks[0][i] = attacks[0][i - 1];
            }

        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                //bug5:取attacks和healths的时候,不是取得同一个格子,所以需要分治
                int row = 0, col = 0;

                if (attacks[i - 1][j] > attacks[i][j - 1]) {
                    row = i - 1;
                    col = j;
                } else {
                    row = i;
                    col = j - 1;
                }
                if ((healths[i][j - 1] - Math.abs(attacks[i][j - 1])) > (healths[i - 1][j] - Math.abs(attacks[i - 1][j]))) {
                    row = i;
                    col = j - 1;
                }

                healths[i][j] = dungeon[i][j] + healths[row][col];
                int minattacks = attacks[row][col];

                if (healths[i][j] < 0) {
                    attacks[i][j] = minattacks + healths[i][j];
                    healths[i][j] = 0;
                } else {
                    attacks[i][j] = minattacks;
                }
            }
        }
        //bug2:最后一个格子如果是正值的情况,是不该进行加算的,否则,结果不正确
//        int result = healths[m - 1][n - 1] <= 0 ? (-1);

        int result = 0;
        //bug6:最后的情况要求再做个精细的处理
        //如果attack[m][n]=0,那么,这个意义代表,它有可能受到伤害,或者没有,若health[m][n]>0,则首个格子
        if (attacks[m - 1][n - 1] < 0) {
            result = -1 * attacks[m - 1][n - 1] + 1;
        } else {
            result = 1;
        }
        return result;


    }
}
