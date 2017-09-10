package leetcode.com.tag.sort;

import java.util.Arrays;

/**
 * Created by JianZhang on 9/10/17.
 * Given an array of citations (each citation is a non-negative integer) of a researcher,
 * write a function to compute the researcher's h-index.
 * <p>
 * According to the definition of h-index on Wikipedia:
 * "A scientist has index h if h of his/her N papers have at least h citations each,
 * and the other N − h papers have no more than h citations each."
 * <p>
 * For example, given citations = [3, 0, 6, 1, 5],
 * which means the researcher has 5 papers in total and each of them had received 3, 0, 6, 1, 5 citations respectively.
 * Since the researcher has 3 papers with at least 3 citations each and the remaining two with no more than 3 citations each, his h-index is 3.
 * <p>
 * Note: If there are several possible values for h, the maximum one is taken as the h-index.
 * Solution:
 * 1.答案h指的是 paper数量,而不是里面的citation数量!!!例子给的不好,把citation改成4,就比较容易理解了
 */
public class No274_H_Index {
    public int hIndex(int[] citations) {
        int result = 0;
        if (citations == null || citations.length == 0) return result;

        //sort
        Arrays.sort(citations);
        int N = citations.length;
        for (int i = N - 1; i >= 0 && citations[i] >= N - i; i--) {
            result = N - i;
        }

        return result;
    }
}
