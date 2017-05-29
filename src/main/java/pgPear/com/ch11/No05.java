package pgPear.com.ch11;

/**
 * Created by tclresearchamerica on 10/2/16.
 * 排序可变长的为字符串,要求排序时间约位字符串的长度之和成正比
 */
public class No05 {

    private void bSort(String[] x, int l, int u, int depth) {
        if (l >= u) return;
        for (int i = l; i <= u; i++) {
            if (x[i].length() < depth)
                swap(x, i, l++);
        }
        int m = l;
        for (int i = l; i <= u; i++) {
//            if x[i].bit
            swap(x, i, m++);
        }
        bSort(x, l, m - 1, depth + 1);
        bSort(x, m, u, depth + 1);
    }

    private void swap(String[] x, int a, int b) {
        String temp = x[a];
        x[a] = x[b];
        x[b] = temp;
    }
}
