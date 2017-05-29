package alg4.com.ch0101;

import java.util.Arrays;

/**
 * Created by JianZhang on 12/4/16.
 */
public class Problem027A extends Problem027 {
    public static int count = 0;

    public static void main(String[] args) {
        System.out.println(Problem027A.binomial(20, 10, 0.5));
        System.out.printf("count:%5d", count);
    }

    public static double binomial(int N, int k, double p) {
        //tips:还是要将这个下标+1,这样好操作呢
        double[][] doubles = new double[N + 1][k + 1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= k; j++)
                doubles[i][j] = -1;
        }

        return binomial(doubles, N, k, p);
    }

    public static double binomial(double[][] doubles, int N, int k, double p) {
        if (N == 0 && k == 0) return 1.0;// --> Bug
        if ((N < 0) || (k < 0)) return 0.0;  // -->bug
        if (doubles[N][k] == -1) {
            doubles[N][k] = (1.0 - p) * binomial(N - 1, k, p) + p * binomial(N - 1, k - 1, p);
            count++;
        }
        return doubles[N][k];


    }
    //静态变量和静态方法说继承并不确切,taeny是属于累的方法月变量
    /*
     1.静态变量与静态方法说继承并不确切，静态方法与变量是属于类的方法与变量。而子类也属于超类，比如说Manage extends Employee，
     则Manage也是一个Employee，所以子类能够调用属于超类的静态变量和方法。注意，子类调用的其实就是超类的静态方法和变量，
     而不是继承自超类的静态方法与变量。但是如果子类中有同名的静态方法与变量，这时候调用的就是子类本身的，
     因为子类的静态变量与静态方法会隐藏父类的静态方法和变量。

       2.如果子类中没有定义同名的变量和方法，那么调用 "子类名.静态方法/变量"调用的是父类的方法及变量

       3,.如果子类中只定义了同名静态变量，而没有定义与父类同名静态方法，则调用”子类名.静态方法"时，调用的是父类的静态方法，
       静态方法中的静态变量也是父类的 (如程序中注[1])

       4.如果子类中既定义了与父类同名的静态变量，也定义了与父类同名的静态方法，这时候调用”子类名.静态方法"时，完全与父类无关，
       里面的静态变量也是子类的(如程序中注[2])
    * */

    class Binomial {
        public int N;
        public int k;
        public double p;

        public Binomial(int N, int k, int p) {

        }

    }
}
