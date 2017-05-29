package adm.com.ch08;

/**
 * Created by tclresearchamerica on 11/13/16.
 * Thoughts:
 * 必须自己重写java代码,将目标实现,需要区别的是table的下标从零开始,但是1才对应着字符串的第一个字符(下标为零),所以需要i-1和j-1操作
 * 记下来这个模板,然后进行必要地替换就可以实现相应功能
 */

/*
Maximum Monotone Subsequence – A numerical sequence is monotonically increasing if the ith element is at least
 as big as the (i − 1)st element. The maximum monotone subsequence problem seeks to delete
 the fewest num- ber of elements from an input string S to leave a monotonically increasing subsequence.
 A longest increasing subsequence of 243517698 is 23568.

In fact, this is just a longest common subsequence problem, where the second string is the elements of S sorted
in increasing order. Any common sequence of these two must (a) represent characters in proper order in S, and (b)
use only characters with increasing position in the collating sequence—so, the longest one does the job. Of course,
this approach can be modified to give the longest decreasing sequence by simply reversing the sorted order.

 */
class MaximuxMonotoneSubsequence extends EditDistance {

}

class LongestCommonSubsequence extends EditDistance {

    public static void main(String[] args) {
        LongestCommonSubsequence lcs = new LongestCommonSubsequence();
        String s = "democratbbbbb";
        String t = "republicanbbbbb";

        int i = s.length();
        int j = t.length();
        System.out.println("Lowest cost: " + lcs.compareString(s, t));
        //bug1:只需要将s和t的最后index传入method就好
        lcs.reconstructPath(s, t, i, j);


    }


    /*
    Longest Common Subsequence – Perhaps we are interested in finding the longest scattered string of characters
    included within both strings. Indeed, this problem will be discussed in Section 18.8.
    The longest common subse- quence (LCS) between “democrat” and “republican” is eca.

    A common subsequence is defined by all the identical-character matches in an edit trace.
    To maximize the number of such matches, we must prevent sub-stitution of nonidentical characters.
    With substitution forbidden, the only way to get rid of the noncommon subsequence is through insertion
    and dele- tion. The minimum cost alignment has the fewest such “in-dels”, so it must preserve the longest
    common substring. We get the alignment we want by changing the match-cost function to make substitutions expensive:
     */
    @Override
    protected int match(char c1, char c2) {
        return c1 == c2 ? 0 : MAXLEN;
    }

    @Override
    protected void insertOut(String t, int j) {
//        System.out.print("I");
    }

    @Override
    protected void deleteOut(String s, int i) {
//        System.out.print("D");
    }

    @Override
    protected void matchOut(String s, String t, int i, int j) {
        System.out.print(s.charAt(i - 1) == t.charAt(j - 1) ? t.charAt(j - 1) : "");
    }
}

class SubstringMatching extends EditDistance {

    public static void main(String[] args) {
        SubstringMatching lcs = new SubstringMatching();
        String s = "Skiena";
        String t = "Skienna";

        int i = s.length();
        int j = t.length();
        System.out.println("Lowest cost: " + lcs.compareString(s, t));
        //bug1:只需要将s和t的最后index传入method就好
        lcs.reconstructPath(s, t, i, j);
    }

    /*
   We want an edit distance search where the cost of starting the match is independent of the position in the text,
    so that a match in the middle is not prejudiced against.
    在字符串中间发生匹配也是完全可以接受的
   * */
    @Override
    protected void initRow(int i) {
        //在字符串中间发生匹配也是完全可以接受的,所以将其cost设为0,parent设为-1(boundary)
        m[0][i].cost = 0; /* note change*/

        m[0][i].parent = -1; /* note change*/
    }

    /*
    The goal state is not necessarily at the end of both strings,
    but the cheapest place to match the entire pattern somewhere in the text.
    */
    @Override
    protected void getGoalCell(String s, String t, int[] index) {

        int k; //counter
        int i = s.length() - 1;
        int j = 0;

        for (k = 1; k < t.length(); k++) {
            if (m[i][k].cost < m[i][j].cost) j = k;
        }

        index[0] = i;
        index[1] = j;
    }

    @Override
    protected void insertOut(String t, int j) {
//        System.out.print("I");
    }

    @Override
    protected void deleteOut(String s, int i) {
//        System.out.print("D");
    }

    @Override
    protected void matchOut(String s, String t, int i, int j) {
        System.out.print(s.charAt(i - 1) == t.charAt(j - 1) ? t.charAt(j - 1) : "");
    }
}

public class EditDistance {

    public static void main(String[] args) {
        EditDistance lcs = new EditDistance();
        String s = "thou shalt not";
        String t = "you should not";

        int i = s.length();
        int j = t.length();
        System.out.println("Lowest cost: " + lcs.compareString(s, t));
        //bug1:只需要将s和t的最后index传入method就好
        lcs.reconstructPath(s, t, i, j);


    }

    public final int MATCH = 0;
    public final int INSERT = 1;
    public final int DELETE = 2;
    DpCell[][] m;
    public int MAXLEN;

    public int compareString(String s, String t) {

        int maxLen = Math.max(s.length(), t.length()) + 1;
        MAXLEN = maxLen;
        m = new DpCell[maxLen + 1][maxLen + 1];
        initDpTable();

        int i, j, k; // coutner
        int[] operation = new int[3];

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

                m[i][j].cost = operation[MATCH];
                m[i][j].parent = MATCH; //设初期值 & reconstruct path时使用
                for (k = INSERT; k <= DELETE; k++) {
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

    protected int match(char c1, char c2) {
        //0:match
        //1:replace
        return c1 == c2 ? 0 : 1;

    }

    protected int indel(char c) {
        return 1;
    }

    /*
    Returns the indices of the cell marking the endpoint of the solution.
    For edit distance, this is defined by the length of the two input strings.
    However, other application we will soon encounter do not have fixed goal locations.
    */
    protected void getGoalCell(String s, String t, int[] index) {
        index[0] = s.length() - 1;
        index[1] = t.length() - 1;
    }

    /*
    用new DpCell[][]的初期化方式,只是声明而已,仍旧需要显示地位元素 new 一个空间出来
     */
    protected void initDpTable() {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++)
                m[i][j] = new DpCell();
        }
    }
    /*
    *对于第零行的所有列而言,长度为i的字符串和空串比较的话,需要不断insert
    *相当于空串变化到目标字符串,需要不断insert操作
    * 见Pg285 Figure8.5
    */

    protected void initRow(int i) {
        m[0][i].cost = i;

        //相当于空串变化到目标字符串,需要不断insert操作
        //i为0的时候,设置为-1,是为了设置boundary
        m[0][i].parent = i > 0 ? INSERT : -1;
    }
    /*
    * initialize the 0th row and column of the dp table, respectively.
     * For the string edit distance problem, cells(i,0) and (0,i) correspond to matching length-i strings against the empty string.
     * This requires exactly i insertions/deletions, so the definition of these functions is clear.
     * 对于第零列的所有行而言,长度为i的字符串和空串比较的话,需要不断insert
     * 相当于目标串变为空串需要不断delete
     * 见Pg285 Figure8.5
    * */

    protected void initCol(int i) {
        m[i][0].cost = i;

        //相当于目标串变为空串需要不断delete
        //i为0的时候,设置为-1,是为了设置boundary
        m[i][0].parent = i > 0 ? DELETE : -1;
    }

    /*
    打印修改路径R:replace, I:insert, D:delete M:match
     */
    public void reconstructPath(String s, String t, int i, int j) {
        //boundary check
        if (m[i][j].parent == -1)
            return;

        if (m[i][j].parent == MATCH) {
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

    /*
    Traceback Actions
     */
    protected void insertOut(String t, int j) {
        System.out.print("I");
    }

    protected void deleteOut(String s, int i) {
        System.out.print("D");
    }

    protected void matchOut(String s, String t, int i, int j) {
        //Bug1: dp table的开始下标是0,所以有效打印字符从1开始,那么就要进行减一操作
        System.out.print(s.charAt(i - 1) == t.charAt(j - 1) ? "M" : "R");
    }

}


class DpCell {
    public int cost; //cost of reaching this cell
    public int parent;// parent cell

    public DpCell() {
    }

}