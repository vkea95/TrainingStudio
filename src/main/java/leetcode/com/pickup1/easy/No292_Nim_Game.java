package leetcode.com.pickup1.easy;

/**
 * Created by tclresearchamerica on 7/9/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/nim-game/
 * ****************************************************
 * Description:
 * You are playing the following Nim Game with your friend: There is a heap of stones on the table, each time one of
 * you take turns to remove 1 to 3 stones. The one who removes the last stone will be the winner. You will take the
 * first turn to remove the stones.
 * <p>
 * Both of you are very clever and have optimal strategies for the game. Write a function to determine whether you can
 * win the game given the number of stones in the heap.
 * <p>
 * For example, if there are 4 stones in the heap, then you will never win the game: no matter 1, 2, or 3 stones
 * you remove, the last stone will always be removed by your friend.
 * ****************************************************
 * Thoughts:
 * 1.来自于游戏的算法题,那么就是说首先要把游戏破解掉,这个游戏肯定可以有个必胜的条件啦
 * 但是空想并没有找到答案.那么推导呗
 * A:先手 B:后手
 * 1~3:A
 * 4:B
 * 5:A
 * 6:A
 * 7:A
 * 8:
 * --->必须要在纸面上推导,所以发现只要能被4整除,就是后手赢,估计这个换成其他玩法,规律类似.
 * ****************************************************
 * Time: 20mins
 * Beat: 2%
 * Bug: 0
 * ****************************************************
 * Hindsight:
 * 还是要在纸面上进行推导,而且要保证一次性推导成功,才不会误导结论,误导思路,才能节省时间哦
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No292_Nim_Game {
    public boolean canWinNim(int n) {
        return n % 4 > 0;
    }

}
