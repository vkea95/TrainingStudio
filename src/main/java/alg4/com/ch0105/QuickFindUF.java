package alg4.com.ch0105;

/**
 * Created by JianZhang on 5/28/17.
 */
public class QuickFindUF {

    private int[] id = null;
    private int count;
    private int hitArray = 0;

    public QuickFindUF(int N) {
        //initialzie cout = N
        count = N;
        id = new int[N];
        initializeData();
    }

    private void initializeData() {
        for (int i = 0; i < id.length; i++) {
            id[i] = i;
        }
    }


    /*
    * 在p & q间添加一条连接
    * */
    public void union(int p, int q) {
        int pID = find(p);
        int qID = find(q);
        //bug2: forget to check the i ==j
        if (pID == qID) return;

        for (int i = 0; i < id.length; i++) {
            if (id[i] == pID) {
                id[i] = qID;

                hitArray++;
            }

            hitArray++;
        }
        //bug1:
        count--;
    }

    /*
    * p(0~N-1)所在的分量的标识符
    * */
    public int find(int p) {
        //如果p所在数组位置的value不等于p就继续向树的根部寻址


        return id[p];
    }

    /*
    * 如果p & q存在于同一个分量中则返回true
    * */
    public boolean connectetd(int p, int q) {
        return find(p) == find(q);
    }

    /*
    * 联通分量的数量
    * */
    public int count() {
        return count;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("WeightedQuickUnionUF{ count = ");
        sb.append(count);
        sb.append(" parent:{");
        for (int i = 0; i < id.length; i++) {
            sb.append(id[i]);
            sb.append(", ");
        }
        sb.append("}}");

        sb.append(" HitArray: " + hitArray);

        return sb.toString();
    }
}
