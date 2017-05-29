package leetcode.com.pickup1.medium;

/**
 * Created by tclresearchamerica on 6/1/16.
 * *******************************************
 * Location:
 * https://leetcode.com/problems/h-index-ii/
 * *******************************************
 * Description:
 * Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to
 * compute the researcher's h-index.
 * <p>
 * According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have
 * at least h citations each, and the other N − h papers have no more than h citations each."
 * <p>
 * For example, given citations = [3, 0, 6, 1, 5], which means the researcher has 5 papers in total and each of them
 * had received 3, 0, 6, 1, 5 citations respectively. Since the researcher has 3 papers with at least 3 citations each
 * and the remaining two with no more than 3 citations each, his h-index is 3.
 * <p>
 * Note: If there are several possible values for h, the maximum one is taken as the h-index.
 * *******************************************
 * *******************************************
 * *******************************************
 */
public class No275_H_Index_II {
    public int hIndex(int[] citations) {

        int sum = 0;
        for (int i = citations.length - 1; i >= 0 && citations[i] >= citations.length - i; i--) {
            sum = citations.length - i;
        }
        return sum;

    }
}
