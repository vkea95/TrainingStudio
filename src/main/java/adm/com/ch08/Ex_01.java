package adm.com.ch08;

/**
 * Created by tclresearchamerica on 11/13/16.
 * Ref: http://www.saschaschnepp.net/2013/11/
 * <p>
 * 增加swap的话,如何处理i-1和j,i和j-1,然后那个表的基础值,就该从s[i-2]和t[j-2]开始计算
 */
public class Ex_01 extends EditDistance {

    public static void main(String[] args) {
        Ex_01 ex01 = new Ex_01();
        String s = "setve";
        String t = "steve";
        int i = s.length();
        int j = t.length();
        ex01.compareString(s, t);

        ex01.reconstructPath(s, t, i, j);
    }

    public final int SWAP = 3;

    public int compareString(String s, String t) {

        int maxLen = Math.max(s.length(), t.length()) + 1;
        MAXLEN = maxLen;
        m = new DpCell[maxLen + 1][maxLen + 1];
        super.initDpTable();

        int i, j, k; // coutner
        int[] operation = new int[4];

        for (i = 0; i < maxLen; i++) {
            initRow(i);
            initCol(i);
        }

        for (i = 1; i < s.length(); i++) {
            for (j = 1; j < t.length(); j++) {
                //bug2:字符串下标要进行减一操作呢
                operation[MATCH] = m[i - 1][j - 1].cost + match(s.charAt(i - 1), t.charAt(j - 1));
                operation[INSERT] = m[i][j - 1].cost + indel(t.charAt(j));
                operation[DELETE] = m[i - 1][j].cost + indel(s.charAt(i));
                if ((i - 2 >= 1) && (j - 2 >= 1)) {
                    //bug2:字符串下标要进行减一操作呢,再往前一个的话是-2操作呢
                    operation[SWAP] = m[i - 2][j - 2].cost + swap(s.charAt(i - 2), s.charAt(i - 1), t.charAt(j - 2), t.charAt(j - 1));

                } else {

                    operation[SWAP] = MAXLEN;
                }
                m[i][j].cost = operation[MATCH];
                m[i][j].parent = MATCH; //设初期值 & reconstruct path时使用
                for (k = INSERT; k <= SWAP; k++) {
                    if (operation[k] < m[i][j].cost) {
                        m[i][j].cost = operation[k];
                        m[i][j].parent = k; //reconstruct path时使用
                        if (k == SWAP) {
                            continue;
                        }
                    }
                }

            }
        }

        int[] index = new int[2];
        getGoalCell(s, t, index);
        i = index[0];
        j = index[1];
        return m[i][j].cost;

    }

    protected int swap(char c1, char c2, char d1, char d2) {
        if (c1 == d2 && c2 == d1) {
            return 1;
        } else {
            return MAXLEN;
        }

    }

    /*
    打印修改路径S:replace, I:insert, D:delete M:match
     */
    public void reconstructPath(String s, String t, int i, int j) {
        //boundary check
        if (m[i][j].parent == -1)
            return;

        if (m[i][j].parent == SWAP) {
            reconstructPath(s, t, i - 2, j - 2);
            swapOut(s, i);

        } else if (m[i][j].parent == MATCH) {
            reconstructPath(s, t, i - 1, j - 1);
            matchOut(s, t, i, j);
        } else if (m[i][j].parent == INSERT) {
            reconstructPath(s, t, i, j - 1);
            insertOut(t, j);
        } else {
            //m[i][j].parent == DELETE
            reconstructPath(s, t, i - 1, j);
            deleteOut(s, i);

        }

    }

    protected void swapOut(String s, int j) {
        System.out.print("SS");
    }
}
