package alg4.com.ch0101;

/**
 * Created by JianZhang on 12/10/16.
 * 1.1.35 Dice simulation. The following code computes the exact probability distribu- tion for the sum of two dice:
 * The value dist[i] is the probability that the dice sum to k. Run experiments to vali- date this calculation
 * simulating N dice throws, keeping track of the frequencies of oc- currence of each value
 * when you compute the sum of two random integers between 1 and 6. How large does N have to be
 * before your empirical results match the exact results to three decimal places?
 */
public class P_35 {
    public static void main(String[] args) {
        int SIDES = 6;
        double[] dist = new double[2 * SIDES + 1];
        for (int i = 1; i <= SIDES; i++){

            for (int j = 1; j <= SIDES; j++)
                dist[i + j] += 1.0;
            for (double d : dist)
                System.out.printf("%2.5f ", d);
            System.out.println();
            System.out.println("------------------------------- ");
        }
        System.out.println("===============================");
        for (double d : dist)
            System.out.printf("%2.5f ", d);
        System.out.println();
        for (int k = 2; k <= 2 * SIDES; k++)
            dist[k] /= 36.0;
        for (double d : dist)
            System.out.printf("%.5f ", d);
    }
}
