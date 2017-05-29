package leetcode.com.pickup1.easy;

/**
 * Created by tclresearchamerica on 6/3/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/rectangle-area/
 * ****************************************************
 * bug1:
 * 跟着给的示例,进行思考,定向假定某个矩形的边肯定处于另一个矩形内部,所以导致计算不正确...
 * ****************************************************
 * time: 30'   Beats:50% 4ms
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No223_Rectangle_Area {

    public static void main(String[] args) {
        No223_Rectangle_Area obj = new No223_Rectangle_Area();
        obj.computeArea(-2, -2, 2, 2, -3, -3, 3, 1);
    }

    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int duplicateWidth = 0, duplicateHeight = 0;
        if (!(E > C || F > D || A > G || B > H)) {
            duplicateWidth = Math.min(C, G) - Math.max(A, E);
            duplicateHeight = Math.min(D, H) - Math.max(B, F);
        }
        return (C - A) * (D - B) + (G - E) * (H - F) - duplicateWidth * duplicateHeight;

    }

    public int computeArea_failed(int A, int B, int C, int D, int E, int F, int G, int H) {
        int duplicateWidth = 0, duplicateHeight = 0;
        if (E >= A && E <= C) {
            duplicateWidth = Math.min(C, G) - E;
        } else if (G >= A && G <= C) {

            duplicateWidth = G - Math.max(A, E);
        }
        if (H >= B && H <= D) {
            duplicateHeight = H - Math.max(B, F);
        } else if (F >= B && F <= D) {
            duplicateHeight = Math.min(D, H) - F;
        }
        return (C - A) * (D - B) + (G - E) * (H - F) - duplicateWidth * duplicateHeight;
    }
}
