package adm.com.ch08;

/**
 * Created by tclresearchamerica on 11/14/16.
 * Longest Common Substring based on LCS
 * s: photograph
 * t: tomography
 * ->LCS: ography
 * 所以相当于,寻找连续含有M最长的子字符串,
 * 参考网上的一个答案,
 * 意识到,可能不需要考虑insert/delete这2个操作,只是留下主干就好,那么就是说
 */
public class Ex_03 extends LongestCommonSubsequence {

    public static void main(String[] args) {
        Ex_03 ex03 = new Ex_03();
        String s = "tomograph";
        String t = "phootograph";


        int i = s.length();
        int j = t.length();
        System.out.println("Lowest cost: " + ex03.compareString(s, t));
        //bug1:只需要将s和t的最后index传入method就好
        ex03.reconstructPath(s, t, i, j);

    }

    /*
    将替换从match中区分出来
     */
    protected int substitute(char c1, char c2) {
        return c1 == c2 ? 0 : 1;
    }

    /*
    因为父类中已经标志出了M, 那么只要寻找最长的M就可以啦
     */
    public void reconstructPath(String s, String t, int i, int j) {
        //boundary check
        System.out.println("i: " + i + " j: " + j);
        if (m[i][j].parent == -1)
            return;

        if (m[i][j].parent == MATCH) {
            reconstructPath(s, t, i - 1, j - 1);
            matchOut(s, t, i, j);
        } else if (m[i][j].parent == SUBSTITUTE) {
            reconstructPath(s, t, i - 1, j - 1);
            substituteOut(s, t, i, j);
        } else if (m[i][j].parent == INSERT) {
            reconstructPath(s, t, i, j - 1);
            insertOut(t, j);
        } else {
            //m[i][j].parent == DELETE
            reconstructPath(s, t, i - 1, j);
            deleteOut(s, i);

        }

    }

    public final int MATCH = 0;
    public final int MATCH_COST = 0;
    public final int SUBSTITUTE = 1;
    public final int INSERT = 2;
    public final int DELETE = 3;


    public int compareString(String s, String t) {

        int maxLen = Math.max(s.length(), t.length()) + 1;
        MAXLEN = maxLen;
        m = new DpCell[maxLen + 1][maxLen + 1];
        initDpTable();

        int i, j, k; // coutner
        int[] operation = new int[4];

        for (i = 0; i < maxLen; i++) {
            initRow(i);
            initCol(i);
        }

        for (i = 1; i < s.length(); i++) {
            for (j = 1; j < t.length(); j++) {
                //bug2:字符串的下标该是从0开始呢
                operation[MATCH] = m[i - 1][j - 1].cost + match(s.charAt(i - 1), t.charAt(j - 1));
                operation[INSERT] = m[i][j - 1].cost + indel(t.charAt(j));
                operation[DELETE] = m[i - 1][j].cost + indel(s.charAt(i));
                operation[SUBSTITUTE] = m[i - 1][j - 1].cost + substitute(s.charAt(i - 1), t.charAt(j - 1));

                m[i][j].cost = operation[MATCH];
                m[i][j].parent = MATCH; //设初期值 & reconstruct path时使用
                for (k = SUBSTITUTE; k <= DELETE; k++) {
                    if (operation[k] < m[i][j].cost) {
                        m[i][j].cost = operation[k];
                        m[i][j].parent = k; //reconstruct path时使用
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

    protected void substituteOut(String s, String t, int i, int j) {
        System.out.print("S");
    }

}

/*
http://algorithms.tutorialhorizon.com/dynamic-programming-longest-common-substring/
 */
class LongestCommonSubString {

    public static int find(char[] A, char[] B) {
        int[][] LCS = new int[A.length + 1][B.length + 1];
        // if A is null then LCS of A, B =0
        for (int i = 0; i <= B.length; i++) {
            LCS[0][i] = 0;
        }

        // if B is null then LCS of A, B =0
        for (int i = 0; i <= A.length; i++) {
            LCS[i][0] = 0;
        }

        //fill the rest of the matrix
        for (int i = 1; i <= A.length; i++) {
            for (int j = 1; j <= B.length; j++) {
                if (A[i - 1] == B[j - 1]) {
                    LCS[i][j] = LCS[i - 1][j - 1] + 1;
                } else {
                    LCS[i][j] = 0;
                }
            }
        }
        int result = -1;
        for (int i = 0; i <= A.length; i++) {
            for (int j = 0; j <= B.length; j++) {
                if (result < LCS[i][j]) {
                    result = LCS[i][j];
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String A = "tutorialhorizon";
        String B = "dynamictutorialProgramming";
        System.out.println("LCS length : " + find(A.toCharArray(), B.toCharArray()));
    }
}