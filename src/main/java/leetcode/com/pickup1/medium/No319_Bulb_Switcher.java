package leetcode.com.pickup1.medium;

/**
 * Created by tclresearchamerica on 9/29/16.
 * **************************************************************
 * Location:
 * https://leetcode.com/problems/bulb-switcher/
 * **************************************************************
 * Description:
 * There are n bulbs that are initially off. You first turn on all the bulbs. Then, you turn off every second bulb.
 * On the third round, you toggle every third bulb (turning on if it's off or turning off if it's on).
 * For the ith round, you toggle every i bulb. For the nth round, you only toggle the last bulb.
 * Find how many bulbs are on after n rounds.
 * <p>
 * Example:
 * <p>
 * Given n = 3.
 * <p>
 * At first, the three bulbs are [off, off, off].
 * After first round, the three bulbs are [on, on, on].
 * After second round, the three bulbs are [on, off, on].
 * After third round, the three bulbs are [on, off, off].
 * <p>
 * So you should return 1, because there is only one bulb is on.
 * **************************************************************
 * Ref:
 * https://segmentfault.com/a/1190000004315488
 * 方法2：我们可以很容易地想到，最后状态是on的灯泡代表的标号，说明只有奇数个约数。
 * eg:在N足够大的情况下：
 * 1号：因为只为1的倍数，只switch一次，on
 * 2号：为1,2的倍数，switch两次，off
 * 3号：为1,3的倍数，switch两次，off
 * 4号：为1,2,4的倍数，switch三次，on
 * -->
 * 所以就是说某个球是几的倍数,就会被在第几次的时候,给toggle,那么如果toggle奇数次,就是on,偶数次就是off
 * 所以只要将n开平方,求下限即可啦
 * **************************************************************
 * **************************************************************
 * **************************************************************
 */
public class No319_Bulb_Switcher {
    public int bulbSwitch(int n) {
        return (int) Math.sqrt(n);
    }
}
