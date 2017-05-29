package leetcode.com.pickup1.medium;

/**
 * Created by tclresearchamerica on 7/16/16.
 * Location:
 * https://leetcode.com/problems/water-and-jug-problem/
 * ****************************************************
 * Description:
 * You are given two jugs with capacities x and y litres. There is an infinite amount of water supply available.
 * You need to determine whether it is possible to measure exactly z litres using these two jugs.
 * If z liters of water is measurable, you must have z liters of water contained within one or both buckets by the end.
 * Operations allowed:
 * Fill any of the jugs completely with water.
 * Empty any of the jugs.
 * Pour water from one jug into another till the other jug is completely full or the first jug itself is empty.
 * Example 1: (From the famous "Die Hard" example)
 * Input: x = 3, y = 5, z = 4
 * Output: True
 * Example 2:
 * Input: x = 2, y = 6, z = 5
 * Output: False
 * ****************************************************
 * Thought:
 * 1.这是上周做过的题目,但是依然没有明白怎么搞出答案来啊,
 * 2.首先,要是x+y<z肯定就不灵了
 * 3.其次,只要任意一个和z匹配,或是x+y=z就可以解决问题啦
 * 4.就是寻找最大公约数,用的是辗转相除法
 * 其最大公约数也是相同的，则有f（x, y）= f（y, x % y）（y > 0），如此便可把原问题转化为求两个更小数的最大公约数，
 * 直到其中一个数为0，剩下的另外一个数就是两者最大的公约数。
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No365_Water_and_Jug_Problem {
    public static void main(String[] args) {
        No365_Water_and_Jug_Problem obj = new No365_Water_and_Jug_Problem();
        System.out.println(obj.getGCD(3, 5));

        System.out.println(obj.canMeasureWater(7, 8, 6));
    }

    public boolean canMeasureWater(int x, int y, int z) {
        if (x + y < z) return false;
        if (x + y == z || x == z || y == z) return true;

        return z % getGCD(x, y) == 0;
    }

    public int getGCD(int x, int y) {
        while (y != 0) {
            int temp = y;
            y = x % y;
            x = temp;
        }
        return x;
    }
}
