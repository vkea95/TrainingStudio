package adm.com.ch08;

/**
 * Created by tclresearchamerica on 11/11/16.
 */
public class PartitionProblem {

    public static void main(String[] args) {
        int[] s = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        int k = 3;

        PartitionProblem partitionProblem = new PartitionProblem();
        partitionProblem.partiion(s, 8, k);
    }


    public void partiion(int s[], int n, int k) {
        int MAXN = n;

        int[][] m = new int[MAXN + 1][k + 1];/*DP table for value*/
        int[][] d = new int[MAXN + 1][k + 1];/*DP table for dividers*/
        int[] p = new int[MAXN + 1];/*prefix sums array*/
        int cost;/*test split cost*/
        int i, j, x;/*counter*/

        p[0] = 0;
        for (i = 1; i <= n; i++) p[i] = p[i - 1] + s[i];

        for (i = 1; i <= n; i++) m[i][1] = p[i]; /*initial boundaries*/
        for (j = 1; j <= k; j++) m[1][j] = s[1];

        for (i = 2; i <= n; i++) {
            for (j = 2; j <= k; j++) {
                m[i][j] = Integer.MAX_VALUE; //设最大值
                for (x = 1; x <= (i - 1); x++) {
                    cost = Integer.max(m[x][j - 1], p[i] - p[x]);
                    if (m[i][j] > cost) {
                        m[i][j] = cost;
                        d[i][j] = x;
                    }
                }
            }
        }

//        System.out.print("over");
        reconstruct_partition(s, d, n, k);

    }


    public void reconstruct_partition(int[] s, int[][] d, int n, int k) {
        if (k == 1) {
            print_books(s, 1, n);//k =1 时,从第一个打到最后一个
        } else {
            reconstruct_partition(s, d, d[n][k], k - 1);//k !=1 时, n被 d[n][k] 也就是divider给取代
            print_books(s, d[n][k] + 1, n);//然后,本次的打印从d[n][k]+1开始计算啦
        }
    }

    public void print_books(int s[], int start, int end) {
        int i;
        for (i = start; i <= end; i++) {
            System.out.print(" " + s[i]);
        }
        System.out.println();
    }

}
