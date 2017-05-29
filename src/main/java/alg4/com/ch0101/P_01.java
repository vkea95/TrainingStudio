package alg4.com.ch0101;

/**
 * Created by JianZhang on 12/4/16.
 */
public class P_01 {

    public static void main(String[] args) {
        P_01.pro_006();
        P_01.pro_007();

    }

    public static void pro_006() {
        int f = 0;
        int g = 1;
        for (int i = 0; i <= 15; i++) {
            System.out.println(f);
            f += g;
            g = f - g;
        }
    }

    public static void pro_007() {
        double t = 9.0;

        while (Math.abs(t - 9.0 / t) > .001) {
            t = (9.0 / t + t) / 2.0;
        }
        System.out.printf("%.5f", t);
    }
}
