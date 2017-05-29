package leetcode.com.easy.part2;

/**
 * Created by tclresearchamerica on 5/3/16.
 * *******************************************
 * Location:
 * https://leetcode.com/problems/nim-game/
 * *******************************************
 * Description:
 * You are playing the following Nim Game with your friend: There is a heap of stones on the table, each time one of
 * you take turns to remove 1 to 3 stones. The one who removes the last stone will be the winner. You will take
 * the first turn to remove the stones.
 * <p>
 * Both of you are very clever and have optimal strategies for the game. Write a function to determine whether you can
 * win the game given the number of stones in the heap.
 * <p>
 * For example, if there are 4 stones in the heap, then you will never win the game: no matter 1, 2, or 3 stones
 * you remove, the last stone will always be removed by your friend.
 * *******************************************
 * Analysis:
 * 有意思的题目,以前曾经想过这个游戏,但是无解.
 * 看来要找到解法才可以呢.
 * 原来是数学归纳法:
 * 1--3 win
 * 4    lose
 * 5-7  win
 * 8    lose
 * 9-11 win
 * ...
 * 所以就是求对4的余数是否比零大
 */
public class No292_Nim_Game {
    public boolean canWinNim(int n) {
        return n % 4 > 0;
    }
}
