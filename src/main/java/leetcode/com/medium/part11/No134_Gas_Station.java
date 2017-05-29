package leetcode.com.medium.part11;

/**
 * Created by tclresearchamerica on 5/16/16.
 * **************************************************************
 * Location:
 * https://leetcode.com/problems/gas-station/
 * **************************************************************
 * Description:
 * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
 * <p>
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next
 * station (i+1). You begin the journey with an empty tank at one of the gas stations.
 * <p>
 * Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.
 * **************************************************************
 * Analysis:
 * 1.这个就是规划出来汽车从哪个油站出发可以走完一圈路程,如果没有这样的油站,则返回-1;
 * 2.思路:
 * 2.1 用sum(cost[i]-gas[i])<0,肯定就不能绕场一周
 * 2.2 或者是说, 满足(gas[i] - cost[i]) >=0 的集合里面, 下标的连续序列, 要从哪里开始是最好的?
 * 2.3 感觉可以用动态规划, 数据结构用哪个都不知道呢
 * 2.4 其实如果总的油料数量 大于 消耗的数量,就可以保证tank可以绕场一周,因为造不出这样的图:即切断两个点之间的油料供应,然后
 * **************************************************************
 * **************************************************************
 * **************************************************************
 * **************************************************************
 */
public class No134_Gas_Station {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int index = 0;

        int gasLeft = 0;
        int sum = 0;
        int diff = 0;
        for (int i = 0; i < gas.length; i++) {
            diff = gas[i] - cost[i];
            sum += diff;
            gasLeft += diff;
            if (gasLeft < 0) {
                gasLeft = 0;
                index = i;
            }
        }
        return sum >= 0 ? index + 1 : -1;
    }
}
