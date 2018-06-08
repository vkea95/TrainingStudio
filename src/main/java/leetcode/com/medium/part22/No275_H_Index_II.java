package leetcode.com.medium.part22;

/**
 * Created by tclresearchamerica on 5/2/16.
 * *******************************************
 * Location:
 * https://leetcode.com/problems/h-index-ii/
 * *******************************************
 * Description:
 * What if the citations array is sorted in ascending order? Could you optimize your algorithm?
 * <p>
 * Show Hint
 * <p>
 * *******************************************
 * Analysis:
 * 同I是一样一样的
 */
public class No275_H_Index_II {
    public int hIndex_2(int[] citations) {
        int h = 0;
        if (citations == null) return h;


        for (int i = citations.length - 1; i >= 0 && citations[i] >= citations.length - i; i--) {
            h = citations.length - i;
        }

        return h;

    }

    public int hIndex(int[] citations) {

        int n = citations.length;
        int left = 0;
        int right = n - 1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if ((n - mid) == citations[mid]) {
                return n - mid;
            } else if (citations[mid] > (n - mid)) {
                right = mid - 1; // remove mid

            } else {
                left = mid + 1; // remove mid
            }
        }
        return n - left;

    }
}
