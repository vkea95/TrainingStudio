package alg4.com.ch0105;

/**
 * Created by JianZhang on 5/28/17.
 */
public class WeihtedQuickUnionPathCompressionUF extends WeightedQuickUnionUF {
    public WeihtedQuickUnionPathCompressionUF(int N) {
        super(N);
    }


    /*
    * p(0~N-1)所在的分量的标识符
    * */
    public int find(int p) {
        validate(p);
        //如果p所在数组位置的value不等于p就继续向树的根部寻址
        int root = p;
        while (root != parent[root]) {
            root = parent[root];
            hitArray++;
            hitArray++;

        }
        hitArray++;

        while (p != root) {
            int temp = parent[p];
            parent[p] = root;
            p = temp;
            hitArray++;
            hitArray++;
        }
        return root;
    }
}
