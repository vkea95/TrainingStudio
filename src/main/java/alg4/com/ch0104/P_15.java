package alg4.com.ch0104;

/**
 * Created by JianZhang on 12/20/16.
 * Faster 3-sum. As a warmup, develop an implementation TwoSumFaster that
 * uses a linear algorithm to count the pairs that sum to zero after the array is sorted (in- stead of
 * the binary-search-based linearithmic algorithm). Then apply a similar idea to develop a quadratic
 * algorithm for the 3-sum problem.
 */
public class P_15 {
}

class TwoSumFaster {
    /*
    make sure a & b are both sorted
    逆序寻找和a的元素相配对的元素然后返回,若没有则根据-[a]下限,判断是否跳出内部循环。
     */
    public static void findPair(int[] a, int[] b) {
        int count = 0;
        int i = 0;
        int j = b.length - 1;
        for (; i < a.length; i++) {
            for (; j >= 0; j--) {
                if (b[j] == -a[i]) {
                    count++;
                    break;
                } else if (b[j] < -a[i]) {
                    break;
                }
            }
        }
    }
}