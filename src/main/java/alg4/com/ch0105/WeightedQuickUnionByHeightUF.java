package alg4.com.ch0105;

/**
 * Created by JianZhang on 5/29/17.
 * 高度的计算,就是放在union,也就是在划线的时候,完成了树的构建,其他的算法也是类似。
 * 唯一注意的是高度建树的话,如果是低的树挂在高的树下height不进行自加
 */
public class WeightedQuickUnionByHeightUF extends WeightedQuickUnionUF {

    private int[] height = null;

    public WeightedQuickUnionByHeightUF(int N) {
        super(N);
        height = new int[N];

    }

    private void initializeData() {
        for (int i = 0; i < parent.length; i++) {
//            设置高度
            height[i] = 0;
        }
    }

    /*
    * 在p & q间添加一条连接
    * */
    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);
        //bug2: forget to check the i ==j
        if (i == j) return;
        if (height[i] < height[j]) {
            parent[i] = j;
            //bug1:高度不必增加,因为矮树总是挂在高树的下面
//            height[j] += size[i];
        } else if (height[i] > height[j]) {
            //bug1:高度不必增加,因为矮树总是挂在高树的下面
            parent[j] = i;
        } else {
            parent[j] = i;
            height[i]++;
        }
        hitArray++;
        //bug1:
        count--;
    }
}
