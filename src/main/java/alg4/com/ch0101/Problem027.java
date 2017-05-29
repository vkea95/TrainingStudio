package alg4.com.ch0101;
/**
 * Created by JianZhang on 12/4/16.
 */
public class Problem027 {
    public static int count = 0;

    public static void main(String[] args) {
        System.out.println(Problem027.binomial(20, 10, 0.5));
        System.out.printf("count:%5d", count);
    }

    public static double binomial(int N, int k, double p) {
        count++;
        if ((N == 0) || (k < 0)) return 1.0;
        return (1.0 - p) * binomial(N - 1, k, p) + p * binomial(N - 1, k - 1, p);

    }

}
