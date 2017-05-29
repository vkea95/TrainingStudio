package leetcode.com.medium.part11;

import java.util.*;

/**
 * Created by jason on 2016/1/9.
 * Description:
 * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
 * <p>
 * For example, given the following triangle
 * [
 * [2],
 * [3,4],
 * [6,5,7],
 * [4,1,8,3]
 * ]
 * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
 * <p>
 * Note:
 * Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
 * ****************************************************
 * Solutions:
 * f[x][y]: the min path of from (x,y) to bottom
 * so f[x][y] = min(f[x+1][y], f[x+1][y+1])+triangle[x][y]
 */
public class No120_Triangle {

    public static void main(String[] args) {
        No120_Triangle triangle = new No120_Triangle();
        List<Integer> in = new ArrayList<>();
        in.add(-10);
        List<List<Integer>> out = new ArrayList<List<Integer>>();
        out.add(in);
        triangle.minimumTotal(out);
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        int l = triangle.size();
        int[][] f = new int[l][l];

        //initialization
        for (int i = 0; i < l; i++) {
            f[l - 1][i] = triangle.get(l - 1).get(i);
        }
        //judgement & function change
        for (int i = l - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                f[i][j] = f[i + 1][j] > f[i + 1][j + 1] ? f[i + 1][j + 1] : f[i + 1][j];
                f[i][j] += triangle.get(i).get(j);
            }
        }
        return f[0][0];
    }

//    public void dfs(int x, int y, int sum, List<List<Integer>> triangle) {
//
//        if (x == (triangle.size())) {
//            this.answer = sum < this.answer ? sum : this.answer;
//            return;
//        }
//        sum += triangle.get(x).get(y);
//        dfs(x + 1, y, sum, triangle);
//        dfs(x + 1, y + 1, sum, triangle);
//    }
}
