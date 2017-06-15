package alg4.com.ch0203;

/**
 * Created by JianZhang on 6/15/17.
 * Dijkstra’s solution to this problem leads to the remarkably simple partition code shown on the next page.
 * It is based on a single left-to-right pass through the array that maintains a pointer lt such that
 * a[lo..lt-1] is less than v,
 * a pointer gt such that a[gt+1, hi] is greater than v,
 * and a pointer i such that a[lt..i-1] are equal to v and
 * a[i..gt]are not yet examined.
 * Starting with i equal to lo, we process a[i] using the 3-way comparison given us by the
 * Comparable interface (instead of using less()) to directly handle the three possible cases:
 * ■ a[i] less than v: exchange a[lt] with a[i] and increment both lt and i
 * ■ a[i] greater than v: exchange a[i] with a[gt] and decrement gt
 * ■ a[i] equal to v: increment i
 */
public class Quick3way extends Quick {
    protected void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int lt = lo, i = lo + 1, gt = hi;
        Comparable v = a[lo];

        while (i <= gt) {// a[i..gt]are not yet examined.
            int cmp = a[i].compareTo(v);
            if (cmp < 0) exch(a, lt++, i++);//已知a[i]<v,则 交换
            else if (cmp > 0) exch(a, i, gt--);//此时i不变,方便下次a[i]和v进行比较
            else i++;//a[i]和一样大,所以i++
        }
        //a[lt..i-1] are equal to v s
        sort(a, lo, lt - 1);//a[lo..lt-1] is less than v 对比v小的再排序
        sort(a, gt + 1, hi);//a pointer gt such that a[gt+1, hi] is greater than v,对比v大的再排序

    }
}
