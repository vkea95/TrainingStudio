package alg4.com.ch0105;

/**
 * Created by JianZhang on 5/28/17.
 */
public class WeightedQuickUnionUF {

    protected int[] parent = null; // parent[i] = parent of i
    protected int count;   // size[i] = number of sites in subtree rooted at i
    protected int[] size = null;//各个根节点所对应的分量的大小:节点数量
    protected int hitArray = 0;

    public WeightedQuickUnionUF(int N) {
        //initialzie cout = N
        count = N;
        parent = new int[N];
        size = new int[N];
        initializeData();
    }

    private void initializeData() {
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }


    /*
    * 在p & q间添加一条连接
    * */
    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);
        //bug2: forget to check the i ==j
        if (i == j) return;
        if (size[i] < size[j]) {
            parent[i] = j;
            size[j] += size[i];
        } else {
            parent[j] = i;
            size[j] += size[i];
        }
        hitArray++;
        //bug1:
        count--;
    }

    /**
     * Returns the component identifier for the component containing site {@code p}.
     *
     * @param  p the integer representing one site
     * @return the component identifier for the component containing site {@code p}
     * @throws IndexOutOfBoundsException unless {@code 0 <= p < n}
     */
    public int find(int p) {
        validate(p);
        //如果p所在数组位置的value不等于p就继续向树的根部寻址
        while (p != parent[p]) {
            p = parent[p];
            hitArray++;
            hitArray++;

        }
        hitArray++;

        return p;
    }

    /**
     * Returns true if the the two sites are in the same component.
     *
     * @param  p the integer representing one site
     * @param  q the integer representing the other site
     * @return {@code true} if the two sites {@code p} and {@code q} are in the same component;
     *         {@code false} otherwise
     * @throws IndexOutOfBoundsException unless
     *         both {@code 0 <= p < n} and {@code 0 <= q < n}
     */
    public boolean connectetd(int p, int q) {
        return find(p) == find(q);
    }

    /*
    * 联通分量的数量
    * */
    public int count() {
        return count;
    }
    protected void validate(int p) {
        int n = parent.length;
        if (p < 0 || p >= n) {
            throw new IndexOutOfBoundsException("index " + p + " is not between 0 and " + (n-1));
        }
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("WeightedQuickUnionUF{ count = ");
        sb.append(count);
        sb.append(" parent:{");
        for (int i = 0; i < parent.length; i++) {
            sb.append(parent[i]);
            sb.append(", ");
        }
        sb.append("}");
        sb.append(" size:{");

        for (int i = 0; i < parent.length; i++) {
            sb.append(size[i]);
            sb.append(", ");
        }
        sb.append("}}");

        sb.append(" HitArray: " + hitArray);

        return sb.toString();
    }
}
