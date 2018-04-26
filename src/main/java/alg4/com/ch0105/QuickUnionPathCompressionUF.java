package alg4.com.ch0105;

/**
 * Created by JianZhang on 5/28/17.
 */
public class QuickUnionPathCompressionUF extends QuickUnionUF {
    public QuickUnionPathCompressionUF(int N) {
        super(N);
    }

    /*
    * p(0~N-1)所在的分量的标识符
    * */
    public int find(int p) {
        //如果p所在数组位置的value不等于p就继续向树的根部寻址
        int root = p;
        //find the root node
        while (root != id[root]) {
            root = id[root];
            hitArray++;
            hitArray++;

        }
        hitArray++;

        //important!!! compress the path; all the elements point to root node
        while (p != root) {
            int temp = id[p];
            id[p] = root;
            p = temp;
            hitArray++;
            hitArray++;
        }

        return root;
    }

}
