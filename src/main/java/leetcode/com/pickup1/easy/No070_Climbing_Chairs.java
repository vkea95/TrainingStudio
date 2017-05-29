package leetcode.com.pickup1.easy;

/**
 * Created by jason on 2016/7/25.
 * 猜测和奇偶数有关系
 * n=1 1
 * n=2 2
 * n=3 3
 * n=4
 * 1 2 3 4
 * 1 3 4
 * 1 2 4
 * 2 4
 * 2 3 4
 * 所以就是f[n]=f[n-1]+f[n-2]
 */
public class No070_Climbing_Chairs {
    public int climbStairs(int n) {
        if (n==0) return 0;
        if (n==1) return 1;
        if (n==2) return 2;
        int[] solutions=new int[n+1];
        //bug1:没有对前三个元素进行初始化
        solutions[1]=1;
        solutions[2]=2;
        for (int i=3;i<=n;i++){
            solutions[i]=solutions[i-1]+solutions[i-2];
        }
        return solutions[n];
    }
}
