package alg4.com.ch0202;

/**
 * Created by JianZhang on 6/12/17.
 */
public class Jian_MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq; //
    private int N = 0; // pa[0] is useless

    public Jian_MaxPQ(int maxN) {
        pq = (Key[]) new Comparable[maxN + 1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void insert(Key v) {
        pq[++N] = v;//N先加的1
        swim(N);//use index
    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k / 2, k);
            k /= 2;
        }
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }


    public Key delMax() {
        Key max = pq[1];
        exch(1, N--);//change with the last element
        pq[N + 1] = null;//set free the useless object
        sink(1);
        return max;

    }


    private void sink(int k) {
        //remeber the check condition
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(j, j + 1)) j++;//判断条件很重要!!! 要看明白哦 j<N
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }

    }

}
